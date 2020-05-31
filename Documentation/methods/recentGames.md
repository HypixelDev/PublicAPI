# recentGames

## Description

Returns recent games of a player. A maximum of 100 games are returned and recent games are only stored for up to 3 days at this time.

Players can disable this endpoint via in-game settings. When done so the API will return as if there is no games.

## Parameters
- key
- uuid

## Example Response
```js
{
  "success": true,
  "games": [
    { // No 'ended' means the game is still running
      "date": 1590935247444,
      "gameType": "SKYWARS",
      "mode": "solo_normal",
      "map": "Shire"
    },
    {
      "date": 1590850836485,
      "gameType": "BEDWARS",
      "mode": "FOUR_FOUR",
      "map": "Dreamgrove",
      "ended": 1590850919917
    },
    {
      "date": 1590850404473,
      "gameType": "SKYWARS",
      "mode": "ranked_normal",
      "map": "Meteor",
      "ended": 1590850783962
    },
    {
      "date": 1590850359562,
      "gameType": "DUELS",
      "mode": "SW_DUEL",
      "map": "Agni Temple",
      "ended": 1590850500815
    },
    {
      "date": 1590850287155,
      "gameType": "SKYWARS",
      "mode": "solo_insane",
      "map": "Mythic",
      "ended": 1590850352734
    }
  ]
}
```
