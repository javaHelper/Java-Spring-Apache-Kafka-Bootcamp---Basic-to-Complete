#

```sh
kafka-topics --bootstrap-server localhost:9092 --create --partitions 2 --replication-factor 1 --topic t-image
```

```
[Kafka Core Consumer] 15:51:38.104  INFO --- o.a.k.c.c.internals.SubscriptionState    : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Resetting offset for partition t-image-0 to position FetchPosition{offset=0, offsetEpoch=Optional.empty, currentLeader=LeaderAndEpoch{leader=Optional[127.0.0.1:9092 (id: 1 rack: null)], epoch=0}}.
[Kafka Core Consumer] 15:51:38.105  INFO --- o.s.k.l.KafkaMessageListenerContainer    : default-spring-consumer: partitions assigned: [t-image-1]
[Kafka Core Consumer] 15:51:38.105  INFO --- o.s.k.l.KafkaMessageListenerContainer    : default-spring-consumer: partitions assigned: [t-image-0]
[Kafka Core Consumer] 15:51:38.607  INFO --- com.course.kafka.consumer.ImageConsumer  : Processing on partition 1 for image Image(name=image-4, size=6651, type=gif)
[Kafka Core Consumer] 15:51:38.607  INFO --- com.course.kafka.consumer.ImageConsumer  : Processing on partition 0 for image Image(name=image-1, size=8645, type=jpg)
[Kafka Core Consumer] 15:51:38.608  WARN --- com.course.kafka.consumer.ImageConsumer  : Throwing exception on partition 0 for image Image(name=image-2, size=5191, type=svg)
[Kafka Core Consumer] 15:51:38.608  INFO --- com.course.kafka.consumer.ImageConsumer  : Processing on partition 1 for image Image(name=image-5, size=155, type=bmp)
[Kafka Core Consumer] 15:51:38.608  INFO --- com.course.kafka.consumer.ImageConsumer  : Processing on partition 1 for image Image(name=image-6, size=859, type=tiff)
[Kafka Core Consumer] 15:51:48.775  INFO --- o.a.k.clients.consumer.KafkaConsumer     : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Seeking to offset 1 for partition t-image-0
[Kafka Core Consumer] 15:51:48.793 ERROR --- o.s.k.l.KafkaMessageListenerContainer    : Error handler threw an exception

org.springframework.kafka.KafkaException: Seek to current after exception; nested exception is org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.ImageConsumer.consume(org.apache.kafka.clients.consumer.ConsumerRecord<java.lang.String, java.lang.String>) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Simulate API call failed; nested exception is java.lang.IllegalArgumentException: Simulate API call failed
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
Caused by: org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.ImageConsumer.consume(org.apache.kafka.clients.consumer.ConsumerRecord<java.lang.String, java.lang.String>) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Simulate API call failed; nested exception is java.lang.IllegalArgumentException: Simulate API call failed
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
Caused by: java.lang.IllegalArgumentException: Simulate API call failed
	at com.course.kafka.consumer.ImageConsumer.consume(ImageConsumer.java:30) ~[classes/:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
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

[Kafka Core Consumer] 15:51:48.819  WARN --- com.course.kafka.consumer.ImageConsumer  : Throwing exception on partition 0 for image Image(name=image-2, size=5191, type=svg)
[Kafka Core Consumer] 15:51:58.872  INFO --- o.a.k.clients.consumer.KafkaConsumer     : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Seeking to offset 1 for partition t-image-0
[Kafka Core Consumer] 15:51:58.875 ERROR --- o.s.k.l.KafkaMessageListenerContainer    : Error handler threw an exception

org.springframework.kafka.KafkaException: Seek to current after exception; nested exception is org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.ImageConsumer.consume(org.apache.kafka.clients.consumer.ConsumerRecord<java.lang.String, java.lang.String>) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Simulate API call failed; nested exception is java.lang.IllegalArgumentException: Simulate API call failed
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
Caused by: org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.ImageConsumer.consume(org.apache.kafka.clients.consumer.ConsumerRecord<java.lang.String, java.lang.String>) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Simulate API call failed; nested exception is java.lang.IllegalArgumentException: Simulate API call failed
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
Caused by: java.lang.IllegalArgumentException: Simulate API call failed
	at com.course.kafka.consumer.ImageConsumer.consume(ImageConsumer.java:30) ~[classes/:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
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

[Kafka Core Consumer] 15:51:58.925  WARN --- com.course.kafka.consumer.ImageConsumer  : Throwing exception on partition 0 for image Image(name=image-2, size=5191, type=svg)
[Kafka Core Consumer] 15:52:08.979  INFO --- o.a.k.clients.consumer.KafkaConsumer     : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Seeking to offset 1 for partition t-image-0
[Kafka Core Consumer] 15:52:08.986 ERROR --- o.s.k.l.KafkaMessageListenerContainer    : Error handler threw an exception

org.springframework.kafka.KafkaException: Seek to current after exception; nested exception is org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.ImageConsumer.consume(org.apache.kafka.clients.consumer.ConsumerRecord<java.lang.String, java.lang.String>) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Simulate API call failed; nested exception is java.lang.IllegalArgumentException: Simulate API call failed
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
Caused by: org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.ImageConsumer.consume(org.apache.kafka.clients.consumer.ConsumerRecord<java.lang.String, java.lang.String>) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Simulate API call failed; nested exception is java.lang.IllegalArgumentException: Simulate API call failed
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
Caused by: java.lang.IllegalArgumentException: Simulate API call failed
	at com.course.kafka.consumer.ImageConsumer.consume(ImageConsumer.java:30) ~[classes/:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
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

[Kafka Core Consumer] 15:52:09.028  WARN --- com.course.kafka.consumer.ImageConsumer  : Throwing exception on partition 0 for image Image(name=image-2, size=5191, type=svg)
[Kafka Core Consumer] 15:52:09.032 ERROR --- o.s.kafka.listener.DefaultErrorHandler   : Backoff FixedBackOff{interval=10000, currentAttempts=4, maxAttempts=3} exhausted for t-image-0@1

org.springframework.kafka.listener.ListenerExecutionFailedException: Listener method 'public void com.course.kafka.consumer.ImageConsumer.consume(org.apache.kafka.clients.consumer.ConsumerRecord<java.lang.String, java.lang.String>) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Simulate API call failed; nested exception is java.lang.IllegalArgumentException: Simulate API call failed
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.decorateException(KafkaMessageListenerContainer.java:2719) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2689) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeOnMessage(KafkaMessageListenerContainer.java:2649) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeRecordListener(KafkaMessageListenerContainer.java:2576) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeWithRecords(KafkaMessageListenerContainer.java:2456) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeRecordListener(KafkaMessageListenerContainer.java:2334) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeListener(KafkaMessageListenerContainer.java:2005) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.invokeIfHaveRecords(KafkaMessageListenerContainer.java:1375) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.pollAndInvoke(KafkaMessageListenerContainer.java:1366) ~[spring-kafka-2.8.10.jar:2.8.10]
	at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.run(KafkaMessageListenerContainer.java:1257) ~[spring-kafka-2.8.10.jar:2.8.10]
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515) ~[na:na]
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264) ~[na:na]
	at java.base/java.lang.Thread.run(Thread.java:834) ~[na:na]
	Suppressed: org.springframework.kafka.listener.ListenerExecutionFailedException: Restored Stack Trace
		at org.springframework.kafka.listener.adapter.MessagingMessageListenerAdapter.invokeHandler(MessagingMessageListenerAdapter.java:363) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:92) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.adapter.RecordMessagingMessageListenerAdapter.onMessage(RecordMessagingMessageListenerAdapter.java:53) ~[spring-kafka-2.8.10.jar:2.8.10]
		at org.springframework.kafka.listener.KafkaMessageListenerContainer$ListenerConsumer.doInvokeOnMessage(KafkaMessageListenerContainer.java:2669) ~[spring-kafka-2.8.10.jar:2.8.10]
Caused by: java.lang.IllegalArgumentException: Simulate API call failed
	at com.course.kafka.consumer.ImageConsumer.consume(ImageConsumer.java:30) ~[classes/:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
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

[Kafka Core Consumer] 15:52:09.035  INFO --- o.a.k.clients.consumer.KafkaConsumer     : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Seeking to offset 2 for partition t-image-0
[Kafka Core Consumer] 15:52:09.557  INFO --- com.course.kafka.consumer.ImageConsumer  : Processing on partition 0 for image Image(name=image-3, size=6360, type=png)

```