package com.course.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.course.kafka.entity.FoodOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FoodOrderConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(FoodOrderConsumer.class);

	private static final int MAX_ORDER_AMOUNT = 7;

	@Autowired
	private ObjectMapper objectMapper;

	@KafkaListener(topics = "t-food-order", errorHandler = "myFoodOrderErrorHandler")
	public void consume(String message) throws JsonMappingException, JsonProcessingException {
		var foodOrder = objectMapper.readValue(message, FoodOrder.class);
		if (foodOrder.getAmount() > MAX_ORDER_AMOUNT) {
			throw new IllegalArgumentException("Order amount is too many : " + foodOrder.getAmount());
		}

		LOG.info("Processing food order : {}", foodOrder);
	}

}
