package com.course.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic topicCommodityOrder(){
        return TopicBuilder.name("t-commodity-order")
                .partitions(2)
                .build();
    }
}