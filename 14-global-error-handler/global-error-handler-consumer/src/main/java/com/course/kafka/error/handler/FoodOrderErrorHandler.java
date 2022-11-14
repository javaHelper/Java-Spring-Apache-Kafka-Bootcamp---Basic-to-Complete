package com.course.kafka.error.handler;

import org.apache.kafka.clients.consumer.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service(value = "myFoodOrderErrorHandler")
public class FoodOrderErrorHandler implements ConsumerAwareListenerErrorHandler {

	private static final Logger LOG = LoggerFactory.getLogger(FoodOrderErrorHandler.class);

	@Override
	public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
		LOG.warn("Food order error, sending to elasticsearch : {}, because : {}", message.getPayload(),
				exception.getMessage());

		if (exception.getCause() instanceof RuntimeException) {
			throw exception;
		}
		
		return null;
	}

}
