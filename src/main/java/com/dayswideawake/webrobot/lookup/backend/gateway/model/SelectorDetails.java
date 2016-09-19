package com.dayswideawake.webrobot.lookup.backend.gateway.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder=SelectorDetails.Builder.class)
public class SelectorDetails {

	private String selector;
	private SelectorType selectorType;

	private SelectorDetails(Builder builder) {
		selector = builder.selector;
		selectorType = builder.selectorType;
	}

	public String getSelector() {
		return selector;
	}

	public SelectorType getSelectorType() {
		return selectorType;
	}

	public static class Builder {
		private String selector;
		private SelectorType selectorType;

		@JsonCreator
		public Builder(@JsonProperty("selector") String selector, @JsonProperty("selectorType") SelectorType selectorType) {
			this.selector = selector;
			this.selectorType = selectorType;
		}
		
		public SelectorDetails build() {
			return new SelectorDetails(this);
		}

	}

}
