package com.dayswideawake.webrobot.lookup.backend.gateway.transformer;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookup.backend.domain.Site;
import com.dayswideawake.webrobot.lookup.backend.gateway.model.SiteDetails;

@Component
public class SiteGatewayDomainTransformer {

	public Site gatewayToDomain(SiteDetails siteDetails) throws MalformedURLException {
		URL url = new URL(siteDetails.getUrl());
		return new Site.Builder(url).build();
	}
	
}
