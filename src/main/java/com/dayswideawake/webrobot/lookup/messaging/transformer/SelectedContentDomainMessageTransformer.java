package com.dayswideawake.webrobot.lookup.messaging.transformer;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dayswideawake.webrobot.lookup.aop.annotation.Loggable;
import com.dayswideawake.webrobot.lookup.backend.event.SelectedContentChangedEvent;
import com.dayswideawake.webrobot.lookup.messaging.model.SelectedContentChangedMessage;

@Component
public class SelectedContentDomainMessageTransformer {

    @Loggable
    public SelectedContentChangedMessage eventToMessage(SelectedContentChangedEvent event) {
        Long lookupJobId = event.getNewLookup().getLookupJobId();
        Long lookupDefinitionId = event.getNewLookup().getLookupDefinitionId();
        Long previousLookupTime = event.getPreviousLookup().getLookupTime().getTime();
        Long newLookupTime = event.getNewLookup().getLookupTime().getTime();
        List<String> previousSelectedContent = event.getPreviousLookup().getSelectedContent();
        List<String> newSelectedContent = event.getNewLookup().getSelectedContent();
        return new SelectedContentChangedMessage.Builder(lookupJobId, lookupDefinitionId, previousLookupTime, newLookupTime, previousSelectedContent, newSelectedContent).build(); 
    }
    
}
