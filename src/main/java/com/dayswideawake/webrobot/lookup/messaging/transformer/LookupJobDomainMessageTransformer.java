package com.dayswideawake.webrobot.lookup.messaging.transformer;

import com.dayswideawake.webrobot.lookup.backend.domain.LookupJob;
import com.dayswideawake.webrobot.lookup.messaging.model.LookupJobMessage;

public class LookupJobDomainMessageTransformer {

	public LookupJob messageToDomain(LookupJobMessage message) {
		return new LookupJob.Builder()
				.build();
	}
	
}
