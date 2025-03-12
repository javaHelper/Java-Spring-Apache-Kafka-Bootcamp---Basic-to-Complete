package com.course.kafka;

import java.util.function.BiFunction;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.Joined;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KafkaStreamsJoinTableApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaStreamsJoinTableApplication.class, args);
	}

	@Bean
	public BiFunction<KStream<String, Long>, KTable<String, String>, KStream<String, Long>> process() {

		return (userClicksStream, userRegionsTable) -> userClicksStream
				.leftJoin(userRegionsTable,
						(clicks, region) -> new RegionWithClicks(region == null ? "UNKNOWN" : region, clicks),
						Joined.with(Serdes.String(), Serdes.Long(), null))
				.map((user, regionWithClicks) -> new KeyValue<>(regionWithClicks.getRegion(), regionWithClicks.getClicks()))
				.groupByKey(Grouped.with(Serdes.String(), Serdes.Long()))
				.reduce((firstClicks, secondClicks) -> firstClicks + secondClicks)
				.toStream();
	}
}
