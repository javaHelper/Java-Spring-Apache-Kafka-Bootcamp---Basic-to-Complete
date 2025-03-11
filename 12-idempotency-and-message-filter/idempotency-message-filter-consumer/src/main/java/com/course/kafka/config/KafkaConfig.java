package com.course.kafka.config;

import com.course.kafka.entity.PaymentRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;

@Configuration
public class KafkaConfig {

	@Autowired
	private KafkaProperties kafkaProperties;

	@Autowired
	private ObjectMapper objectMapper;

	@Bean
	public ConsumerFactory<Object, Object> consumerFactory(SslBundles sslBundles) {
		var properties = kafkaProperties.buildConsumerProperties(sslBundles);

		properties.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG, "120000");

		return new DefaultKafkaConsumerFactory<>(properties);
	}

	@Bean(name = "paymentRequestContainerFactory")
	public ConcurrentKafkaListenerContainerFactory paymentRequestContainerFactory(
			ConcurrentKafkaListenerContainerFactoryConfigurer configurer, SslBundles sslBundles, ObjectMapper objectMapper,
			@Qualifier("cachePaymentRequest") Cache<String, Boolean> cachePaymentRequest){

		ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
		configurer.configure(factory, consumerFactory(sslBundles));

		factory.setRecordFilterStrategy(new RecordFilterStrategy<Object, Object>() {
			@Override
			public boolean filter(ConsumerRecord<Object, Object> consumerRecord) {
				try {
					PaymentRequest paymentRequest = objectMapper.readValue(consumerRecord.value().toString(), PaymentRequest.class);
					String cacheKey = paymentRequest.calculateHash();

					return cachePaymentRequest.getIfPresent(cacheKey) != null;
				} catch (Exception e) {
					return false;
				}
			}
		});
		return factory;
	}
}