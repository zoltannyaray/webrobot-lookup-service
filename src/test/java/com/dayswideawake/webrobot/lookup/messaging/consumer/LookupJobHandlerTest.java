package com.dayswideawake.webrobot.lookup.messaging.consumer;

import java.net.MalformedURLException;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dayswideawake.webrobot.lookup.TestGroup;
import com.dayswideawake.webrobot.lookup.backend.domain.LookupJob;
import com.dayswideawake.webrobot.lookup.backend.domain.Selector;
import com.dayswideawake.webrobot.lookup.backend.domain.Site;
import com.dayswideawake.webrobot.lookup.backend.gateway.ViewLookupDefinitionGateway;
import com.dayswideawake.webrobot.lookup.backend.gateway.model.LookupDefinitionDetails;
import com.dayswideawake.webrobot.lookup.backend.gateway.transformer.LookupDefinitionGatewayDomainTransformer;
import com.dayswideawake.webrobot.lookup.backend.service.LookupService;
import com.dayswideawake.webrobot.lookup.messaging.model.LookupJobMessage;

@Test(groups = TestGroup.GROUP_UNIT)
public class LookupJobHandlerTest {

	@InjectMocks
	private LookupJobHandler lookupJobHandler;
	@Mock
	private ViewLookupDefinitionGateway viewLookupDefinitionGateway;
	@Mock
	private LookupService lookupService;
	@Mock
	private LookupDefinitionGatewayDomainTransformer gatewayDomainTransformer;
	@Mock
	private LookupJobMessage lookupJobMessage;
	@Mock
	private LookupJob lookupJob;
	@Mock
	private Site site;
	@Mock
	private Selector selector;
	@Mock
	private LookupDefinitionDetails lookupDefinitionDetails;

	@BeforeMethod
	public void beforeMethod() throws MalformedURLException {
		// given
		MockitoAnnotations.initMocks(this);
		Mockito.when(lookupJobMessage.getLookupDefinitionId()).thenReturn(1L);
		Mockito.when(gatewayDomainTransformer.lookupDefinitionDetailsToLookupJob(Mockito.any(), Mockito.any())).thenReturn(lookupJob);
		Mockito.when(viewLookupDefinitionGateway.viewLookupDefinition(Mockito.any())).thenReturn(lookupDefinitionDetails);
	}

	public void handleShouldCallViewLookupDefinitionGateway() throws MalformedURLException {
		// when
		lookupJobHandler.handle(lookupJobMessage);
		// then
		Mockito.verify(viewLookupDefinitionGateway).viewLookupDefinition(lookupJobMessage.getLookupDefinitionId());
	}

	public void handleShouldCallGatewayDomainTransformer() throws MalformedURLException {
		// when
		lookupJobHandler.handle(lookupJobMessage);
		// then
		Mockito.verify(gatewayDomainTransformer).lookupDefinitionDetailsToLookupJob(lookupJobMessage, lookupDefinitionDetails);
	}

	public void handleShouldCallLookupService() throws MalformedURLException {
		// when
		lookupJobHandler.handle(lookupJobMessage);
		// then
		Mockito.verify(lookupService).doLookup(lookupJob);
	}

}
