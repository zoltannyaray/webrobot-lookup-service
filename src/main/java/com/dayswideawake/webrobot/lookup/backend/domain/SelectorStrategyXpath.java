package com.dayswideawake.webrobot.lookup.backend.domain;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SelectorStrategyXpath implements SelectorStrategy<SelectorXpath> {

	@Override
	public List<String> select(String content, SelectorXpath selector) {
		return null;
	}

}
