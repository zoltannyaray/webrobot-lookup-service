package com.dayswideawake.webrobot.lookup.backend.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.dayswideawake.webrobot.lookup.aop.annotation.Loggable;
import com.dayswideawake.webrobot.lookup.backend.domain.Lookup;
import com.dayswideawake.webrobot.lookup.backend.event.LookupCreatedEvent;

@Service
public class LookupCompareService {

	@EventListener
	@Loggable
	public void onLookupCreated(LookupCreatedEvent lookupCreatedEvent) {
		Lookup newLookup = lookupCreatedEvent.getLookup();
		
	}
	
}
