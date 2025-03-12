#

Execute below two times or more

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

```
kafka-console-consumer --bootstrap-server localhost:9092 --topic t-commodity-order --from-beginning
{"orderLocation":"IND","orderNumber":"GHOUDMVK","creditCardNumber":"1234567890","orderDateTime":"2025-03-12T08:13:07","itemName":"iPhone 16","price":1000,"quantity":10}
{"orderLocation":"IND","orderNumber":"JPGVWPIY","creditCardNumber":"1234567890","orderDateTime":"2025-03-12T08:13:08","itemName":"iPhone 16","price":1000,"quantity":10}
{"orderLocation":"IND","orderNumber":"MAAQBCDF","creditCardNumber":"1234567890","orderDateTime":"2025-03-12T08:13:06","itemName":"iPhone 16","price":1000,"quantity":10}
```

<img width="932" alt="Screenshot 2025-03-12 at 8 14 10 AM" src="https://github.com/user-attachments/assets/ddbf4c0a-5cef-4d61-b7ed-e034c23c37f1" />

<img width="745" alt="Screenshot 2025-03-12 at 8 14 49 AM" src="https://github.com/user-attachments/assets/6b3b4f65-5bcb-46fa-a289-fa9ef41e074c" />
