package com.dayswideawake.webrobot.lookup.backend.domain;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Lookup {

	private Long lookupDefinitionId;
	private Date lookupTime;
	private List<String> selectedContent;

	private Lookup(Builder builder) {
		lookupDefinitionId = builder.lookupDefinitionId;
		lookupTime = builder.lookupTime;
		selectedContent = builder.selectedContent;
	}

	public Long getLookupDefinitionId() {
		return lookupDefinitionId;
	}

	public Date getLookupTime() {
		return new Date(lookupTime.getTime());
	}

	public List<String> getSelectedContent() {
		return Collections.unmodifiableList(selectedContent);
	}

	public static class Builder {
		private Long lookupDefinitionId;
		private Date lookupTime;
		private List<String> selectedContent;

		public Builder(Long lookupDefinitionId, Date lookupTime, List<String> selectedContent) {
			this.lookupDefinitionId = lookupDefinitionId;
			this.lookupTime = lookupTime;
			this.selectedContent = selectedContent;
		}

		public Lookup build() {
			return new Lookup(this);
		}

	}

}
