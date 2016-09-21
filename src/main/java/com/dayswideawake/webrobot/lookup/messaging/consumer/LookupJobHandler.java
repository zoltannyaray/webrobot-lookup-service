package com.dayswideawake.webrobot.lookup.messaging.consumer;

import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookup.aop.annotation.Loggable;
import com.dayswideawake.webrobot.lookup.backend.domain.LookupJob;
import com.dayswideawake.webrobot.lookup.backend.gateway.ViewLookupDefinitionGateway;
import com.dayswideawake.webrobot.lookup.backend.gateway.model.LookupDefinitionDetails;
import com.dayswideawake.webrobot.lookup.backend.gateway.transformer.LookupDefinitionGatewayDomainTransformer;
import com.dayswideawake.webrobot.lookup.backend.service.LookupService;
import com.dayswideawake.webrobot.lookup.messaging.Channels;
import com.dayswideawake.webrobot.lookup.messaging.model.LookupJobMessage;

@Component
public class LookupJobHandler {

	private ViewLookupDefinitionGateway viewLookupDefinitionGateway;
	private LookupDefinitionGatewayDomainTransformer lookupDefinitionGatewayDomainTransformer;
	private LookupService lookupService;

	@Autowired
	public LookupJobHandler(ViewLookupDefinitionGateway viewLookupDefinitionGateway, LookupDefinitionGatewayDomainTransformer lookupDefinitionGatewayDomainTransformer, LookupService lookupService) {
		this.viewLookupDefinitionGateway = viewLookupDefinitionGateway;
		this.lookupDefinitionGatewayDomainTransformer = lookupDefinitionGatewayDomainTransformer;
		this.lookupService = lookupService;
	}

	@StreamListener(Channels.CHANNEL_INPUT_LOOKUP_JOBS)
	@Loggable
	public void handle(LookupJobMessage lookupJobMessage) throws MalformedURLException {
		Long lookupDefinitionId = lookupJobMessage.getLookupDefinitionId();
		LookupDefinitionDetails lookupDefinitionDetails = viewLookupDefinitionGateway.viewLookupDefinition(lookupDefinitionId);
		LookupJob lookupJob = lookupDefinitionGatewayDomainTransformer.lookupDefinitionDetailsToLookupJob(lookupJobMessage, lookupDefinitionDetails);
		lookupService.doLookup(lookupJob);
	}

}
