package com.dayswideawake.webrobot.lookup.backend.gateway.transformer;

import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookup.backend.domain.Selector;
import com.dayswideawake.webrobot.lookup.backend.domain.SelectorCss;
import com.dayswideawake.webrobot.lookup.backend.domain.SelectorXpath;
import com.dayswideawake.webrobot.lookup.backend.gateway.model.SelectorDetails;
import com.dayswideawake.webrobot.lookup.backend.gateway.model.SelectorType;

@Component
public class SelectorGatewayDomainTransformer {

	public Selector gatewayToDomain(SelectorDetails selectorDetails) {
		Selector result;
		if (selectorDetails.getSelectorType().equals(SelectorType.CSS)) {
			result = new SelectorCss.Builder(selectorDetails.getSelector()).build();
		}
		else if(selectorDetails.getSelectorType().equals(SelectorType.XPATH)) {
			result = new SelectorXpath.Builder(selectorDetails.getSelector()).build();
		}
		else {
			throw new IllegalArgumentException("Selector type is invalid: " + selectorDetails.getSelectorType());
		}
		return result;
	}
	
}
