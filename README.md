#

```
docker-compose -f docker-compose-core.yml -p core up -d
```

# Delete all kafka topics

```
kafka-topics --bootstrap-server localhost:9092 --delete --topic '.*'
```
