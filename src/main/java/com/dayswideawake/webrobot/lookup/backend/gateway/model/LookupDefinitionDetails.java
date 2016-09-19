package com.dayswideawake.webrobot.lookup.backend.gateway.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = LookupDefinitionDetails.Builder.class)
public class LookupDefinitionDetails {

	private Long id;
	private Long accountId;
	private Long intervalInSeconds;
	private SelectorDetails selectorDetails;
	private SiteDetails siteDetails;

	private LookupDefinitionDetails(Builder builder) {
		id = builder.id;
		accountId = builder.accountId;
		selectorDetails = builder.selectorDetails;
		siteDetails = builder.siteDetails;
		intervalInSeconds = builder.intervalInSeconds;
	}

	public Long getId() {
		return id;
	}

	public Long getAccountId() {
		return accountId;
	}

	public SelectorDetails getSelectorDetails() {
		return selectorDetails;
	}

	public SiteDetails getSiteDetails() {
		return siteDetails;
	}

	public Long getIntervalInSeconds() {
		return intervalInSeconds;
	}

	public static class Builder {
		private Long id;
		private Long accountId;
		private Long intervalInSeconds;
		private SelectorDetails selectorDetails;
		private SiteDetails siteDetails;

		@JsonCreator
		public Builder(@JsonProperty("id") Long id, @JsonProperty("accountId") Long accountId, @JsonProperty("selector") SelectorDetails selectorDetails, @JsonProperty("site") SiteDetails siteDetails, @JsonProperty("intervalInSeconds") Long intervalInSeconds) {
			this.id = id;
			this.accountId = accountId;
			this.selectorDetails = selectorDetails;
			this.siteDetails = siteDetails;
			this.intervalInSeconds = intervalInSeconds;
		}

		public LookupDefinitionDetails build() {
			return new LookupDefinitionDetails(this);
		}

	}

}
