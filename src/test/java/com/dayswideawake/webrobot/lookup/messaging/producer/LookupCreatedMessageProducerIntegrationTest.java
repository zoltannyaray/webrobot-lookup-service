package com.dayswideawake.webrobot.lookup.messaging.producer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.Message;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.dayswideawake.webrobot.lookup.TestGroup;
import com.dayswideawake.webrobot.lookup.backend.domain.Lookup;
import com.dayswideawake.webrobot.lookup.backend.event.LookupCreatedEvent;
import com.dayswideawake.webrobot.lookup.messaging.Channels;
import com.dayswideawake.webrobot.lookup.messaging.model.LookupCreatedMessage;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@Test(groups=TestGroup.GROUP_INTEGRATION)
public class LookupCreatedMessageProducerIntegrationTest extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	@Autowired
	private LookupCreatedMessageProducer lookupCreatedMessageProducer;
	@Autowired
	private Channels channels;
	@Autowired
	private MessageCollector messageCollector;
	@Autowired
	private ObjectMapper mapper;
	
	public void onLookupCreatedShouldBeCalledOnLookupCreatedApplicationEvent() {
		LookupCreatedEvent lookupCreatedEvent = getLookupCreatedEvent();
		Mockito.reset(lookupCreatedMessageProducer);
		applicationEventPublisher.publishEvent(lookupCreatedEvent);
		Mockito.verify(lookupCreatedMessageProducer).onLookupCreated(lookupCreatedEvent);
	}
	
	public void lookupCreatedMessageShouldBeSentToMessagingSystem() throws JsonParseException, JsonMappingException, IOException {
		// given
		LookupCreatedEvent lookupCreatedEvent = getLookupCreatedEvent();
		Lookup lookup = lookupCreatedEvent.getLookup();
		messageCollector.forChannel(channels.newLookups()).clear();
		// when
		lookupCreatedMessageProducer.onLookupCreated(lookupCreatedEvent);
		// then
		Message<String> message = (Message<String>) messageCollector.forChannel(channels.newLookups()).poll();
		Assert.assertNotNull(message);
		LookupCreatedMessage messagePayload = mapper.readValue(message.getPayload(), LookupCreatedMessage.class);
		Assert.assertEquals(messagePayload.getLookupDefinitionId(), lookup.getLookupDefinitionId());
		Assert.assertEquals(messagePayload.getLookupJobId(), lookup.getLookupJobId());
		Assert.assertEquals(messagePayload.getLookupTime(), Long.valueOf(lookup.getLookupTime().getTime()));
	}
	
	private LookupCreatedEvent getLookupCreatedEvent() {
		Long lookupJobId = 1L;
		Long lookupDefinitionId = 2L;
		Date lookupTime = new Date();
		List<String> selectedContent = Arrays.asList("content1", "content2");
		Lookup lookup = new Lookup.Builder(lookupJobId, lookupDefinitionId, lookupTime, selectedContent).withId(1L).build();
		return new LookupCreatedEvent.Builder(lookup).build();
	}
	
}
