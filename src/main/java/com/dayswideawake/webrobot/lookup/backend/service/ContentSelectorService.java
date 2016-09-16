package com.dayswideawake.webrobot.lookup.backend.service;

import java.util.List;

import com.dayswideawake.webrobot.lookup.backend.domain.Selector;
import com.dayswideawake.webrobot.lookup.backend.domain.Site;

public interface ContentSelectorService {

	public <T extends Selector> List<String> selectContent(Site site, T selector);
	
}
