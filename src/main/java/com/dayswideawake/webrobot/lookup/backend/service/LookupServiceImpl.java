package com.dayswideawake.webrobot.lookup.backend.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.dayswideawake.webrobot.lookup.aop.annotation.Loggable;
import com.dayswideawake.webrobot.lookup.backend.domain.Lookup;
import com.dayswideawake.webrobot.lookup.backend.domain.LookupJob;
import com.dayswideawake.webrobot.lookup.backend.domain.Selector;
import com.dayswideawake.webrobot.lookup.backend.domain.Site;
import com.dayswideawake.webrobot.lookup.backend.event.LookupCreatedEvent;
import com.dayswideawake.webrobot.lookup.backend.repository.LookupRepository;
import com.dayswideawake.webrobot.lookup.backend.repository.entity.LookupEntity;
import com.dayswideawake.webrobot.lookup.backend.service.transformer.LookupDomainEntityTransformer;

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

}
