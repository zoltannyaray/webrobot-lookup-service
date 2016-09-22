package com.dayswideawake.webrobot.lookup.backend.gateway.transformer;

import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookup.backend.domain.LookupJob;
import com.dayswideawake.webrobot.lookup.backend.domain.Selector;
import com.dayswideawake.webrobot.lookup.backend.domain.Site;
import com.dayswideawake.webrobot.lookup.backend.gateway.model.LookupDefinitionDetails;
import com.dayswideawake.webrobot.lookup.messaging.model.LookupJobMessage;

@Component
public class LookupDefinitionGatewayDomainTransformer {

	private SiteGatewayDomainTransformer siteGatewayDomainTransformer;
	private SelectorGatewayDomainTransformer selectorGatewayDomainTransformer;
	
	@Autowired
	public LookupDefinitionGatewayDomainTransformer(SiteGatewayDomainTransformer siteGatewayDomainTransformer, SelectorGatewayDomainTransformer selectorGatewayDomainTransformer) {
		this.siteGatewayDomainTransformer = siteGatewayDomainTransformer;
		this.selectorGatewayDomainTransformer = selectorGatewayDomainTransformer;
	}

	public LookupJob lookupDefinitionDetailsToLookupJob(LookupJobMessage lookupJobMessage, LookupDefinitionDetails details) throws MalformedURLException {
		Long lookupJobId = lookupJobMessage.getLookupJobId();
		Long lookupDefinitionId = details.getLookupDefinitionId();
		Site site = siteGatewayDomainTransformer.gatewayToDomain(details.getSiteDetails());
		Selector selector = selectorGatewayDomainTransformer.gatewayToDomain(details.getSelectorDetails());
		return new LookupJob.Builder(lookupJobId, lookupDefinitionId, site, selector).build();
	}
	
}
