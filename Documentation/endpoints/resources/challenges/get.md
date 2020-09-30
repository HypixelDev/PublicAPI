# GET /resources/challenges

## Description

Get challenge list

## Parameters

## Response

* success (bool): Response status
* lastUpdated (int): Last updated timestamp
* challenges (dict): Dictionnary of game name as key and [challenge](../../../object/challenge.md) list as value

## Example Response

Answer for: https://api.hypixel.net/resources/challenges

```js
{
  "success": true,
  "lastUpdated": 1601556987670,
  "challenges": {
    "arcade": [
      {
        "id": "ARCADE__farm_hunt_challenge",
        "name": "Farm Hunt Challenge",
        "rewards": [
          {
            "type": "MultipliedExperienceReward",
            "amount": 3360
          }
        ]
      },
      {
        "id": "ARCADE__blocking_dead_challenge",
        "name": "Blocking Dead Challenge",
        "rewards": [
          {
            "type": "MultipliedExperienceReward",
            "amount": 3360
          }
        ]
      },
      {
        "id": "ARCADE__bounty_hunter_challenge",
        "name": "Bounty Hunter Challenge",
        "rewards": [
          {
            "type": "MultipliedExperienceReward",
            "amount": 3360
          }
        ]
      },
      [...]
    ]
  }
}
```
