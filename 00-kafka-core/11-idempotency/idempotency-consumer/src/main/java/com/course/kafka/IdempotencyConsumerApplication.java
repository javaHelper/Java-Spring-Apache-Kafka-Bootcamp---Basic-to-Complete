package com.course.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IdempotencyConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdempotencyConsumerApplication.class, args);
	}

}
