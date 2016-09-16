package com.dayswideawake.webrobot.lookup.backend.service;

import com.dayswideawake.webrobot.lookup.backend.domain.Lookup;
import com.dayswideawake.webrobot.lookup.backend.domain.LookupJob;

public interface LookupService {

	Lookup doLookup(LookupJob lookupJob);
	
}
