package com.course.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class RebalanceConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(RebalanceConsumer.class);

    @KafkaListener(topics = "t-rebalance", concurrency = "3")
    public void consume(ConsumerRecord<String, String> consumerRecord) {
        LOG.info("Partition : {}, Offset : {}, Message : {}", consumerRecord.partition(), consumerRecord.offset(),
                consumerRecord.value());
    }
}