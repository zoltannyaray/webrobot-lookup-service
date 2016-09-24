package com.dayswideawake.webrobot.lookup.messaging.producer;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dayswideawake.webrobot.lookup.TestGroup;
import com.dayswideawake.webrobot.lookup.backend.event.SelectedContentChangedEvent;
import com.dayswideawake.webrobot.lookup.messaging.Channels;
import com.dayswideawake.webrobot.lookup.messaging.model.SelectedContentChangedMessage;
import com.dayswideawake.webrobot.lookup.messaging.transformer.SelectedContentDomainMessageTransformer;

@Test(groups=TestGroup.GROUP_UNIT)
public class SelectedContentChangedMessageProducerTest {
    
    @InjectMocks
    private SelectedContentChangedMessageProducer selectedContentChangedMessageProducer;
    @Mock
    private SelectedContentDomainMessageTransformer selectedContentDomainMessageTransformer;
    @Mock
    private Channels channels;
    @Mock
    private SelectedContentChangedEvent selectedContentChangedEvent;
    @Mock
    private SelectedContentChangedMessage selectedContentChangedMessage;
    @Mock
    private MessageChannel changedContentMessageChannel;
    @Captor
    private ArgumentCaptor<Message<SelectedContentChangedMessage>> selectedContentChangedMessageCaptor;
    
    @BeforeMethod
    public void beforeMethod() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(selectedContentDomainMessageTransformer.eventToMessage(Mockito.any())).thenReturn(selectedContentChangedMessage);
        Mockito.when(channels.changedContent()).thenReturn(changedContentMessageChannel);
    }
    
    public void onSelectedContentChangedApplicationEventShouldCallEventToMessageTransformer() {
        // when
        selectedContentChangedMessageProducer.onSelectedContentChangedApplicationEvent(selectedContentChangedEvent);
        // then
        Mockito.verify(selectedContentDomainMessageTransformer).eventToMessage(selectedContentChangedEvent);
    }
    
    public void onSelectedContentChangedApplicationEventShouldSendMessageToChannel() {
        // when
        selectedContentChangedMessageProducer.onSelectedContentChangedApplicationEvent(selectedContentChangedEvent);
        // then
        Mockito.verify(changedContentMessageChannel).send(selectedContentChangedMessageCaptor.capture());
        Assert.assertEquals(selectedContentChangedMessageCaptor.getValue().getPayload(), selectedContentChangedMessage);
    }
    
}
