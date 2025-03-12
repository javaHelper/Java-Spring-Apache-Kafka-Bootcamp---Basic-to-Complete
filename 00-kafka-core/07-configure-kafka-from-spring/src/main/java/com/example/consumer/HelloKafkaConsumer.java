package com.example.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class HelloKafkaConsumer {

	@KafkaListener(topics = "t-hello")
	public void consume(String message) {
		System.out.println(message);
	}
	
}