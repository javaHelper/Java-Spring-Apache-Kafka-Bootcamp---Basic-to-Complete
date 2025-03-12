package com.course.kafka.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue
	private int orderId;
	
	@Column
	private String orderNumber;
	
	@Column
	private String orderLocation;
	
	@Column
	private LocalDateTime orderDateTime;
	
	@Column
	private String creditCardNumber;
	
	@OneToMany(mappedBy = "order")
	private List<OrderItem> items;

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public LocalDateTime getOrderDateTime() {
		return orderDateTime;
	}

	public int getOrderId() {
		return orderId;
	}

	public String getOrderLocation() {
		return orderLocation;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public void setOrderDateTime(LocalDateTime orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setOrderLocation(String orderLocation) {
		this.orderLocation = orderLocation;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderNumber=" + orderNumber + ", orderLocation=" + orderLocation
				+ ", orderDateTIme=" + orderDateTime + ", creditCardNumber=" + creditCardNumber + ", items=" + items
				+ "]";
	}
}