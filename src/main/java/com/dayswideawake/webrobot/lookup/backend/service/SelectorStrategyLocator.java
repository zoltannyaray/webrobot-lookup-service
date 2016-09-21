package com.dayswideawake.webrobot.lookup.backend.service;

import com.dayswideawake.webrobot.lookup.backend.domain.Selector;

public interface SelectorStrategyLocator {
	
	public <T extends Selector> SelectorStrategy<T> getSelectorStrategyFor(T selector);

}
