package com.dayswideawake.webrobot.lookup.backend.gateway.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = SiteDetails.Builder.class)
public class SiteDetails {

	private String url;

	public SiteDetails(Builder builder) {
		url = builder.url;
	}

	public String getUrl() {
		return url;
	}

	public static class Builder {
		private String url;

		@JsonCreator
		public Builder(@JsonProperty("url") String url) {
			this.url = url;
		}

		public SiteDetails build() {
			return new SiteDetails(this);
		}
	}

}
