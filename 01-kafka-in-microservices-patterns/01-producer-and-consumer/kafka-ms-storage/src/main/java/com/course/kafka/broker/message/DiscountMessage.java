package com.course.kafka.broker.message;

public class DiscountMessage {

	private String discountCode;

	private int discountPercentage;

	public DiscountMessage() {

	}

	public DiscountMessage(String discountCode, int discountPercentage) {
		super();
		this.discountCode = discountCode;
		this.discountPercentage = discountPercentage;
	}

	public String getDiscountCode() {
		return discountCode;
	}

	public int getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	@Override
	public String toString() {
		return "DiscountMessage [discountCode=" + discountCode + ", discountPercentage=" + discountPercentage + "]";
	}

}
