package com.course.kafka;

import java.util.function.Consumer;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.serializer.JsonSerde;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class KafkaStreamsAggregateApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaStreamsAggregateApplication.class, args);
	}
	
	@Bean
	public Consumer<KStream<String, DomainEvent>> aggregate() {

		ObjectMapper mapper = new ObjectMapper();
		Serde<DomainEvent> domainEventSerde = new JsonSerde<>( DomainEvent.class, mapper );

		return input -> input
				.groupBy((s, domainEvent) -> domainEvent.boardUuid, Grouped.with(null, domainEventSerde))
				.aggregate(
						String::new,
						(s, domainEvent, board) -> board.concat(domainEvent.eventType),
						Materialized.<String, String, KeyValueStore<Bytes, byte[]>>as("test-events-snapshots")
								.withKeySerde(Serdes.String()).
								withValueSerde(Serdes.String())
				);
	}

}
