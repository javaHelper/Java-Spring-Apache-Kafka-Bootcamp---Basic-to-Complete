# 

```
[Kafka Core Consumer] 12:19:49.940  INFO --- o.a.k.c.c.internals.SubscriptionState    : [Consumer clientId=consumer-default-spring-consumer-2, groupId=default-spring-consumer] Resetting offset for partition t-simple-number-0 to position FetchPosition{offset=0, offsetEpoch=Optional.empty, currentLeader=LeaderAndEpoch{leader=Optional[127.0.0.1:9092 (id: 1 rack: null)], epoch=0}}.
[Kafka Core Consumer] 12:19:49.941  INFO --- o.s.k.l.KafkaMessageListenerContainer    : default-spring-consumer: partitions assigned: [t-simple-number-0]
[Kafka Core Consumer] 12:19:49.944  INFO --- o.a.k.c.c.internals.SubscriptionState    : [Consumer clientId=consumer-default-spring-consumer-1, groupId=default-spring-consumer] Resetting offset for partition t-food-order-0 to position FetchPosition{offset=0, offsetEpoch=Optional.empty, currentLeader=LeaderAndEpoch{leader=Optional[127.0.0.1:9092 (id: 1 rack: null)], epoch=0}}.
[Kafka Core Consumer] 12:19:49.945  INFO --- o.s.k.l.KafkaMessageListenerContainer    : default-spring-consumer: partitions assigned: [t-food-order-0]
[Kafka Core Consumer] 12:19:50.053  INFO --- com.course.kafka.entity.FoodOrder        : Food Order valid: FoodOrder(amount=3, item=Chicken)
[Kafka Core Consumer] 12:19:50.053  INFO --- c.c.kafka.consumer.SimpleNumberConsumer  : Processing simpleNumber : SimpleNumber(number=100)
[Kafka Core Consumer] 12:19:50.054 ERROR --- com.course.kafka.entity.FoodOrder        : Food Order Not valid: FoodOrder(amount=10, item=Fish)
[Kafka Core Consumer] 12:19:50.057  WARN --- c.c.k.e.handler.FoodOrderErrorHandler    : Food order error, sending to elasticsearch : {"amount":10,"item":"Fish"}, because : Listener method 'public void com.course.kafka.consumer.FoodOrderConsumer.consume(java.lang.String) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Food order amount is high
[Kafka Core Consumer] 12:19:50.060 ERROR --- c.c.k.error.handler.GlobalErrorHandler   : ### Global Error Handler for message : {"amount":10,"item":"Fish"}
[Kafka Core Consumer] 12:19:50.059 ERROR --- c.c.k.error.handler.GlobalErrorHandler   : ### Global Error Handler for message : {"number":101}
[Kafka Core Consumer] 12:19:50.065  INFO --- com.course.kafka.entity.FoodOrder        : Food Order valid: FoodOrder(amount=3, item=Pizza)
[Kafka Core Consumer] 12:19:50.065  INFO --- c.c.kafka.consumer.SimpleNumberConsumer  : Processing simpleNumber : SimpleNumber(number=102)
```