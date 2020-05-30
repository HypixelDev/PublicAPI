# status

## Description

Returns online status information for given player, including game, mode and map when available.

Players can disable this endpoint via in-game settings. When done so the API will return as if the player is offline.

## Parameters
- key
- uuid

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
