# Full working kafka in Microservices

```
curl --location 'http://localhost:9001/api/order' \
--header 'Content-Type: application/json' \
--data '{
    "orderLocation": "IND",
    "creditCardNumber": "1234567890",
    "items": [
        {
            "itemName": "iPhone 16",
            "price": 1000,
            "quantity": "10"
        }
    ]
}'
```

order logs

```
[Kafka Order] 11:19:42.929  INFO --- o.a.kafka.common.utils.AppInfoParser     : Kafka commitId: 2ae524ed625438c5
[Kafka Order] 11:19:42.930  INFO --- o.a.kafka.common.utils.AppInfoParser     : Kafka startTimeMs: 1741758582929
[Kafka Order] 11:19:42.961  INFO --- org.apache.kafka.clients.Metadata        : [Producer clientId=producer-1] Cluster ID: c-Hxf8jkRJy9SikkyhcMvg
[Kafka Order] 11:19:42.962  INFO --- o.a.k.c.p.internals.TransactionManager   : [Producer clientId=producer-1] ProducerId set to 10 with epoch 0
[Kafka Order] 11:19:42.983  INFO --- c.c.kafka.broker.producer.OrderProducer  : Just a dummy message for order 3PGXFQET, item iPhone 16
[Kafka Order] 11:19:43.016  INFO --- c.c.kafka.broker.producer.OrderProducer  : Order 3PGXFQET sent successfully 
[Kafka Order] 11:19:43.190  INFO --- c.c.k.b.consumer.OrderReplyConsumer      : Reply message received : OrderReplyMessage [replyMessage=Order 3PGXFQET, item iPhone 16 processed]
```

You can send the random promotions and discounts.

