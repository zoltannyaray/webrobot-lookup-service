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

	@Id
	@GeneratedValue
	private Long id;
	private Long lookupDefinitionId;
	private Long lookupTime;
	@ElementCollection
	@Column(length = 3000)
	private List<String> selectedContent;

	public LookupEntity() {
	}

	private LookupEntity(Builder builder) {
		lookupDefinitionId = builder.lookupDefinitionId;
		lookupTime = builder.lookupTime;
		selectedContent = builder.selectedContent;
	}

	public Long getId() {
		return id;
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
		private Long lookupDefinitionId;
		private Long lookupTime;
		private List<String> selectedContent;

		public Builder(Long lookupDefinitionId, Long lookupTime, List<String> selectedContent) {
			this.lookupDefinitionId = lookupDefinitionId;
			this.lookupTime = lookupTime;
			this.selectedContent = selectedContent;
		}

		public LookupEntity build() {
			return new LookupEntity(this);
		}

	}

}
