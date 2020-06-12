# leaderboards

## Description

Returns a list of the official leaderboards and their current standings for games on the network.

## Parameters
- key

## Example Response
```js
{
  "success": true,
  "leaderboards": {
    "SKYWARS": [
      {
        "path": "wins",
        "prefix": "Overall",
        "title": "Wins",
        "location": "-2556,55,712", // Coordinates of the board in the lobby
        "count": 14,
        "leaders": [
          "2afdb69c-c007-40cd-98b9-76a7554612d9",
          "6951ccdb-9ca7-4c8a-883b-a8d3bb81c3d2",
          "e61044cc-c42f-439b-9ad7-817c51ae7174",
          ...
        ]
      }
    ]
  }
}
```
