# GET /recentGames

## Description

Get recent games for a player

A maximium of 100 games are returned and recent games are only stored for up to 3 days.

Player can disable this endpoint via in-game settings, in that case the API will return no games.

## Parameters

* key (string): API key
* uuid (string): Player UUID

## Response

* success (bool): Response status
* games (list): List of [RecentGames](../../object/recentgames.md) objects

## Example Response

```js
{
  "success": true,
  "games": [
    {
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
