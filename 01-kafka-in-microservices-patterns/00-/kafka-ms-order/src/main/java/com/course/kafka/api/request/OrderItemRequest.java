package com.course.kafka.api.request;

public class OrderItemRequest {

	private String itemName;
	private int price;
	private int quantity;

	public String getItemName() {
		return itemName;
	}

	public int getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderItemRequest [itemName=" + itemName + ", price=" + price + ", quantity=" + quantity + "]";
	}

}
