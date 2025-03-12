package com.course.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Predicate;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KafkaStreamsBranchingApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaStreamsBranchingApplication.class, args);
	}
	
	@Bean
	@SuppressWarnings("unchecked")
	public Function<KStream<Object, String>, KStream<?, WordCount>[]> process() {

		Predicate<Object, WordCount> isEnglish = (k, v) -> v.getWord().equals("english");
		Predicate<Object, WordCount> isFrench =  (k, v) -> v.getWord().equals("french");
		Predicate<Object, WordCount> isSpanish = (k, v) -> v.getWord().equals("spanish");

		return input -> input
				.flatMapValues(value -> Arrays.asList(value.toLowerCase().split("\\W+")))
				.groupBy((key, value) -> value)
				.windowedBy(TimeWindows.ofSizeWithNoGrace(Duration.ofSeconds(6)))
				.count(Materialized.as("WordCounts-1"))
				.toStream()
				.map((key, value) -> new KeyValue<>(null, new WordCount(key.key(), value, new Date(key.window().start()), new Date(key.window().end()))))
				.branch(isEnglish, isFrench, isSpanish);
	}

}
