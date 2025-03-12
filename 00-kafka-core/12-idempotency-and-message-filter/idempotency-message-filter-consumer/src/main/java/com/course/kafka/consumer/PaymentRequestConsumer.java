package com.course.kafka.consumer;

import com.course.kafka.entity.PaymentRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentRequestConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentRequestConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @Qualifier("cachePaymentRequest")
    private Cache<String, Boolean> cachePaymentRequest;


    @KafkaListener(topics = "t-payment-request", containerFactory = "paymentRequestContainerFactory")
    public void consume(String message) {
        try {
            var paymentRequest = objectMapper.readValue(message, PaymentRequest.class);
            String cacheKey = paymentRequest.calculateHash();

            LOG.info("Processing payment request{}", paymentRequest);
            cachePaymentRequest.put(cacheKey, true);
        } catch (Exception e) {
            LOG.error("Error processing payment request", e);
        }
    }
}