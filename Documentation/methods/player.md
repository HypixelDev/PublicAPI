# Player

| Endpoint | Authentication Required |
| --- | --- |
| https://api.hypixel.net/player | yes |

## Description
Returns a player's data, such as game stats.

## Parameters

| Param | Example | Deprecated |
| --- | --- | --- |
| [`key`](README.md#api-key) | `36ab954b-4200-6969-aaaa-2aa15f189bad` |
| `uuid` | `41dae80633f442f0982f6c274dcf8a84` |
| `name` | `grahhnt` | yes |

## Example Data

!> **NOTE:** The JSON below is invalid JSON, as it includes comments

?> Some values may not be set at all, if you are checking for Bedwars stats, ensure that the `stats` object exists, as well as the `stats.Bedwars` object.

```js
{
  "success": true,
  "player": {
    "_id": "5f1a18b96d0493774c1b8f72", // Internal Hypixel Player ID
    "uuid": "b7468f98c55d4b4aa57a14ff9ebbc0b3", // Player UUID
    "displayname": "bedwarsDOTgg", // Username of player
    "playername": "bedwarsdotgg", // Lowercase username
    "firstLogin": 1595545785045, // UNIX Timestamp
    "lastLogin": 1610910405547,
    "lastLogout": 1610917062931,
    "knownAliases": [ // Previous names
      "Sqrinton",
      "bedwarsDOTgg"
    ],
    "knownAliasesLower": [
      "sqrinton",
      "bedwarsdotgg"
    ],

    "networkExp": 133317,
    "karma": 245, // GGs
    "friendRequestsUuid": [],
    "mostRecentGameType": "BEDWARS",

    "achievementPoints": 355, // Total achievement points
    "achievementsOneTime": [
      "general_first_join",
      "general_first_party",
      "general_first_chat",
      // {...} Lots of one time achievements
    ],
    // General player data, for some reason as an achievement
    "achievements": {
      // Bedwars
      "bedwars_level": 5,
      "bedwars_beds": 80,
      "bedwars_bedwars_killer": 176,
      "bedwars_wins": 30,
      "bedwars_collectors_edition": 100,

      // Skywars
      "skywars_you_re_a_star": 1,

      // Duels
      "duels_goals": 10,
      "duels_bridge_duels_wins": 2,
      "duels_bridge_win_streak": 1,
      "duels_bridge_wins": 2,
      "duels_duels_traveller": 2,
      "duels_duels_win_streak": 1,
      "duels_duels_winner": 2,
      "duels_unique_map_wins": 2,

      // SKyblock
      "skyblock_minion_lover": 1,

      // Unused/unknown
      "summer_shopaholic": 2191,
      "general_challenger": 28,
      "christmas2017_present_collector": 214,
      "general_wins": 4
    },
    "achievementTracking": [],

    "stats": {
      "Bedwars": {
        "bedwars_boxes": 2, // Loot boxes the player has
        "bedwars_christmas_boxes": 3, // Christmas loot boxes
        "Experience": 14130, // Experience total?
        "coins": 7341, // Coins

        // Quick buy menu
        "favourites_2": "wool,oak_wood_planks,null,iron_boots,speed_ii_potion_(45_seconds),invisibility_potion_(30_seconds),tnt,stone_sword,iron_sword,diamond_sword,diamond_boots,bow,arrow,water_bucket,wooden_axe,wooden_pickaxe,shears,magic_milk,golden_apple,jump_v_potion_(45_seconds),fireball",
        "favorite_slots": "Melee,Tools,Tools,Tools,Blocks,null,null,null,Utility", // Hotbar setup

        // Not entirely sure
        "first_join_7": true,
        "games_played_bedwars_1": 64,
        // Not entirely sure

        // Overall Stats
        "_items_purchased_bedwars": 1997,
        "beds_lost_bedwars": 33,
        "deaths_bedwars": 264,
        "emerald_resources_collected_bedwars": 101,
        "entity_attack_deaths_bedwars": 124,
        "entity_attack_final_deaths_bedwars": 18,
        "final_deaths_bedwars": 31,
        "games_played_bedwars": 64,
        "gold_resources_collected_bedwars": 1941,
        "iron_resources_collected_bedwars": 14684,
        "items_purchased_bedwars": 2094,
        "kills_bedwars": 145,
        "losses_bedwars": 28,
        "permanent_items_purchased_bedwars": 97,
        "resources_collected_bedwars": 16921,
        "void_deaths_bedwars": 123,
        "void_kills_bedwars": 78,
        "winstreak": 1,
        "beds_broken_bedwars": 80,
        "entity_attack_final_kills_bedwars": 57,
        "entity_attack_kills_bedwars": 54,
        "fall_deaths_bedwars": 9,
        "final_kills_bedwars": 89,
        "void_final_kills_bedwars": 25,
        "wins_bedwars": 30,
        "diamond_resources_collected_bedwars": 195,
        "entity_explosion_final_deaths_bedwars": 1,
        "fall_kills_bedwars": 12,
        "magic_final_kills_bedwars": 3, // Potions maybe?
        "fall_final_kills_bedwars": 4,
        "void_final_deaths_bedwars": 11,
        "entity_explosion_deaths_bedwars": 1,
        "fall_final_deaths_bedwars": 1,
        "magic_kills_bedwars": 1,

        // 4v4v4v4 Stats
        "four_four_winstreak": 3,
        "four_four__items_purchased_bedwars": 252,
        "four_four_beds_lost_bedwars": 4,
        "four_four_deaths_bedwars": 40,
        "four_four_emerald_resources_collected_bedwars": 65,
        "four_four_entity_attack_deaths_bedwars": 20,
        "four_four_entity_attack_final_deaths_bedwars": 3,
        "four_four_final_deaths_bedwars": 4,
        "four_four_games_played_bedwars": 8,
        "four_four_gold_resources_collected_bedwars": 326,
        "four_four_iron_resources_collected_bedwars": 2334,
        "four_four_items_purchased_bedwars": 274,
        "four_four_kills_bedwars": 29,
        "four_four_losses_bedwars": 3,
        "four_four_permanent_items_purchased_bedwars": 22,
        "four_four_resources_collected_bedwars": 2731,
        "four_four_void_deaths_bedwars": 19,
        "four_four_void_kills_bedwars": 18,
        "four_four_beds_broken_bedwars": 10,
        "four_four_entity_attack_final_kills_bedwars": 14,
        "four_four_entity_attack_kills_bedwars": 9,
        "four_four_fall_deaths_bedwars": 1,
        "four_four_final_kills_bedwars": 18,
        "four_four_void_final_kills_bedwars": 3,
        "four_four_wins_bedwars": 5,
        "four_four_diamond_resources_collected_bedwars": 6,
        "four_four_entity_explosion_final_deaths_bedwars": 1,
        "four_four_fall_kills_bedwars": 2,
        "four_four_magic_final_kills_bedwars": 1,

        // Solo Stats
        "eight_one_winstreak": 0,
        "eight_one_deaths_bedwars": 25,
        "eight_one_games_played_bedwars": 11,
        "eight_one_magic_deaths_bedwars": 6,
        "magic_deaths_bedwars": 7,
        "eight_one_beds_lost_bedwars": 7,
        "eight_one_entity_attack_final_deaths_bedwars": 3,
        "eight_one_final_deaths_bedwars": 6,
        "eight_one_gold_resources_collected_bedwars": 283,
        "eight_one_iron_resources_collected_bedwars": 2142,
        "eight_one_resources_collected_bedwars": 2468,
        "eight_one__items_purchased_bedwars": 213,
        "eight_one_beds_broken_bedwars": 14,
        "eight_one_diamond_resources_collected_bedwars": 43,
        "eight_one_entity_attack_kills_bedwars": 3,
        "eight_one_items_purchased_bedwars": 220,
        "eight_one_kills_bedwars": 7,
        "eight_one_losses_bedwars": 5,
        "eight_one_permanent_items_purchased_bedwars": 7,
        "eight_one_void_deaths_bedwars": 11,
        "eight_one_void_kills_bedwars": 4,
        "eight_one_void_final_deaths_bedwars": 3,
        "eight_one_entity_attack_deaths_bedwars": 7,
        "eight_one_entity_attack_final_kills_bedwars": 3,
        "eight_one_final_kills_bedwars": 7,
        "eight_one_magic_final_kills_bedwars": 1,
        "eight_one_void_final_kills_bedwars": 2,
        "eight_one_wins_bedwars": 1,
        "eight_one_fall_deaths_bedwars": 1,
        "eight_one_fall_final_kills_bedwars": 1,

        // Doubles Stats
        "eight_two__items_purchased_bedwars": 978,
        "eight_two_beds_broken_bedwars": 40,
        "eight_two_beds_lost_bedwars": 15,
        "eight_two_deaths_bedwars": 105,
        "eight_two_entity_attack_deaths_bedwars": 56,
        "eight_two_entity_attack_final_kills_bedwars": 26,
        "eight_two_fall_final_kills_bedwars": 1,
        "eight_two_final_deaths_bedwars": 14,
        "eight_two_final_kills_bedwars": 43,
        "eight_two_games_played_bedwars": 23,
        "eight_two_gold_resources_collected_bedwars": 987,
        "eight_two_iron_resources_collected_bedwars": 6607,
        "eight_two_items_purchased_bedwars": 1009,
        "eight_two_losses_bedwars": 14,
        "eight_two_permanent_items_purchased_bedwars": 31,
        "eight_two_resources_collected_bedwars": 7741,
        "eight_two_void_deaths_bedwars": 44,
        "eight_two_void_final_deaths_bedwars": 8,
        "eight_two_winstreak": 1,
        "eight_two_diamond_resources_collected_bedwars": 125,
        "eight_two_kills_bedwars": 36,
        "eight_two_void_kills_bedwars": 17,
        "eight_two_entity_attack_final_deaths_bedwars": 5,
        "eight_two_entity_attack_kills_bedwars": 17,
        "eight_two_fall_deaths_bedwars": 3,
        "eight_two_wins_bedwars": 8,
        "eight_two_magic_deaths_bedwars": 1,
        "eight_two_void_final_kills_bedwars": 15,
        "eight_two_fall_kills_bedwars": 2,
        "eight_two_emerald_resources_collected_bedwars": 22,
        "eight_two_entity_explosion_deaths_bedwars": 1,
        "eight_two_magic_final_kills_bedwars": 1,
        "eight_two_fall_final_deaths_bedwars": 1,

        // 4v4 Stats
        "two_four_winstreak": 0,
        "two_four__items_purchased_bedwars": 554,
        "two_four_beds_broken_bedwars": 16,
        "two_four_deaths_bedwars": 94,
        "two_four_entity_attack_deaths_bedwars": 41,
        "two_four_games_played_bedwars": 22,
        "two_four_gold_resources_collected_bedwars": 345,
        "two_four_iron_resources_collected_bedwars": 3601,
        "two_four_items_purchased_bedwars": 591,
        "two_four_permanent_items_purchased_bedwars": 37,
        "two_four_resources_collected_bedwars": 3981,
        "two_four_wins_bedwars": 16,
        "two_four_beds_lost_bedwars": 7,
        "two_four_entity_attack_final_deaths_bedwars": 7,
        "two_four_entity_attack_kills_bedwars": 25,
        "two_four_final_deaths_bedwars": 7,
        "two_four_kills_bedwars": 73,
        "two_four_void_deaths_bedwars": 49,
        "two_four_final_kills_bedwars": 21,
        "two_four_void_final_kills_bedwars": 5,
        "two_four_void_kills_bedwars": 39,
        "two_four_entity_attack_final_kills_bedwars": 14,
        "two_four_diamond_resources_collected_bedwars": 21,
        "two_four_emerald_resources_collected_bedwars": 14,
        "two_four_losses_bedwars": 6,
        "two_four_fall_kills_bedwars": 8,
        "two_four_fall_deaths_bedwars": 4,
        "two_four_fall_final_kills_bedwars": 2,
        "two_four_magic_kills_bedwars": 1
      },
      "Arcade": {
        "coins": 3000,
        "lastTourneyAd": 1608441326818 // Unix Timestamp of last tournament notification?
      },
      "SkyBlock": {
        "profiles": {
          "b7468f98c55d4b4aa57a14ff9ebbc0b3": {
            "profile_id": "b7468f98c55d4b4aa57a14ff9ebbc0b3",
            "cute_name": "Cucumber"
          }
        }
      },
      "SkyWars": {
        "souls": 10,
        "levelFormatted": "§71⋆",
        // Unlocked kits?
        "packages": [
          "update_solo_team_kits2",
          "update_solo_team_kits_and_perks",
          "update_opals_prestige",
          "update_solo_team_perk_levels"
        ],
        "hunters_vs_beasts_explained": 2,
        "hunters_vs_beasts_explained_last": 1602882344495,
        "games_played_skywars": 2
      },
      "HungerGames": {
        "lastTourneyAd": 1602882197254 // Unix Timestamp of last tournament notification?
      },
      "Duels": {
        "all_modes_rookie_title_prestige": 1,
        "bridge_rookie_title_prestige": 1,
        "skywars_rookie_title_prestige": 1,
        "uhc_rookie_title_prestige": 1,
        "blitz_rookie_title_prestige": 1,
        "op_rookie_title_prestige": 1,
        "mega_walls_rookie_title_prestige": 1,
        "bow_rookie_title_prestige": 1,
        "no_debuff_rookie_title_prestige": 1,
        "classic_rookie_title_prestige": 1,
        "tnt_games_rookie_title_prestige": 1,
        "sumo_rookie_title_prestige": 1,
        "combo_rookie_title_prestige": 1,
        "duels_recently_played2": "BRIDGE_DUEL##",
        "chat_enabled": "on",
        "show_lb_option": "on",
        "selected_2_new": "blitz",
        "selected_1_new": "sumo",
        "games_played_duels": 3,
        "best_bridge_winstreak": 1,
        "bridgeMapWins": [
          "Mister Cheesy",
          "Boo"
        ],
        "maps_won_on": [
          "Mister Cheesy",
          "Boo"
        ],
        "best_overall_winstreak": 1,
        "best_winstreak_mode_bridge_duel": 1,
        "current_winstreak_mode_bridge_duel": 1,
        "current_winstreak": 1,
        "current_bridge_winstreak": 1,
        "blocks_placed": 225,
        "bow_hits": 4,
        "bow_shots": 13,
        "bridge_deaths": 18,
        "bridge_duel_blocks_placed": 225,
        "bridge_duel_bow_hits": 4,
        "bridge_duel_bow_shots": 13,
        "bridge_duel_bridge_deaths": 18,
        "bridge_duel_bridge_kills": 9,
        "bridge_duel_damage_dealt": 16,
        "bridge_duel_goals": 10,
        "bridge_duel_health_regenerated": 17,
        "bridge_duel_rounds_played": 3,
        "bridge_duel_wins": 2,
        "bridge_kills": 9,
        "coins": 55,
        "damage_dealt": 16,
        "goals": 10,
        "health_regenerated": 17,
        "rounds_played": 3,
        "wins": 2,
        "bridge_duel_losses": 1,
        "losses": 1,
        "bridge_duel_melee_hits": 2,
        "bridge_duel_melee_swings": 11,
        "melee_hits": 2,
        "melee_swings": 11
      }
    },
    // Challenges you've done X times
    "challenges": {
      "all_time": {
        "BEDWARS__offensive": 19,
        "BEDWARS__support": 7,
        "DUELS__feed_the_void_challenge": 2
      }
    },

    // Map select quickjoin?
    "quickjoin_timestamp": 1610838841433,
    "quickjoin_uses": 3,

    // Parkour
    "parkourCheckpointBests": {
      "Duels": {
        "0": 13309
      },
      "Bedwars": {
        "0": 10453,
        "1": 7879,
        "2": 10078,
        "3": 19566
      }
    },
    "parkourCompletions": {
      "Bedwars": [
        {
          "timeStart": 1608441751278,
          "timeTook": 49090
        },
        {
          "timeStart": 1610910533170,
          "timeTook": 48070
        }
      ]
    },

    // Unknown
    "summer2020Cooldowns": {
      "NORMAL0": true,
      "NORMAL1": true,
      "NORMAL2": true
    },
    "monthlycrates": {
      "7-2020": {
        "REGULAR": true
      }
    },
    "eugene": {
      "dailyTwoKExp": 1595548120933
    },
  }
}
```
