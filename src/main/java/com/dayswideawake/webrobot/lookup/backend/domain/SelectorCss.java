package com.dayswideawake.webrobot.lookup.backend.domain;

public class SelectorCss extends Selector {

	private String cssSelector;

	private SelectorCss(Builder builder) {
		cssSelector = builder.cssSelector;
	}

	public String getCssSelector() {
		return cssSelector;
	}

	@Override
	public String toString() {
		return "SelectorCss [cssSelector=" + cssSelector + "]";
	}

	public static class Builder {
		private String cssSelector;

		public Builder(String cssSelector) {
			this.cssSelector = cssSelector;
		}

		public SelectorCss build() {
			return new SelectorCss(this);
		}
	}

}
