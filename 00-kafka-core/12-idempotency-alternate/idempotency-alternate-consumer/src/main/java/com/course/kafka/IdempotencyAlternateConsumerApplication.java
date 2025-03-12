package com.course.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IdempotencyAlternateConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdempotencyAlternateConsumerApplication.class, args);
	}

}
