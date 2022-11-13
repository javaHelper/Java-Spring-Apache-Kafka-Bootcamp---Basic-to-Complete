# Message Filter

```
[Kafka Core Consumer] 20:03:33.348  INFO --- o.s.k.l.KafkaMessageListenerContainer    : cg-all-location: partitions assigned: [t-location-0]
[Kafka Core Consumer] 20:03:33.348  INFO --- o.s.k.l.KafkaMessageListenerContainer    : cg-far-location: partitions assigned: [t-location-0]
[Kafka Core Consumer] 20:03:33.466  INFO --- c.c.kafka.consumer.CarLocationConsumer   : >> listenAll : CarLocation(carId=car-one, timestamp=1668350002218, distance=1)
[Kafka Core Consumer] 20:03:33.468  INFO --- c.c.kafka.consumer.CarLocationConsumer   : >> listenAll : CarLocation(carId=car-two, timestamp=1668350002218, distance=109)
[Kafka Core Consumer] 20:03:33.468  INFO --- c.c.kafka.consumer.CarLocationConsumer   : == listenFar: CarLocation(carId=car-two, timestamp=1668350002218, distance=109)
[Kafka Core Consumer] 20:03:33.468  INFO --- c.c.kafka.consumer.CarLocationConsumer   : >> listenAll : CarLocation(carId=car-three, timestamp=1668350002218, distance=96)
[Kafka Core Consumer] 20:03:33.469  INFO --- c.c.kafka.consumer.CarLocationConsumer   : >> listenAll : CarLocation(carId=car-one, timestamp=1668350012223, distance=2)
[Kafka Core Consumer] 20:03:33.469  INFO --- c.c.kafka.consumer.CarLocationConsumer   : == listenFar: CarLocation(carId=car-two, timestamp=1668350012223, distance=108)
[Kafka Core Consumer] 20:03:33.475  INFO --- c.c.kafka.consumer.CarLocationConsumer   : >> listenAll : CarLocation(carId=car-two, timestamp=1668350012223, distance=108)
[Kafka Core Consumer] 20:03:33.475  INFO --- c.c.kafka.consumer.CarLocationConsumer   : >> listenAll : CarLocation(carId=car-three, timestamp=1668350012223, distance=97)
[Kafka Core Consumer] 20:03:40.622  INFO --- inMXBeanRegistrar$SpringApplicationAdmin : Application shutdown requested.
```