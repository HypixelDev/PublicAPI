# guild

## Description
Returns information about given guild.

See [`resources.md`](/Documentation/methods/resources.md) for a JSON list of Guild achievements and a JSON list of Guild permissions

## Parameters
- key
- id _Guild id returned by [findGuild](https://github.com/HypixelDev/PublicAPI/blob/master/Documentation/methods/findGuild.md)_
- player (Lookup by Player UUID)
- name (Lookup by Guild name)

## Example
```php
{
	"success": true,
	"guild": {
		"_id": "536ac9ceed5032e412eb2ae1", // Guild ID
		"coins": 459178, // Number of coins this guild has - Deprecated
		"coinsEver": 619178, // Number of coins this guild has ever earned - Deprecated
		"created": 1399507406038, // Timestamp that this guild was created at
		"members": [{ // Member list
			"uuid": "e72660b18b88424ea23f2cdd3597c581", // This user's non-dashed UUID
			"rank": "GUILDMASTER", // This user's rank in this guild
			"joined": 1512200875108, // Timestamp the member joined
			"dailyCoins-12-6-2018": 1560, // Guild coins earned by this user on any given day - Deprecated
			"questParticipation": 4, // The number of challenges completed that count towards the current quest
			"mutedTill":1532797185516, // Timestamp that this user will be unmuted at
			"expHistory": {
				"2019-10-17": 17260 // Last 7 days of exp earned for this member
			}
		}, {
			"uuid": "6407035ace144175a731fac937d410cd",
			"rank": "Co-Owner",
			"joined": 1512074687198,
			"last_rank_modification": { // Data for last changes to this users rank
				"at": 1532715237290, // Timestamp the rank was changed
				"uuid": "e72660b18b88424ea23f2cdd3597c581" // User the rank was changed by
			},
			"expHistory": {
				"2019-10-17": 15812
			}
		}
    		// ...
    		],
		"name": "Froggy", // Name of this guild
		"name_lower": "froggy" // Name of this guild in all lower case
		"tagColor": "DARK_GREEN", // Color of this guild's guild tag, if set
		"joinable": false, // Whether this guild can be joined using /g join
		"legacyRanking": 10446, // Ranking in the number of guild coins owned in the legacy guild system (0-indexed)
		"exp": 619178, // Total experience this guild has
		"description": "This guild is cool \u0026 fun!", // Description of this guild that appears in the guild list and /g info
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
		"guildExpByGameType": { // Amount of EXP earned for this guild by which game it was earned in
			"SKYWARS": 173,
			"PROTOTYPE": 3100,
			"BUILD_BATTLE": 46095
			// ...
		},
		"tag": "HYPIXL", // This guild's Guild tag
		"preferredGames": ["ARCADE", "SPEED_UHC", "UHC"], // This guild's set preferred games
		"chatThrottle": 10000, // Number of milliseconds users must wait between messages
		"vipCount": 22, // The number of players in the guild with VIP or VIP+ (Unreliable)
		"mvpCount": 39, // The number of players in the guild with MVP or higher (Unreliable)
		"publiclyListed": false, // Whether the guild is listed in the Guild Finder or not
		"chatMute": 1532797565473 // Timestamp that the entire guild chat will be unmuted at
	}
}
```
