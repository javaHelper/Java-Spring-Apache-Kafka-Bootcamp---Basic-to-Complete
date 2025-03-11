package com.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

@Configuration
public class KafkaConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public ProducerFactory<String, String> producerFactory(SslBundles sslBundles){
        Map<String, Object> properties = kafkaProperties.buildProducerProperties(sslBundles);
        properties.put(ProducerConfig.METRICS_SAMPLE_WINDOW_MS_CONFIG, "50000");
        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(SslBundles sslBundles){
        return new KafkaTemplate<>(producerFactory(sslBundles));
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory(SslBundles sslBundles){
        Map<String, Object> properties = kafkaProperties.buildProducerProperties(sslBundles);
        properties.put(ConsumerConfig.METRICS_SAMPLE_WINDOW_MS_CONFIG, "40000");
        return new DefaultKafkaConsumerFactory<>(properties);
    }
}