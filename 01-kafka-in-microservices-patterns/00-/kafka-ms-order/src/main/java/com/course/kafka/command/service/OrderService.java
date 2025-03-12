package com.course.kafka.command.service;

import com.course.kafka.broker.message.OrderMessage;
import com.course.kafka.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.kafka.api.request.OrderRequest;
import com.course.kafka.command.action.OrderAction;

@Service
public class OrderService {

	@Autowired
	private OrderAction action;
	
	public String saveOrder(OrderRequest request) {
		Order orderEntity = action.convertToOrder(request);
		action.saveToDatabase(orderEntity);
		
		// flatten message & publish
		orderEntity.getItems().forEach(item -> {
			OrderMessage orderMessage = action.convertToOrderMessage(item);
			action.sendToKafka(orderMessage);
		});
		
		return orderEntity.getOrderNumber();
	}
}