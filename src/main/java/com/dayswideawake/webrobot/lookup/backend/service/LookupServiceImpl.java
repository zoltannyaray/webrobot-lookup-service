package com.dayswideawake.webrobot.lookup.backend.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import com.dayswideawake.webrobot.lookup.aop.annotation.Loggable;
import com.dayswideawake.webrobot.lookup.backend.domain.Lookup;
import com.dayswideawake.webrobot.lookup.backend.domain.LookupJob;
import com.dayswideawake.webrobot.lookup.backend.domain.Selector;
import com.dayswideawake.webrobot.lookup.backend.domain.Site;
import com.dayswideawake.webrobot.lookup.backend.event.LookupCreatedEvent;
import com.dayswideawake.webrobot.lookup.backend.repository.LookupRepository;
import com.dayswideawake.webrobot.lookup.backend.repository.entity.LookupEntity;
import com.dayswideawake.webrobot.lookup.backend.repository.entity.QLookupEntity;
import com.dayswideawake.webrobot.lookup.backend.service.transformer.LookupDomainEntityTransformer;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service
public class LookupServiceImpl implements LookupService {

	private ContentSelectorService contentSelectorService;
	private LookupRepository lookupRepository;
	private LookupDomainEntityTransformer domainEntityTransformer;
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	public LookupServiceImpl(ContentSelectorService contentSelectorService, LookupRepository lookupRepository, LookupDomainEntityTransformer domainEntityTransformer, ApplicationEventPublisher eventPublisher) {
		this.contentSelectorService = contentSelectorService;
		this.lookupRepository = lookupRepository;
		this.domainEntityTransformer = domainEntityTransformer;
		this.eventPublisher = eventPublisher;
	}

	@Override
	@Loggable
	public Lookup doLookup(LookupJob lookupJob) {
		Long lookupJobId = lookupJob.getId();
		Long lookupDefinitionId = lookupJob.getLookupDefinitionId();
		Site site = lookupJob.getSite();
		Selector selector = lookupJob.getSelector();
		List<String> selectedContent = contentSelectorService.selectContent(site, selector);
		Date lookupTime = new Date();
		Lookup lookup = new Lookup.Builder(lookupJobId, lookupDefinitionId, lookupTime, selectedContent).build();
		LookupEntity lookupEntity = domainEntityTransformer.domainToEntity(lookup);
		lookupEntity = lookupRepository.save(lookupEntity);
		lookup = domainEntityTransformer.entityToDomain(lookupEntity);
		LookupCreatedEvent lookupCreatedEvent = new LookupCreatedEvent.Builder(lookup).build();
		eventPublisher.publishEvent(lookupCreatedEvent);
		return lookup;
	}

	@Override
	@Loggable
	public Optional<Lookup> getPreviousLookup(Long currentLookupId, Long lookupDefinitionId) {
		QLookupEntity qLookupEntity = QLookupEntity.lookupEntity;
		BooleanExpression notThisLookup = qLookupEntity.id.ne(currentLookupId);
		BooleanExpression withSameLookupDefinitionId = qLookupEntity.lookupDefinitionId.eq(lookupDefinitionId);
		BooleanExpression matchingLookup = notThisLookup.and(withSameLookupDefinitionId);
		OrderSpecifier<Long> orderByLookupTimeDesc = qLookupEntity.lookupTime.desc();
		Pageable pageable = new QPageRequest(0, 1, orderByLookupTimeDesc);
		Page<LookupEntity> lookupEntityPage = lookupRepository.findAll(matchingLookup, pageable);
		Optional<Lookup> optionalLookup = Optional.empty();
		if (lookupEntityPage.getTotalElements() == 1L) {
			LookupEntity lookupEntity = lookupEntityPage.getContent().get(0);
			Lookup lookup = domainEntityTransformer.entityToDomain(lookupEntity);
			optionalLookup = Optional.of(lookup);
		}
		return optionalLookup;
	}

}
