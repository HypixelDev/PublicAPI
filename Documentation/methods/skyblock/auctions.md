# skyblock/auctions

## Description
Returns SkyBlock auctions that are currently active in the in-game Auction House. This method uses pagination due to the amount of results returned. Each page will return up to 1,000 results.

## Parameters
- key
- page (Zero indexed and defaults to 0)

## Example Response
```php
{
    "success": true,
    "page": 0, // The current page
    "totalPages": 32, // Total amount of pages avaliable
    "totalAuctions": 31267, // Total amount of results in the auction house
    "lastUpdated": 1571065561345, // Unix timestamp in milliseconds of when the data last updated in the API
    "auctions": [ // The auctions themselves
        {
            "uuid": "bc581ce675e94a0c88ac9deae06090f0",
            "auctioneer": "96a7c06732f54c1382ab6a2515dbb960",
            "profile_id": "96a7c06732f54c1382ab6a2515dbb960",
            "coop": [
                "96a7c06732f54c1382ab6a2515dbb960"
            ],
            "start": 1571049581232,
            "end": 1571071181232,
            "item_name": "Magical Mushroom Soup",
            "item_lore": "§7Consuming this Magical Mushroom\n§7Soup on your private island\n§7allows the player to fly for §a2\n§aminutes§7. Leaving the private\n§7island will remove the effect!\n\n§a§lUNCOMMON",
            "extra": "Magical Mushroom Soup Mushroom Soup",
            "category": "consumables",
            "tier": "UNCOMMON",
            "starting_bid": 256,
            "item_bytes": "...",
            "claimed": false,
            "claimed_bidders": [],
            "highest_bid_amount": 256,
            "bids": [
                {
                    "auction_id": "bc581ce675e94a0c88ac9deae06090f0",
                    "bidder": "70aafcc6764b45ff80e60226193a0784",
                    "profile_id": "70aafcc6764b45ff80e60226193a0784",
                    "amount": 256,
                    "timestamp": 1571065921089
                }
            ]
        }
    ]
}   
```