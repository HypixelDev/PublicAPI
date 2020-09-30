# GET /resources/guilds/permissions

## Description

Get guild's permission list

## Parameters

## Response

* success (bool): Response status
* lastUpdated (int): Last updated timestamp
* permissions (list): List of dictionnary with local as key and [permission](../../../../permission.md) object as value.

## Example Response

Answer for: https://api.hypixel.net/resources/guilds/permissions

```js
{
  "success": true,
  "lastUpdated": 1570754198669,
  "permissions": [
    {
      "en_us": {
        "name": "Modify Guild Name",
        "description": "Change the guild's name.",
        "item": {
          "name": "name_tag"
        }
      }
    },
    {
      "en_us": {
        "name": "Modify Guild MOTD",
        "description": "Change the guild's message of the day.",
        "item": {
          "name": "paper"
        }
      }
    },
    {
      "en_us": {
        "name": "Modify Guild Tag",
        "description": "Change the guild's tag.",
        "item": {
          "name": "sign"
        }
      }
    },
    {
      "en_us": {
        "name": "Change ranks",
        "description": "Promote or demote members (up to their own rank).",
        "item": {
          "name": "crafting_table"
        }
      }
    },
    {
      "en_us": {
        "name": "Guild Finder options",
        "description": "Change how the guild is shown in the Guild Finder, if at all.",
        "item": {
          "name": "book"
        }
      }
    },
    {
      "en_us": {
        "name": "Officer Chat",
        "description": "Send and receive messages in the officer chat.",
        "item": {
          "name": "emerald"
        }
      }
    },
    {
      "en_us": {
        "name": "Kick Members",
        "description": "Kick members from the guild.",
        "item": {
          "name": "barrier"
        }
      }
    },
    {
      "en_us": {
        "name": "Mute Members",
        "description": "Mute guild members.",
        "item": {
          "name": "redstone"
        }
      }
    },
    {
      "en_us": {
        "name": "Invite members",
        "description": "Invite members to the guild.",
        "item": {
          "name": "arrow"
        }
      }
    },
    {
      "en_us": {
        "name": "Audit Log",
        "description": "View the audit log.",
        "item": {
          "name": "lever"
        }
      }
    },
    {
      "en_us": {
        "name": "View Stats",
        "description": "View a guild member's stats.",
        "item": {
          "name": "diamond"
        }
      }
    },
    {
      "en_us": {
        "name": "Guild Party",
        "description": "Start a guild party.",
        "item": {
          "name": "chest"
        }
      }
    },
    {
      "en_us": {
        "name": "Guild Settings",
        "description": "Change the guild settings.",
        "item": {
          "name": "comparator"
        }
      }
    },
    {
      "en_us": {
        "name": "Change Guild Discord",
        "description": "Change the guild's Discord join link.",
        "item": {
          "name": "filled_map"
        }
      }
    }
  ]
}
``` 
