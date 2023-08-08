# HypixelAPI Java Examples

This codebase serves as examples for how to integrate the HypixelAPI into your project.

## A small overview of where to find things 

- The main/shared API initalization and global constant defention is in [ExampleUtil](https://github.com/HypixelDev/PublicAPI/blob/master/hypixel-api-example/src/main/java/net/hypixel/api/example/ExampleUtil.java)

   ExampleUtil also defines the ` getTestConsumer` an often seen error handler, that prints out the response if sucess, and throws if failure.
  Note: When it prints for sucess, it prints out a stringfied version of the object/response.
- An example of getting a list of active and qued boosters is in [GetBoostersExample](https://github.com/HypixelDev/PublicAPI/blob/master/hypixel-api-example/src/main/java/net/hypixel/api/example/GetBoostersExample.java)

  Stringfied response can be found [here](https://github.com/HypixelDev/PublicAPI/blob/master/hypixel-api-core/src/main/java/net/hypixel/api/reply/BoostersReply.java#L87C1-L99C6)
- An example of getting the current player count is in [GetCountsExample](https://github.com/HypixelDev/PublicAPI/blob/master/hypixel-api-example/src/main/java/net/hypixel/api/example/GetCountsExample.java)
- An example of getting information about a guild is in [GetGuildExample](https://github.com/HypixelDev/PublicAPI/blob/master/hypixel-api-example/src/main/java/net/hypixel/api/example/GetGuildExample.java)

  Information includes: guild members usernames, the guilds name and tag, gexp breakdown, level, their ranks, and more!
- An example of getting the current leaderboards is in [GetLeaderboardsExample](https://github.com/HypixelDev/PublicAPI/blob/master/hypixel-api-example/src/main/java/net/hypixel/api/example/GetLeaderboardsExample.java)

  Stringfied response can be found [here](https://github.com/HypixelDev/PublicAPI/blob/master/hypixel-api-core/src/main/java/net/hypixel/api/reply/LeaderboardsReply.java#L55C1-L63C10)
- An example of getting pet information can be found [here, in GetPetsExample](https://github.com/firetrqck/PublicAPI/blob/master/hypixel-api-example/src/main/java/net/hypixel/api/example/GetPetsExample.java)

  Information includes pet rarity, rarity colors, wheter a player posses that pet, and more!
- An example of getting player information can be found in [GetPlayerExample](https://github.com/HypixelDev/PublicAPI/blob/master/hypixel-api-example/src/main/java/net/hypixel/api/example/GetPlayerExample.java)

  Information includes their: UUID, network level(exact), rank, mc version, and more!
- An example of getting the current punishment stats can be found in [GetPunishmentStatsExample](https://github.com/HypixelDev/PublicAPI/blob/master/hypixel-api-example/src/main/java/net/hypixel/api/example/GetPunishmentStatsExample.java)
- An example of getting recent games of a UUID(in the example, hypixel's) can be found in [GetRecentGamesExample](https://github.com/HypixelDev/PublicAPI/blob/master/hypixel-api-example/src/main/java/net/hypixel/api/example/GetRecentGamesExample.java)
<!-- in development and not much information available, done to my understanding -->
- An example of getting a games challenges can be found in [GetResourceExample](https://github.com/HypixelDev/PublicAPI/blob/master/hypixel-api-example/src/main/java/net/hypixel/api/example/GetResourceExample.java)
- An example of getting a player's status can be found in [GetStatusExample](https://github.com/HypixelDev/PublicAPI/blob/master/hypixel-api-example/src/main/java/net/hypixel/api/example/GetStatusExample.java)

  Note: this is not a reliable indicator of online status, as in game a player can run `/status offline` to set their status to offline

<!-- I have not the slightest clue what KeyInfoExample even is -->
