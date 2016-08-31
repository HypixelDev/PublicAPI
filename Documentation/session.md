# session  
## Description  
this method will return the session data of a player if they are currently online on Hypixel, if the player is not online the response will be a ```null``` error
**THIS METHOD WILL NEVER WORK ON STAFF MEMBERS**  [//]: <> (At the time of typing this, you CAN query people with youtuber rank) [//]: <> (if you do try to do it on a staff member, it will return this JSON response {"success":false,"cause":"staff player"})  
## Parameters  
**(required)** key: *Xx3XAMPL3K3YxX*  
**(required)** ```player | uuid```: ```username | player UUID```  
## Example  
[https://api.hypixel.net/session?key=Xx3XAMPL3K3YxX&player=puppy0cam](https://api.hypixel.net/session?key=Xx3XAMPL3K3YxX&player=puppy0cam) will either give the null error or it will give session data on puppy0cam depending on whether or not he is online  
