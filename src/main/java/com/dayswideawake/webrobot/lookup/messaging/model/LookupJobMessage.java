package com.dayswideawake.webrobot.lookup.messaging.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = LookupJobMessage.Builder.class)
public class LookupJobMessage {

	private Long lookupDefinitionId;

	private LookupJobMessage(Builder builder) {
		lookupDefinitionId = builder.lookupDefinitionId;
	}

	public Long getLookupDefinitionId() {
		return lookupDefinitionId;
	}

	@Override
	public String toString() {
		return "LookupJobMessage [lookupDefinitionId=" + lookupDefinitionId + "]";
	}

	public static class Builder {
		private Long lookupDefinitionId;

		@JsonCreator
		public Builder(@JsonProperty("lookupDefinitionId") Long lookupDefinitionId) {
			this.lookupDefinitionId = lookupDefinitionId;
		}

		public LookupJobMessage build() {
			return new LookupJobMessage(this);
		}
	}

}
