package com.dayswideawake.webrobot.lookup.backend.service;

import java.util.Optional;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.ApplicationEventPublisher;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dayswideawake.webrobot.lookup.TestGroup;
import com.dayswideawake.webrobot.lookup.backend.domain.Lookup;
import com.dayswideawake.webrobot.lookup.backend.event.LookupCreatedEvent;
import com.dayswideawake.webrobot.lookup.backend.event.SelectedContentChangedEvent;

@Test(groups=TestGroup.GROUP_UNIT)
public class LookupCompareServiceImplTest {
    
    @InjectMocks
    private LookupCompareServiceImpl lookupCompareServiceImpl;
    @Mock
    private LookupService lookupService;
    @Mock
    private ApplicationEventPublisher applicationEventPublisher;
    @Mock
    private LookupCreatedEvent lookupCreatedEvent;
    @Mock
    private Lookup newLookup;
    private Optional<Long> newLookupId = Optional.of(2L);
    private Long newLookupDefinitionId = 10L;
    @Mock
    private Lookup previousLookup;
    private Optional<Long> previousLookupId = Optional.of(1L);
    private Long previousLookupDefinitionId = 10L;
    @Captor
    private ArgumentCaptor<SelectedContentChangedEvent> selectedContentChangedEventCaptor;
    
    
    @BeforeMethod
    public void beforeMethod() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(newLookup.getId()).thenReturn(newLookupId);
        Mockito.when(newLookup.getLookupDefinitionId()).thenReturn(newLookupDefinitionId);
        Mockito.when(lookupCreatedEvent.getLookup()).thenReturn(newLookup);
        Mockito.when(lookupService.getPreviousLookup(Mockito.anyLong(), Mockito.any())).thenReturn(Optional.of(previousLookup));
    }
    
    @Test(expectedExceptions=IllegalStateException.class)
    public void onLookupCreatedMustThrowIllegalStateOnEmptyLookupId() {
        // given
        Mockito.when(newLookup.getId()).thenReturn(Optional.empty());
        // when
        lookupCompareServiceImpl.onLookupCreated(lookupCreatedEvent);
    }
    
    public void onLookupCreatedShouldCallGetPreviousLookup() {
        // when
        lookupCompareServiceImpl.onLookupCreated(lookupCreatedEvent);
        // then
        Mockito.verify(lookupService).getPreviousLookup(newLookupId.get(), newLookupDefinitionId);
    }
    
//    public void onLookupCreatedShouldPulishLookupContentChangedEvent() {
//        // given
//        Mockito.when(lookupCompareServiceImpl.isLookupResultChanged(newLookup, previousLookup)).thenReturn(true);
//        // when
//        lookupCompareServiceImpl.onLookupCreated(lookupCreatedEvent);
//        // then
//        Mockito.verify(applicationEventPublisher).publishEvent(selectedContentChangedEventCaptor.capture());
//        Assert.assertEquals(selectedContentChangedEventCaptor.getValue().getNewLookup(), newLookup);
//        Assert.assertEquals(selectedContentChangedEventCaptor.getValue().getPreviousLookup(), previousLookup);
//    }
    
}
