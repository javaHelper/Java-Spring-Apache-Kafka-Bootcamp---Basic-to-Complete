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
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

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


	@Bean(name = "imageRetryContainerFactory")
	public ConcurrentKafkaListenerContainerFactory<Object, Object> imageRetryContainerFactory(
			ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
			SslBundles sslBundles) {
		var factory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();
		configurer.configure(factory, consumerFactory(sslBundles));

		factory.setCommonErrorHandler(new DefaultErrorHandler(new FixedBackOff(10_000, 3)));

		return factory;
	}

	@Bean(name = "invoiceDltContainerFactory")
	public ConcurrentKafkaListenerContainerFactory<Object, Object> invoiceDltContainerFactory(
			ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
			KafkaTemplate<String, String> kafkaTemplate, SslBundles sslBundles) {
		var factory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();
		configurer.configure(factory, consumerFactory(sslBundles));

		var recoverer = new DeadLetterPublishingRecoverer(kafkaTemplate);

		factory.setCommonErrorHandler(new DefaultErrorHandler(recoverer, new FixedBackOff(1000, 5)));

		return factory;
	}
}