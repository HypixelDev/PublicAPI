# player  
=====
## Description  
this API query will retrieve a player's data which can include all of the following information:  
Information                 | Information                | Information             
--------------------------- | -------------------------- | ------------------------
PRESENTS FROM GAME          | _id                        | achievements  
achievementsOneTime         | adsense_tokens             | beamLink  
channel                     | displayname                | dmcrates-8-2016  
eugene                      | firstLogin                 | flashingSaleClicks  
flashingSaleOpens           | flashingSalePoppedUp       | flashingSalePopup  
friendRequests              | friendRequestsUuid         | guildNotifications  
housingMeta                 | karma                      | knownAliases  
knownAliasesLower           | lastAdsenseGenerateTime    | lastClaimedReward  
lastEugeneMessage           | lastLogin                  | last_survey   
mainlobbytutorial           | mcVersionRp                | mostRecentlyThanked  
mostRecentlyThankedUuid     | mostRecentlyTipped         | mostRecentlyTippedUuid  
networkExp                  | networkLevel               | newClock  
newMainTutorial             | outfit                     | parkourCompletions  
particleQuality             | petConsumables             | petJourneyTimestamp  
petStats                    | playername                 | quests  
rewardConsumed              | rewardHighScore            | rewardScore  
rewardStreak                | settings                   | spec_night_vision  
spec_speed                  | stats                      | testPass  
thanksReceived              | thanksSent                 | timePlaying  
totalDailyRewards           | totalRewards               | transformation  
userLanguage                | uuid                       | vanityConvertedBoxToday  
vanityFirstConvertedBox     | vanityMeta                 | vanityTokens  
voting                      | websiteSet                 | REWARDS FROM LOBBY  
LEVEL UP REWARDS            | N/A                        | N/A  
## parameters  
**(required)** key: *Xx3XAMPL3K3YxX*  
**(required)** ```uuid | name```: ```UUID | username```  
## example  
[https://api.hypixel.net/player?key=Xx3XAMPL3K3YxX&name=puppy0cam](https://api.hypixel.net/player?key=Xx3XAMPL3K3YxX&name=puppy0cam) will return player data for the player ```puppy0cam``` in JSON format (assuming the key is valid) 