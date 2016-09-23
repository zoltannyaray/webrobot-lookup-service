package com.dayswideawake.webrobot.lookup.backend.service;

import java.util.Optional;

import com.dayswideawake.webrobot.lookup.backend.domain.Lookup;
import com.dayswideawake.webrobot.lookup.backend.domain.LookupJob;

public interface LookupService {

	Lookup doLookup(LookupJob lookupJob);
	
	Optional<Lookup> getPreviousLookup(Long lookupId, Long lookupDefinitionId);
	
}
