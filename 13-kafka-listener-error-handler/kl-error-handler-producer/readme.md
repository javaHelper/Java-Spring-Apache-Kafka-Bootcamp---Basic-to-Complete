#

```
kafka-topics --bootstrap-server localhost:9092 --create --partitions 1 --replication-factor 1 --topic t-food-order    
```


```
[Kafka Core Consumer] 21:55:14.430  INFO --- o.s.k.l.KafkaMessageListenerContainer    : default-spring-consumer: partitions assigned: [t-food-order-0]
[Kafka Core Consumer] 21:55:14.522  INFO --- c.c.kafka.consumer.FoodOrderConsumer     : Processing food order : FoodOrder(amount=3, item=Chicken)
[Kafka Core Consumer] 21:55:14.525  WARN --- c.c.k.e.handler.FoodOrderErrorHandler    : Food order error, sending to elasticsearch : {"amount":10,"item":"Fish"}, because : Listener method 'public void com.course.kafka.consumer.FoodOrderConsumer.consume(java.lang.String) throws com.fasterxml.jackson.databind.JsonMappingException,com.fasterxml.jackson.core.JsonProcessingException' threw exception; nested exception is java.lang.IllegalArgumentException: Order amount is too many : 10
[Kafka Core Consumer] 21:55:14.526  INFO --- c.c.kafka.consumer.FoodOrderConsumer     : Processing food order : FoodOrder(amount=3, item=Pizza)

```