package com.course.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class HelloKafkaProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public void sendHello(String name){
        kafkaTemplate.send("t-hello", "Hello "+name);
    }
}