package com.course.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.course.kafka.entity.PurchaseRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PurchaseRequestProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	public void send(PurchaseRequest purchaseRequest) throws JsonProcessingException {
		var json = objectMapper.writeValueAsString(purchaseRequest);
		kafkaTemplate.send("t-purchase-request", purchaseRequest.getPrNumber(), json);
	}

}