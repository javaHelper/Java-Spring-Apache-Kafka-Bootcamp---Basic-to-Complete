package com.course.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.kafka.entity.FoodOrder;
import com.course.kafka.entity.SimpleNumber;
import com.course.kafka.producer.FoodOrderProducer;
import com.course.kafka.producer.SimpleNumberProducer;

@SpringBootApplication
public class GlobalErrorHandlerProducerApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(GlobalErrorHandlerProducerApplication.class, args);
	}
	
	@Autowired
	private FoodOrderProducer producer;
	@Autowired
	private SimpleNumberProducer numberProducer;

	@Override
	public void run(String... args) throws Exception {
		FoodOrder chickenOrder = new FoodOrder(3, "Chicken");
		FoodOrder fishOrder = new FoodOrder(10, "Fish");
		FoodOrder pizzaOrder = new FoodOrder(3, "Pizza");
		
		producer.send(chickenOrder);
		producer.send(fishOrder);
		producer.send(pizzaOrder);
		
		
		for (int i = 100; i < 103; i++) {
			SimpleNumber number = new SimpleNumber(i);
			numberProducer.send(number);
		}
	}

}
