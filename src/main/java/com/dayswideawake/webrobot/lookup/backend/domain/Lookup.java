package com.dayswideawake.webrobot.lookup.backend.domain;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Lookup {

	private Long lookupJobId;
	private Long lookupDefinitionId;
	private Date lookupTime;
	private List<String> selectedContent;

	private Lookup(Builder builder) {
		lookupJobId = builder.lookupJobId;
		lookupDefinitionId = builder.lookupDefinitionId;
		lookupTime = builder.lookupTime;
		selectedContent = builder.selectedContent;
	}

	public Long getLookupJobId() {
		return lookupJobId;
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

	@Override
	public String toString() {
		return "Lookup [lookupJobId=" + lookupJobId + ", lookupDefinitionId=" + lookupDefinitionId + ", lookupTime=" + lookupTime + ", selectedContent=" + selectedContent + "]";
	}

	public static class Builder {
		private Long lookupJobId;
		private Long lookupDefinitionId;
		private Date lookupTime;
		private List<String> selectedContent;

		public Builder(Long lookupJobId, Long lookupDefinitionId, Date lookupTime, List<String> selectedContent) {
			this.lookupJobId = lookupJobId;
			this.lookupDefinitionId = lookupDefinitionId;
			this.lookupTime = lookupTime;
			this.selectedContent = selectedContent;
		}

		public Lookup build() {
			return new Lookup(this);
		}

	}

}
