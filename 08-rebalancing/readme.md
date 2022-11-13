# Rebalancing 

Assume topic created uisng only 1 partition and then your producer and consumer both are running. 

```
kafka-topics --bootstrap-server localhost:9092 --create --topic t-rebalance --partitions 1 --replication-factor 1
WARNING: Due to limitations in metric names, topics with a period ('.') or underscore ('_') could collide. To avoid issues it is best to use either, but not both.
Created topic t_rebalance_new.
```

when you add more partitions then Kafka automatically does the rebalancing and data is consumed by two partions

```
kafka-topics --bootstrap-server localhost:9092 --alter --topic t-rebalance --partitions 2

kafka-topics --bootstrap-server localhost:9092 --describe --topic t-rebalance
Topic: t-rebalance	TopicId: WI8qRCP1ShKotdacdyO-iA	PartitionCount: 2	ReplicationFactor: 1	Configs: segment.bytes=1073741824
Topic: t-rebalance	Partition: 0	Leader: 1	Replicas: 1	Isr: 1	Offline:
Topic: t-rebalance	Partition: 1	Leader: 1	Replicas: 1	Isr: 1	Offline:
```

- App logs

````
[Kafka Core Consumer] 15:47:01.706  INFO --- c.c.kafka.consumer.RebalanceConsumer     : Partition: 0, offset: 149, Message :Counter 150
[Kafka Core Consumer] 15:47:03.727  INFO --- c.c.kafka.consumer.RebalanceConsumer     : Partition: 0, offset: 150, Message :Counter 151
[Kafka Core Consumer] 15:47:04.341  INFO --- o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-default-spring-consumer-2, groupId=default-spring-consumer] Request joining group due to: cached metadata has changed from (version2: {t-rebalance=1}) at the beginning of the rebalance to (version3: {t-rebalance=2})
[Kafka Core Consumer] 15:47:04.346  INFO --- o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-default-spring-consumer-2, groupId=default-spring-consumer] Revoke previously assigned partitions 
[Kafka Core Consumer] 15:47:04.347  INFO --- o.s.k.l.KafkaMessageListenerContainer    : default-spring-consumer: partitions revoked: []
[Kafka Core Consumer] 15:47:04.348  INFO --- o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-default-spring-consumer-2, groupId=default-spring-consumer] (Re-)joining group
[Kafka Core Consumer] 15:47:05.089  INFO --- o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-default-spring-consumer-3, groupId=default-spring-consumer] Request joining group due to: group is already rebalancing
[Kafka Core Consumer] 15:47:05.090  INFO --- o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-default-spring-consumer-3, groupId=default-spring-consumer] Revoke previously assigned partitions 
[Kafka Core Consumer] 15:47:05.091  INFO --- o.s.k.l.KafkaMessageListenerContainer    : default-spring-consumer: partitions revoked: []
[Kafka Core Consumer] 15:47:05.089  INFO --- o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Request joining group due to: group is already rebalancing
[Kafka Core Consumer] 15:47:05.092  INFO --- o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-default-spring-consumer-3, groupId=default-spring-consumer] (Re-)joining group
[Kafka Core Consumer] 15:47:05.096  INFO --- o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Revoke previously assigned partitions t-rebalance-0
[Kafka Core Consumer] 15:47:05.097  INFO --- o.s.k.l.KafkaMessageListenerContainer    : default-spring-consumer: partitions revoked: [t-rebalance-0]
[Kafka Core Consumer] 15:47:05.098  INFO --- o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] (Re-)joining group
[Kafka Core Consumer] 15:47:05.118  INFO --- o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Successfully joined group with generation Generation{generationId=2, memberId='consumer-default-spring-consumer-1-80ddf201-99b1-4cc0-a6f2-69c638a52c29', protocol='range'}
[Kafka Core Consumer] 15:47:05.118  INFO --- o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-default-spring-consumer-2, groupId=default-spring-consumer] Successfully joined group with generation Generation{generationId=2, memberId='consumer-default-spring-consumer-2-d2b659fa-511a-47e8-b451-fdb8ba3c916e', protocol='range'}
[Kafka Core Consumer] 15:47:05.118  INFO --- o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-default-spring-consumer-3, groupId=default-spring-consumer] Successfully joined group with generation Generation{generationId=2, memberId='consumer-default-spring-consumer-3-e5f08ca0-3c73-49d0-9e0f-49737b3dfc7e', protocol='range'}
[Kafka Core Consumer] 15:47:05.122  INFO --- o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-default-spring-consumer-2, groupId=default-spring-consumer] Finished assignment for group at generation 2: {consumer-default-spring-consumer-2-d2b659fa-511a-47e8-b451-fdb8ba3c916e=Assignment(partitions=[t-rebalance-1]), consumer-default-spring-consumer-3-e5f08ca0-3c73-49d0-9e0f-49737b3dfc7e=Assignment(partitions=[]), consumer-default-spring-consumer-1-80ddf201-99b1-4cc0-a6f2-69c638a52c29=Assignment(partitions=[t-rebalance-0])}
[Kafka Core Consumer] 15:47:05.146  INFO --- o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-default-spring-consumer-3, groupId=default-spring-consumer] Successfully synced group in generation Generation{generationId=2, memberId='consumer-default-spring-consumer-3-e5f08ca0-3c73-49d0-9e0f-49737b3dfc7e', protocol='range'}
[Kafka Core Consumer] 15:47:05.146  INFO --- o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-default-spring-consumer-2, groupId=default-spring-consumer] Successfully synced group in generation Generation{generationId=2, memberId='consumer-default-spring-consumer-2-d2b659fa-511a-47e8-b451-fdb8ba3c916e', protocol='range'}
[Kafka Core Consumer] 15:47:05.146  INFO --- o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Successfully synced group in generation Generation{generationId=2, memberId='consumer-default-spring-consumer-1-80ddf201-99b1-4cc0-a6f2-69c638a52c29', protocol='range'}
[Kafka Core Consumer] 15:47:05.147  INFO --- o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Notifying assignor about the new Assignment(partitions=[t-rebalance-0])
[Kafka Core Consumer] 15:47:05.147  INFO --- o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-default-spring-consumer-3, groupId=default-spring-consumer] Notifying assignor about the new Assignment(partitions=[])
[Kafka Core Consumer] 15:47:05.147  INFO --- o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-default-spring-consumer-2, groupId=default-spring-consumer] Notifying assignor about the new Assignment(partitions=[t-rebalance-1])
[Kafka Core Consumer] 15:47:05.147  INFO --- o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-default-spring-consumer-3, groupId=default-spring-consumer] Adding newly assigned partitions: 
[Kafka Core Consumer] 15:47:05.148  INFO --- o.s.k.l.KafkaMessageListenerContainer    : default-spring-consumer: partitions assigned: []
[Kafka Core Consumer] 15:47:05.148  INFO --- o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-default-spring-consumer-2, groupId=default-spring-consumer] Adding newly assigned partitions: t-rebalance-1
[Kafka Core Consumer] 15:47:05.148  INFO --- o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Adding newly assigned partitions: t-rebalance-0
[Kafka Core Consumer] 15:47:05.161  INFO --- o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-default-spring-consumer-2, groupId=default-spring-consumer] Found no committed offset for partition t-rebalance-1
[Kafka Core Consumer] 15:47:05.166  INFO --- o.a.k.c.c.internals.ConsumerCoordinator  : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Setting offset for partition t-rebalance-0 to the committed offset FetchPosition{offset=151, offsetEpoch=Optional.empty, currentLeader=LeaderAndEpoch{leader=Optional[127.0.0.1:9092 (id: 1 rack: null)], epoch=0}}
[Kafka Core Consumer] 15:47:05.167  INFO --- o.s.k.l.KafkaMessageListenerContainer    : default-spring-consumer: partitions assigned: [t-rebalance-0]
[Kafka Core Consumer] 15:47:05.170  INFO --- o.a.k.c.c.internals.SubscriptionState    : [Consumer clientId=consumer-default-spring-consumer-2, groupId=default-spring-consumer] Resetting offset for partition t-rebalance-1 to position FetchPosition{offset=0, offsetEpoch=Optional.empty, currentLeader=LeaderAndEpoch{leader=Optional[127.0.0.1:9092 (id: 1 rack: null)], epoch=0}}.
[Kafka Core Consumer] 15:47:05.170  INFO --- o.s.k.l.KafkaMessageListenerContainer    : default-spring-consumer: partitions assigned: [t-rebalance-1]
[Kafka Core Consumer] 15:47:05.741  INFO --- c.c.kafka.consumer.RebalanceConsumer     : Partition: 1, offset: 0, Message :Counter 152
[Kafka Core Consumer] 15:47:07.744  INFO --- c.c.kafka.consumer.RebalanceConsumer     : Partition: 0, offset: 151, Message :Counter 153
[Kafka Core Consumer] 15:47:09.746  INFO --- c.c.kafka.consumer.RebalanceConsumer     : Partition: 1, offset: 1, Message :Counter 154
[Kafka Core Consumer] 15:47:11.699  INFO --- c.c.kafka.consumer.RebalanceConsumer     : Partition: 0, offset: 152, Message :Counter 155
[Kafka Core Consumer] 15:47:13.716  INFO --- c.c.kafka.consumer.RebalanceConsumer     : Partition: 1, offset: 2, Message :Counter 156
[Kafka Core Consumer] 15:47:15.716  INFO --- c.c.kafka.consumer.RebalanceConsumer     : Partition: 0, offset: 153, Message :Counter 157
[Kafka Core Consumer] 15:47:17.705  INFO --- c.c.kafka.consumer.RebalanceConsumer     : Partition: 1, offset: 3, Message :Counter 158
````