package com.dayswideawake.webrobot.lookup.backend.event;

import com.dayswideawake.webrobot.lookup.backend.domain.Lookup;

public class LookupCreatedEvent {

	private Lookup lookup;

	private LookupCreatedEvent(Builder builder) {
		lookup = builder.lookup;
	}

	public Lookup getLookup() {
		return lookup;
	}

	public static class Builder {
		private Lookup lookup;

		public Builder(Lookup lookup) {
			this.lookup = lookup;
		}

		public LookupCreatedEvent build() {
			return new LookupCreatedEvent(this);
		}
	}

}
