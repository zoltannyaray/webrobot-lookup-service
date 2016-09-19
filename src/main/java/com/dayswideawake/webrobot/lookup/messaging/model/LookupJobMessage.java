package com.dayswideawake.webrobot.lookup.messaging.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = LookupJobMessage.Builder.class)
public class LookupJobMessage {

	private Long lookupJobId;
	private Long lookupDefinitionId;

	private LookupJobMessage(Builder builder) {
		lookupJobId = builder.lookupJobId;
		lookupDefinitionId = builder.lookupDefinitionId;
	}

	public Long getLookupJobId() {
		return lookupJobId;
	}

	public Long getLookupDefinitionId() {
		return lookupDefinitionId;
	}

	@Override
	public String toString() {
		return "LookupJobMessage [lookupJobId=" + lookupJobId + ", lookupDefinitionId=" + lookupDefinitionId + "]";
	}

	public static class Builder {
		private Long lookupJobId;
		private Long lookupDefinitionId;

		@JsonCreator
		public Builder(@JsonProperty("lookupJobId") Long lookupJobId, @JsonProperty("lookupDefinitionId") Long lookupDefinitionId) {
			this.lookupJobId = lookupJobId;
			this.lookupDefinitionId = lookupDefinitionId;
		}

		public LookupJobMessage build() {
			return new LookupJobMessage(this);
		}
	}

}
