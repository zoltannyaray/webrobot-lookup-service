package com.dayswideawake.webrobot.lookup.backend.service;

import java.util.List;

import com.dayswideawake.webrobot.lookup.backend.domain.Selector;

public interface SelectorStrategy<T extends Selector> {

	List<String> select(String content, T selector);
	
}
