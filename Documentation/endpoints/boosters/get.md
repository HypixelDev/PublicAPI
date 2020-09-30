# GET /boosters

## Description

Get boosters list

## URL Parameters

* key: API key

## Response

* success (bool): Response status
* boosters (List): List of [booster](../../object/booster.md)
* boosterState (Object): 
  * decrementing: Indicate whether booster duration is ticking down

## Example Response

Answer for: https://api.hypixel.net/boosters?key=xxxxxx-xxxx-xxxx

```json
{
  "success": true,
  "boosters": [
    {
      "_id": "5c197fadc8f245280926413d",
      "purchaserUuid": "978ddb705a8e43618e41749178c020b0",
      "amount": 2,
      "originalLength": 3600,
      "length": 3595,
      "gameType": 24,
      "dateActivated": 1545244519133,
      "stacked": true
    },
    {
      "_id": "5de1bf590cf2544cd01a7e04",
      "purchaserUuid": "9e29fa1bedde445abb79526e22a7e051",
      "amount": 2.2,
      "originalLength": 3600,
      "length": 2074,
      "gameType": 13,
      "dateActivated": 1590842991659,
      "stacked": [
        "e8558c2a-935c-4eba-9a20-1e7eddeb2530",
        "d33dbdc9-a65b-4090-ab20-d101452647fa"
      ]
    }
  ],
  "boosterState": {
    "decrementing": true
  }
}
```
