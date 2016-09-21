package com.dayswideawake.webrobot.lookup.backend.domain;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dayswideawake.webrobot.lookup.TestGroup;
import com.dayswideawake.webrobot.lookup.backend.service.SelectorStrategyCss;

@Test(groups = TestGroup.GROUP_UNIT)
public class SelectorStrategyCssTest {
	private SelectorStrategyCss selectorStrategyCss;
	private String content;

	@BeforeMethod
	public void init() {
		selectorStrategyCss = new SelectorStrategyCss();
		content = "<html><body><p id=\"id1\">Test</p><p class=\"class1\">Test 2</p></body></html>";
	}

	public void selectShouldSelectBody() {
		List<String> selectedContent = selectorStrategyCss.select(content, new SelectorCss.Builder("body").build());
		Assert.assertEquals(selectedContent, Arrays.asList("<body><p id=\"id1\">Test</p><p class=\"class1\">Test 2</p></body>"));
	}

	public void selectShouldSelectMultiplePs() {
		List<String> selectedContent = selectorStrategyCss.select(content, new SelectorCss.Builder("p").build());
		Assert.assertEquals(selectedContent, Arrays.asList("<p id=\"id1\">Test</p>", "<p class=\"class1\">Test 2</p>"));
	}

	public void selectShouldSelectByClass() {
		List<String> selectedContent = selectorStrategyCss.select(content, new SelectorCss.Builder(".class1").build());
		Assert.assertEquals(selectedContent, Arrays.asList("<p class=\"class1\">Test 2</p>"));
	}

	public void selectShouldSelectById() {
		List<String> selectedContent = selectorStrategyCss.select(content, new SelectorCss.Builder("#id1").build());
		Assert.assertEquals(selectedContent, Arrays.asList("<p id=\"id1\">Test</p>"));
	}

	public void selectShouldSelectByFollowing() {
		List<String> selectedContent = selectorStrategyCss.select(content, new SelectorCss.Builder("#id1 + .class1").build());
		Assert.assertEquals(selectedContent, Arrays.asList("<p class=\"class1\">Test 2</p>"));
	}
}
