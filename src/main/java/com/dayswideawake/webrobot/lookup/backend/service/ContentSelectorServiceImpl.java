package com.dayswideawake.webrobot.lookup.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayswideawake.webrobot.lookup.aop.annotation.Loggable;
import com.dayswideawake.webrobot.lookup.backend.domain.Selector;
import com.dayswideawake.webrobot.lookup.backend.domain.SelectorStrategy;
import com.dayswideawake.webrobot.lookup.backend.domain.Site;

@Service
public class ContentSelectorServiceImpl implements ContentSelectorService {

	private ContentLoaderService contentLoaderService;
	private SelectorStrategyLocator selectorStrategyLocator;

	@Autowired
	public ContentSelectorServiceImpl(ContentLoaderService contentLoaderService, SelectorStrategyLocator selectorStrategyLocator) {
		this.contentLoaderService = contentLoaderService;
		this.selectorStrategyLocator = selectorStrategyLocator;
	}

	@Override
	@Loggable
	public <T extends Selector> List<String> selectContent(Site site, T selector) {
		String content = contentLoaderService.loadContent(site);
		SelectorStrategy<T> selectorStrategy = selectorStrategyLocator.getSelectorStrategyFor(selector);
		return selectorStrategy.select(content, selector);
	}

}
