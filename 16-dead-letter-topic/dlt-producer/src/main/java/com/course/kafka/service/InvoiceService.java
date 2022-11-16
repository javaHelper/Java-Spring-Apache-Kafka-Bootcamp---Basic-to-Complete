package com.course.kafka.service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.course.kafka.entity.Invoice;

@Service
public class InvoiceService {

	private AtomicInteger counter = new AtomicInteger();
	
	public Invoice generateInvoice() {
		var invoiceNumber = "INV-" + counter.incrementAndGet();
		var amount = ThreadLocalRandom.current().nextInt(1, 1000);
		
		return new Invoice(invoiceNumber, amount, "USD");
	}
	
}
