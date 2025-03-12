package com.course.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.kafka.entity.PaymentRequest;
import com.course.kafka.producer.PaymentRequestProducer;

@SpringBootApplication
public class IdempotencyAlternateProducerApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(IdempotencyAlternateProducerApplication.class, args);
	}
	
	@Autowired
	private PaymentRequestProducer producer;

	@Override
	public void run(String... args) throws Exception {
		PaymentRequest alphaTx1 = new PaymentRequest("Pay-Alpha", 551, "USD", "Notes alpha", "Budget reserve");
		PaymentRequest alphaTx2 = new PaymentRequest("Pay-Alpha", 551, "USD", "Notes alpha", "Approval workflow");
		PaymentRequest alphaTx3 = new PaymentRequest("Pay-Alpha", 551, "USD", "Notes alpha", "Push Notification");
		
		PaymentRequest betaTx1 = new PaymentRequest("Pay-Beta", 551, "USD", "Notes Beta", "Budget reserve");
		PaymentRequest betaTx2 = new PaymentRequest("Pay-Beta", 551, "USD", "Notes Beta", "Approval workflow");
		PaymentRequest betaTx3 = new PaymentRequest("Pay-Beta", 551, "USD", "Notes Beta", "Push Notification");
		
		producer.send(alphaTx1);
		producer.send(alphaTx2);
		producer.send(alphaTx3);
		
		producer.send(betaTx1);
		producer.send(betaTx2);
		producer.send(betaTx3);
		
		producer.send(alphaTx2);
		producer.send(betaTx3);
	}

}
