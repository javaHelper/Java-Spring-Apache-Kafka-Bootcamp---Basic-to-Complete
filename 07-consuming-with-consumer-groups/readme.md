
```shell
kafka-topics --bootstrap-server localhost:9092 --create --topic t-commodity --partitions 1 --replication-factor 1
```


http://localhost:8080/api/commodity/v1/all

- This is when you've not code for consumer logic
````shell
prateekashtikar@Prateeks-MacBook-Pro 06-customizing-json-msg % kafka-console-consumer --bootstrap-server localhost:9092 --offset earliest --partition 0 --topic t-commodity
{"name":"gold","price":986.6581331766613,"measurement":"ounce","timestamp":1668282113909}
{"name":"copper","price":4789.567906812845,"measurement":"tonne","timestamp":1668282113909}
{"name":"gold","price":969.6721607058996,"measurement":"ounce","timestamp":1668282118893}
{"name":"copper","price":4798.551870318417,"measurement":"tonne","timestamp":1668282118893}
{"name":"gold","price":1000.535747162934,"measurement":"ounce","timestamp":1668282123883}
{"name":"copper","price":4940.175742362773,"measurement":"tonne","timestamp":1668282123883}
{"name":"gold","price":1842.5761724489562,"measurement":"ounce","timestamp":1668282407310}
{"name":"copper","price":5833.104864003043,"measurement":"tonne","timestamp":1668282407310}
{"name":"gold","price":1888.030415820481,"measurement":"ounce","timestamp":1668282412167}
{"name":"copper","price":5702.323145020014,"measurement":"tonne","timestamp":1668282412167}
{"name":"gold","price":1846.6478493698287,"measurement":"ounce","timestamp":1668282417157}
{"name":"copper","price":5589.827402747044,"measurement":"tonne","timestamp":1668282417157}
{"name":"gold","price":1923.1925088288674,"measurement":"ounce","timestamp":1668282422165}
{"name":"copper","price":5460.957108486698,"measurement":"tonne","timestamp":1668282422165}
{"name":"gold","price":1879.1974457444758,"measurement":"ounce","timestamp":1668282427152}
{"name":"copper","price":5286.165121640718,"measurement":"tonne","timestamp":1668282427152}
{"name":"gold","price":1911.236465820406,"measurement":"ounce","timestamp":1668282432154}
{"name":"copper","price":5048.894980561488,"measurement":"tonne","timestamp":1668282432154}
{"name":"gold","price":1853.0050412508356,"measurement":"ounce","timestamp":1668282437147}
{"name":"copper","price":5184.879912316648,"measurement":"tonne","timestamp":1668282437148}
{"name":"gold","price":1887.2088342224279,"measurement":"ounce","timestamp":1668282442156}
{"name":"copper","price":5005.180273165089,"measurement":"tonne","timestamp":1668282442156}
````

- Console 

````
[Kafka Core Consumer] 12:09:25.510  INFO --- o.s.k.l.KafkaMessageListenerContainer    : cg-notification: partitions assigned: [t-commodity-0]
[Kafka Core Consumer] 12:09:25.510  INFO --- o.s.k.l.KafkaMessageListenerContainer    : cg-dashboard: partitions assigned: [t-commodity-0]
[Kafka Core Consumer] 12:09:25.676  INFO --- c.c.k.c.CommodityNotificationConsumer    : Notification logic for : Commodity(name=gold, price=986.6581331766613, measurement=ounce, timestamp=1668282113909)
[Kafka Core Consumer] 12:09:25.674  INFO --- c.c.k.c.CommodityDashboardConsumer       : >> Dashboard logic for : Commodity(name=gold, price=986.6581331766613, measurement=ounce, timestamp=1668282113909)
[Kafka Core Consumer] 12:09:25.765  INFO --- c.c.k.c.CommodityDashboardConsumer       : >> Dashboard logic for : Commodity(name=copper, price=4789.567906812845, measurement=tonne, timestamp=1668282113909)
[Kafka Core Consumer] 12:09:25.765  INFO --- c.c.k.c.CommodityDashboardConsumer       : >> Dashboard logic for : Commodity(name=gold, price=969.6721607058996, measurement=ounce, timestamp=1668282118893)
[Kafka Core Consumer] 12:09:25.766  INFO --- c.c.k.c.CommodityDashboardConsumer       : >> Dashboard logic for : Commodity(name=copper, price=4798.551870318417, measurement=tonne, timestamp=1668282118893)
[Kafka Core Consumer] 12:09:25.766  INFO --- c.c.k.c.CommodityDashboardConsumer       : >> Dashboard logic for : Commodity(name=gold, price=1000.535747162934, measurement=ounce, timestamp=1668282123883)
[Kafka Core Consumer] 12:09:25.766  INFO --- c.c.k.c.CommodityDashboardConsumer       : >> Dashboard logic for : Commodity(name=copper, price=4940.175742362773, measurement=tonne, timestamp=1668282123883)
[Kafka Core Consumer] 12:09:25.766  INFO --- c.c.k.c.CommodityDashboardConsumer       : >> Dashboard logic for : Commodity(name=gold, price=1842.5761724489562, measurement=ounce, timestamp=1668282407310)
[Kafka Core Consumer] 12:09:25.766  INFO --- c.c.k.c.CommodityDashboardConsumer       : >> Dashboard logic for : Commodity(name=copper, price=5833.104864003043, measurement=tonne, timestamp=1668282407310)
[Kafka Core Consumer] 12:09:25.767  INFO --- c.c.k.c.CommodityDashboardConsumer       : >> Dashboard logic for : Commodity(name=gold, price=1888.030415820481, measurement=ounce, timestamp=1668282412167)
[Kafka Core Consumer] 12:09:25.767  INFO --- c.c.k.c.CommodityDashboardConsumer       : >> Dashboard logic for : Commodity(name=copper, price=5702.323145020014, measurement=tonne, timestamp=1668282412167)
[Kafka Core Consumer] 12:09:25.767  INFO --- c.c.k.c.CommodityDashboardConsumer       : >> Dashboard logic for : Commodity(name=gold, price=1846.6478493698287, measurement=ounce, timestamp=1668282417157)
[Kafka Core Consumer] 12:09:25.768  INFO --- c.c.k.c.CommodityNotificationConsumer    : Notification logic for : Commodity(name=copper, price=4789.567906812845, measurement=tonne, timestamp=1668282113909)
[Kafka Core Consumer] 12:09:25.769  INFO --- c.c.k.c.CommodityDashboardConsumer       : >> Dashboard logic for : Commodity(name=copper, price=5589.827402747044, measurement=tonne, timestamp=1668282417157)
[Kafka Core Consumer] 12:09:25.770  INFO --- c.c.k.c.CommodityDashboardConsumer       : >> Dashboard logic for : Commodity(name=gold, price=1923.1925088288674, measurement=ounce, timestamp=1668282422165)
[Kafka Core Consumer] 12:09:25.770  INFO --- c.c.k.c.CommodityDashboardConsumer       : >> Dashboard logic for : Commodity(name=copper, price=5460.957108486698, measurement=tonne, timestamp=1668282422165)
[Kafka Core Consumer] 12:09:25.770  INFO --- c.c.k.c.CommodityDashboardConsumer       : >> Dashboard logic for : Commodity(name=gold, price=1879.1974457444758, measurement=ounce, timestamp=1668282427152)
[Kafka Core Consumer] 12:09:25.771  INFO --- c.c.k.c.CommodityNotificationConsumer    : Notification logic for : Commodity(name=gold, price=969.6721607058996, measurement=ounce, timestamp=1668282118893)
[Kafka Core Consumer] 12:09:25.772  INFO --- c.c.k.c.CommodityDashboardConsumer       : >> Dashboard logic for : Commodity(name=copper, price=5286.165121640718, measurement=tonne, timestamp=1668282427152)
[Kafka Core Consumer] 12:09:25.772  INFO --- c.c.k.c.CommodityNotificationConsumer    : Notification logic for : Commodity(name=copper, price=4798.551870318417, measurement=tonne, timestamp=1668282118893)
[Kafka Core Consumer] 12:09:25.772  INFO --- c.c.k.c.CommodityDashboardConsumer       : >> Dashboard logic for : Commodity(name=gold, price=1911.236465820406, measurement=ounce, timestamp=1668282432154)
[Kafka Core Consumer] 12:09:25.773  INFO --- c.c.k.c.CommodityDashboardConsumer       : >> Dashboard logic for : Commodity(name=copper, price=5048.894980561488, measurement=tonne, timestamp=1668282432154)
[Kafka Core Consumer] 12:09:25.773  INFO --- c.c.k.c.CommodityDashboardConsumer       : >> Dashboard logic for : Commodity(name=gold, price=1853.0050412508356, measurement=ounce, timestamp=1668282437147)
[Kafka Core Consumer] 12:09:25.773  INFO --- c.c.k.c.CommodityDashboardConsumer       : >> Dashboard logic for : Commodity(name=copper, price=5184.879912316648, measurement=tonne, timestamp=1668282437148)
[Kafka Core Consumer] 12:09:25.773  INFO --- c.c.k.c.CommodityDashboardConsumer       : >> Dashboard logic for : Commodity(name=gold, price=1887.2088342224279, measurement=ounce, timestamp=1668282442156)
````


```sh
kafka-consumer-groups --bootstrap-server localhost:9092 --group cg-dashboard --describe 

Consumer group 'cg-dashboard' has no active members.

GROUP           TOPIC           PARTITION  CURRENT-OFFSET  LOG-END-OFFSET  LAG             CONSUMER-ID     HOST            CLIENT-ID
cg-dashboard    t-commodity     0          26              26              0               -               -               -



kafka-consumer-groups --bootstrap-server localhost:9092 --group cg-notification --describe 

Consumer group 'cg-notification' has no active members.

GROUP           TOPIC           PARTITION  CURRENT-OFFSET  LOG-END-OFFSET  LAG             CONSUMER-ID     HOST            CLIENT-ID
cg-notification t-commodity     0          26              26              0               -               -               -
prateekashtikar@Prateeks-MacBook-Pro 06-customizing-json-msg % 
```
