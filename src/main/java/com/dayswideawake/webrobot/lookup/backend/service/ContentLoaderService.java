package com.dayswideawake.webrobot.lookup.backend.service;

import com.dayswideawake.webrobot.lookup.backend.domain.Site;

public interface ContentLoaderService {

	String loadContent(Site site);
	
}
