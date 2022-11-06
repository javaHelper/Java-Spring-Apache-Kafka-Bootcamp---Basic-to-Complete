
```shell
kafka-topics --bootstrap-server localhost:9092 --create --topic t-commodity --partitions 1 --replication-factor 1
```

```sh
kafka-consumer-groups --bootstrap-server localhost:9092 --group cg-dashboard --describe

Consumer group 'cg-dashboard' has no active members.

GROUP           TOPIC           PARTITION  CURRENT-OFFSET  LOG-END-OFFSET  LAG             CONSUMER-ID     HOST            CLIENT-ID
cg-dashboard    t-commodity     0          10              10              0               -               -               -
```

```shell

kafka-consumer-groups --bootstrap-server localhost:9092 --group cg-notification --describe

Consumer group 'cg-notification' has no active members.

GROUP           TOPIC           PARTITION  CURRENT-OFFSET  LOG-END-OFFSET  LAG             CONSUMER-ID     HOST            CLIENT-ID
cg-notification t-commodity     0          10              10              0               -               -               -
````

http://localhost:8080/api/commodity/v1/all

```

2022-11-05 22:26:16.770  INFO 30422 --- [ntainer#1-0-C-1] o.s.k.l.KafkaMessageListenerContainer    : cg-notification: partitions assigned: [t-commodity-0]
2022-11-05 22:26:16.770  INFO 30422 --- [ntainer#0-0-C-1] o.s.k.l.KafkaMessageListenerContainer    : cg-dashboard: partitions assigned: [t-commodity-0]
2022-11-05 22:26:21.486  INFO 30422 --- [ntainer#1-0-C-1] c.c.k.c.CommodityNotificationConsumer    : Notification logic for : Commodity(name=gold, price=1798.6521839161703, measurement=ounce, timestamp=1667667381090)
2022-11-05 22:26:21.486  INFO 30422 --- [ntainer#0-0-C-1] c.c.k.c.CommodityDashboardConsumer       : Dashboard logic for : Commodity(name=gold, price=1798.6521839161703, measurement=ounce, timestamp=1667667381090)
2022-11-05 22:26:21.570  INFO 30422 --- [ntainer#0-0-C-1] c.c.k.c.CommodityDashboardConsumer       : Dashboard logic for : Commodity(name=copper, price=5856.927605300785, measurement=tonne, timestamp=1667667381090)
2022-11-05 22:26:21.570  INFO 30422 --- [ntainer#1-0-C-1] c.c.k.c.CommodityNotificationConsumer    : Notification logic for : Commodity(name=copper, price=5856.927605300785, measurement=tonne, timestamp=1667667381090)
2022-11-05 22:26:26.118  INFO 30422 --- [ntainer#1-0-C-1] c.c.k.c.CommodityNotificationConsumer    : Notification logic for : Commodity(name=gold, price=1789.5416598082102, measurement=ounce, timestamp=1667667386088)
2022-11-05 22:26:26.118  INFO 30422 --- [ntainer#0-0-C-1] c.c.k.c.CommodityDashboardConsumer       : Dashboard logic for : Commodity(name=gold, price=1789.5416598082102, measurement=ounce, timestamp=1667667386088)
2022-11-05 22:26:26.118  INFO 30422 --- [ntainer#1-0-C-1] c.c.k.c.CommodityNotificationConsumer    : Notification logic for : Commodity(name=copper, price=6052.472193411418, measurement=tonne, timestamp=1667667386088)
2022-11-05 22:26:26.119  INFO 30422 --- [ntainer#0-0-C-1] c.c.k.c.CommodityDashboardConsumer       : Dashboard logic for : Commodity(name=copper, price=6052.472193411418, measurement=tonne, timestamp=1667667386088)
2022-11-05 22:26:31.099  INFO 30422 --- [ntainer#0-0-C-1] c.c.k.c.CommodityDashboardConsumer       : Dashboard logic for : Commodity(name=gold, price=1781.717482486059, measurement=ounce, timestamp=1667667391080)
2022-11-05 22:26:31.100  INFO 30422 --- [ntainer#1-0-C-1] c.c.k.c.CommodityNotificationConsumer    : Notification logic for : Commodity(name=gold, price=1781.717482486059, measurement=ounce, timestamp=1667667391080)
2022-11-05 22:26:31.101  INFO 30422 --- [ntainer#1-0-C-1] c.c.k.c.CommodityNotificationConsumer    : Notification logic for : Commodity(name=copper, price=5970.911209465262, measurement=tonne, timestamp=1667667391080)
2022-11-05 22:26:31.112  INFO 30422 --- [ntainer#0-0-C-1] c.c.k.c.CommodityDashboardConsumer       : Dashboard logic for : Commodity(name=copper, price=5970.911209465262, measurement=tonne, timestamp=1667667391080)
2022-11-05 22:26:36.144  INFO 30422 --- [ntainer#0-0-C-1] c.c.k.c.CommodityDashboardConsumer       : Dashboard logic for : Commodity(name=gold, price=1843.091262953159, measurement=ounce, timestamp=1667667396095)
2022-11-05 22:26:36.144  INFO 30422 --- [ntainer#1-0-C-1] c.c.k.c.CommodityNotificationConsumer    : Notification logic for : Commodity(name=gold, price=1843.091262953159, measurement=ounce, timestamp=1667667396095)
2022-11-05 22:26:36.146  INFO 30422 --- [ntainer#0-0-C-1] c.c.k.c.CommodityDashboardConsumer       : Dashboard logic for : Commodity(name=copper, price=5857.812854183046, measurement=tonne, timestamp=1667667396095)
2022-11-05 22:26:36.146  INFO 30422 --- [ntainer#1-0-C-1] c.c.k.c.CommodityNotificationConsumer    : Notification logic for : Commodity(name=copper, price=5857.812854183046, measurement=tonne, timestamp=1667667396095)
```