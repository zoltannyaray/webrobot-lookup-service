package com.dayswideawake.webrobot.lookup.backend.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dayswideawake.webrobot.lookup.aop.annotation.Loggable;
import com.dayswideawake.webrobot.lookup.backend.gateway.model.LookupDefinitionDetails;

@Service
public class ViewLookupDefinitionGateway {
	
	private static final String VIEW_URL = "http://webrobot-lookup-definition-service/lookup-definitions/{id}";
	private RestTemplate restTemplate;
	
	
	@Autowired
	public ViewLookupDefinitionGateway(@LoadBalanced RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Loggable
	public LookupDefinitionDetails viewLookupDefinition(Long lookupDefinitionId) {
		ParameterizedTypeReference<Resource<LookupDefinitionDetails>> ptr = new ParameterizedTypeReference<Resource<LookupDefinitionDetails>>() {};
		ResponseEntity<Resource<LookupDefinitionDetails>> response = restTemplate.exchange(VIEW_URL, HttpMethod.GET, null, ptr, String.valueOf(lookupDefinitionId));
		return response.getBody().getContent();
	}

}
