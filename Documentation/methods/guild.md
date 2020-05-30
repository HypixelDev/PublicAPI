# guild

## Description
Returns information about given guild.

See [`resources.md`](/Documentation/methods/resources.md) for a JSON list of Guild achievements and a JSON list of Guild permissions

## Parameters
- key
- id _Guild id returned by [findGuild](https://github.com/HypixelDev/PublicAPI/blob/master/Documentation/methods/findGuild.md)_
- player (Lookup by Player UUID)
- name (Lookup by Guild name)

## Example Response
```js
{
  "success": true,
  "guild": {
    "_id": "52e57a1c0cf2e250d1cd00f8", // Guild ID
    "created": 1390770716373, // Timestamp this guild was created at
    "name": "The Sloths",
    "name_lower": "the sloths",
    "description": "The sloths", // Description of this guild that appears in the guild list and /g info
    "tag": "SLOTH",
    "tagColor": "DARK_AQUA", // Color of this guild's tag, if set
    "exp": 2238673,
    "members": [
      {
        "uuid": "f7c77d999f154a66a87dc4a51ef30d19",
        "rank": "GUILDMASTER",
        "joined": 1390770716373, // Timestamp this member joined at
        "expHistory": { // Last 7 days of exp earned by this member
          "2020-05-25": 108,
          "2020-05-24": 404
        },
        "questParticipation": 4, // The number of challenges completed that count towards the current quest
        "mutedTill": 1399507406038 // Timestamp this member will be unmuted at
      }
    ],
    "achievements": { // Guild achievements earned and the current progress
      "ONLINE_PLAYERS": 4,
      "EXPERIENCE_KINGS": 40062,
      "WINNERS": 2
    },
    "ranks": [
      {
        "name": "Member",
        "default": true, // Whether this rank is the defualt rank a player gets when they join a guild
        "created": 1, // Timestamp this rank was created at
        "priority": 1, // Rank priority - Higher number = higher up in the heirarchy
        "tag": "Member" // Rank tag that appears in guild chat, or null if none
      }
    ],
    "joinable": true, // Whether this guild can be joined using /g join
    "legacyRanking": 10446, // Ranking in the number of guild coins owned in the legacy guild system (0-indexed)
    "publiclyListed": false, // Whether this guild is listed in the Guild Finder
    "hideGmTag": true, // Whether guild master tag is hidden in guild chat
    "preferredGames": [ // This guild's set preferred games
      "ARCADE",
      "SPEED_UHC",
      "UHC"
    ],
    "chatMute": 1590703490783, // Timestamp guild chat will be unmuted at, or 0 if guild chat is not muted
    "guildExpByGameType": { // Amount of EXP earned for this guild by which game it was earned in
      "TNTGAMES": 1312,
      "VAMPIREZ": 4495,
      "ARCADE": 10285
    },
    "banner": { // This guild's Minecraft banner - Displayed on the Hypixel forums page
                // See https://minecraft.gamepedia.com/Banner/Patterns for pattern IDs
      "Base": "0", // Base color
      "Patterns": [ // Array of each layer on the banner pattern
        {
          "Pattern": "vhr", // Pattern ID for this layer
          "Color": "15" // Color of this layer (16-color 0-indexed pallete)
        },
        {
          "Pattern": "vh",
          "Color": "15"
        }
      ]
    }
  }
}
```
