# Documentation  
within this folder, you will find detailed instructions on the various functions the Hypixel Public API allows for.  
*please keep in mind, that this documentation had URL based HTTP requests, and is meant to be converted into you're own respective programming language in practise*  
## Game IDs  
in here, I have provided you with a convenient guide to game IDs as when the API references a Hypixel game it uses a ID rather than String.  
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
## Date and Time  
Some methods will provide a time but all you will see is a number. many programming languages do provide an inbuilt method of converting that number but just in case you do not have that option, the number is a indication of how many milliseconds since January 1 1970 (hypixel is NOT the ones who chose 1970 its just programming stuff)  
## Response Format  
all Hypixel Public API requests will be replied to with either the fake 404 error or it will respond in a data type known as ```JSON```  
In case you are unaware, ```JSON``` stands for **J**ava**S**cript **O**bject **N**otation, and as you assume it is originated in ```JavaScript```  
Because of the simplicity and easy implementation JSON provides, it began being made an inbuilt data storage method within most programming languages  
an example of JSON would be:  
> {  
>     hypixelLevel: 10,  
>     hypixelAchievements: {  
>         exampleAchievement: 'unlocked'  
>     },  
>     username: 'hypixel'  
> }  
## Final Note  
please be aware that if you see ```example1 | example2``` it means that the request has to choose between them.  
if it appears twice on the same line, for example: ```byName | byUuid```: ```Name | UUID```, then you have to choose between: ```byName: Name``` or ```byUuid: UUID```  