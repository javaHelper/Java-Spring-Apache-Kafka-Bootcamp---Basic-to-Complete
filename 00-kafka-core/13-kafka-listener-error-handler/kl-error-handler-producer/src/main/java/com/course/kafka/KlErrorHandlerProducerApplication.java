package com.course.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.kafka.entity.FoodOrder;
import com.course.kafka.producer.FoodOrderProducer;

@SpringBootApplication
public class KlErrorHandlerProducerApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(KlErrorHandlerProducerApplication.class, args);
	}

	@Autowired
	private FoodOrderProducer producer;
	
	@Override
	public void run(String... args) throws Exception {
		FoodOrder chickenOrder = new FoodOrder(3, "Chicken");
		FoodOrder fishOrder = new FoodOrder(10, "Fish");
		FoodOrder pizzaOrder = new FoodOrder(3, "Pizza");
		
		producer.send(chickenOrder);
		producer.send(fishOrder);
		producer.send(pizzaOrder);
	}

}
