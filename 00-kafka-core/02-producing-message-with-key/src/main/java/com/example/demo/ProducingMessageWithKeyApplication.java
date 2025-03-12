package com.example.demo;

import com.example.demo.producer.KafkaKeyProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProducingMessageWithKeyApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProducingMessageWithKeyApplication.class, args);
    }

    @Autowired
    private KafkaKeyProducer kafkaKeyProducer;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 30; i++) {
            String key = "key-" + (i % 4);
            String value = "value " + i + " with key " + key;
            kafkaKeyProducer.send(key, value);
        }
    }
}