package com.dayswideawake.webrobot.lookup.backend.domain;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Lookup {

	private Optional<Long> id;
	private Long lookupJobId;
	private Long lookupDefinitionId;
	private Date lookupTime;
	private List<String> selectedContent;

	private Lookup(Builder builder) {
		id = builder.id;
		lookupJobId = builder.lookupJobId;
		lookupDefinitionId = builder.lookupDefinitionId;
		lookupTime = builder.lookupTime;
		selectedContent = builder.selectedContent;
	}

	public Optional<Long> getId() {
		return id;
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
		return "Lookup [id=" + id.orElse(null) + ", lookupJobId=" + lookupJobId + ", lookupDefinitionId=" + lookupDefinitionId + ", lookupTime=" + lookupTime + ", selectedContent=" + selectedContent + "]";
	}

	public static class Builder {
		private Optional<Long> id;
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
		
		public Builder withId(Long id) {
			this.id = Optional.ofNullable(id);
			return this;
		}

		public Lookup build() {
			return new Lookup(this);
		}

	}

}
