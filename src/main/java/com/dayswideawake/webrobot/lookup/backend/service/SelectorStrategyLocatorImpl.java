package com.dayswideawake.webrobot.lookup.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Service;

import com.dayswideawake.webrobot.lookup.backend.domain.Selector;

@Service
public class SelectorStrategyLocatorImpl implements SelectorStrategyLocator {

    private List<SelectorStrategy<? extends Selector>> allSelectorStrategies;
	
	@Autowired
	public SelectorStrategyLocatorImpl(List<SelectorStrategy<? extends Selector>> allSelectorStrategies) {
		this.allSelectorStrategies = allSelectorStrategies;
	}

	@Override
	public <T extends Selector> SelectorStrategy<T> getSelectorStrategyFor(T selector) {
		ResolvableType neededStrategyType = ResolvableType.forClassWithGenerics(SelectorStrategy.class, selector.getClass());
        return allSelectorStrategies.stream()
                .filter(s -> neededStrategyType.isInstance(s))
                .map(s -> (SelectorStrategy<T>) s)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("SelectorStrategy<" + selector.getClass().getName() + "> could not been found"));
	}

}
