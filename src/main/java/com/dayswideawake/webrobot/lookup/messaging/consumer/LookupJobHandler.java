package com.dayswideawake.webrobot.lookup.messaging.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookup.backend.domain.Lookup;
import com.dayswideawake.webrobot.lookup.backend.domain.LookupJob;
import com.dayswideawake.webrobot.lookup.backend.service.LookupService;
import com.dayswideawake.webrobot.lookup.messaging.Channels;
import com.dayswideawake.webrobot.lookup.messaging.model.LookupJobMessage;
import com.dayswideawake.webrobot.lookup.messaging.transformer.LookupJobDomainMessageTransformer;

@Component
public class LookupJobHandler {

	private LookupJobDomainMessageTransformer domainMessageTransformer;
	private LookupService lookupService;

	@Autowired
	public LookupJobHandler(LookupJobDomainMessageTransformer domainMessageTransformer, LookupService lookupService) {
		this.domainMessageTransformer = domainMessageTransformer;
		this.lookupService = lookupService;
	}

	@StreamListener(Channels.CHANNEL_INPUT_LOOKUP_JOBS)
	public void handle(LookupJobMessage lookupJobMessage) {
		LookupJob lookupJob = domainMessageTransformer.messageToDomain(lookupJobMessage);
		Lookup lookup = lookupService.doLookup(lookupJob);
	}

}
