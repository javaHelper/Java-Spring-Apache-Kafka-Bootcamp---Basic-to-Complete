package com.course.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaKeyProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String key, String value){
        kafkaTemplate.send("t-multi-partitions", key, value);
    }

}