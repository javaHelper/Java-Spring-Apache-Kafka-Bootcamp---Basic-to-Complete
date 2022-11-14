package com.course.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.course.kafka.entity.FoodOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FoodOrderConsumer {
	private static final Logger LOGGER = LoggerFactory.getLogger(FoodOrder.class);

	private ObjectMapper objectMapper = new ObjectMapper();

	private static final int MAX_AMOUNT_ORDER = 7;

	@KafkaListener(topics = "t-food-order", errorHandler = "myFoodOrderErrorHandler")
	public void consume(String message) throws JsonMappingException, JsonProcessingException {
		FoodOrder foodOrder = objectMapper.readValue(message, FoodOrder.class);

		if (foodOrder.getAmount() > MAX_AMOUNT_ORDER) {
			LOGGER.error("Food Order Not valid: {}", foodOrder);
			throw new IllegalArgumentException("Food order amount is high");
		}
		LOGGER.info("Food Order valid: {}", foodOrder);
	}
}