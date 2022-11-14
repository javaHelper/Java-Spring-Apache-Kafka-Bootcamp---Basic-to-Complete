# 

```
[Kafka Core Consumer] 11:14:35.338  INFO --- o.a.k.c.c.internals.SubscriptionState    : [Consumer clientId=consumer-default-spring-consumer-2, groupId=default-spring-consumer] Resetting offset for partition t-simple-number-0 to position FetchPosition{offset=0, offsetEpoch=Optional.empty, currentLeader=LeaderAndEpoch{leader=Optional[127.0.0.1:9092 (id: 1 rack: null)], epoch=0}}.
[Kafka Core Consumer] 11:14:35.338  INFO --- o.s.k.l.KafkaMessageListenerContainer    : default-spring-consumer: partitions assigned: [t-simple-number-0]
[Kafka Core Consumer] 11:14:35.434  INFO --- c.c.kafka.consumer.SimpleNumberConsumer  : Processing simpleNumber : SimpleNumber(number=0)
[Kafka Core Consumer] 11:14:35.434  INFO --- com.course.kafka.entity.FoodOrder        : Food Order valid: FoodOrder(amount=3, item=Chicken)
[Kafka Core Consumer] 11:14:35.435  INFO --- c.c.kafka.consumer.SimpleNumberConsumer  : Processing simpleNumber : SimpleNumber(number=0)
[Kafka Core Consumer] 11:14:35.435 ERROR --- com.course.kafka.entity.FoodOrder        : Food Order Not valid: FoodOrder(amount=10, item=Fish)
[Kafka Core Consumer] 11:14:35.435  INFO --- c.c.kafka.consumer.SimpleNumberConsumer  : Processing simpleNumber : SimpleNumber(number=0)
[Kafka Core Consumer] 11:14:35.438  WARN --- c.c.k.e.handler.FoodOrderErrorHandler    : Food order error, sending to elasticsearch : {"amount":10,"item":"Fish"}, because : Listener method 'public void com.course.kafka.consumer.FoodOrderConsumer.consume(java.lang.String) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Food order amount is high
[Kafka Core Consumer] 11:14:35.445 ERROR --- c.c.k.error.handler.GlobalErrorHandler   : ### Global Error Handler for message : {"amount":10,"item":"Fish"}
[Kafka Core Consumer] 11:14:35.445  INFO --- com.course.kafka.entity.FoodOrder        : Food Order valid: FoodOrder(amount=3, item=Pizza)

```