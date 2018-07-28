# guild

## Description
Returns information about given guild.

See [`GuildAchievements.json`](/Documentation/misc/GuildAchievements.json) for a JSON list of Guild achievements<br>
See [`GuildPermissions.json`](/Documentation/misc/GuildPermissions.json) for a JSON list of Guild permissions.

## Parameters
- key
- id _Guild id returned by [findGuild](https://github.com/HypixelDev/PublicAPI/blob/master/Documentation/methods/findGuild.md)_

## Example
```php
{
	"success": true,
	"guild": {
		"_id": "536ac9ceed5032e412eb2ae1", // Guild ID
		"bankSizeLevel": 2, // Size of the guild's coin bank - Deprecated
		"canMotd": true, // Whether this guild can have an MOTD - Deprecated
		"canParty": true, // Whether this guild can start a guild party - Deprecated
		"canTag": true, // Whether this guild can set a guild tag - Deprecated
		"coins": 459178, // Number of coins this guild has - Deprecated
		"coinsEver": 619178, // Number of coins this guild has ever earned - Deprecated
		"created": 1399507406038, // Timestamp that this guild was created at
		"memberSizeLevel": 6, // Size level of members this guild can have - Deprecated
		"members": [{ // Member list
			"uuid": "e72660b18b88424ea23f2cdd3597c581", // This user's non-dashed UUID
			"rank": "GUILDMASTER", // This user's rank in this guild
			"joined": 1512200875108, // Timestamp the member joined
			"dailyCoins-12-6-2018": 1560, // Guild coins earned by this user on any given day - Deprecated
			"questParticipation": 4 // The number of challenges completed that count towards the current quest
		}, {
			"uuid": "6407035ace144175a731fac937d410cd",
			"rank": "Co-Owner",
			"joined": 1512074687198,
			"last_rank_modification": { // Data for last changes to this users rank
				"at": 1532715237290, // Timestamp the rank was changed
				"uuid": "e72660b18b88424ea23f2cdd3597c581" // User the rank was changed by
			}
		}
    		// ...
    		],
		"name": "Froggy", // Name of this guild
		"tagColor": "GOLD", // Color of this guild's guild tag, if set
		"joinable": false, // Whether this guild can be joined using /g join
		"legacyRanking": 10446, // Ranking in the number of guild coins owned in the legacy guild system (0-indexed)
		"exp": 619178, // Total experience this guild has
		"discord": "https://discord.gg/ZWquWcs", // Link to this Guild's discord server (/g discord)
		"description": "Private guild", // Description of this guild that appears in the guild list and /g info
		"dailyCoins-27-6-2018": 20000, // The number of guild coins earned on any given day - Deprecated
		"achievements": { // Guild achievements earned and the current progress
			"WINNERS": 803,
			"EXPERIENCE_KINGS": 243576
      			// ...
		},
		"ranks": [{
			"name": "Member", // Display name
			"permissions": [], // Array of the IDs of permissions this rank has
			"default": true, // Whether this rank is the defualt rank a user gets when they join a guild
			"tag": null, // Rank tag that appears in guild chat, or null if none
			"created": 1532713597406, // Timestamp of rank creation
			"priority": 1 // Rank priority - Higher number = higher up in the heirarchy 
		}, {
			"name": "Co-Owner",
			"permissions": [5, 6, 7, 8, 9, 10, 11],
			"default": false,
			"tag": "CO",
			"created": 1532715059862,
			"priority": 2
		}
    		// ...
    		],
    		"banner": { // This guild's Minecraft banner - Displayed on the Hypixel forums page
                	    // See https://minecraft.gamepedia.com/Banner/Patterns for pattern IDs
			"Base": "0", // Base color
			"Patterns": [ // Array of each layer on the banner pattern
				{
					"Pattern": "vhr", // Pattern ID for this layer
					"Color": "15" // Color of this layer (16-color 0-indexed pallete)
				}, {
					"Pattern": "vh",
					"Color": "15"
				}
      				// ...
      			]
		},
		"tag": "HYPIXL", // This guild's Guild tag
		"preferredGames": ["ARCADE", "SPEED_UHC", "UHC"], // This guild's set preferred games
		"chatThrottle": 10000, // Number of milliseconds users must wait between messages
		"publiclyListed": false // Whether the guild is listed in the Guild Finder or not
	}
}
```
