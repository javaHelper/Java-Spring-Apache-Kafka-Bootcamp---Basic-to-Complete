#

- start the main app, start the console consumer and then run the producer

```sh
kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092 --topic output-topic --key-deserializer org.apache.kafka.common.serialization.StringDeserializer --value-deserializer org.apache.kafka.common.serialization.LongDeserializer --property print.key=true --property key.separator="-"
americas-101
europe-109
asia-124
```