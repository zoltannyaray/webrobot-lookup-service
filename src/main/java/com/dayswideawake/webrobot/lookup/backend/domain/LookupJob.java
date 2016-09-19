package com.dayswideawake.webrobot.lookup.backend.domain;

public class LookupJob {

	private Long lookupDefinitionId;
	private Site site;
	private Selector selector;

	private LookupJob(Builder builder) {
		lookupDefinitionId = builder.lookupDefinitionId;
		site = builder.site;
		selector = builder.selector;
	}

	public Long getLookupDefinitionId() {
		return lookupDefinitionId;
	}

	public Site getSite() {
		return site;
	}

	public Selector getSelector() {
		return selector;
	}

	public static class Builder {
		private Long lookupDefinitionId;
		private Site site;
		private Selector selector;

		public Builder(Long lookupDefinitionId, Site site, Selector selector) {
			this.lookupDefinitionId = lookupDefinitionId;
			this.site = site;
			this.selector = selector;
		}

		public LookupJob build() {
			return new LookupJob(this);
		}
	}

}
