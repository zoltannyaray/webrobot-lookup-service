package com.dayswideawake.webrobot.lookup.backend.service;

import java.util.Arrays;
import java.util.List;

import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dayswideawake.webrobot.lookup.TestGroup;
import com.dayswideawake.webrobot.lookup.backend.domain.SelectorCss;
import com.dayswideawake.webrobot.lookup.backend.domain.SelectorXpath;

@Test(groups=TestGroup.GROUP_UNIT)
public class SelectorStrategyLocatorImplTest {

	private SelectorStrategyLocatorImpl selectorStrategyLocator;
	private SelectorCss selectorCss;
	private SelectorXpath selectorXpath;
	
	@BeforeMethod
	public void setup() {
		MockitoAnnotations.initMocks(this);
		selectorCss = new SelectorCss.Builder("test").build();
		selectorXpath = new SelectorXpath.Builder("test").build();
		SelectorStrategyCss selectorStrategyCss = new SelectorStrategyCss();
		SelectorStrategyXpath selectorStrategyXpath = new SelectorStrategyXpath();
		List<SelectorStrategy<?>> allSelectorStrategies = Arrays.asList(selectorStrategyCss, selectorStrategyXpath);
		selectorStrategyLocator = new SelectorStrategyLocatorImpl(allSelectorStrategies);
	}
	
    public void getSelectStrategyForShouldReturnCorrectStrategyForCssSelector(){
        SelectorStrategy<SelectorCss> selectorStrategy = selectorStrategyLocator.getSelectorStrategyFor(selectorCss);
        Assert.assertEquals(selectorStrategy.getClass(), SelectorStrategyCss.class);
    }
    
    public void getSelectStrategyForShouldReturnCorrectStrategyForXPathSelector(){
        SelectorStrategy<SelectorXpath> selectorStrategy = selectorStrategyLocator.getSelectorStrategyFor(selectorXpath);
        Assert.assertEquals(selectorStrategy.getClass(), SelectorStrategyXpath.class);
    }
	
}
