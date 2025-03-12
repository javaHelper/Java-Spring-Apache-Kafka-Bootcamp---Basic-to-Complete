package com.course.kafka.broker.producer;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.course.kafka.broker.message.OrderMessage;

@Service
public class OrderProducer {

	private static final Logger LOG = LoggerFactory.getLogger(OrderProducer.class);

	@Autowired
	private KafkaTemplate<String, OrderMessage> kafkaTemplate;

	public void publish(OrderMessage message) {
		var producerRecord = buildProducerRecord(message);

		kafkaTemplate.send(producerRecord)
				.whenComplete((recordMetadata, ex) -> {
					if (ex == null) {
						LOG.info("Order {} sent successfully ", message.getOrderNumber());
					} else {
						LOG.info("Failed to send order {}", message.getOrderNumber(), ex);
					}
				});

		LOG.info("Just a dummy message for order {}, item {}", message.getOrderNumber(), message.getItemName());
	}

	private ProducerRecord<String, OrderMessage> buildProducerRecord(OrderMessage message) {
		var surpriseBonus = StringUtils.startsWithIgnoreCase(message.getOrderLocation(), "A") ? 25 : 15;
		var headers = new ArrayList<Header>();
		var surpriseBonusHeader = new RecordHeader("surpriseBonus", Integer.toString(surpriseBonus).getBytes());

		headers.add(surpriseBonusHeader);

		return new ProducerRecord<>("t-commodity-order", null, message.getOrderNumber(), message,
                headers);
	}

}