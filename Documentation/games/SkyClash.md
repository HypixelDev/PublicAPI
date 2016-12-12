Below you can find the current spec for SkyClash stats.

Example Stats: https://gist.github.com/Plancke/5b5cbb93cf2241490391877a5538dd60

```json
{
	"kits": {
		"__desc": "All available kits, if cost is not present the kit is unlocked by default. Else the player will have {package} in their packages. Total kit level is determined by upgrades stored as {{package}_{upgradeType}}",
		"list": {
			"ARCHER": {
				"display": "Archer",
				"package": "kit_archer",
				"description": "Archers specialize in long-range combat by using a powerful bow and enhanced arrows."
			},
			"SCOUT": {
				"display": "Scout",
				"package": "kit_scout",
				"description": "Scouts are fast and agile. They are able to get to chests and other areas faster than other kits.",
				"cost": 1000
			},
			"GUARDIAN": {
				"display": "Guardian",
				"package": "kit_guardian",
				"description": "Guardians become resistant when their health gets low and have access to defensive Splash Potions."
			},
			"SWORDSMAN": {
				"display": "Swordsman",
				"package": "kit_swordsman",
				"description": "Swordsmen have mastered a sword technique that allows them to deal extra damage when they first hit a target."
			},
			"CLERIC": {
				"display": "Cleric",
				"package": "kit_cleric",
				"description": "Clerics use a holy book as a weapon and are capable of healing themselves (and nearby team members).",
				"cost": 1000
			},
			"FROST_KNIGHT": {
				"display": "Frost Knight",
				"package": "kit_frost_knight",
				"description": "Frost Knights use snowballs and snow golems to knock enemies back and gain speed boosts.",
				"cost": 1000
			},
			"ASSASSIN": {
				"display": "Assassin",
				"package": "kit_assassin",
				"description": "Assassins use ender pearls to teleport around and turn invisible, allowing them to take opponents by surprise.",
				"cost": 1000
			},
			"NECROMANCER": {
				"display": "Necromancer",
				"package": "kit_necromancer",
				"description": "Necromancers control undead minions to do their bidding and can turn dead opponents into zombies.",
				"cost": 1000
			},
			"BERSERKER": {
				"display": "Berserker",
				"package": "kit_berserker",
				"description": "Berserkers are offensive powerhouses, becoming even stronger when their health gets low.",
				"cost": 1000
			}
		},
		"upgradeCosts": {
			"MINOR": [
				2000,
				4000,
				10000,
				20000,
				50000,
				100000,
				250000
			],
			"MAJOR": -1,
			"MASTER": 500000
		}
	},
	"cards": {
		"__desc": "Card levels are stored as {package}, duplicates stored as {{package}_duplicates}.",
		"list": {
			"ALCHEMY": {
				"display": "Alchemy",
				"package": "perk_alchemy",
				"tier": "RARE"
			},
			"ARROW_DEFLECTION": {
				"display": "Arrow Deflection",
				"package": "perk_arrow_deflection",
				"tier": "LEGENDARY"
			},
			"BLAST_PROTECTION": {
				"display": "Blast Protection",
				"package": "perk_blast_protection",
				"tier": "COMMON"
			},
			"BLAZING_ARROWS": {
				"display": "Blazing Arrows",
				"package": "perk_blazing_arrows",
				"tier": "RARE"
			},
			"CHICKEN_BOW": {
				"display": "Chicken Bow",
				"package": "perk_chicken_bow",
				"tier": "LEGENDARY"
			},
			"CREEPER": {
				"display": "Creeper",
				"package": "perk_creeper",
				"tier": "COMMON"
			},
			"DAMAGE_POTION": {
				"display": "Damage Potion",
				"package": "perk_damage_potion",
				"tier": "COMMON"
			},
			"ENDERMAN": {
				"display": "Enderman",
				"package": "perk_enderman",
				"tier": "LEGENDARY"
			},
			"ENDLESS_QUIVER": {
				"display": "Endless Quiver",
				"package": "perk_endless_quiver",
				"tier": "COMMON"
			},
			"ENERGY_DRINK": {
				"display": "Energy Drink",
				"package": "perk_energy_drink",
				"tier": "RARE"
			},
			"EXPLOSIVE_BOW": {
				"display": "Explosive Bow",
				"package": "perk_explosive_bow",
				"tier": "LEGENDARY"
			},
			"FRUIT_FINDER": {
				"display": "Fruit Finder",
				"package": "perk_fruit_finder",
				"tier": "COMMON"
			},
			"GUARDIAN": {
				"display": "Guardian",
				"package": "perk_guardian",
				"tier": "RARE"
			},
			"HEADSTART": {
				"display": "Headstart",
				"package": "perk_headstart",
				"tier": "LEGENDARY"
			},
			"HEARTY_START": {
				"display": "Hearty Start",
				"package": "perk_hearty_start",
				"tier": "RARE"
			},
			"HIT_AND_RUN": {
				"display": "Hit And Run",
				"package": "perk_hit_and_run",
				"tier": "COMMON"
			},
			"HONED_BOW": {
				"display": "Honed Bow",
				"package": "perk_honed_bow",
				"tier": "RARE"
			},
			"INVISIBILITY": {
				"display": "Invisibility",
				"package": "perk_invisibility",
				"tier": "RARE"
			},
			"IRON_GOLEM": {
				"display": "Iron Golem",
				"package": "perk_iron_golem",
				"tier": "LEGENDARY"
			},
			"MARKSMAN": {
				"display": "Marksman",
				"package": "perk_marksman",
				"tier": "COMMON"
			},
			"NUTRITIOUS": {
				"display": "Nutritious",
				"package": "perk_nutritious",
				"tier": "LEGENDARY"
			},
			"PACIFY": {
				"display": "Pacify",
				"package": "perk_pacify",
				"tier": "COMMON"
			},
			"PEARL_ABSORPTION": {
				"display": "Pearl Absorption",
				"package": "perk_pearl_absorption",
				"tier": "COMMON"
			},
			"RAMPAGE": {
				"display": "Rampage",
				"package": "perk_rampage",
				"tier": "LEGENDARY"
			},
			"REGENERATION": {
				"display": "Regeneration",
				"package": "perk_regeneration",
				"tier": "RARE"
			},
			"RESISTANT": {
				"display": "Resistant",
				"package": "perk_resistant",
				"tier": "COMMON"
			},
			"SHARPENED_SWORD": {
				"display": "Sharpened Sword",
				"package": "perk_sharpened_sword",
				"tier": "RARE"
			},
			"SKELETON_JOCKEY": {
				"display": "Skeleton Jockey",
				"package": "perk_skeleton_jockey",
				"tier": "RARE"
			},
			"SNOW_GOLEM": {
				"display": "Snow Golem",
				"package": "perk_snow_golem",
				"tier": "RARE"
			},
			"SUGAR_RUSH": {
				"display": "Sugar Rush",
				"package": "perk_sugar_rush",
				"tier": "COMMON"
			},
			"SUPPLY_DROP": {
				"display": "Supply Drop",
				"package": "perk_supply_drop",
				"tier": "RARE"
			},
			"TRIPLESHOT": {
				"display": "Triple Shot",
				"package": "perk_tripleshot",
				"tier": "LEGENDARY"
			},
			"WINGED_BOOTS": {
				"display": "Winged Boots",
				"package": "perk_winged_boots",
				"tier": "LEGENDARY"
			},
			"WITCH": {
				"display": "Witch",
				"package": "perk_witch",
				"tier": "LEGENDARY"
			},
			"VOID_MAGNET": {
				"display": "Void Magnet",
				"package": "perk_void_magnet",
				"tier": "COMMON"
			},
			"VOID_WARRANTY": {
				"display": "Void Warranty",
				"package": "perk_void_warranty",
				"tier": "LEGENDARY"
			}
		},
		"duplicatesNeeded": [
			0,
			2,
			8,
			32,
			100
		]
	},
	"trackedStats": {
		"__desc": "Stats that are tracked in this gamemode, stored globally as {field} and per kit as {{field}_{kitPackage}}",
		"list": [
			"kills",
			"melee_kills",
			"bow_kills",
			"void_kills",
			"mob_kills",
			"longest_bow_shot",
			"longest_bow_kill",
			"bow_shots",
			"bow_hits",
			"mobs_killed",
			"fastest_win_solo",
			"fastest_win_doubles",
			"fastest_win_four_teams",
			"fastest_win_team_war",
			"most_kills_game",
			"enderchests_opened",
			"solo_wins",
			"doubles_wins",
			"four_teams_wins",
			"team_war_wins",
			"games_played",
			"assists",
			"deaths",
			"damage",
			"quits"
		]
	}
}
```