package com.course.kafka.consumer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentRequestCacheKey {
	private String paymentNumber;
	private int amount;
	private String transactionType;
}
