package com.dayswideawake.webrobot.lookup.backend.repository.entity;

import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LookupEntity {

	public static final int MAX_SELECTED_CONTENT_LENGTH = 10000; 
	
	@Id
	@GeneratedValue
	private Long id;
	private Long lookupJobId;
	private Long lookupDefinitionId;
	private Long lookupTime;
	@ElementCollection
	@Column(length = MAX_SELECTED_CONTENT_LENGTH)
	private List<String> selectedContent;

	public LookupEntity() {
	}

	private LookupEntity(Builder builder) {
		lookupJobId = builder.lookupJobId;
		lookupDefinitionId = builder.lookupDefinitionId;
		lookupTime = builder.lookupTime;
		selectedContent = builder.selectedContent;
	}

	public Long getId() {
		return id;
	}

	public Long getLookupJobId() {
		return lookupJobId;
	}

	public Long getLookupDefinitionId() {
		return lookupDefinitionId;
	}

	public Long getLookupTime() {
		return lookupTime;
	}

	public List<String> getSelectedContent() {
		return Collections.unmodifiableList(selectedContent);
	}

	public static class Builder {
		private Long lookupJobId;
		private Long lookupDefinitionId;
		private Long lookupTime;
		private List<String> selectedContent;

		public Builder(Long lookupJobId, Long lookupDefinitionId, Long lookupTime, List<String> selectedContent) {
			this.lookupJobId = lookupJobId;
			this.lookupDefinitionId = lookupDefinitionId;
			this.lookupTime = lookupTime;
			this.selectedContent = selectedContent;
		}

		public LookupEntity build() {
			return new LookupEntity(this);
		}

	}

}
