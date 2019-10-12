# skyblock/profile

## Description
Returns a SkyBlock profile's data, such as stats, objectives etc. The data returned can differ depending on the players in-game API settings.
Inventory data is stored as a base64 encoded string containing gzipped nbt data.

## Parameters
- key
- profile _(Can be obtained via the player endpoint under `stats.SkyBlock`)_ 
