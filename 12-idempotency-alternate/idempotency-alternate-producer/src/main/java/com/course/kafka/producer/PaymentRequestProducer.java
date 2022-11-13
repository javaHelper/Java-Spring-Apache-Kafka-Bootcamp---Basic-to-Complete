package com.course.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.course.kafka.entity.PaymentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PaymentRequestProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	public void send(PaymentRequest paymentRequest) throws JsonProcessingException {
		var json = objectMapper.writeValueAsString(paymentRequest);
		kafkaTemplate.send("t-payment-request", paymentRequest.getPaymentNumber(), json);
	}

}