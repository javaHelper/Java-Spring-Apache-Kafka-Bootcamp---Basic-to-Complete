#

kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-payment-request 

```
Kafka Core Consumer] 21:34:18.265  INFO --- o.a.k.c.c.internals.SubscriptionState    : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Resetting offset for partition t-payment-request-0 to position FetchPosition{offset=0, offsetEpoch=Optional.empty, currentLeader=LeaderAndEpoch{leader=Optional[127.0.0.1:9092 (id: 1 rack: null)], epoch=0}}.
[Kafka Core Consumer] 21:34:18.266  INFO --- o.s.k.l.KafkaMessageListenerContainer    : default-spring-consumer: partitions assigned: [t-payment-request-0]
[Kafka Core Consumer] 21:34:18.365  INFO --- c.c.k.consumer.PaymentRequestConsumer    : Processing PaymentRequest(paymentNumber=Pay-Alpha, amount=551, currency=USD, notes=Notes alpha, transactionType=Budget reserve)
[Kafka Core Consumer] 21:34:18.370  INFO --- c.c.k.consumer.PaymentRequestConsumer    : Processing PaymentRequest(paymentNumber=Pay-Alpha, amount=551, currency=USD, notes=Notes alpha, transactionType=Approval workflow)
[Kafka Core Consumer] 21:34:18.370  INFO --- c.c.k.consumer.PaymentRequestConsumer    : Processing PaymentRequest(paymentNumber=Pay-Alpha, amount=551, currency=USD, notes=Notes alpha, transactionType=Push Notification)
[Kafka Core Consumer] 21:34:18.371  INFO --- c.c.k.consumer.PaymentRequestConsumer    : Processing PaymentRequest(paymentNumber=Pay-Beta, amount=551, currency=USD, notes=Notes Beta, transactionType=Budget reserve)
[Kafka Core Consumer] 21:34:18.371  INFO --- c.c.k.consumer.PaymentRequestConsumer    : Processing PaymentRequest(paymentNumber=Pay-Beta, amount=551, currency=USD, notes=Notes Beta, transactionType=Approval workflow)
[Kafka Core Consumer] 21:34:18.372  INFO --- c.c.k.consumer.PaymentRequestConsumer    : Processing PaymentRequest(paymentNumber=Pay-Beta, amount=551, currency=USD, notes=Notes Beta, transactionType=Push Notification)

```