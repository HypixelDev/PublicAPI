# gameCounts

## Description
Returns the current player count along with the player count of each public game + mode on the server.

Due to the large amount of modes and that they can change at anytime we don't currently have a friendly list of mode keys to clean names. This may be added at a later date.

## Parameters
- key

## Example Response
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
