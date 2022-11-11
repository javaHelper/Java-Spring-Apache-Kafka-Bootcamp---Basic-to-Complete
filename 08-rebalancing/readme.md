# Rebalancing 

Assume topic created uisng only 1 partition and then your producer and consumer both are running. 

```
E:\>kafka-topics.bat --bootstrap-server localhost:9092 --create --topic t_rebalance_new --partitions 1 --replication-factor 1
WARNING: Due to limitations in metric names, topics with a period ('.') or underscore ('_') could collide. To avoid issues it is best to use either, but not both.
Created topic t_rebalance_new.
```

when you add more partitions then Kafka automatically does the rebalancing and data is consumed by two partions

`E:\>kafka-topics.bat --bootstrap-server localhost:9092 --alter --topic t_rebalance_new --partitions 2`

With Spring Boot 2.5.7 versions, its not working, may be need to revisite later.


# add topic
```kafka-topics.sh --bootstrap-server localhost:9092 --create --topic t_rebalance_new --partitions 1 --replication-factor 1```

```kafka-topics.bat --bootstrap-server localhost:9092 --create --topic t_rebalance_new --partitions 1 --replication-factor 1```

# add partition to t_rebalance_new
```kafka-topics.sh --bootstrap-server localhost:9092 --alter --topic t_rebalance_new --partitions 2```

```kafka-topics.bat --bootstrap-server localhost:9092 --alter --topic t_rebalance_new --partitions 2```
