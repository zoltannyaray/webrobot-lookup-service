package com.dayswideawake.webrobot.lookup.messaging.transformer;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookup.backend.domain.Lookup;
import com.dayswideawake.webrobot.lookup.messaging.model.LookupCreatedMessage;

@Component
public class LookupDomainMessageTransformer {

	public LookupCreatedMessage domainToCreatedMessage(Lookup lookup) {
		Long lookupDefinitionId = lookup.getLookupDefinitionId();
		Long lookupTime = lookup.getLookupTime().getTime();
		List<String> selectedContent = lookup.getSelectedContent();
		return new LookupCreatedMessage.Builder(lookupDefinitionId, lookupTime, selectedContent).build();
	}
	
}
