# weathermetrics

The endpoint should return a JSON object with the average of the following metrics:
1. Average of daily temperature
2. Average of nightly temperature
3. Average of pressure

There is a `getData` endpoint, with two **required** query params:

-  `city`: City name
- `appid`: API Key

Example:
```sh
curl -X GET http://localhost:4567/getData\?city\=London,uk\&appid\=<YOUR_API_KEY>
```

Response:

```json
{
    "averages": {
        "daily": 292.9257142857,
        "nightly": 288.0971428571,
        "pressure": 1020.0171428571
    }
}
```


# How to run

```sh
./gradlew run
```
