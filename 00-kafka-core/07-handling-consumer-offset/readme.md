#


````
kafka-consumer-groups --bootstrap-server localhost:9092 --group counter-group-fast --describe 

Consumer group 'counter-group-fast' has no active members.

GROUP              TOPIC           PARTITION  CURRENT-OFFSET  LOG-END-OFFSET  LAG             CONSUMER-ID     HOST            CLIENT-ID
counter-group-fast t-counter       0          100             100             0               -               -               -
@Prateeks-MacBook-Pro kafka-learning % kafka-consumer-groups --bootstrap-server localhost:9092 --group counter-group-slow --describe 

Consumer group 'counter-group-slow' has no active members.
@Prateeks-MacBook-Pro kafka-learning % 
````

````
kafka-console-consumer --from-beginning --bootstrap-server localhost:9092 --property print.key=false --property print.value=false --topic t-counter --timeout-ms 5000 | tail -n 10 | grep "Processed a total of"
[2025-03-11 10:30:25,624] ERROR Error processing message, terminating consumer process:  (kafka.tools.ConsoleConsumer$)
org.apache.kafka.common.errors.TimeoutException
[2025-03-11 10:30:25,724] ERROR [Consumer clientId=console-consumer, groupId=console-consumer-97979] Unable to find FetchSessionHandler for node 1. Ignoring fetch response. (org.apache.kafka.clients.consumer.internals.AbstractFetch)
Processed a total of 100 messages

````


````
  kafka:
    listener:
      ack-mode: RECORD
````

This means offset will be automatically committed when message has been processed   

````

kafka-consumer-groups --bootstrap-server localhost:9092 --group counter-group-slow --describe 

GROUP              TOPIC           PARTITION  CURRENT-OFFSET  LOG-END-OFFSET  LAG             CONSUMER-ID                                                        HOST            CLIENT-ID
counter-group-slow t-counter       0          11              100             89              consumer-counter-group-slow-2-d3eacb77-fddb-43a1-afde-63608ac812ab /172.23.0.1     consumer-counter-group-slow-2
prateekashtikar@Prateeks-MacBook-Pro kafka-learning % 
````

````
Slow : Data 0
Slow : Data 1
Slow : Data 2
Slow : Data 3
Slow : Data 4
Slow : Data 5
Slow : Data 6
Slow : Data 7
Slow : Data 8
Slow : Data 9
Slow : Data 10
````