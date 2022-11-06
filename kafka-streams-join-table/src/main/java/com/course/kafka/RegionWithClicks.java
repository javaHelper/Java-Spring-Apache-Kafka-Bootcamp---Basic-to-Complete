package com.course.kafka;

public class RegionWithClicks {
	private final String region;
	private final long clicks;

	public RegionWithClicks(String region, long clicks) {
		if (region == null || region.isEmpty()) {
			throw new IllegalArgumentException("region must be set");
		}
		if (clicks < 0) {
			throw new IllegalArgumentException("clicks must not be negative");
		}
		this.region = region;
		this.clicks = clicks;
	}

	public String getRegion() {
		return region;
	}

	public long getClicks() {
		return clicks;
	}
}
