# leaderboards

## Description

Returns a list of the official leaderboards and their current standings for games on the network.

## Parameters
- key

## Example Leaderboard
```php
{
  "TNTGAMES": [
    {
      "path": "wins_tntrun",
      "prefix": "Overall",
      "count": 10, // Number of properties in "leaders"
      "location": "-2554,57,715", // Coordinates of the board in the lobby
      "leaders": [
        "494a5e4a-ecd1-4a16-b9ee-de37ef24820d",
        "adc9fbdd-ede0-4ce9-8abf-42a97148bdbf",
        "222c4837-a906-40ed-a1fb-5713a70c13db",
        ...
      ],
      "title": "TNT Run Wins"
    },
    ...
  ]
}
```
