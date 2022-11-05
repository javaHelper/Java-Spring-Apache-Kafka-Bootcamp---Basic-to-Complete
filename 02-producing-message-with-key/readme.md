#

```sh
kafka-console-consumer --bootstrap-server localhost:9092 --topic t-multi-partitions --offset earliest --partition 0
value 1 with key key-1
value 5 with key key-1
value 9 with key key-1
value 13 with key key-1
value 17 with key key-1
value 21 with key key-1
value 25 with key key-1
value 29 with key key-1
```

```
consumer-is-realtime % kafka-console-consumer --bootstrap-server localhost:9092 --topic t-multi-partitions --offset earliest --partition 1
value 0 with key key-0
value 4 with key key-0
value 8 with key key-0
value 12 with key key-0
value 16 with key key-0
value 20 with key key-0
value 24 with key key-0
value 28 with key key-0
```

```
consumer-is-realtime % kafka-console-consumer --bootstrap-server localhost:9092 --topic t-multi-partitions --offset earliest --partition 2
value 2 with key key-2
value 3 with key key-3
value 6 with key key-2
value 7 with key key-3
value 10 with key key-2
value 11 with key key-3
value 14 with key key-2
value 15 with key key-3
value 18 with key key-2
value 19 with key key-3
value 22 with key key-2
value 23 with key key-3
value 26 with key key-2
value 27 with key key-3
```
