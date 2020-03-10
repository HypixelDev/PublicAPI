# recentGames

## Description

Returns recent games of a player. A maximum of 100 games are returned and recent games are only stored for up to 3 days at this time.

Players can disable this endpoint via in-game settings. When done so the API will return as if there is no games.

## Parameters
- key
- uuid

## Example Response
```php
{
    "games": [
        {
            "date": 1583837333968,
            "gameType": "BEDWARS",
            "mode": "FOUR_FOUR",
            "map": "Dreamgrove"
        },
        {
            "date": 1583835955288,
            "gameType": "BEDWARS",
            "mode": "FOUR_FOUR",
            "map": "Ashore"
        },
        {
            "date": 1583832051787,
            "gameType": "DUELS",
            "mode": "SW_DUEL",
            "map": "Agni Temple"
        },
        {
            "date": 1583831476897,
            "gameType": "SKYWARS",
            "mode": "solo_insane",
            "map": "Hibiscus"
        }
    ]
}
```
