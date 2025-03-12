kafka-topics --bootstrap-server localhost:9092 --create --topic t-multi-partitions --partitions 3 --replication-factor 1

kafka-topics --bootstrap-server localhost:9092 --describe --topic t-multi-partitions


kafka-console-consumer --bootstrap-server localhost:9092 --topic t-multi-partitions --offset earliest --partition 0

kafka-console-consumer --bootstrap-server localhost:9092 --topic t-multi-partitions --offset earliest --partition 1

kafka-console-consumer --bootstrap-server localhost:9092 --topic t-multi-partitions --offset earliest --partition 2