# Boosters

| Endpoint | Authentication Required |
| --- | --- |
| https://api.hypixel.net/boosters | yes |

## Description
Returns active boosters on the network

## Parameters

| Param | Example |
| --- | --- |
| [`key`](README.md#api-key) | `36ab954b-4200-6969-aaaa-2aa15f189bad` |

## Example Data

!> **NOTE:** The JSON below is invalid JSON, as it includes comments

```js
{
  "success": true,
  "boosters": [
    {
      "_id": "5c197fadc8f245280926413d",
      "purchaserUuid": "978ddb705a8e43618e41749178c020b0",
      "amount": 2, // Multiplier
      "originalLength": 3600,
      "length": 3595, // Length remaining
      "gameType": 24, // GameType ID
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
      "stacked": [ // Array of up to 10 UUIDs who have stacked the booster
        "e8558c2a-935c-4eba-9a20-1e7eddeb2530",
        "d33dbdc9-a65b-4090-ab20-d101452647fa"
      ]
    }
  ],
  "boosterState": {
    "decrementing": true // Indicates whether booster duration is ticking down
  }
}
```
