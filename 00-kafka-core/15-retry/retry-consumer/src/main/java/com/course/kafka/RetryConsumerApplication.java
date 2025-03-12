package com.course.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RetryConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetryConsumerApplication.class, args);
	}

}
