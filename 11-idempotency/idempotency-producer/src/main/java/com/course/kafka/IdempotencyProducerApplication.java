package com.course.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.kafka.entity.PurchaseRequest;
import com.course.kafka.producer.PurchaseRequestProducer;

@SpringBootApplication
public class IdempotencyProducerApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(IdempotencyProducerApplication.class, args);
	}
	
	@Autowired
	private PurchaseRequestProducer producer;

	@Override
	public void run(String... args) throws Exception {
		PurchaseRequest pr1 = new PurchaseRequest(5551, "PR-First", 991, "USD");
		PurchaseRequest pr2 = new PurchaseRequest(5552, "PR-Second", 992, "USD");
		PurchaseRequest pr3 = new PurchaseRequest(5553, "PR-Third", 993, "USD");
		
		producer.send(pr1);
		producer.send(pr2);
		producer.send(pr3);
		
		//sending the duplicate record again
		producer.send(pr1);
	}

}
