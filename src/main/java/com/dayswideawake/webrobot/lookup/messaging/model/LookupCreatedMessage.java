package com.dayswideawake.webrobot.lookup.messaging.model;

import java.util.List;

public class LookupCreatedMessage {

	private Long lookupJobId;
	private Long lookupDefinitionId;
	private Long lookupTime;
	private List<String> selectedContent;

	private LookupCreatedMessage(Builder builder) {
		lookupJobId = builder.lookupJobId;
		lookupDefinitionId = builder.lookupDefinitionId;
		lookupTime = builder.lookupTime;
		selectedContent = builder.selectedContent;
	}

	public Long getLookupJobId() {
		return lookupJobId;
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
		return "LookupCreatedMessage [lookupJobId=" + lookupJobId + ", lookupDefinitionId=" + lookupDefinitionId + ", lookupTime=" + lookupTime + ", selectedContent=" + selectedContent + "]";
	}

	public static class Builder {
		private Long lookupJobId;
		private Long lookupDefinitionId;
		private Long lookupTime;
		private List<String> selectedContent;

		public Builder(Long lookupJobId, Long lookupDefinitionId, Long lookupTime, List<String> selectedContent) {
			this.lookupJobId = lookupJobId;
			this.lookupDefinitionId = lookupDefinitionId;
			this.lookupTime = lookupTime;
			this.selectedContent = selectedContent;
		}

		public LookupCreatedMessage build() {
			return new LookupCreatedMessage(this);
		}

	}

}
