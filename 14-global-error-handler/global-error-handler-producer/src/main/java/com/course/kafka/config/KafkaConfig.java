package com.course.kafka.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfig {

	@Autowired
	private KafkaProperties kafkaProperties;

	@Bean
	public ProducerFactory<String, String> producerFactory(SslBundles sslBundles) {
		var properties = kafkaProperties.buildProducerProperties(sslBundles);

		properties.put(ProducerConfig.METADATA_MAX_AGE_CONFIG, "180000");

		return new DefaultKafkaProducerFactory<String, String>(properties);
	}

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate(SslBundles sslBundles) {
		return new KafkaTemplate<>(producerFactory(sslBundles));
	}
}