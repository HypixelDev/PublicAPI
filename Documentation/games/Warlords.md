_Please Note_: This is a reformatted and slightly edited version of the original (and likely more updated) guide to reading Warlords Weapons posted [here](http://hypixel.net/threads/guide-how-to-read-weapons-from-the-hypixel-api.274908/).

### Example Weapon JSON Object
```json
{
  "spec": {
    "spec": 2,
    "playerClass": 0
  },
  "ability": 1,
  "abilityBoost": 15,
  "damage": 113,
  "energy": 25,
  "chance": 15,
  "multiplier": 181,
  "health": 380,
  "cooldown": 5,
  "movement": 6,
  "material": "GOLDEN_CARROT",
  "id": 1425426612529,
  "category": "LEGENDARY",
  "crafted": true,
  "upgradeMax": 4,
  "upgradeTimes": 3
}
```
Such objects can be loaded from a player's ```weapon_inventory``` array inside their Warlords stat object ([learn more](Documentation/GameType.md)). 

### Weapon Field Descriptions
| Name | Description |
| ------ | ------------- |
| id   | The time the weapon was unlocked. Used by the ```current_weapon``` field to reference the player's currently selected weapon. |
| spec.spec | The specialization of the weapon's class. This is discussed more below. |
| spec.playerClass | The weapon's class. More information is provided below. |
| material | The material of the weapon. Used to reference it's rarity and name. |

### Player Classes
Each player class is assigned a unique ID (they should never overlap.) This is used to lookup data associated with the class (such as specializations.)

| ID | Name |
| ----- | -------- |
| 0 | Mage |
| 1 | Warrior |
| 2 | Paladin |
| 3 | Shaman |

### Specializations by Player Class
Each specialization is assigned a unique ID (relative to it's parent class.) In order to know which specialization any ID is assigned to, you must know the ID of the class. 

| Class | ID | Name |
| ----- | ---- | ---- |
| 0     | 0  | Pyromancer  |
| 0     | 1  | Cryomancer  |
| 0     | 2  | Aquamancer  |
| 1     | 0  | Berserker   |
| 1     | 1  | Defender    |
| 2     | 0  | Avenger     |
| 2     | 1  | Crusader    |
| 2     | 2  | Protector   |
| 3     | 0  | Thunderlord |
| 3     | 1  | Earthwarden |

### Weapon Materials
The ID is the item's actual underlying Material used in creating it's ItemStack in Bukkit. It's used purely to reference the name and rarity (as well as it's skin.)

| ID | Name | Rarity |
| --- | ---- | --------- |
| WOODEN_AXE | Steel Sword | COMMON |
| STONE_AXE | Training Sword | COMMON |
| GOLDEN_HOE | Hatchet | COMMON |
| IRON_SHOVEL | Hammer | COMMON |
| STONE_PICKAXE | Walking Stick | COMMON |
| SALMON | Scimitar | COMMON |
| ROTTEN_FLESH | Pike | COMMON |
| MUTTON | Claws | COMMON |
| PUMPKIN_PIE | Orc Axe | COMMON |
| RABBIT_STEW | Bludgeon | COMMON |
| IRON_AXE | Demonblade | RARE |
| GOLD_AXE | Venomstrike | RARE |
| DIAMOND_HOE | Gem Axe | RARE |
| GOLDEN_SHOVEL | Stone Mallet | RARE |
| IRON_PICKAXE | World Tree Branch | RARE |
| PUFFERFISH | Golden Gladius | RARE |
| POTATO | Halberd | RARE |
| PORKCHOP | Mandibles | RARE |
| COOKED_COD | Doubleaxe | RARE |
| COOKED_RABBIT | Cudgel | RARE |
| DIAMOND_AXE | Diamondspark | EPIC |
| WOODEN_HOE | Zweireaper | EPIC |
| STONE_HOE | Runeblade | EPIC |
| IRON_HOE | Elven Greatsword | EPIC |
| WOODEN_SHOVEL | Nomegusta | EPIC |
| DIAMOND_SHOVEL | Gemcrusher | EPIC |
| GOLDEN_PICKAXE | Flameweaver | EPIC |
| CLOWNFISH | Magmasword | EPIC |
| MELON | Divine Reach | EPIC |
| STRING | Hammer of Light | EPIC |
| CHICKEN | Nethersteel Katana | EPIC |
| BEEF | Katar | EPIC |
| BREAD | Runic Axe | EPIC |
| MUSHROOM_STEW | Lunar Relic | EPIC |
| COOKED_CHICKEN | Tenderizer | EPIC |
| STONE_SHOVEL | Drakefang | LEGENDARY |
| WOODEN_PICKAXE | Abbadon | LEGENDARY |
| DIAMOND_PICKAXE | Void Twig | LEGENDARY |
| COD | Frostbite | LEGENDARY |
| POISONOUS_POTATO | Ruby Thorn | LEGENDARY |
| APPLE | Enderfist | LEGENDARY |
| BAKED_POTATO | Broccomace | LEGENDARY |
| COOKED_SALMON | Felflame Blade | LEGENDARY |
| COOKED_MUTTON | Amaranth | LEGENDARY |
| COOKED_BEEF | Armblade | LEGENDARY |
| COOKED_PORKCHOP | Gemini | LEGENDARY |
| GOLDEN_CARROT | Void Edge | LEGENDARY |