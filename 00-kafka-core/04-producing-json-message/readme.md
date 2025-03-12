# Producing Json Message

```
kafka-console-consumer --bootstrap-server localhost:9092 --offset earliest --partition 0 --topic t-employee
{"employee_id":"emp-0","name":"Employee 0","birth_date":[2022,11,5]}
{"employee_id":"emp-1","name":"Employee 1","birth_date":[2022,11,5]}
{"employee_id":"emp-2","name":"Employee 2","birth_date":[2022,11,5]}
{"employee_id":"emp-3","name":"Employee 3","birth_date":[2022,11,5]}
{"employee_id":"emp-4","name":"Employee 4","birth_date":[2022,11,5]}
```