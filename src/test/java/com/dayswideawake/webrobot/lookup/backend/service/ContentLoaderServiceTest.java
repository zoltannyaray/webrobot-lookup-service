package com.dayswideawake.webrobot.lookup.backend.service;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.dayswideawake.webrobot.lookup.TestGroup;
import com.dayswideawake.webrobot.lookup.backend.domain.Site;

@SpringBootTest
@Test(groups=TestGroup.GROUP_INTEGRATION)
public class ContentLoaderServiceTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private ContentLoaderService contentLoaderService;
	
	public void contentLoaderShouldBeAutowired() {
		Assert.assertNotNull(contentLoaderService);
	}
	
	public void nonEmptyContentShouldBeLoaded() throws MalformedURLException {
		URL url = new URL("http://google.com");
		Site site = new Site.Builder(url).build();
		String content = contentLoaderService.loadContent(site);
		Assert.assertNotNull(content);
		Assert.assertNotEquals(content, "");
	}
	
	

}
