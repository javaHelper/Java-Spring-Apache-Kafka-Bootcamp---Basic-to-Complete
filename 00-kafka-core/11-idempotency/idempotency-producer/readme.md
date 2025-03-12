#

```
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-purchase-request
```


- App Console

```
Kafka Core Consumer] 20:49:42.582  INFO --- o.a.k.c.c.internals.SubscriptionState    : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Resetting offset for partition t-purchase-request-0 to position FetchPosition{offset=0, offsetEpoch=Optional.empty, currentLeader=LeaderAndEpoch{leader=Optional[127.0.0.1:9092 (id: 1 rack: null)], epoch=0}}.
[Kafka Core Consumer] 20:49:42.583  INFO --- o.s.k.l.KafkaMessageListenerContainer    : default-spring-consumer: partitions assigned: [t-purchase-request-0]
[Kafka Core Consumer] 20:49:42.681  INFO --- c.c.k.consumer.PurchaseRequestConsumer   : Processing PurchaseRequest(id=5551, prNumber=PR-First, amount=991, currency=USD)
[Kafka Core Consumer] 20:49:42.685  INFO --- c.c.k.consumer.PurchaseRequestConsumer   : Processing PurchaseRequest(id=5552, prNumber=PR-Second, amount=992, currency=USD)
[Kafka Core Consumer] 20:49:42.686  INFO --- c.c.k.consumer.PurchaseRequestConsumer   : Processing PurchaseRequest(id=5553, prNumber=PR-Third, amount=993, currency=USD)

```