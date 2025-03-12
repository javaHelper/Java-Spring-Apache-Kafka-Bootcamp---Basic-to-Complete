package com.course.kafka.broker.message;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderMessage {

	private String orderLocation;
	private String orderNumber;
	private String creditCardNumber;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime orderDateTime;

	private String itemName;
	private int price;
	private int quantity;

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public String getItemName() {
		return itemName;
	}

	public LocalDateTime getOrderDateTime() {
		return orderDateTime;
	}

	public String getOrderLocation() {
		return orderLocation;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public int getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setOrderDateTime(LocalDateTime orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	public void setOrderLocation(String orderLocation) {
		this.orderLocation = orderLocation;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderMessage [orderLocation=" + orderLocation + ", orderNumber=" + orderNumber + ", creditCardNumber="
				+ creditCardNumber + ", orderDateTime=" + orderDateTime + ", itemName=" + itemName + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}

}
