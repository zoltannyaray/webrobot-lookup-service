package com.dayswideawake.webrobot.lookup.backend.gateway.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = LookupDefinitionDetails.Builder.class)
public class LookupDefinitionDetails {

	private Long lookupDefinitionId;
	private Long accountId;
	private Long intervalInSeconds;
	private SelectorDetails selectorDetails;
	private SiteDetails siteDetails;

	private LookupDefinitionDetails(Builder builder) {
		lookupDefinitionId = builder.lookupDefinitionId;
		accountId = builder.accountId;
		selectorDetails = builder.selectorDetails;
		siteDetails = builder.siteDetails;
		intervalInSeconds = builder.intervalInSeconds;
	}

	public Long getLookupDefinitionId() {
		return lookupDefinitionId;
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

	@Override
	public String toString() {
		return "LookupDefinitionDetails [lookupDefinitionId=" + lookupDefinitionId + ", accountId=" + accountId + ", intervalInSeconds=" + intervalInSeconds + ", selectorDetails=" + selectorDetails + ", siteDetails=" + siteDetails + "]";
	}

	public static class Builder {
		private Long lookupDefinitionId;
		private Long accountId;
		private Long intervalInSeconds;
		private SelectorDetails selectorDetails;
		private SiteDetails siteDetails;

		@JsonCreator
		public Builder(@JsonProperty("lookupDefinitionId") Long lookupDefinitionId, @JsonProperty("accountId") Long accountId, @JsonProperty("selector") SelectorDetails selectorDetails, @JsonProperty("site") SiteDetails siteDetails, @JsonProperty("intervalInSeconds") Long intervalInSeconds) {
			this.lookupDefinitionId = lookupDefinitionId;
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
