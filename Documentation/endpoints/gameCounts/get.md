# GET /gameCounts

## Description

Get the current player count with the player for each public game + mode on the server

## Parameters

* key: API key

## Response

* success (bool): Response status
* games (object): Dictionnary of [GameType](../../object/gametype.md) as key and [gamestatus](../../object/gamestatus.md) as value.
* playerCount (int): Total player count

## Example Response

Answer for: https://api.hypixel.net/gameCounts?key=xxxxxxxxxxxxxx

```js
{
  "success": true,
  "games": {
    "SKYBLOCK": { // The GameType Name
      "players": 30522, // Total players for the GameType
      "modes": {
        "combat_1": 690, // The mode key along with the player count
        "foraging_1": 1456,
        "hub": 8049,
        "mining_2": 1062,
        "combat_2": 277,
        "farming_2": 247,
        "mining_1": 300,
        "farming_1": 204,
        "combat_3": 2350,
        "dynamic": 15888
      }
    }
  },
  "playerCount": 77238 // Total players on the network
}
```
