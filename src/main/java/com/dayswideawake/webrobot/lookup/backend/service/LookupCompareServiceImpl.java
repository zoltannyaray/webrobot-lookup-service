package com.dayswideawake.webrobot.lookup.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.dayswideawake.webrobot.lookup.aop.annotation.Loggable;
import com.dayswideawake.webrobot.lookup.backend.domain.Lookup;
import com.dayswideawake.webrobot.lookup.backend.event.SelectedContentChangedEvent;
import com.dayswideawake.webrobot.lookup.backend.event.LookupCreatedEvent;

@Service
public class LookupCompareServiceImpl {

	private LookupService lookupService;
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	public LookupCompareServiceImpl(LookupService lookupService, ApplicationEventPublisher applicationEventPublisher) {
		this.lookupService = lookupService;
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@EventListener
	@Loggable
	public void onLookupCreated(LookupCreatedEvent lookupCreatedEvent) {
		Lookup newLookup = lookupCreatedEvent.getLookup();
		Long newLookupId = newLookup.getId().orElseThrow(() -> new IllegalStateException("Lookup must have an ID"));
		Long newLookupLookupDefinitionId = newLookup.getLookupDefinitionId();
		Optional<Lookup> previousLookup = lookupService.getPreviousLookup(newLookupId, newLookupLookupDefinitionId);
		if (previousLookup.isPresent() && isLookupResultChanged(newLookup, previousLookup.get())) {
		    SelectedContentChangedEvent lookupContentChangedEvent = new SelectedContentChangedEvent.Builder(previousLookup.get(), newLookup).build();
		    applicationEventPublisher.publishEvent(lookupContentChangedEvent);
		}
	}
	
	@Loggable
	public Boolean isLookupResultChanged(Lookup newLookup, Lookup previousLookup) {
		Boolean isChanged = false;
		List<String> newSelectedContent = newLookup.getSelectedContent();
		List<String> previousSelectedContent = previousLookup.getSelectedContent();
		if (newSelectedContent.size() != previousSelectedContent.size()) {
			isChanged = true;
		}
		if (!isChanged) {
			for (int i = 0; i < newSelectedContent.size(); i++) {
				if (!newSelectedContent.get(i).equals(previousSelectedContent.get(i))) {
					isChanged = true;
					break;
				}
			}
		}
		return isChanged;
	}

}
