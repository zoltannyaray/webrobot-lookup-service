package com.dayswideawake.webrobot.lookup.backend.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookup.backend.domain.SelectorXpath;

@Component
public class SelectorStrategyXpath implements SelectorStrategy<SelectorXpath> {

	@Override
	public List<String> select(String content, SelectorXpath selector) {
		return null;
	}

}
