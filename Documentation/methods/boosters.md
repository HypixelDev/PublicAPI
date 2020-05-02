# boosters

## Description

Returns list of boosters.

## Parameters
- key

## Example Response
```php
{
    "success": true,
    "boosters": [
         {
             "purchaserUuid": "",
             "amount": 3, // multiplier
             "originalLength": 3600,
             "length": 2329, // length remaining
             "gameType": 20, // GameType ID
             "dateActivated": 1471657389139 // unix timestamp
             "stacked": [
                "" // array of up to 10 UUIDs who have stacked the booster, or "true" if there are none
             ]
         }  
     ]
     "boosterState": {
        "decrementing": true  // indicates whether booster duration is ticking down
     }
}
```
