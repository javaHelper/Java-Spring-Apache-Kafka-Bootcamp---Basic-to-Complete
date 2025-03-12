package com.course.kafka.producer;

import com.course.kafka.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Employee2JsonProducer {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(Employee employee) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(employee);
        kafkaTemplate.send("t-employee-2", json);
    }
}