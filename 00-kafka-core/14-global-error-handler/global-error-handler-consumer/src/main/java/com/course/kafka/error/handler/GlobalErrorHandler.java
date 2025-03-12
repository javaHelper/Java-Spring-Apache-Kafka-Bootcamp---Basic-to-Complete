package com.course.kafka.error.handler;

//import org.apache.kafka.clients.consumer.Consumer;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.listener.ConsumerAwareErrorHandler;
//import org.springframework.stereotype.Service;
//
//@Service
//public class GlobalErrorHandler implements ConsumerAwareErrorHandler {
//
//	private static final Logger Logger = LoggerFactory.getLogger(GlobalErrorHandler.class);
//
//	@Override
//	public void handle(Exception thrownException, ConsumerRecord<?, ?> data, Consumer<?, ?> consumer) {
//		Logger.error("### Global Error Handler for message : {}", data.value().toString());
//	}
//}