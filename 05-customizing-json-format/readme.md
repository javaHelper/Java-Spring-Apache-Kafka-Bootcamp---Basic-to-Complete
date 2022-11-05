#

```sh
kafka-topics --bootstrap-server localhost:9092 --create --topic t-employee-2 --partitions 1 --replication-factor 1
```

```sh
kafka-console-consumer --bootstrap-server localhost:9092 --offset earliest --partition 0 --topic t-employee-2
{"employee_id":"emp-0","name":"Employee 0","birth_date":"2022-11-05"}
{"employee_id":"emp-1","name":"Employee 1","birth_date":"2022-11-05"}
{"employee_id":"emp-2","name":"Employee 2","birth_date":"2022-11-05"}
{"employee_id":"emp-3","name":"Employee 3","birth_date":"2022-11-05"}
{"employee_id":"emp-4","name":"Employee 4","birth_date":"2022-11-05"}
```