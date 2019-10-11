# resources

## Description

Provides an endpoint to retrieve resources which don't change often. This does _not_ require an API key.

All resources return `lastUpdated` field which is a Unix milliseconds value of when the file was last updated. Some files, such as for SkyBlock will also return the game version they were generated for.

## Supported Resources
- achievements
- challenges
- quests
- guilds/achievements
- guilds/permissions
- skyblock/collections
- skyblock/skills

## Example Request
```
https://api.hypixel.net/resources/achievements
```