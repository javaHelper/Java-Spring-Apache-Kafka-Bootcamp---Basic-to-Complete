package com.course.kafka.entity;

public class PaymentRequest {

	private String paymentNumber;
	private int amount;
	private String currency;
	private String notes;
	private String transactionType;

	public PaymentRequest() {

	}

	public PaymentRequest(String paymentNumber, int amount, String currency, String notes, String transactionType) {
		super();
		this.paymentNumber = paymentNumber;
		this.amount = amount;
		this.currency = currency;
		this.notes = notes;
		this.transactionType = transactionType;
	}

	public int getAmount() {
		return amount;
	}

	public String getCurrency() {
		return currency;
	}

	public String getNotes() {
		return notes;
	}

	public String getPaymentNumber() {
		return paymentNumber;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	@Override
	public String toString() {
		return "PaymentRequest [paymentNumber=" + paymentNumber + ", amount=" + amount + ", currency=" + currency
				+ ", notes=" + notes + ", transactionType=" + transactionType + "]";
	}

}
