# status

| Endpoint | Authentication Required |
| --- | --- |
| https://api.hypixel.net/status | yes |

## Description

!> Players can disable this endpoint via in-game settings. When done so the API will return as if the player is offline.

Returns online status information for given player, including game, mode and map when available.

## Parameters

| Param | Example |
| --- | --- |
| [`key`](README.md#api-key) | `36ab954b-4200-6969-aaaa-2aa15f189bad` |
| `uuid` | `41dae80633f442f0982f6c274dcf8a84` |

## Example Response
```js
{
  "success": true,
  "session":{
    "online": true,
    "gameType": "SKYWARS",
    "mode": "ranked_normal",
    "map": "Agni Temple"
  }
}
```
