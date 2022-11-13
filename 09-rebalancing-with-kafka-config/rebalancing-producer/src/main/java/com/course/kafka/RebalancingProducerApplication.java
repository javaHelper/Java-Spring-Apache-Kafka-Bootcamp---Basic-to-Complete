package com.course.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RebalancingProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RebalancingProducerApplication.class, args);
	}

}