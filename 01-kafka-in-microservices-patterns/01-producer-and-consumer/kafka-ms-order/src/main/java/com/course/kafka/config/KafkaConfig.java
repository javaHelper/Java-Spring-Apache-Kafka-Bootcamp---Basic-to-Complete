package com.course.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

	@Bean
	public NewTopic topicOrder() {
		return TopicBuilder.name("t-commodity-order").partitions(2).replicas(1).build();
	}
	

	@Bean
	public NewTopic topicOrderReply() {
		return TopicBuilder.name("t-commodity-order-reply").partitions(1).replicas(1).build();
	}

}
