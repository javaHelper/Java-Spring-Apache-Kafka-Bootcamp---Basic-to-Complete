# Multiple Consumers for each topic

# In this case, one consumer processing data from 3 partitions.
This shows only 1 consumer (ntainer#0-0-C-1) is running.

```
kafka-topics.bat --bootstrap-server localhost:9092 --create --topic t_multi_partitions --partitions 3 --replication-factor 1
WARNING: Due to limitations in metric names, topics with a period ('.') or underscore ('_') could collide. To avoid issues it is best to use either, but not both.
Created topic t_multi_partitions.

kafka-topics.bat --bootstrap-server localhost:9092 --describe --topic t_multi_partitions
Topic: t_multi_partitions       PartitionCount: 3       ReplicationFactor: 1    Configs: segment.bytes=1073741824
        Topic: t_multi_partitions       Partition: 0    Leader: 0       Replicas: 0     Isr: 0
        Topic: t_multi_partitions       Partition: 1    Leader: 0       Replicas: 0     Isr: 0
        Topic: t_multi_partitions       Partition: 2    Leader: 0       Replicas: 0     Isr: 0
```


![image](https://user-images.githubusercontent.com/54174687/142464009-77a48a34-52e4-46f5-8721-2ab7a9109709.png)

# By Setting 

`@KafkaListener(topics = "t_multi_partitions", concurrency = "2")` - concurrency = "2" that many consumers will be created.

Now one partition is assigned to handle 2 partitions

![image](https://user-images.githubusercontent.com/54174687/142465440-4239c156-1725-49aa-88c2-a2ec259b112f.png)


# By Setting 

`@KafkaListener(topics = "t_multi_partitions", concurrency = "3")` - concurrency = "3" that many consumers will be created.

Now 3 consumers are assigned to 3 paritions, 1 consumer is assigned to unique partition

![image](https://user-images.githubusercontent.com/54174687/142466102-013b5bad-5df3-4020-822f-32099449fc8b.png)


# By Setting 

`@KafkaListener(topics = "t_multi_partitions", concurrency = "4")` - concurrency = "4" that many consumers will be created.

Now 3 consumers are working and 1 is idle, hence no. of partions = no. of consumers

![image](https://user-images.githubusercontent.com/54174687/142466487-1ed3e8e4-b895-41b9-bea4-bc1d9fd4be68.png)

# Alter the topic

```
>kafka-topics.bat --bootstrap-server localhost:9092 --alter --topic t_multi_partitions --partitions 4

>kafka-topics.bat --bootstrap-server localhost:9092 --describe --topic t_multi_partitions
Topic: t_multi_partitions       PartitionCount: 4       ReplicationFactor: 1    Configs: segment.bytes=1073741824
        Topic: t_multi_partitions       Partition: 0    Leader: 0       Replicas: 0     Isr: 0
        Topic: t_multi_partitions       Partition: 1    Leader: 0       Replicas: 0     Isr: 0
        Topic: t_multi_partitions       Partition: 2    Leader: 0       Replicas: 0     Isr: 0
        Topic: t_multi_partitions       Partition: 3    Leader: 0       Replicas: 0     Isr: 0
```

Now, you can see all four consumers are currently working.

![image](https://user-images.githubusercontent.com/54174687/142467166-7948ba26-9dfa-487f-bacd-7655f7d8070c.png)
