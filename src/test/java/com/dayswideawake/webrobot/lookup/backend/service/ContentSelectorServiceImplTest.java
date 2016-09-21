package com.dayswideawake.webrobot.lookup.backend.service;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dayswideawake.webrobot.lookup.TestGroup;
import com.dayswideawake.webrobot.lookup.backend.domain.SelectorCss;
import com.dayswideawake.webrobot.lookup.backend.domain.SelectorXpath;
import com.dayswideawake.webrobot.lookup.backend.domain.Site;

@Test(groups=TestGroup.GROUP_UNIT)
public class ContentSelectorServiceImplTest {

	private ContentSelectorServiceImpl service;
    @Mock
    private SelectorStrategyLocator selectorStrategyLocator;
    @Mock
    private ContentLoaderService contentLoaderService;
    @Mock
    private Site site;
    @Mock
    private SelectorStrategyCss selectorStrategyCss;
    @Mock
    private SelectorStrategyXpath selectorStrategyXPath;
    private String content = "test content";
    
    @BeforeMethod
    public void beforeMethod(){
        MockitoAnnotations.initMocks(this);
        Mockito.when(contentLoaderService.loadContent(Mockito.any(Site.class))).thenReturn(content);
        Mockito.when(selectorStrategyLocator.getSelectorStrategyFor(Mockito.isA(SelectorCss.class))).thenReturn(selectorStrategyCss);
        Mockito.when(selectorStrategyLocator.getSelectorStrategyFor(Mockito.isA(SelectorXpath.class))).thenReturn(selectorStrategyXPath);
        service = new ContentSelectorServiceImpl(contentLoaderService, selectorStrategyLocator);
    }
    
    public void selectContentShouldUseCssSelectorStrategy(){
        SelectorCss selector = new SelectorCss.Builder("test selector").build();
        service.selectContent(site, selector);
        Mockito.verify(selectorStrategyCss, Mockito.times(1)).select(content, selector);
        Mockito.verify(selectorStrategyXPath, Mockito.never()).select(Mockito.any(), Mockito.any());
    }
    
    public void selectContentShouldUseXPathSelectorStrategy(){
        SelectorXpath selector = new SelectorXpath.Builder("test selector").build();
        service.selectContent(site, selector);
        Mockito.verify(selectorStrategyXPath, Mockito.times(1)).select(content, selector);
        Mockito.verify(selectorStrategyCss, Mockito.never()).select(Mockito.any(), Mockito.any());
    }    
	
}
