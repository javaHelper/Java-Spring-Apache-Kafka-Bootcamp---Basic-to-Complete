package com.course.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;


@Configuration
public class KafkaConfig {

	@Autowired
	private KafkaProperties kafkaProperties;

	@Bean
	public ConsumerFactory<Object, Object> consumerFactory(SslBundles sslBundles) {
		var properties = kafkaProperties.buildConsumerProperties(sslBundles);
		properties.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG, "120000");
		return new DefaultKafkaConsumerFactory<>(properties);
	}
}