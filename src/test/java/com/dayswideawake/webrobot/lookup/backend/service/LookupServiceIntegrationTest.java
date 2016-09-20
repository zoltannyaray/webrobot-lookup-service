package com.dayswideawake.webrobot.lookup.backend.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dayswideawake.webrobot.lookup.TestGroup;
import com.dayswideawake.webrobot.lookup.backend.domain.Lookup;
import com.dayswideawake.webrobot.lookup.backend.domain.LookupJob;
import com.dayswideawake.webrobot.lookup.backend.domain.Selector;
import com.dayswideawake.webrobot.lookup.backend.domain.SelectorCss;
import com.dayswideawake.webrobot.lookup.backend.domain.Site;
import com.dayswideawake.webrobot.lookup.backend.repository.LookupRepository;

@SpringBootTest
@Test(groups=TestGroup.GROUP_INTEGRATION)
public class LookupServiceIntegrationTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private LookupService lookupService;
	@Autowired
	private LookupRepository lookupRepository;
	
	@BeforeClass
	public void setup() {
		Assert.assertNotNull(lookupRepository, "LookupRepository should be autowired");
		lookupRepository.deleteAll();
	}
	
	public void lookupServiceShouldBeAutowired() {
		Assert.assertNotNull(lookupService);
	}
	
	public void lookupShouldBeSavedAndReturned() throws MalformedURLException {
		// given
		Long lookupJobId = 1L;
		Long lookupDefinitionId = 2L;
		URL siteUrl = new URL("http://google.com");
		Site site  = new Site.Builder(siteUrl).build();
		Selector selector = new SelectorCss.Builder("body").build(); 
		LookupJob lookupJob = new LookupJob.Builder(lookupJobId, lookupDefinitionId, site, selector).build();
		// when
		Lookup lookup = lookupService.doLookup(lookupJob);
		// then
		Assert.assertEquals(lookupRepository.count(), 1);
		Assert.assertEquals(lookup.getLookupDefinitionId(), lookupDefinitionId);
		Assert.assertEquals(lookup.getLookupJobId(), lookupJobId);
		Assert.assertTrue(lookup.getLookupTime().before(new Date()));
		Calendar tenSecondsAgo = Calendar.getInstance();
		tenSecondsAgo.add(Calendar.SECOND, -10);
		Assert.assertTrue(lookup.getLookupTime().after(tenSecondsAgo.getTime()));
		Assert.assertEquals(lookup.getSelectedContent().size(), 1);
	}
	
}
