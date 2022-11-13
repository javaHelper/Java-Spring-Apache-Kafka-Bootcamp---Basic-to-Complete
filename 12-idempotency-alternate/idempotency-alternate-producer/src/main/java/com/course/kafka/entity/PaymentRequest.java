package com.course.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentRequest {
	private String paymentNumber;
	private int amount;
	private String currency;
	private String notes;
	private String transactionType;
}
