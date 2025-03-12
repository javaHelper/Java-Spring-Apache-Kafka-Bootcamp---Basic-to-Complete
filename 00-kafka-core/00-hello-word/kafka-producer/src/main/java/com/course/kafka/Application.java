package com.course.kafka;

import com.course.kafka.producer.HelloKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private HelloKafkaProducer helloKafkaProducer;

	@Override
	public void run(String... args) throws Exception {
		helloKafkaProducer.sendHello("John Doe "+ ThreadLocalRandom.current().nextInt());
	}
}