# Resources

| Endpoint | Authentication Required |
| --- | --- |
| https://api.hypixel.net/resources | no |

## Description

?> Does _not_ require an API key!

Provides an endpoint to retrieve resources which don't change often.

All resources return `lastUpdated` field which is a Unix milliseconds value of when the file was last updated. Some files, such as for SkyBlock will also return the game version they were generated for.

## Supported Resources

| Resource Name | Request |
| --- | --- |
| Achievements | https://api.hypixel.net/resources/achievements |
| Challenges | https://api.hypixel.net/resources/challenges |
| Quests | https://api.hypixel.net/resources/quests |
| Guild Achievements | https://api.hypixel.net/resources/guilds/achievements |
| Guild Permissions | https://api.hypixel.net/resources/guilds/permissions |
| Skyblock Collections | https://api.hypixel.net/resources/skyblock/collections |
| Skyblock Skills | https://api.hypixel.net/resources/skyblock/skills |
