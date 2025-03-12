package com.course.kafka.consumer;

import com.course.kafka.entity.Commodity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
public class CommodityNotificationConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommodityNotificationConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "t-commodity", groupId = "cg-notification")
    public void consume(String message) throws JsonProcessingException, InterruptedException {
        Commodity commodity = objectMapper.readValue(message, Commodity.class);

        // Intentionally added if they overlap each other, or both consumer gets both records and do not bloack each other
        int randomDelayMillis = ThreadLocalRandom.current().nextInt(500, 2000);
        TimeUnit.MICROSECONDS.sleep(randomDelayMillis);

        LOGGER.info("Notification logic for : {}", commodity);
    }
}