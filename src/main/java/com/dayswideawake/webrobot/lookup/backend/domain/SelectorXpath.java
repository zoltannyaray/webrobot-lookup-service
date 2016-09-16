package com.dayswideawake.webrobot.lookup.backend.domain;

public class SelectorXpath extends Selector {

	private String xpathSelector;

	private SelectorXpath(Builder builder) {
		xpathSelector = builder.xpathSelector;
	}

	public String getXpathSelector() {
		return xpathSelector;
	}

	public static class Builder {
		private String xpathSelector;

		public Builder(String xpathSelector) {
			this.xpathSelector = xpathSelector;
		}

		public SelectorXpath build() {
			return new SelectorXpath(this);
		}
	}
	
}
