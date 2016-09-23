package com.dayswideawake.webrobot.lookup.backend.event;

import com.dayswideawake.webrobot.lookup.backend.domain.Lookup;

public class LookupContentChangedEvent {

	private Lookup previousLookup;
	private Lookup newLookup;

	private LookupContentChangedEvent(Builder builder) {
		previousLookup = builder.previousLookup;
		newLookup = builder.newLookup;
	}

	public Lookup getPreviousLookup() {
		return previousLookup;
	}

	public Lookup getNewLookup() {
		return newLookup;
	}

	public static class Builder {
		private Lookup previousLookup;
		private Lookup newLookup;

		public Builder(Lookup previousLookup, Lookup newLookup) {
			this.previousLookup = previousLookup;
			this.newLookup = newLookup;
		}

		public LookupContentChangedEvent build() {
			return new LookupContentChangedEvent(this);
		}

	}

}
