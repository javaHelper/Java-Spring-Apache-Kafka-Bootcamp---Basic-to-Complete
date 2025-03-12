package com.course.kafka.command.action;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.course.kafka.api.request.OrderItemRequest;
import com.course.kafka.api.request.OrderRequest;
import com.course.kafka.broker.message.OrderMessage;
import com.course.kafka.broker.producer.OrderProducer;
import com.course.kafka.entity.Order;
import com.course.kafka.entity.OrderItem;
import com.course.kafka.repository.OrderItemRepository;
import com.course.kafka.repository.OrderRepository;

@Component
public class OrderAction {

	@Autowired
	private OrderProducer orderProducer;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	public Order convertToOrder(OrderRequest request) {
		var result = new Order();

		result.setCreditCardNumber(request.getCreditCardNumber());
		result.setOrderLocation(request.getOrderLocation());
		result.setOrderDateTime(LocalDateTime.now());
		result.setOrderNumber(RandomStringUtils.randomAlphanumeric(8).toUpperCase());

		var items = request.getItems().stream().map(this::convertToOrderItem).collect(Collectors.toList());
		items.forEach(item -> item.setOrder(result));
		
		result.setItems(items);

		return result;
	}

	private OrderItem convertToOrderItem(OrderItemRequest itemRequest) {
		var result = new OrderItem();
		
		result.setItemName(itemRequest.getItemName());
		result.setPrice(itemRequest.getPrice());
		result.setQuantity(itemRequest.getQuantity());
		
		return result;
	}
	
	public void saveToDatabase(Order order) {
		orderRepository.save(order);
		order.getItems().forEach(orderItemRepository::save);
	}

	public void publishToKafka(OrderItem orderItem) {
		var orderMessage = new OrderMessage();
		
		orderMessage.setItemName(orderItem.getItemName());
		orderMessage.setPrice(orderItem.getPrice());
		orderMessage.setQuantity(orderItem.getQuantity());
		
		var order = orderItem.getOrder();
		orderMessage.setOrderDateTime(order.getOrderDateTime());
		orderMessage.setOrderLocation(order.getOrderLocation());
		orderMessage.setOrderNumber(order.getOrderNumber());
		orderMessage.setCreditCardNumber(order.getCreditCardNumber());
		
		orderProducer.publish(orderMessage);
	}
	
}
