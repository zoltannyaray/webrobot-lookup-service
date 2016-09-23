package com.dayswideawake.webrobot.lookup.messaging.model;

import java.util.List;

public class SelectedContentChangedMessage {

    private Long lookupJobId;
    private Long lookupDefinitionId;
    private Long previousLookupTime;
    private Long newLookupTime;
    private List<String> previousSelectedContent;
    private List<String> newSelectedContent;

    private SelectedContentChangedMessage(Builder builder) {
        lookupJobId = builder.lookupJobId;
        lookupDefinitionId = builder.lookupDefinitionId;
        previousLookupTime = builder.previousLookupTime;
        newLookupTime = builder.newLookupTime;
        previousSelectedContent = builder.previousSelectedContent;
        newSelectedContent = builder.newSelectedContent;
    }

    public Long getLookupJobId() {
        return lookupJobId;
    }

    public Long getLookupDefinitionId() {
        return lookupDefinitionId;
    }

    public Long getPreviousLookupTime() {
        return previousLookupTime;
    }

    public Long getNewLookupTime() {
        return newLookupTime;
    }

    public List<String> getPreviousSelectedContent() {
        return previousSelectedContent;
    }

    public List<String> getNewSelectedContent() {
        return newSelectedContent;
    }

    public static class Builder {
        private Long lookupJobId;
        private Long lookupDefinitionId;
        private Long previousLookupTime;
        private Long newLookupTime;
        private List<String> previousSelectedContent;
        private List<String> newSelectedContent;

        public Builder(Long lookupJobId, Long lookupDefinitionId, Long previousLookupTime, Long newLookupTime, List<String> previousSelectedContent, List<String> newSelectedContent) {
            this.lookupJobId = lookupJobId;
            this.lookupDefinitionId = lookupDefinitionId;
            this.previousLookupTime = previousLookupTime;
            this.newLookupTime = newLookupTime;
            this.previousSelectedContent = previousSelectedContent;
            this.newSelectedContent = newSelectedContent;
        }

        public SelectedContentChangedMessage build() {
            return new SelectedContentChangedMessage(this);
        }

    }

}
