package com.course.kafka.error.handler;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;


@Component
public class CommonKafkaErrorHandler implements CommonErrorHandler {
    private static final Logger LOG = LoggerFactory.getLogger(CommonKafkaErrorHandler.class);

    @Override
    public boolean handleOne(Exception thrownException, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer, MessageListenerContainer container) {
        LOG.error("handleOne() for : {}", record.value().toString());
        return true;
    }

    @Override
    public void handleOtherException(Exception thrownException, Consumer<?, ?> consumer, MessageListenerContainer container, boolean batchListener) {
        LOG.error("handleOtherException() for : {}", thrownException.getMessage());
    }
}