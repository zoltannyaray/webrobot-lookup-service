package com.dayswideawake.webrobot.lookup.messaging.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookup.backend.domain.Lookup;
import com.dayswideawake.webrobot.lookup.backend.event.LookupCreatedEvent;
import com.dayswideawake.webrobot.lookup.messaging.Channels;
import com.dayswideawake.webrobot.lookup.messaging.model.LookupCreatedMessage;
import com.dayswideawake.webrobot.lookup.messaging.transformer.LookupDomainMessageTransformer;

@Component
public class LookupCreatedMessageProducer {

	private Channels channels;
	private LookupDomainMessageTransformer lookupDomainMessageTransformer;
	
	@Autowired
	public LookupCreatedMessageProducer(Channels channels, LookupDomainMessageTransformer lookupDomainMessageTransformer) {
		this.channels = channels;
		this.lookupDomainMessageTransformer = lookupDomainMessageTransformer;
	}
	
	@EventListener
	private void onLookupCreated(LookupCreatedEvent lookupCreatedEvent) {
		Lookup lookup = lookupCreatedEvent.getLookup();
		LookupCreatedMessage payload = lookupDomainMessageTransformer.domainToCreatedMessage(lookup);
		Message<LookupCreatedMessage> message = MessageBuilder.withPayload(payload).build();
		channels.newLookups().send(message);
	}
	
}
