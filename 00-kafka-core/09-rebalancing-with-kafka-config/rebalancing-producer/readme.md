# rebalancing producer 

- Note - Start with the 2 partitions run the producer and consumer and then add the one more parition

````
kafka-topics --bootstrap-server localhost:9092 --alter --topic t-rebalance --partitions 3

kafka-topics --bootstrap-server localhost:9092 --describe --topic t-rebalance     
Topic: t-rebalance	TopicId: WI8qRCP1ShKotdacdyO-iA	PartitionCount: 3	ReplicationFactor: 1	Configs: segment.bytes=1073741824
	Topic: t-rebalance	Partition: 0	Leader: 1	Replicas: 1	Isr: 1	Offline: 
	Topic: t-rebalance	Partition: 1	Leader: 1	Replicas: 1	Isr: 1	Offline: 
	Topic: t-rebalance	Partition: 2	Leader: 1	Replicas: 1	Isr: 1	Offline: 
````

App Logs

````
[Kafka Core Consumer] 16:25:29.049  INFO --- c.c.kafka.consumer.RebalanceConsumer     : Partition : 2, Offset : 25, Message : Counter 77
[Kafka Core Consumer] 16:25:29.049  INFO --- c.c.kafka.consumer.RebalanceConsumer     : Partition : 1, Offset : 24, Message : Counter 61
[Kafka Core Consumer] 16:25:29.049  INFO --- c.c.kafka.consumer.RebalanceConsumer     : Partition : 0, Offset : 181, Message : Counter 78
[Kafka Core Consumer] 16:25:29.049  INFO --- c.c.kafka.consumer.RebalanceConsumer     : Partition : 1, Offset : 25, Message : Counter 65
[Kafka Core Consumer] 16:25:29.049  INFO --- c.c.kafka.consumer.RebalanceConsumer     : Partition : 1, Offset : 26, Message : Counter 68
[Kafka Core Consumer] 16:25:29.049  INFO --- c.c.kafka.consumer.RebalanceConsumer     : Partition : 1, Offset : 27, Message : Counter 73
[Kafka Core Consumer] 16:25:29.076  INFO --- c.c.kafka.consumer.RebalanceConsumer     : Partition : 1, Offset : 28, Message : Counter 79
[Kafka Core Consumer] 16:25:30.097  INFO --- c.c.kafka.consumer.RebalanceConsumer     : Partition : 0, Offset : 182, Message : Counter 80
[Kafka Core Consumer] 16:25:31.105  INFO --- c.c.kafka.consumer.RebalanceConsumer     : Partition : 2, Offset : 26, Message : Counter 81
[Kafka Core Consumer] 16:25:32.073  INFO --- c.c.kafka.consumer.RebalanceConsumer     : Partition : 1, Offset : 29, Message : Counter 82
[Kafka Core Consumer] 16:25:33.133  INFO --- c.c.kafka.consumer.RebalanceConsumer     : Partition : 0, Offset : 183, Message : Counter 83
[Kafka Core Consumer] 16:25:34.089  INFO --- c.c.kafka.consumer.RebalanceConsumer     : Partition : 2, Offset : 27, Message : Counter 84
[Kafka Core Consumer] 16:25:35.115  INFO --- c.c.kafka.consumer.RebalanceConsumer     : Partition : 0, Offset : 184, Message : Counter 85
[Kafka Core Consumer] 16:25:36.108  INFO --- c.c.kafka.consumer.RebalanceConsumer     : Partition : 2, Offset : 28, Message : Counter 86
[Kafka Core Consumer] 16:25:37.090  INFO --- c.c.kafka.consumer.RebalanceConsumer     : Partition : 1, Offset : 30, Message : Counter 87
[Kafka Core Consumer] 16:25:37.102  INFO --- o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Revoke previously assigned partitions t-rebalance-0
[Kafka Core Consumer] 16:25:37.102  INFO --- o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-default-spring-consumer-3, groupId=default-spring-consumer] Revoke previously assigned partitions t-rebalance-2
[Kafka Core Consumer] 16:25:37.103  INFO --- o.s.k.l.KafkaMessageListenerContainer    : default-spring-consumer: partitions revoked: [t-rebalance-0]
[Kafka Core Consumer] 16:25:37.103  INFO --- o.s.k.l.KafkaMessageListenerContainer    : default-spring-consumer: partitions revoked: [t-rebalance-2]
[Kafka Core Consumer] 16:25:37.103  INFO --- o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-default-spring-consumer-2, groupId=default-spring-consumer] Revoke previously assigned partitions t-rebalance-1
[Kafka Core Consumer] 16:25:37.103  INFO --- o.s.k.l.KafkaMessageListenerContainer    : default-spring-consumer: partitions revoked: [t-rebalance-1]
````