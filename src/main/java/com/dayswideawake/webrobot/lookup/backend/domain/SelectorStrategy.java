package com.dayswideawake.webrobot.lookup.backend.domain;

import java.util.List;

public interface SelectorStrategy<T extends Selector> {

	List<String> select(String content, T selector);
	
}
