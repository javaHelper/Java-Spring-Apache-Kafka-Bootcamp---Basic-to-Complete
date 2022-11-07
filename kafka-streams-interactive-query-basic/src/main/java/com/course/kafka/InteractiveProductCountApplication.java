package com.course.kafka;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StringUtils;

@Configuration
@EnableConfigurationProperties(ProductTrackerProperties.class)
@EnableScheduling
public class InteractiveProductCountApplication {
	private static final String STORE_NAME = "prod-id-count-store";
	
	@Autowired
	private InteractiveQueryService queryService;
	
	@Autowired
	private ProductTrackerProperties productTrackerProperties;
	
	ReadOnlyKeyValueStore<Object, Object> keyValueStore;
	
	
	@Scheduled(fixedRate = 30000, initialDelay = 5000)
	public void printProductCounts() {
		if (keyValueStore == null) {
			keyValueStore = queryService.getQueryableStore(STORE_NAME, QueryableStoreTypes.keyValueStore());
		}

		for (Integer id : productIds()) {
			System.out.println("Product ID: " + id + " Count: " + keyValueStore.get(id));
		}
	}
	
	@Bean
	public Function<KStream<Object, Product>, KStream<Integer, Long>> process() {
		return input -> input
				.filter((key, product) -> productIds().contains(product.getId()))
				.map((key, value) -> new KeyValue<>(value.getId(), value))
				.groupByKey(Grouped.with(Serdes.Integer(), new JsonSerde<>(Product.class)))
				.count(Materialized.<Integer, Long, KeyValueStore<Bytes, byte[]>>as(STORE_NAME)
					.withKeySerde(Serdes.Integer())
					.withValueSerde(Serdes.Long()))
				.toStream();
	}
	
	private Set<Integer> productIds() {
		return StringUtils.commaDelimitedListToSet(productTrackerProperties.getProductIds())
				.stream().map(Integer::parseInt).collect(Collectors.toSet());
	}
}
