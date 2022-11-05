package com.course.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.kafka.producer.KafkaKeyProducer;

@SpringBootApplication
public class MultipleConsumersForEachTopicApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MultipleConsumersForEachTopicApplication.class, args);
	}
	
	@Autowired
	private KafkaKeyProducer producer;

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 50; i++) {
			var key = "key-" + (i % 4);
			var data = "data " + i + " with key " + key;
			producer.send(key, data);
			Thread.sleep(500);
		}
	}
}