package com.dayswideawake.webrobot.lookup.messaging.model;

import java.util.List;

public class LookupCreatedMessage {

	private Long lookupDefinitionId;
	private Long lookupTime;
	private List<String> selectedContent;

	private LookupCreatedMessage(Builder builder) {
		lookupDefinitionId = builder.lookupDefinitionId;
		lookupTime = builder.lookupTime;
		selectedContent = builder.selectedContent;
	}

	public Long getLookupDefinitionId() {
		return lookupDefinitionId;
	}

	public Long getLookupTime() {
		return lookupTime;
	}

	public List<String> getSelectedContent() {
		return selectedContent;
	}

	@Override
	public String toString() {
		return "LookupCreatedMessage [lookupDefinitionId=" + lookupDefinitionId + ", lookupTime=" + lookupTime + ", selectedContent=" + selectedContent + "]";
	}

	public static class Builder {
		private Long lookupDefinitionId;
		private Long lookupTime;
		private List<String> selectedContent;

		public Builder(Long lookupDefinitionId, Long lookupTime, List<String> selectedContent) {
			this.lookupDefinitionId = lookupDefinitionId;
			this.lookupTime = lookupTime;
			this.selectedContent = selectedContent;
		}

		public LookupCreatedMessage build() {
			return new LookupCreatedMessage(this);
		}

	}

}
