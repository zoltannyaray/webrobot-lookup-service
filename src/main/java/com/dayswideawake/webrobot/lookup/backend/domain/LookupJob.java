package com.dayswideawake.webrobot.lookup.backend.domain;

public class LookupJob {

	private Long id;
	private Long lookupDefinitionId;
	private Site site;
	private Selector selector;

	private LookupJob(Builder builder) {
		id = builder.id;
		lookupDefinitionId = builder.lookupDefinitionId;
		site = builder.site;
		selector = builder.selector;
	}

	public Long getId() {
		return id;
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

	@Override
	public String toString() {
		return "LookupJob [id=" + id + ", lookupDefinitionId=" + lookupDefinitionId + ", site=" + site + ", selector=" + selector + "]";
	}

	public static class Builder {
		private Long id;
		private Long lookupDefinitionId;
		private Site site;
		private Selector selector;

		public Builder(Long id, Long lookupDefinitionId, Site site, Selector selector) {
			this.id = id;
			this.lookupDefinitionId = lookupDefinitionId;
			this.site = site;
			this.selector = selector;
		}

		public LookupJob build() {
			return new LookupJob(this);
		}
	}

}
