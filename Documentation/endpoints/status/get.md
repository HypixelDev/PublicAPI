# GET /status

## Description

Get online status information for a given player

Players can disable this endpoint via in-game settings, if so, the API vill return as if the player is offline.

## Parameters

* key (string): API key
* uuid (string): Player UUID

## Response

* succes (bool): Response status
* session (object):
  * online (bool): Is online
  * gametype (string): [GameType](../../object/gametype.md) name
  * mode (string): mode name if available
  * map (string): map name if available

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
