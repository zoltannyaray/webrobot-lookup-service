package com.dayswideawake.webrobot.lookup.backend.service.transformer;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookup.backend.domain.Lookup;
import com.dayswideawake.webrobot.lookup.backend.repository.entity.LookupEntity;

@Component
public class LookupDomainEntityTransformer {

	public LookupEntity domainToEntity(Lookup lookup) {
		Long lookupDefinitionId = lookup.getLookupDefinitionId();
		Long lookupTime = lookup.getLookupTime().getTime();
		List<String> selectedContent = lookup.getSelectedContent();
		return new LookupEntity.Builder(lookupDefinitionId, lookupTime, selectedContent).build();
	}

	public Lookup entityToDomain(LookupEntity lookupEntity) {
		Long lookupDefinitionId = lookupEntity.getLookupDefinitionId();
		Date lookupTime = new Date(lookupEntity.getLookupTime());
		List<String> selectedContent = lookupEntity.getSelectedContent();
		return new Lookup.Builder(lookupDefinitionId, lookupTime, selectedContent).build();
	}
	
}
