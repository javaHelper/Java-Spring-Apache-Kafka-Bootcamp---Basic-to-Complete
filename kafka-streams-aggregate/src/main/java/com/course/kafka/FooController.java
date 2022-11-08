package com.course.kafka;

import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController {
	@Autowired
	private InteractiveQueryService interactiveQueryService;

	@RequestMapping("/events")
	public String events() {

		final ReadOnlyKeyValueStore<String, String> topFiveStore = interactiveQueryService
				.getQueryableStore("test-events-snapshots", QueryableStoreTypes.<String, String>keyValueStore());
		return topFiveStore.get("12345");
	}
}