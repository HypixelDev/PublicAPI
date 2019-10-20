# gameCounts

## Description
Returns the current player count along with the player count of each public game + mode on the server. 

Due to the large amount of modes and that they can change at anytime we don't currently have a friendly list of mode keys to clean names. This may be added at a later date.
 
## Parameters
- key

## Example Player Count
 ```php
{
    "games": {
        "BUILD_BATTLE": { // The GameType Name
            "modes": {
                "BUILD_BATTLE_HALLOWEEN": 82, // The mode key along with the player count
                "BUILD_BATTLE_GUESS_THE_BUILD": 177,
                "BUILD_BATTLE_TEAMS_NORMAL": 512,
                "BUILD_BATTLE_SOLO_NORMAL": 440,
                "BUILD_BATTLE_SOLO_PRO": 114
            },
            "players": 1453 // Total players for the GameType
        }
    },
    "playerCount": 59715
}
```
