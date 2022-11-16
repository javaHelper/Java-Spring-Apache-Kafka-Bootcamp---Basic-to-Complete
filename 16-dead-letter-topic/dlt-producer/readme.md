# Dead Letter Topic

```sh
% kafka-topics --bootstrap-server localhost:9092 --create --partitions 2 --replication-factor 1 --topic t-invoice
Created topic t-invoice.


% kafka-topics --bootstrap-server localhost:9092 --create --partitions 2 --replication-factor 1 --topic t-invoice-dead
Created topic t-invoice-dead.


% kafka-topics --bootstrap-server localhost:9092 --describe --topic t-invoice-dead 
Topic: t-invoice-dead	TopicId: EJp2XjKNRKyMcJYT0evLJw	PartitionCount: 2	ReplicationFactor: 1	Configs: segment.bytes=1073741824
	Topic: t-invoice-dead	Partition: 0	Leader: 1	Replicas: 1	Isr: 1	Offline: 
	Topic: t-invoice-dead	Partition: 1	Leader: 1	Replicas: 1	Isr: 1	Offline: 

% kafka-topics --bootstrap-server localhost:9092 --describe --topic t-invoice
Topic: t-invoice	TopicId: LloMi2VAQJWC-FhRJZWUkA	PartitionCount: 2	ReplicationFactor: 1	Configs: segment.bytes=1073741824
	Topic: t-invoice	Partition: 0	Leader: 1	Replicas: 1	Isr: 1	Offline: 
	Topic: t-invoice	Partition: 1	Leader: 1	Replicas: 1	Isr: 1	Offline: 

```

Run the code and simply check the below topic

```sh
kafka-console-consumer --bootstrap-server localhost:9092 --offset earliest --partition 0 --topic t-invoice
{"invoiceNumber":"INV-1","amount":402,"currency":"USD"}
{"invoiceNumber":"INV-3","amount":950,"currency":"USD"}
{"invoiceNumber":"INV-4","amount":856,"currency":"USD"}
{"invoiceNumber":"INV-5","amount":492,"currency":"USD"}
{"invoiceNumber":"INV-7","amount":0,"currency":"USD"}
{"invoiceNumber":"INV-8","amount":0,"currency":"USD"}
{"invoiceNumber":"INV-9","amount":0,"currency":"USD"}
{"invoiceNumber":"INV-10","amount":0,"currency":"USD"}
```

```sh
kafka-console-consumer --bootstrap-server localhost:9092 --offset earliest --partition 1 --topic t-invoice
{"invoiceNumber":"INV-2","amount":597,"currency":"USD"}
{"invoiceNumber":"INV-6","amount":245,"currency":"USD"}
```


Error - 

```
Caused by: org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.InvoiceConsumer.consume(java.lang.String) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-8, amount=0, currency=USD); nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-8, amount=0, currency=USD)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.decorateException(KafkaMessageListenerContainer.java:2719) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2689) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeOnMessage(KafkaMessageListenerContainer.java:2649) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeRecordListener(KafkaMessageListenerContainer.java:2576) ~[spring-kafka-2.8.10.jar:2.8.10]
	... 9 common frames omitted
	Suppressed: org.springframework.kafka.listener.ListenerExecutionFailedException: Restored Stack Trace
		at org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:363) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:92) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:53) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2669) ~[spring-kafka-2.8.10.jar:2.8.10]
Caused by: java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-8, amount=0, currency=USD)
	at com.course.kafka.consumer.InvoiceConsumer.consume(InvoiceConsumer.java:27) ~[classes/:na]
	at jdk.internal.reflect.GeneratedMethodAccessor37.invoke(Unknown Source) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:169) ~[spring-messaging-5.3.23.jar:5.3.23]
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.invoke(InvocableHandlerMethod.java:119) ~[spring-messaging-5.3.23.jar:5.3.23]
	at org.springframework.kafka.listener.adapter.HandlerAdapter.invoke(HandlerAdapter.java:56) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:347) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:92) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:53) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2669) ~[spring-kafka-2.8.10.jar:2.8.10]
	... 11 common frames omitted

[Kafka Core Consumer] 18:58:49.671  INFO --- o.a.k.clients.consumer.KafkaConsumer     : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Seeking to offset 6 for partition t-invoice-0
[Kafka Core Consumer] 18:58:49.676 ERROR --- o.s.k.l.KafkaMessageListenerContainer    : Error handler threw an exception

org.springframework.kafka.KafkaException: Seek to current after exception; nested exception is org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.InvoiceConsumer.consume(java.lang.String) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-8, amount=0, currency=USD); nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-8, amount=0, currency=USD)
	at org.springframework.kafka.listener.SeekUtils.seekOrRecover(SeekUtils.java:208) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.DefaultErrorHandler.handleRemaining(DefaultErrorHandler.java:135) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeErrorHandler(KafkaMessageListenerContainer.java:2706) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeRecordListener(KafkaMessageListenerContainer.java:2587) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeWithRecords(KafkaMessageListenerContainer.java:2456) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeRecordListener(KafkaMessageListenerContainer.java:2334) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeListener(KafkaMessageListenerContainer.java:2005) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeIfHaveRecords(KafkaMessageListenerContainer.java:1375) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.pollAndInvoke(KafkaMessageListenerContainer.java:1366) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.run(KafkaMessageListenerContainer.java:1257) ~[spring-kafka-2.8.10.jar:2.8.10]
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515) ~[na:na]
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264) ~[na:na]
	at java.base/java.lang.Thread.run(Thread.java:834) ~[na:na]
Caused by: org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.InvoiceConsumer.consume(java.lang.String) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-8, amount=0, currency=USD); nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-8, amount=0, currency=USD)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.decorateException(KafkaMessageListenerContainer.java:2719) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2689) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeOnMessage(KafkaMessageListenerContainer.java:2649) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeRecordListener(KafkaMessageListenerContainer.java:2576) ~[spring-kafka-2.8.10.jar:2.8.10]
	... 9 common frames omitted
	Suppressed: org.springframework.kafka.listener.ListenerExecutionFailedException: Restored Stack Trace
		at org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:363) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:92) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:53) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2669) ~[spring-kafka-2.8.10.jar:2.8.10]
Caused by: java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-8, amount=0, currency=USD)
	at com.course.kafka.consumer.InvoiceConsumer.consume(InvoiceConsumer.java:27) ~[classes/:na]
	at jdk.internal.reflect.GeneratedMethodAccessor37.invoke(Unknown Source) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:169) ~[spring-messaging-5.3.23.jar:5.3.23]
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.invoke(InvocableHandlerMethod.java:119) ~[spring-messaging-5.3.23.jar:5.3.23]
	at org.springframework.kafka.listener.adapter.HandlerAdapter.invoke(HandlerAdapter.java:56) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:347) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:92) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:53) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2669) ~[spring-kafka-2.8.10.jar:2.8.10]
	... 11 common frames omitted

[Kafka Core Consumer] 18:58:50.217  INFO --- o.a.k.clients.consumer.KafkaConsumer     : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Seeking to offset 7 for partition t-invoice-0
[Kafka Core Consumer] 18:58:51.280  INFO --- o.a.k.clients.consumer.KafkaConsumer     : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Seeking to offset 7 for partition t-invoice-0
[Kafka Core Consumer] 18:58:51.281 ERROR --- o.s.k.l.KafkaMessageListenerContainer    : Error handler threw an exception

org.springframework.kafka.KafkaException: Seek to current after exception; nested exception is org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.InvoiceConsumer.consume(java.lang.String) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-9, amount=0, currency=USD); nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-9, amount=0, currency=USD)
	at org.springframework.kafka.listener.SeekUtils.seekOrRecover(SeekUtils.java:208) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.DefaultErrorHandler.handleRemaining(DefaultErrorHandler.java:135) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeErrorHandler(KafkaMessageListenerContainer.java:2706) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeRecordListener(KafkaMessageListenerContainer.java:2587) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeWithRecords(KafkaMessageListenerContainer.java:2456) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeRecordListener(KafkaMessageListenerContainer.java:2334) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeListener(KafkaMessageListenerContainer.java:2005) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeIfHaveRecords(KafkaMessageListenerContainer.java:1375) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.pollAndInvoke(KafkaMessageListenerContainer.java:1366) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.run(KafkaMessageListenerContainer.java:1257) ~[spring-kafka-2.8.10.jar:2.8.10]
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515) ~[na:na]
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264) ~[na:na]
	at java.base/java.lang.Thread.run(Thread.java:834) ~[na:na]
Caused by: org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.InvoiceConsumer.consume(java.lang.String) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-9, amount=0, currency=USD); nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-9, amount=0, currency=USD)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.decorateException(KafkaMessageListenerContainer.java:2719) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2689) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeOnMessage(KafkaMessageListenerContainer.java:2649) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeRecordListener(KafkaMessageListenerContainer.java:2576) ~[spring-kafka-2.8.10.jar:2.8.10]
	... 9 common frames omitted
	Suppressed: org.springframework.kafka.listener.ListenerExecutionFailedException: Restored Stack Trace
		at org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:363) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:92) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:53) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2669) ~[spring-kafka-2.8.10.jar:2.8.10]
Caused by: java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-9, amount=0, currency=USD)
	at com.course.kafka.consumer.InvoiceConsumer.consume(InvoiceConsumer.java:27) ~[classes/:na]
	at jdk.internal.reflect.GeneratedMethodAccessor37.invoke(Unknown Source) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:169) ~[spring-messaging-5.3.23.jar:5.3.23]
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.invoke(InvocableHandlerMethod.java:119) ~[spring-messaging-5.3.23.jar:5.3.23]
	at org.springframework.kafka.listener.adapter.HandlerAdapter.invoke(HandlerAdapter.java:56) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:347) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:92) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:53) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2669) ~[spring-kafka-2.8.10.jar:2.8.10]
	... 11 common frames omitted

[Kafka Core Consumer] 18:58:52.324  INFO --- o.a.k.clients.consumer.KafkaConsumer     : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Seeking to offset 7 for partition t-invoice-0
[Kafka Core Consumer] 18:58:52.324 ERROR --- o.s.k.l.KafkaMessageListenerContainer    : Error handler threw an exception

org.springframework.kafka.KafkaException: Seek to current after exception; nested exception is org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.InvoiceConsumer.consume(java.lang.String) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-9, amount=0, currency=USD); nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-9, amount=0, currency=USD)
	at org.springframework.kafka.listener.SeekUtils.seekOrRecover(SeekUtils.java:208) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.DefaultErrorHandler.handleRemaining(DefaultErrorHandler.java:135) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeErrorHandler(KafkaMessageListenerContainer.java:2706) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeRecordListener(KafkaMessageListenerContainer.java:2587) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeWithRecords(KafkaMessageListenerContainer.java:2456) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeRecordListener(KafkaMessageListenerContainer.java:2334) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeListener(KafkaMessageListenerContainer.java:2005) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeIfHaveRecords(KafkaMessageListenerContainer.java:1375) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.pollAndInvoke(KafkaMessageListenerContainer.java:1366) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.run(KafkaMessageListenerContainer.java:1257) ~[spring-kafka-2.8.10.jar:2.8.10]
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515) ~[na:na]
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264) ~[na:na]
	at java.base/java.lang.Thread.run(Thread.java:834) ~[na:na]
Caused by: org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.InvoiceConsumer.consume(java.lang.String) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-9, amount=0, currency=USD); nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-9, amount=0, currency=USD)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.decorateException(KafkaMessageListenerContainer.java:2719) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2689) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeOnMessage(KafkaMessageListenerContainer.java:2649) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeRecordListener(KafkaMessageListenerContainer.java:2576) ~[spring-kafka-2.8.10.jar:2.8.10]
	... 9 common frames omitted
	Suppressed: org.springframework.kafka.listener.ListenerExecutionFailedException: Restored Stack Trace
		at org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:363) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:92) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:53) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2669) ~[spring-kafka-2.8.10.jar:2.8.10]
Caused by: java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-9, amount=0, currency=USD)
	at com.course.kafka.consumer.InvoiceConsumer.consume(InvoiceConsumer.java:27) ~[classes/:na]
	at jdk.internal.reflect.GeneratedMethodAccessor37.invoke(Unknown Source) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:169) ~[spring-messaging-5.3.23.jar:5.3.23]
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.invoke(InvocableHandlerMethod.java:119) ~[spring-messaging-5.3.23.jar:5.3.23]
	at org.springframework.kafka.listener.adapter.HandlerAdapter.invoke(HandlerAdapter.java:56) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:347) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:92) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:53) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2669) ~[spring-kafka-2.8.10.jar:2.8.10]
	... 11 common frames omitted

[Kafka Core Consumer] 18:58:53.369  INFO --- o.a.k.clients.consumer.KafkaConsumer     : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Seeking to offset 7 for partition t-invoice-0
[Kafka Core Consumer] 18:58:53.370 ERROR --- o.s.k.l.KafkaMessageListenerContainer    : Error handler threw an exception

org.springframework.kafka.KafkaException: Seek to current after exception; nested exception is org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.InvoiceConsumer.consume(java.lang.String) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-9, amount=0, currency=USD); nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-9, amount=0, currency=USD)
	at org.springframework.kafka.listener.SeekUtils.seekOrRecover(SeekUtils.java:208) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.DefaultErrorHandler.handleRemaining(DefaultErrorHandler.java:135) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeErrorHandler(KafkaMessageListenerContainer.java:2706) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeRecordListener(KafkaMessageListenerContainer.java:2587) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeWithRecords(KafkaMessageListenerContainer.java:2456) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeRecordListener(KafkaMessageListenerContainer.java:2334) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeListener(KafkaMessageListenerContainer.java:2005) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeIfHaveRecords(KafkaMessageListenerContainer.java:1375) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.pollAndInvoke(KafkaMessageListenerContainer.java:1366) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.run(KafkaMessageListenerContainer.java:1257) ~[spring-kafka-2.8.10.jar:2.8.10]
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515) ~[na:na]
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264) ~[na:na]
	at java.base/java.lang.Thread.run(Thread.java:834) ~[na:na]
Caused by: org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.InvoiceConsumer.consume(java.lang.String) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-9, amount=0, currency=USD); nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-9, amount=0, currency=USD)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.decorateException(KafkaMessageListenerContainer.java:2719) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2689) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeOnMessage(KafkaMessageListenerContainer.java:2649) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeRecordListener(KafkaMessageListenerContainer.java:2576) ~[spring-kafka-2.8.10.jar:2.8.10]
	... 9 common frames omitted
	Suppressed: org.springframework.kafka.listener.ListenerExecutionFailedException: Restored Stack Trace
		at org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:363) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:92) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:53) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2669) ~[spring-kafka-2.8.10.jar:2.8.10]
Caused by: java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-9, amount=0, currency=USD)
	at com.course.kafka.consumer.InvoiceConsumer.consume(InvoiceConsumer.java:27) ~[classes/:na]
	at jdk.internal.reflect.GeneratedMethodAccessor37.invoke(Unknown Source) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:169) ~[spring-messaging-5.3.23.jar:5.3.23]
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.invoke(InvocableHandlerMethod.java:119) ~[spring-messaging-5.3.23.jar:5.3.23]
	at org.springframework.kafka.listener.adapter.HandlerAdapter.invoke(HandlerAdapter.java:56) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:347) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:92) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:53) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2669) ~[spring-kafka-2.8.10.jar:2.8.10]
	... 11 common frames omitted

[Kafka Core Consumer] 18:58:54.414  INFO --- o.a.k.clients.consumer.KafkaConsumer     : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Seeking to offset 7 for partition t-invoice-0
[Kafka Core Consumer] 18:58:54.415 ERROR --- o.s.k.l.KafkaMessageListenerContainer    : Error handler threw an exception

org.springframework.kafka.KafkaException: Seek to current after exception; nested exception is org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.InvoiceConsumer.consume(java.lang.String) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-9, amount=0, currency=USD); nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-9, amount=0, currency=USD)
	at org.springframework.kafka.listener.SeekUtils.seekOrRecover(SeekUtils.java:208) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.DefaultErrorHandler.handleRemaining(DefaultErrorHandler.java:135) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeErrorHandler(KafkaMessageListenerContainer.java:2706) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeRecordListener(KafkaMessageListenerContainer.java:2587) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeWithRecords(KafkaMessageListenerContainer.java:2456) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeRecordListener(KafkaMessageListenerContainer.java:2334) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeListener(KafkaMessageListenerContainer.java:2005) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeIfHaveRecords(KafkaMessageListenerContainer.java:1375) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.pollAndInvoke(KafkaMessageListenerContainer.java:1366) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.run(KafkaMessageListenerContainer.java:1257) ~[spring-kafka-2.8.10.jar:2.8.10]
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515) ~[na:na]
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264) ~[na:na]
	at java.base/java.lang.Thread.run(Thread.java:834) ~[na:na]
Caused by: org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.InvoiceConsumer.consume(java.lang.String) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-9, amount=0, currency=USD); nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-9, amount=0, currency=USD)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.decorateException(KafkaMessageListenerContainer.java:2719) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2689) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeOnMessage(KafkaMessageListenerContainer.java:2649) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeRecordListener(KafkaMessageListenerContainer.java:2576) ~[spring-kafka-2.8.10.jar:2.8.10]
	... 9 common frames omitted
	Suppressed: org.springframework.kafka.listener.ListenerExecutionFailedException: Restored Stack Trace
		at org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:363) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:92) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:53) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2669) ~[spring-kafka-2.8.10.jar:2.8.10]
Caused by: java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-9, amount=0, currency=USD)
	at com.course.kafka.consumer.InvoiceConsumer.consume(InvoiceConsumer.java:27) ~[classes/:na]
	at jdk.internal.reflect.GeneratedMethodAccessor37.invoke(Unknown Source) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:169) ~[spring-messaging-5.3.23.jar:5.3.23]
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.invoke(InvocableHandlerMethod.java:119) ~[spring-messaging-5.3.23.jar:5.3.23]
	at org.springframework.kafka.listener.adapter.HandlerAdapter.invoke(HandlerAdapter.java:56) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:347) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:92) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:53) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2669) ~[spring-kafka-2.8.10.jar:2.8.10]
	... 11 common frames omitted

[Kafka Core Consumer] 18:58:55.462  INFO --- o.a.k.clients.consumer.KafkaConsumer     : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Seeking to offset 7 for partition t-invoice-0
[Kafka Core Consumer] 18:58:55.464 ERROR --- o.s.k.l.KafkaMessageListenerContainer    : Error handler threw an exception

org.springframework.kafka.KafkaException: Seek to current after exception; nested exception is org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.InvoiceConsumer.consume(java.lang.String) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-9, amount=0, currency=USD); nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-9, amount=0, currency=USD)
	at org.springframework.kafka.listener.SeekUtils.seekOrRecover(SeekUtils.java:208) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.DefaultErrorHandler.handleRemaining(DefaultErrorHandler.java:135) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeErrorHandler(KafkaMessageListenerContainer.java:2706) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeRecordListener(KafkaMessageListenerContainer.java:2587) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeWithRecords(KafkaMessageListenerContainer.java:2456) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeRecordListener(KafkaMessageListenerContainer.java:2334) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeListener(KafkaMessageListenerContainer.java:2005) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeIfHaveRecords(KafkaMessageListenerContainer.java:1375) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.pollAndInvoke(KafkaMessageListenerContainer.java:1366) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.run(KafkaMessageListenerContainer.java:1257) ~[spring-kafka-2.8.10.jar:2.8.10]
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515) ~[na:na]
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264) ~[na:na]
	at java.base/java.lang.Thread.run(Thread.java:834) ~[na:na]
Caused by: org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.InvoiceConsumer.consume(java.lang.String) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-9, amount=0, currency=USD); nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-9, amount=0, currency=USD)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.decorateException(KafkaMessageListenerContainer.java:2719) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2689) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeOnMessage(KafkaMessageListenerContainer.java:2649) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeRecordListener(KafkaMessageListenerContainer.java:2576) ~[spring-kafka-2.8.10.jar:2.8.10]
	... 9 common frames omitted
	Suppressed: org.springframework.kafka.listener.ListenerExecutionFailedException: Restored Stack Trace
		at org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:363) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:92) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:53) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2669) ~[spring-kafka-2.8.10.jar:2.8.10]
Caused by: java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-9, amount=0, currency=USD)
	at com.course.kafka.consumer.InvoiceConsumer.consume(InvoiceConsumer.java:27) ~[classes/:na]
	at jdk.internal.reflect.GeneratedMethodAccessor37.invoke(Unknown Source) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:169) ~[spring-messaging-5.3.23.jar:5.3.23]
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.invoke(InvocableHandlerMethod.java:119) ~[spring-messaging-5.3.23.jar:5.3.23]
	at org.springframework.kafka.listener.adapter.HandlerAdapter.invoke(HandlerAdapter.java:56) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:347) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:92) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:53) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2669) ~[spring-kafka-2.8.10.jar:2.8.10]
	... 11 common frames omitted

[Kafka Core Consumer] 18:58:56.020  INFO --- o.a.k.clients.consumer.KafkaConsumer     : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Seeking to offset 8 for partition t-invoice-0
[Kafka Core Consumer] 18:58:57.083  INFO --- o.a.k.clients.consumer.KafkaConsumer     : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Seeking to offset 8 for partition t-invoice-0
[Kafka Core Consumer] 18:58:57.083 ERROR --- o.s.k.l.KafkaMessageListenerContainer    : Error handler threw an exception

org.springframework.kafka.KafkaException: Seek to current after exception; nested exception is org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.InvoiceConsumer.consume(java.lang.String) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-10, amount=0, currency=USD); nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-10, amount=0, currency=USD)
	at org.springframework.kafka.listener.SeekUtils.seekOrRecover(SeekUtils.java:208) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.DefaultErrorHandler.handleRemaining(DefaultErrorHandler.java:135) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeErrorHandler(KafkaMessageListenerContainer.java:2706) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeRecordListener(KafkaMessageListenerContainer.java:2587) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeWithRecords(KafkaMessageListenerContainer.java:2456) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeRecordListener(KafkaMessageListenerContainer.java:2334) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeListener(KafkaMessageListenerContainer.java:2005) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeIfHaveRecords(KafkaMessageListenerContainer.java:1375) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.pollAndInvoke(KafkaMessageListenerContainer.java:1366) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.run(KafkaMessageListenerContainer.java:1257) ~[spring-kafka-2.8.10.jar:2.8.10]
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515) ~[na:na]
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264) ~[na:na]
	at java.base/java.lang.Thread.run(Thread.java:834) ~[na:na]
Caused by: org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.InvoiceConsumer.consume(java.lang.String) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-10, amount=0, currency=USD); nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-10, amount=0, currency=USD)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.decorateException(KafkaMessageListenerContainer.java:2719) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2689) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeOnMessage(KafkaMessageListenerContainer.java:2649) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeRecordListener(KafkaMessageListenerContainer.java:2576) ~[spring-kafka-2.8.10.jar:2.8.10]
	... 9 common frames omitted
	Suppressed: org.springframework.kafka.listener.ListenerExecutionFailedException: Restored Stack Trace
		at org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:363) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:92) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:53) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2669) ~[spring-kafka-2.8.10.jar:2.8.10]
Caused by: java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-10, amount=0, currency=USD)
	at com.course.kafka.consumer.InvoiceConsumer.consume(InvoiceConsumer.java:27) ~[classes/:na]
	at jdk.internal.reflect.GeneratedMethodAccessor37.invoke(Unknown Source) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:169) ~[spring-messaging-5.3.23.jar:5.3.23]
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.invoke(InvocableHandlerMethod.java:119) ~[spring-messaging-5.3.23.jar:5.3.23]
	at org.springframework.kafka.listener.adapter.HandlerAdapter.invoke(HandlerAdapter.java:56) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:347) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:92) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:53) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2669) ~[spring-kafka-2.8.10.jar:2.8.10]
	... 11 common frames omitted

[Kafka Core Consumer] 18:58:58.127  INFO --- o.a.k.clients.consumer.KafkaConsumer     : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Seeking to offset 8 for partition t-invoice-0
[Kafka Core Consumer] 18:58:58.128 ERROR --- o.s.k.l.KafkaMessageListenerContainer    : Error handler threw an exception

org.springframework.kafka.KafkaException: Seek to current after exception; nested exception is org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.InvoiceConsumer.consume(java.lang.String) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-10, amount=0, currency=USD); nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-10, amount=0, currency=USD)
	at org.springframework.kafka.listener.SeekUtils.seekOrRecover(SeekUtils.java:208) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.DefaultErrorHandler.handleRemaining(DefaultErrorHandler.java:135) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeErrorHandler(KafkaMessageListenerContainer.java:2706) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeRecordListener(KafkaMessageListenerContainer.java:2587) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeWithRecords(KafkaMessageListenerContainer.java:2456) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeRecordListener(KafkaMessageListenerContainer.java:2334) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeListener(KafkaMessageListenerContainer.java:2005) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeIfHaveRecords(KafkaMessageListenerContainer.java:1375) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.pollAndInvoke(KafkaMessageListenerContainer.java:1366) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.run(KafkaMessageListenerContainer.java:1257) ~[spring-kafka-2.8.10.jar:2.8.10]
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515) ~[na:na]
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264) ~[na:na]
	at java.base/java.lang.Thread.run(Thread.java:834) ~[na:na]
Caused by: org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.InvoiceConsumer.consume(java.lang.String) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-10, amount=0, currency=USD); nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-10, amount=0, currency=USD)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.decorateException(KafkaMessageListenerContainer.java:2719) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2689) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeOnMessage(KafkaMessageListenerContainer.java:2649) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeRecordListener(KafkaMessageListenerContainer.java:2576) ~[spring-kafka-2.8.10.jar:2.8.10]
	... 9 common frames omitted
	Suppressed: org.springframework.kafka.listener.ListenerExecutionFailedException: Restored Stack Trace
		at org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:363) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:92) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:53) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2669) ~[spring-kafka-2.8.10.jar:2.8.10]
Caused by: java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-10, amount=0, currency=USD)
	at com.course.kafka.consumer.InvoiceConsumer.consume(InvoiceConsumer.java:27) ~[classes/:na]
	at jdk.internal.reflect.GeneratedMethodAccessor37.invoke(Unknown Source) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:169) ~[spring-messaging-5.3.23.jar:5.3.23]
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.invoke(InvocableHandlerMethod.java:119) ~[spring-messaging-5.3.23.jar:5.3.23]
	at org.springframework.kafka.listener.adapter.HandlerAdapter.invoke(HandlerAdapter.java:56) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:347) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:92) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:53) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2669) ~[spring-kafka-2.8.10.jar:2.8.10]
	... 11 common frames omitted

[Kafka Core Consumer] 18:58:59.196  INFO --- o.a.k.clients.consumer.KafkaConsumer     : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Seeking to offset 8 for partition t-invoice-0
[Kafka Core Consumer] 18:58:59.201 ERROR --- o.s.k.l.KafkaMessageListenerContainer    : Error handler threw an exception

org.springframework.kafka.KafkaException: Seek to current after exception; nested exception is org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.InvoiceConsumer.consume(java.lang.String) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-10, amount=0, currency=USD); nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-10, amount=0, currency=USD)
	at org.springframework.kafka.listener.SeekUtils.seekOrRecover(SeekUtils.java:208) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.DefaultErrorHandler.handleRemaining(DefaultErrorHandler.java:135) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeErrorHandler(KafkaMessageListenerContainer.java:2706) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeRecordListener(KafkaMessageListenerContainer.java:2587) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeWithRecords(KafkaMessageListenerContainer.java:2456) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeRecordListener(KafkaMessageListenerContainer.java:2334) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeListener(KafkaMessageListenerContainer.java:2005) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeIfHaveRecords(KafkaMessageListenerContainer.java:1375) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.pollAndInvoke(KafkaMessageListenerContainer.java:1366) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.run(KafkaMessageListenerContainer.java:1257) ~[spring-kafka-2.8.10.jar:2.8.10]
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515) ~[na:na]
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264) ~[na:na]
	at java.base/java.lang.Thread.run(Thread.java:834) ~[na:na]
Caused by: org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.InvoiceConsumer.consume(java.lang.String) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-10, amount=0, currency=USD); nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-10, amount=0, currency=USD)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.decorateException(KafkaMessageListenerContainer.java:2719) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2689) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeOnMessage(KafkaMessageListenerContainer.java:2649) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeRecordListener(KafkaMessageListenerContainer.java:2576) ~[spring-kafka-2.8.10.jar:2.8.10]
	... 9 common frames omitted
	Suppressed: org.springframework.kafka.listener.ListenerExecutionFailedException: Restored Stack Trace
		at org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:363) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:92) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:53) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2669) ~[spring-kafka-2.8.10.jar:2.8.10]
Caused by: java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-10, amount=0, currency=USD)
	at com.course.kafka.consumer.InvoiceConsumer.consume(InvoiceConsumer.java:27) ~[classes/:na]
	at jdk.internal.reflect.GeneratedMethodAccessor37.invoke(Unknown Source) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:169) ~[spring-messaging-5.3.23.jar:5.3.23]
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.invoke(InvocableHandlerMethod.java:119) ~[spring-messaging-5.3.23.jar:5.3.23]
	at org.springframework.kafka.listener.adapter.HandlerAdapter.invoke(HandlerAdapter.java:56) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:347) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:92) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:53) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2669) ~[spring-kafka-2.8.10.jar:2.8.10]
	... 11 common frames omitted

[Kafka Core Consumer] 18:59:00.269  INFO --- o.a.k.clients.consumer.KafkaConsumer     : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Seeking to offset 8 for partition t-invoice-0
[Kafka Core Consumer] 18:59:00.276 ERROR --- o.s.k.l.KafkaMessageListenerContainer    : Error handler threw an exception

org.springframework.kafka.KafkaException: Seek to current after exception; nested exception is org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.InvoiceConsumer.consume(java.lang.String) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-10, amount=0, currency=USD); nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-10, amount=0, currency=USD)
	at org.springframework.kafka.listener.SeekUtils.seekOrRecover(SeekUtils.java:208) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.DefaultErrorHandler.handleRemaining(DefaultErrorHandler.java:135) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeErrorHandler(KafkaMessageListenerContainer.java:2706) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeRecordListener(KafkaMessageListenerContainer.java:2587) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeWithRecords(KafkaMessageListenerContainer.java:2456) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeRecordListener(KafkaMessageListenerContainer.java:2334) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeListener(KafkaMessageListenerContainer.java:2005) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeIfHaveRecords(KafkaMessageListenerContainer.java:1375) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.pollAndInvoke(KafkaMessageListenerContainer.java:1366) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.run(KafkaMessageListenerContainer.java:1257) ~[spring-kafka-2.8.10.jar:2.8.10]
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515) ~[na:na]
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264) ~[na:na]
	at java.base/java.lang.Thread.run(Thread.java:834) ~[na:na]
Caused by: org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.InvoiceConsumer.consume(java.lang.String) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-10, amount=0, currency=USD); nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-10, amount=0, currency=USD)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.decorateException(KafkaMessageListenerContainer.java:2719) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2689) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeOnMessage(KafkaMessageListenerContainer.java:2649) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeRecordListener(KafkaMessageListenerContainer.java:2576) ~[spring-kafka-2.8.10.jar:2.8.10]
	... 9 common frames omitted
	Suppressed: org.springframework.kafka.listener.ListenerExecutionFailedException: Restored Stack Trace
		at org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:363) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:92) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:53) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2669) ~[spring-kafka-2.8.10.jar:2.8.10]
Caused by: java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-10, amount=0, currency=USD)
	at com.course.kafka.consumer.InvoiceConsumer.consume(InvoiceConsumer.java:27) ~[classes/:na]
	at jdk.internal.reflect.GeneratedMethodAccessor37.invoke(Unknown Source) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:169) ~[spring-messaging-5.3.23.jar:5.3.23]
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.invoke(InvocableHandlerMethod.java:119) ~[spring-messaging-5.3.23.jar:5.3.23]
	at org.springframework.kafka.listener.adapter.HandlerAdapter.invoke(HandlerAdapter.java:56) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:347) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:92) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:53) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2669) ~[spring-kafka-2.8.10.jar:2.8.10]
	... 11 common frames omitted

[Kafka Core Consumer] 18:59:01.327  INFO --- o.a.k.clients.consumer.KafkaConsumer     : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Seeking to offset 8 for partition t-invoice-0
[Kafka Core Consumer] 18:59:01.332 ERROR --- o.s.k.l.KafkaMessageListenerContainer    : Error handler threw an exception

org.springframework.kafka.KafkaException: Seek to current after exception; nested exception is org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.InvoiceConsumer.consume(java.lang.String) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-10, amount=0, currency=USD); nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-10, amount=0, currency=USD)
	at org.springframework.kafka.listener.SeekUtils.seekOrRecover(SeekUtils.java:208) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.DefaultErrorHandler.handleRemaining(DefaultErrorHandler.java:135) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeErrorHandler(KafkaMessageListenerContainer.java:2706) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeRecordListener(KafkaMessageListenerContainer.java:2587) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeWithRecords(KafkaMessageListenerContainer.java:2456) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeRecordListener(KafkaMessageListenerContainer.java:2334) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeListener(KafkaMessageListenerContainer.java:2005) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeIfHaveRecords(KafkaMessageListenerContainer.java:1375) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.pollAndInvoke(KafkaMessageListenerContainer.java:1366) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.run(KafkaMessageListenerContainer.java:1257) ~[spring-kafka-2.8.10.jar:2.8.10]
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515) ~[na:na]
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264) ~[na:na]
	at java.base/java.lang.Thread.run(Thread.java:834) ~[na:na]
Caused by: org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.InvoiceConsumer.consume(java.lang.String) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-10, amount=0, currency=USD); nested exception is java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-10, amount=0, currency=USD)
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.decorateException(KafkaMessageListenerContainer.java:2719) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2689) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeOnMessage(KafkaMessageListenerContainer.java:2649) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeRecordListener(KafkaMessageListenerContainer.java:2576) ~[spring-kafka-2.8.10.jar:2.8.10]
	... 9 common frames omitted
	Suppressed: org.springframework.kafka.listener.ListenerExecutionFailedException: Restored Stack Trace
		at org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:363) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:92) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:53) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2669) ~[spring-kafka-2.8.10.jar:2.8.10]
Caused by: java.lang.IllegalArgumentException: Invalid amount for Invoice(invoiceNumber=INV-10, amount=0, currency=USD)
	at com.course.kafka.consumer.InvoiceConsumer.consume(InvoiceConsumer.java:27) ~[classes/:na]
	at jdk.internal.reflect.GeneratedMethodAccessor37.invoke(Unknown Source) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:169) ~[spring-messaging-5.3.23.jar:5.3.23]
	at org.springframework.messaging.handler.invocation.InvocableHandlerMethod.invoke(InvocableHandlerMethod.java:119) ~[spring-messaging-5.3.23.jar:5.3.23]
	at org.springframework.kafka.listener.adapter.HandlerAdapter.invoke(HandlerAdapter.java:56) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:347) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:92) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:53) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2669) ~[spring-kafka-2.8.10.jar:2.8.10]
	... 11 common frames omitted

```


