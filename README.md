Hypixel Public API (Java)
=
This is a Java implementation of the Hypixel API
## Documentation
### Methods
#### player
retrieves a player's Hypixel Data  
**parameters**  
> **(required)** key: ```API key```  
> **(required)** player: ```UUID | username```  

#### findGuild  
retrieves the UUID of a player's Guild (use with ```guild```)  
**parameters**  
> **(required)** key: ```API key```  
> **(required)** ```byName | byUuid```: ```Guild Name | Player UUID```  

#### guild
retrieves a guild's data  
**parameters**  
> **(required)** key: ```API key```  
> **(required)** id: ```Guild UUID```  

#### friends (deprecated)  
Will only return pre name change friendships  
**parameters**  
> **(required)** key: ```API key```  
> **(required)** ```player | uuid```: ```username | Player UUID```  

#### session  
return's session data of a player  
**parameters**  
> **(required)** key: ```API key```  
> **(required)** uuid: ```Player UUID```  

#### key  
provide's information on the usage of an API key  
**parameters**  
> **(required)** key: ```API key```  

#### boosters  
returns a (rather lengthy) list of boosters. To help you out, here is a quick Game ID guide as the API uses a Number instead of a String to identify the game  
> Quakecraft: 2  
> Walls: 3  
> Paintball: 4  
> Blitz Survival Games: 5  
> The TNT Games: 6  
> VampireZ: 7  
> Mega Walls: 13  
> Arcade: 14  
> Arena Brawl: 17  
> Cops and Crims: 21  
> UHC Champions: 20  
> Warlords: 23  
> Smash Heroes: 24  
> Turbo Kart Racers: 25  
> SkyWars: 51  
> Crazy Walls: 52  
> Speed UHC: 54  

**parameters**  
> **(required)** key: ```API key```  



### Query Limitations
The API server has a request limit of 120 queries per minute. Abuse of the API will lead to your API key being banned.

### Obtaining an API Key
You can obtain an API key by joining ```mc.hypixel.net``` with a valid Minecraft account and running the /api command. You will then be assigned a unique key that is to remain **private**.

### Dependencies
The Hypixel PublicAPI has the following dependencies:
* Google Gson library
* Guava: Google Core Libraries for Java
* Apache HttpClient

### Bug Reporting
You can create an issue here on GitHub to report a bug with the API or to suggest enhancements.

### Copyright
HypixelAPI © 2016

### TODO
* Add a proper copyright header to all files.
* Deploy to a public maven repo.
