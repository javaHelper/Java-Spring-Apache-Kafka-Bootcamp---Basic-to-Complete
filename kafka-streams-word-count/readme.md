#

```sh
kafka-console-producer.sh --broker-list 127.0.0.1:9092 --topic words
>hello
>prateek
>hello prateek
>hello java
>
```


```sh
kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092 --topic counts
{"word":"hello","count":1,"start":1667795040000,"end":1667795070000}
{"word":"prateek","count":1,"start":1667795040000,"end":1667795070000}
{"word":"hello","count":2,"start":1667795040000,"end":1667795070000}
{"word":"prateek","count":2,"start":1667795040000,"end":1667795070000}
{"word":"hello","count":3,"start":1667795040000,"end":1667795070000}
{"word":"java","count":1,"start":1667795040000,"end":1667795070000}
```