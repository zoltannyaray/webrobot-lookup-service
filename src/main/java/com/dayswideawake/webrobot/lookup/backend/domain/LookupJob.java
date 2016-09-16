package com.dayswideawake.webrobot.lookup.backend.domain;

public class LookupJob {

	private Long lookupDefinitionId;
	private Site site;
	private Selector selector;

	private LookupJob(Builder builder) {
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

		public LookupJob build() {
			return new LookupJob(this);
		}
	}

}
