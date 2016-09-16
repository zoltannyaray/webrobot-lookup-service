package com.dayswideawake.webrobot.lookup.backend.domain;

public class Site {

	private Site(Builder builder) {
	}

	public static class Builder {
		
		public Site build() {
			return new Site(this);
		}
	}
	
}
