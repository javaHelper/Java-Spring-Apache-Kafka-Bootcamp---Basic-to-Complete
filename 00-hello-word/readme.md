# Hello World 

```
docker ps      
CONTAINER ID   IMAGE                  COMMAND                  CREATED          STATUS          PORTS                    NAMES
adba4cef923e   bitnami/kafka:latest   "/opt/bitnami/script…"   12 minutes ago   Up 12 minutes   0.0.0.0:9092->9092/tcp   spring-kafka-scripts-kafka-1-1
prateekashtikar@Prateeks-MBP docker-setup % docker exec -it adba4cef923e bash
I have no name!@adba4cef923e:/$ 

```

```
kafka-topics.sh --bootstrap-server localhost:9092 --create --topic t-fixedrate --partitions 1
```
