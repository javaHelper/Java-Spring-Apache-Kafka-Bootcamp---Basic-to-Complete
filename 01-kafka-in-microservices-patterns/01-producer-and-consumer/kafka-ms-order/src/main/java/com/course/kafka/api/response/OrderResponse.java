package com.course.kafka.api.response;

public class OrderResponse {

	private String orderNumber;
	
	public OrderResponse() {
		
	}

	public OrderResponse(String orderNumber) {
		super();
		this.orderNumber = orderNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Override
	public String toString() {
		return "OrderResponse [orderNumber=" + orderNumber + "]";
	}
	
}
