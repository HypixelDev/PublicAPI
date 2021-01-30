# Game Counts

| Endpoint | Authentication Required |
| --- | --- |
| https://api.hypixel.net/gameCounts | yes |

## Description
Returns the player count of all modes on the network

## Parameters

| Param | Example |
| --- | --- |
| [`key`](README.md#api-key) | `36ab954b-4200-6969-aaaa-2aa15f189bad` |

# Example Data

```js
{
  "success": true,
  "games": {
    "MAIN_LOBBY": {
      "players": 589
    },
    "TOURNAMENT_LOBBY": {
      "players": 0
    },
    "SKYBLOCK": {
      "players": 24226,
      "modes": {
        "dungeon_hub": 717,
        "mining_2": 844,
        "dark_auction": 87,
        "farming_2": 148,
        "farming_1": 130,
        "mining_3": 2702,
        "combat_3": 768,
        "dynamic": 10838,
        "combat_1": 291,
        "foraging_1": 450,
        "hub": 5076,
        "dungeon": 1860,
        "combat_2": 131,
        "mining_1": 186
      }
    },
    "LEGACY": {
      "players": 198,
      "modes": {
        "GINGERBREAD": 9,
        "ARENA": 4,
        "QUAKECRAFT": 21,
        "PAINTBALL": 18,
        "WALLS": 49,
        "VAMPIREZ": 55
      }
    },
    "ARCADE": {
      "players": 1574,
      "modes": {
        "PARTY": 491,
        "HOLE_IN_THE_WALL": 15,
        "DEFENDER": 28,
        "MINI_WALLS": 51,
        "SIMON_SAYS": 45,
        "ZOMBIES_BAD_BLOOD": 15,
        "HIDE_AND_SEEK_PARTY_POOPER": 37,
        "DAYONE": 72,
        "DRAW_THEIR_THING": 34,
        "ZOMBIES_ALIEN_ARCADIUM": 85,
        "ONEINTHEQUIVER": 8,
        "SOCCER": 25,
        "PVP_CTW": 200,
        "THROW_OUT": 8,
        "ENDER": 13,
        "STARWARS": 10,
        "ZOMBIES_DEAD_END": 143,
        "HIDE_AND_SEEK_PROP_HUNT": 64,
        "FARM_HUNT": 50
      }
    },
    "MURDER_MYSTERY": {
      "players": 910,
      "modes": {
        "MURDER_DOUBLE_UP": 203,
        "MURDER_INFECTION": 74,
        "MURDER_ASSASSINS": 39,
        "MURDER_CLASSIC": 470
      }
    },
    "DUELS": {
      "players": 2594,
      "modes": {
        "DUELS_BOWSPLEEF_DUEL": 22,
        "DUELS_BOW_DUEL": 28,
        "DUELS_BRIDGE_2V2V2V2": 8,
        "DUELS_UHC_FOUR": 37,
        "DUELS_MW_DUEL": 17,
        "DUELS_UHC_MEETUP": 38,
        "DUELS_BRIDGE_DOUBLES": 115,
        "DUELS_SW_DOUBLES": 88,
        "DUELS_UHC_DOUBLES": 58,
        "DUELS_BRIDGE_FOUR": 39,
        "DUELS_UHC_DUEL": 312,
        "DUELS_BRIDGE_3V3V3V3": 7,
        "DUELS_SUMO_DUEL": 133,
        "DUELS_OP_DOUBLES": 20,
        "DUELS_OP_DUEL": 68,
        "DUELS_MW_DOUBLES": 4,
        "DUELS_BLITZ_DUEL": 35,
        "DUELS_POTION_DUEL": 16,
        "DUELS_CLASSIC_DUEL": 259,
        "DUELS_COMBO_DUEL": 32,
        "DUELS_BRIDGE_DUEL": 488,
        "DUELS_SW_DUEL": 243
      }
    },
    "PIT": {
      "players": 475,
      "modes": {
        "PIT": 475
      }
    },
    "TNTGAMES": {
      "players": 652,
      "modes": {
        "PVPRUN": 17,
        "TNTAG": 300,
        "TNTRUN": 172,
        "BOWSPLEEF": 30,
        "CAPTURE": 26
      }
    },
    "PROTOTYPE": {
      "players": 277,
      "modes": {
        "TOWERWARS_SOLO": 34,
        "TOWERWARS_TEAM_OF_TWO": 11
      }
    },
    "WALLS3": {
      "players": 206,
      "modes": {
        "standard": 162,
        "face_off": 4
      }
    },
    "UHC": {
      "players": 465,
      "modes": {
        "TEAMS": 257,
        "SOLO": 168
      }
    },
    "SKYWARS": {
      "players": 3278,
      "modes": {
        "ranked_normal": 160,
        "solo_insane_tnt_madness": 3,
        "solo_insane_lucky": 6,
        "solo_insane_slime": 48,
        "teams_insane_lucky": 4,
        "solo_insane_rush": 11,
        "teams_insane": 245,
        "solo_normal": 914,
        "solo_insane_hunters_vs_beasts": 12,
        "solo_insane": 889,
        "teams_normal": 433,
        "mega_normal": 2
      }
    },
    "SPEED_UHC": {
      "players": 0
    },
    "BEDWARS": {
      "players": 16736,
      "modes": {
        "BEDWARS_FOUR_FOUR_VOIDLESS": 229,
        "BEDWARS_FOUR_THREE": 2729,
        "BEDWARS_TWO_FOUR": 565,
        "BEDWARS_FOUR_FOUR_LUCKY": 4,
        "BEDWARS_EIGHT_ONE": 2200,
        "BEDWARS_EIGHT_TWO_VOIDLESS": 162,
        "BEDWARS_FOUR_FOUR": 4392,
        "BEDWARS_EIGHT_TWO": 4318,
        "BEDWARS_EIGHT_TWO_LUCKY": 30
      }
    },
    "SUPER_SMASH": {
      "players": 33,
      "modes": {
        "1v1_normal": 3,
        "friends_normal": 4,
        "solo_normal": 13,
        "2v2_normal": 3,
        "teams_normal": 1
      }
    },
    "MCGO": {
      "players": 84,
      "modes": {
        "normal": 37,
        "deathmatch": 34
      }
    },
    "BATTLEGROUND": {
      "players": 35,
      "modes": {
        "ctf_mini": 17,
        "domination": 7,
        "team_deathmatch": 1
      }
    },
    "SURVIVAL_GAMES": {
      "players": 198,
      "modes": {
        "solo_normal": 89,
        "teams_normal": 80
      }
    },
    "BUILD_BATTLE": {
      "players": 814,
      "modes": {
        "BUILD_BATTLE_SOLO_NORMAL_LATEST": 174,
        "BUILD_BATTLE_GUESS_THE_BUILD": 119,
        "BUILD_BATTLE_TEAMS_NORMAL": 209,
        "BUILD_BATTLE_SOLO_NORMAL": 151,
        "BUILD_BATTLE_SOLO_PRO": 67
      }
    },
    "REPLAY": {
      "players": 96,
      "modes": {
        "BASE": 96
      }
    },
    "HOUSING": {
      "players": 1849
    },
    "LIMBO": {
      "players": 128
    },
    "IDLE": {
      "players": 6098
    },
    "QUEUE": {
      "players": 0
    }
  },
  "playerCount": 61433
}
```
