# GET /resources/achievements

# Description

Get the achievements list

## Parameters

## Response

* success (bool): Response status
* lastUpdated (int): Last Updated timestamp
* achievements (dict): Dictionnary of game name as key and object as value:
  * one_time (dict): Dictionnary of name as key and [achievement](../../../object/achievement.md) object as value for one time achievements
  * tiered (dict): Dictionnary of name as key and [achievement](../../../object/achievement.md) object as value for tiered achievements
  * total_points (int): Total achievements point for this game
  * total_legacy_points (int): Total legacy point for this game

## Example Response

Answer for: https://api.hypixel.net/resources/achievements

```js
// Too long
```
