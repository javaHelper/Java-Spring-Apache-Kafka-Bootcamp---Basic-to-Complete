package com.course.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.kafka.entity.PaymentRequest;
import com.course.kafka.producer.PaymentRequestProducer;

import java.time.LocalDate;

@SpringBootApplication
public class IdempotencyAlternateProducerApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(IdempotencyAlternateProducerApplication.class, args);
	}
	
	@Autowired
	private PaymentRequestProducer producer;

	@Override
	public void run(String... args) throws Exception {
		PaymentRequest p1 = new PaymentRequest(100, "USD", "1234567890", "Payment 1", LocalDate.now());
		PaymentRequest p2 = new PaymentRequest(200, "USD", "1234567890", "Payment 2", LocalDate.now());
		PaymentRequest p3 = new PaymentRequest(300, "USD", "0987654321", "Payment 3", LocalDate.now());

		PaymentRequest p4 = new PaymentRequest(400, "USD", "0987654321", "Payment 4", LocalDate.now());
		PaymentRequest p5 = new PaymentRequest(500, "USD", "1122334455", "Payment 5", LocalDate.now());
		PaymentRequest p6 = new PaymentRequest(600, "USD", "1122334455", "Payment 6", LocalDate.now());

		producer.send(p1);
		producer.send(p2);
		producer.send(p3);
		producer.send(p4);
		producer.send(p5);
		producer.send(p6);

		// publish two of them again
		producer.send(p1);
		producer.send(p2);
	}

}