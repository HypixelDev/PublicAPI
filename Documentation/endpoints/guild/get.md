# GET /guild

## Description

Get guild information

## Parameters

* key (string): API key
* id (string): Guild id returned by [findGuild](../findGuild/get.md)
* player (string): Lookup by player UUID
* name (string): Lookup by guild Name

## Response

* success (bool): Response status
* guild (Object):
  * _id (string): Guild ID
  * name (string): Guild Name
  * coins (int):
  * coinsEver (int):
  * created (int): Creatin timestamp
  * members (List): Member list
  * tag (string): ?
  * banner (object): ?
  * ...

## Example Response

Answer for: https://api.hypixel.net/guild?key=xxxxxxxxx&id=553490650cf26f12ae5bac8f

```js
{
  "success": true,
  "guild": {
    "_id": "553490650cf26f12ae5bac8f",
    "name": "Mini Squid",
    "coins": 425310,
    "coinsEver": 1835310,
    "created": 1429508197967,
    "members": [
      {
        "uuid": "20934ef9488c465180a78f861586b4cf",
        "rank": "GUILDMASTER",
        "joined": 1429508197967,
        "questParticipation": 139,
        "expHistory": {
          "2020-09-30": 0,
          "2020-09-29": 289,
          "2020-09-28": 48,
          "2020-09-27": 0,
          "2020-09-26": 0,
          "2020-09-25": 1022,
          "2020-09-24": 657
        }
      },
      [...]
    ],
    "tag": "§a1§e2§c3§7",
    "banner": {
      "Base": 15,
      "Patterns": [
        {
          "Pattern": "bo",
          "Color": "9"
        },
        {
          "Pattern": "bri",
          "Color": "5"
        }
      ]
    },
    "achievements": {
      "WINNERS": 1080,
      "EXPERIENCE_KINGS": 316804,
      "ONLINE_PLAYERS": 125
    },
    "exp": 263328990,
    "legacyRanking": 2891,
    "ranks": [
      {
        "name": "Famous",
        "default": false,
        "tag": "F",
        "created": 1562382958149,
        "priority": 3
      },
      {
        "name": "Member",
        "default": true,
        "tag": null,
        "created": 1562383012859,
        "priority": 2
      },
      {
        "name": "Big Nerd",
        "default": false,
        "tag": "B",
        "created": 1588882422793,
        "priority": 7
      },
      {
        "name": "Notorious Nerd",
        "default": false,
        "tag": "NERD",
        "created": 1588882818050,
        "priority": 5
      },
      {
        "name": "Online",
        "default": false,
        "tag": "ON",
        "created": 1588882828056,
        "priority": 6
      }
    ],
    "name_lower": "mini squid",
    "chatMute": 0,
    "preferredGames": [
      "SKYWARS"
    ],
    "publiclyListed": true,
    "tagColor": "GRAY",
    "guildExpByGameType": {
      "QUAKECRAFT": 855324,
      "PIT": 0,
      "VAMPIREZ": 1487027,
      "BUILD_BATTLE": 10657787,
      "DUELS": 24511316,
      "MCGO": 1239367,
      "MURDER_MYSTERY": 8466894,
      "SPEED_UHC": 311651,
      "WALLS": 1471206,
      "GINGERBREAD": 883917,
      "BEDWARS": 123632916,
      "BATTLEGROUND": 889378,
      "PROTOTYPE": 196703303,
      "SKYWARS": 108082972,
      "TNTGAMES": 7013141,
      "WALLS3": 3440269,
      "SKYBLOCK": 0,
      "HOUSING": 21354500,
      "SURVIVAL_GAMES": 3013866,
      "ARCADE": 10263596,
      "PAINTBALL": 421324,
      "UHC": 12486964,
      "REPLAY": 0,
      "LEGACY": 0,
      "SUPER_SMASH": 307954,
      "ARENA": 300356
    }
  }
}
```
