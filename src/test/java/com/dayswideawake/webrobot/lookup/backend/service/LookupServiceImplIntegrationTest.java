package com.dayswideawake.webrobot.lookup.backend.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dayswideawake.webrobot.lookup.TestGroup;
import com.dayswideawake.webrobot.lookup.backend.domain.Lookup;
import com.dayswideawake.webrobot.lookup.backend.domain.LookupJob;
import com.dayswideawake.webrobot.lookup.backend.domain.Selector;
import com.dayswideawake.webrobot.lookup.backend.domain.SelectorCss;
import com.dayswideawake.webrobot.lookup.backend.domain.Site;
import com.dayswideawake.webrobot.lookup.backend.event.LookupCreatedEvent;
import com.dayswideawake.webrobot.lookup.backend.repository.LookupRepository;
import com.dayswideawake.webrobot.lookup.backend.repository.entity.LookupEntity;
import com.dayswideawake.webrobot.lookup.backend.service.transformer.LookupDomainEntityTransformer;

@SpringBootTest
@Test(groups = TestGroup.GROUP_INTEGRATION)
public class LookupServiceImplIntegrationTest extends AbstractTransactionalTestNGSpringContextTests {

	private LookupService lookupService;
	@Autowired
	private LookupRepository lookupRepository;
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	@Autowired
	private ContentSelectorService contentSelectorService;
	@Autowired
	private LookupDomainEntityTransformer domainEntityTransformer;

	@BeforeMethod
	public void beforeMethod() {
		applicationEventPublisher = Mockito.spy(applicationEventPublisher);
		lookupService = new LookupServiceImpl(contentSelectorService, lookupRepository, domainEntityTransformer, applicationEventPublisher);
		lookupRepository.deleteAll();
	}

	@Transactional
	public void doLookupShouldSaveToRepository() throws MalformedURLException {
		// given
		LookupJob lookupJob = getTestLookupJob();
		Date tenSecondsAgo = getTenSecondsAgo();
		// when
		lookupService.doLookup(lookupJob);
		// then
		Assert.assertEquals(lookupRepository.count(), 1);
		LookupEntity lookupEntity = lookupRepository.findAll().get(0);
		Assert.assertEquals(lookupEntity.getLookupDefinitionId(), lookupJob.getLookupDefinitionId());
		Assert.assertEquals(lookupEntity.getLookupJobId(), lookupJob.getId());
		Assert.assertNotNull(lookupEntity.getLookupTime());
		Date entityLookupTime = new Date(lookupEntity.getLookupTime());
		Assert.assertTrue(entityLookupTime.before(new Date()));
		Assert.assertTrue(entityLookupTime.after(tenSecondsAgo));
		Assert.assertEquals(lookupEntity.getSelectedContent().size(), 1);
	}

	public void doLookupShouldReturnLookup() throws MalformedURLException {
		// given
		LookupJob lookupJob = getTestLookupJob();
		Date tenSecondsAgo = getTenSecondsAgo();
		// when
		Lookup lookup = lookupService.doLookup(lookupJob);
		// then
		Assert.assertEquals(lookup.getLookupDefinitionId(), lookupJob.getLookupDefinitionId());
		Assert.assertEquals(lookup.getLookupJobId(), lookupJob.getId());
		Assert.assertTrue(lookup.getLookupTime().before(new Date()));
		Assert.assertTrue(lookup.getLookupTime().after(tenSecondsAgo));
		Assert.assertEquals(lookup.getSelectedContent().size(), 1);
	}

	public void doLookupShouldPublishLookupCreatedApplicationEvent() throws MalformedURLException {
		// given
		LookupJob lookupJob = getTestLookupJob();
		Date tenSecondsAgo = getTenSecondsAgo();
		// when
		lookupService.doLookup(lookupJob);
		// then
		ArgumentCaptor<LookupCreatedEvent> eventCaptor = ArgumentCaptor.forClass(LookupCreatedEvent.class);
		Mockito.verify(applicationEventPublisher).publishEvent(eventCaptor.capture());
		Lookup capturedLookup = eventCaptor.getValue().getLookup();
		Assert.assertNotNull(capturedLookup);
		Assert.assertEquals(capturedLookup.getLookupDefinitionId(), lookupJob.getLookupDefinitionId());
		Assert.assertEquals(capturedLookup.getLookupJobId(), lookupJob.getId());
		Assert.assertTrue(capturedLookup.getLookupTime().before(new Date()));
		Assert.assertTrue(capturedLookup.getLookupTime().after(tenSecondsAgo));
		Assert.assertEquals(capturedLookup.getSelectedContent().size(), 1);
	}
	
	public void getPreviousLookupShouldReturnEmptyOnNoOtherLookup() {
		Lookup lookup = createTestLookup(1L, new Date());
		Long lookupId = lookup.getId().get();
		Long lookupDefinitionId = lookup.getLookupDefinitionId();
		Optional<Lookup> previousLookup = lookupService.getPreviousLookup(lookupId, lookupDefinitionId);
		Assert.assertNull(previousLookup.orElse(null));
	}
	
	public void getPreviousLookupShouldReturnEmptyOnNewerLookups() {
		List<Lookup> lookups = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			lookups.add(createTestLookup(1L, new Date()));
		}
		Optional<Lookup> previousLookup = lookupService.getPreviousLookup(lookups.get(0).getId().get(), lookups.get(0).getLookupDefinitionId());
		Assert.assertNull(previousLookup.orElse(null));
	}
	
	public void getPreviousLookupShouldReturnEmptyOnNoMatchingLookup() {
		List<Lookup> lookups = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			lookups.add(createTestLookup(Long.valueOf(i), new Date()));
		}
		Optional<Lookup> previousLookup = lookupService.getPreviousLookup(lookups.get(4).getId().get(), lookups.get(4).getLookupDefinitionId());
		Assert.assertNull(previousLookup.orElse(null));
	}
	
	public void getPreviousLookupShouldReturnPreviousLookup() {
		List<Lookup> lookups = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			lookups.add(createTestLookup(Long.valueOf(i % 3), new Date()));
		}
		Optional<Lookup> previousLookup = lookupService.getPreviousLookup(lookups.get(4).getId().get(), lookups.get(4).getLookupDefinitionId());
		Assert.assertNotNull(previousLookup.orElse(null));
		Assert.assertEquals(previousLookup.get().getId(), lookups.get(1).getId());
	} 

	private Lookup createTestLookup(Long lookupDefinitionId, Date lookupTime) {
		Long lookupJobId = 1L;
		List<String> selectedContent = Arrays.asList("content1", "content2");
		LookupEntity lookupEntity = new LookupEntity.Builder(lookupJobId, lookupDefinitionId, lookupTime.getTime(), selectedContent).build();
		lookupEntity = lookupRepository.save(lookupEntity);
		return domainEntityTransformer.entityToDomain(lookupEntity);
	}
	
	private LookupJob getTestLookupJob() throws MalformedURLException {
		Long lookupJobId = 1L;
		Long lookupDefinitionId = 2L;
		URL siteUrl = new URL("http://google.com");
		Site site = new Site.Builder(siteUrl).build();
		Selector selector = new SelectorCss.Builder("body").build();
		return new LookupJob.Builder(lookupJobId, lookupDefinitionId, site, selector).build();
	}

	private Date getTenSecondsAgo() {
		Calendar tenSecondsAgo = Calendar.getInstance();
		tenSecondsAgo.add(Calendar.SECOND, -10);
		return tenSecondsAgo.getTime();
	}

}
