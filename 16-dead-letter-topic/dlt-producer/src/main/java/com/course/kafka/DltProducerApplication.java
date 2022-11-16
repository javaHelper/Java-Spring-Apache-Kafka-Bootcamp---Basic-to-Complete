package com.course.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.kafka.entity.Invoice;
import com.course.kafka.producer.InvoiceProducer;
import com.course.kafka.service.InvoiceService;

@SpringBootApplication
public class DltProducerApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(DltProducerApplication.class, args);
	}
	
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private InvoiceProducer invoiceProducer;
	

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 10; i++) {
			Invoice invoice = invoiceService.generateInvoice();
			if(i > 5) {
				invoice.setAmount(0);
			}
			invoiceProducer.send(invoice);
		}
	}

}
