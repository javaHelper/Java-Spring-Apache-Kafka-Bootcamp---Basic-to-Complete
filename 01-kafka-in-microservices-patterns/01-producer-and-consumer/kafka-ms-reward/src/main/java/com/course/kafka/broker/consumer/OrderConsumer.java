package com.course.kafka.broker.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.course.kafka.broker.message.OrderMessage;

//@Service
public class OrderConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(OrderConsumer.class);

	@KafkaListener(topics = "t-commodity-order")
	public void listen(ConsumerRecord<String, OrderMessage> consumerRecord) {
		var headers = consumerRecord.headers();
		var orderMessage = consumerRecord.value();

		LOG.info("Processing order {},item {}, credit card number {}", orderMessage.getOrderNumber(),
				orderMessage.getItemName(), orderMessage.getCreditCardNumber());
		LOG.info("Headers : ");
		headers.forEach(h -> LOG.info("  key : {}, value : {}", h.key(), new String(h.value())));

		var headerValue = ObjectUtils.isEmpty(headers.lastHeader("surpriseBonus").value()) ? "0"
				: new String(headers.lastHeader("surpriseBonus").value());

		var bonusPercentage = Integer.parseInt(headerValue);
		var bonusAmount = (bonusPercentage / 100d) * orderMessage.getPrice() * orderMessage.getQuantity();

		LOG.info("Surprise bonus is {}%", bonusPercentage);
		LOG.info("Bonus amount is {}", bonusAmount);
	}

}