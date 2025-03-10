# Hello World 

```
docker-setup % docker exec -it kafka bash
I have no name!@612c59413dd9:/$ kafka-topics.sh --bootstrap-server localhost:9092 --create --topic t-hello --partitions 1
Created topic t-hello.
I have no name!@612c59413dd9:/$ kafka-topics.sh --bootstrap-server localhost:9092 --list                                 
t-hello
I have no name!@612c59413dd9:/$ kafka-topics.sh --bootstrap-server localhost:9092 --describe
Topic: t-hello	TopicId: 5egcFED_R7uocC7_ypQNGQ	PartitionCount: 1	ReplicationFactor: 1	Configs: segment.bytes=1073741824
	Topic: t-hello	Partition: 0	Leader: 1	Replicas: 1	Isr: 1
I have no name!@612c59413dd9:/$ 

```
