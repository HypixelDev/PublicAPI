# HypixelAPI Java Examples

This codebase serves as an example of how to integrate the HypixelAPI into your project.

## A small overview of where to find things 

- The main/shared API initalization and global constant defention are in [ExampleUtil](https://github.com/HypixelDev/PublicAPI/blob/master/hypixel-api-example/src/main/java/net/hypixel/api/example/ExampleUtil.java)

   ExampleUtil also defines the `getTestConsumer` an often seen error handler, that prints out the response if sucess, and throws if failure.
  Note: A stringified version of the response object will be printed on sucess
- An example of getting the list of active and qued boosters is in [GetBoostersExample](https://github.com/HypixelDev/PublicAPI/blob/master/hypixel-api-example/src/main/java/net/hypixel/api/example/GetBoostersExample.java)t

  Stringfied response can be found [here](https://github.com/HypixelDev/PublicAPI/blob/master/hypixel-api-core/src/main/java/net/hypixel/api/reply/BoostersReply.java#L87C1-L99C6)
- An example of getting the current player count is in [GetCountsExample](https://github.com/HypixelDev/PublicAPI/blob/master/hypixel-api-example/src/main/java/net/hypixel/api/example/GetCountsExample.java)
- An example of getting information about a guild is in [GetGuildExample](https://github.com/HypixelDev/PublicAPI/blob/master/hypixel-api-example/src/main/java/net/hypixel/api/example/GetGuildExample.java)

  Response data includes the guild's name,tag, gexp breakdown, level and more!
- An example of getting the current leaderboards is in [GetLeaderboardsExample](https://github.com/HypixelDev/PublicAPI/blob/master/hypixel-api-example/src/main/java/net/hypixel/api/example/GetLeaderboardsExample.java)

  A stringfied response can be found [here](https://github.com/HypixelDev/PublicAPI/blob/master/hypixel-api-core/src/main/java/net/hypixel/api/reply/LeaderboardsReply.java#L55C1-L63C10)
- An example of getting pet information can be found [here, in GetPetsExample](https://github.com/firetrqck/PublicAPI/blob/master/hypixel-api-example/src/main/java/net/hypixel/api/example/GetPetsExample.java)

  Response data includes pet rarity, rarity colors, whether a player posses that pet, and more!
- An example of getting player information can be found in [GetPlayerExample](https://github.com/HypixelDev/PublicAPI/blob/master/hypixel-api-example/src/main/java/net/hypixel/api/example/GetPlayerExample.java)

  Response data includes the players: UUID, network level(exact), rank, mc version, and more!
- An example of getting the current punishment stats can be found in [GetPunishmentStatsExample](https://github.com/HypixelDev/PublicAPI/blob/master/hypixel-api-example/src/main/java/net/hypixel/api/example/GetPunishmentStatsExample.java)
- An example of getting recent games of a UUID(using hypixel's as an example) can be found in [GetRecentGamesExample](https://github.com/HypixelDev/PublicAPI/blob/master/hypixel-api-example/src/main/java/net/hypixel/api/example/GetRecentGamesExample.java)
<!-- in development and not much information available, done to my understanding -->
- An example of getting a games challenges can be found in [GetResourceExample](https://github.com/HypixelDev/PublicAPI/blob/master/hypixel-api-example/src/main/java/net/hypixel/api/example/GetResourceExample.java)
- An example of getting a player's status can be found in [GetStatusExample](https://github.com/HypixelDev/PublicAPI/blob/master/hypixel-api-example/src/main/java/net/hypixel/api/example/GetStatusExample.java)

  Note: this is not a perfectc indicator of online status, as in game a player can run `/status offline` to set their status to offline

<!-- I have not the slightest clue what KeyInfoExample even is -->
