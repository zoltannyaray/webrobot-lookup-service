package com.dayswideawake.webrobot.lookup.backend.domain;

import java.net.URL;

public class Site {

	private URL url;

	private Site(Builder builder) {
		url = builder.url;
	}

	public URL getUrl() {
		return url;
	}

	public static class Builder {
		private URL url;

		public Builder(URL url) {
			this.url = url;
		}

		public Site build() {
			return new Site(this);
		}
	}

}
