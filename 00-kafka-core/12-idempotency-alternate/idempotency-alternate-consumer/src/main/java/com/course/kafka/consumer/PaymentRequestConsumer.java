package com.course.kafka.consumer;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;

import com.course.kafka.entity.PaymentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.stereotype.Service;

@Service
public class PaymentRequestConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(PaymentRequestConsumer.class);

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	@Qualifier("cachePaymentRequest")
	private Cache<PaymentRequestCacheKey, Boolean> cache;

	private boolean isExistsInCache(PaymentRequestCacheKey key) {
		return Optional.ofNullable(cache.getIfPresent(key)).orElse(false);
	}

	@KafkaListener(topics = "t-payment-request")
	public void consume(String message) throws JsonMappingException, JsonProcessingException {
		var paymentRequest = objectMapper.readValue(message, PaymentRequest.class);

		var cacheKey = new PaymentRequestCacheKey(paymentRequest.getPaymentNumber(), paymentRequest.getAmount(),
				paymentRequest.getTransactionType());
		var processed = isExistsInCache(cacheKey);

		if (processed) {
			return;
		}

		LOG.info("Processing {}", paymentRequest);

		cache.put(cacheKey, true);
	}

}