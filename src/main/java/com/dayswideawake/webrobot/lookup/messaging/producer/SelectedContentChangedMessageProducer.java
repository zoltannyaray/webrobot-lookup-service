package com.dayswideawake.webrobot.lookup.messaging.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookup.backend.event.SelectedContentChangedEvent;
import com.dayswideawake.webrobot.lookup.messaging.Channels;
import com.dayswideawake.webrobot.lookup.messaging.model.SelectedContentChangedMessage;
import com.dayswideawake.webrobot.lookup.messaging.transformer.SelectedContentDomainMessageTransformer;

@Component
public class SelectedContentChangedMessageProducer {

    private SelectedContentDomainMessageTransformer transformer;
    private Channels channels;

    @Autowired
    public SelectedContentChangedMessageProducer(SelectedContentDomainMessageTransformer transformer, Channels channels) {
        this.transformer = transformer;
        this.channels = channels;
    }

    @EventListener
    public void onSelectedContentChangedApplicationEvent(SelectedContentChangedEvent event) {
        SelectedContentChangedMessage messagePayload = transformer.eventToMessage(event);
        Message<SelectedContentChangedMessage> message = MessageBuilder.withPayload(messagePayload).build();
        channels.changedContent().send(message);
    }

}
