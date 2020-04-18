# skyblock/bazaar

## Description
Returns the list of products along with their sell summary, buy summary and quick status.

## Parameters
- key

## Example Response
```php
{
  "success": true,
  "products": {
    "INK_SACK:3": {
      "product_id": "INK_SACK:3",
      "sell_summary": [
        {
          "amount": 1730,
          "pricePerUnit": 4.7,
          "orders": 1
        },
        {
          "amount": 50381,
          "pricePerUnit": 3.6,
          "orders": 2
        },
        {
          "amount": 59380,
          "pricePerUnit": 3.5,
          "orders": 2
        },
        {
          "amount": 1024,
          "pricePerUnit": 0.5,
          "orders": 2
        },
        {
          "amount": 1015,
          "pricePerUnit": 0.4,
          "orders": 2
        }
      ],
      "buy_summary": [
        {
          "amount": 66091,
          "pricePerUnit": 6.1,
          "orders": 1
        },
        {
          "amount": 8594,
          "pricePerUnit": 6.3,
          "orders": 7
        },
        {
          "amount": 71680,
          "pricePerUnit": 6.9,
          "orders": 1
        },
        {
          "amount": 42336,
          "pricePerUnit": 7,
          "orders": 2
        },
        {
          "amount": 1432,
          "pricePerUnit": 7.1,
          "orders": 1
        },
        {
          "amount": 2700,
          "pricePerUnit": 7.2,
          "orders": 2
        },
        {
          "amount": 1280,
          "pricePerUnit": 7.3,
          "orders": 2
        },
        {
          "amount": 1920,
          "pricePerUnit": 7.4,
          "orders": 1
        },
        {
          "amount": 1920,
          "pricePerUnit": 7.5,
          "orders": 1
        },
        {
          "amount": 8320,
          "pricePerUnit": 7.7,
          "orders": 7
        },
        {
          "amount": 1280,
          "pricePerUnit": 7.8,
          "orders": 1
        },
        {
          "amount": 640,
          "pricePerUnit": 8,
          "orders": 2
        },
        {
          "amount": 20000,
          "pricePerUnit": 8.3,
          "orders": 2
        },
        {
          "amount": 7376,
          "pricePerUnit": 9,
          "orders": 1
        },
        {
          "amount": 6803,
          "pricePerUnit": 9.1,
          "orders": 4
        },
        {
          "amount": 29039,
          "pricePerUnit": 9.2,
          "orders": 3
        },
        {
          "amount": 160,
          "pricePerUnit": 9.4,
          "orders": 2
        },
        {
          "amount": 264,
          "pricePerUnit": 9.5,
          "orders": 2
        },
        {
          "amount": 640,
          "pricePerUnit": 9.6,
          "orders": 1
        },
        {
          "amount": 640,
          "pricePerUnit": 9.7,
          "orders": 1
        },
        {
          "amount": 768,
          "pricePerUnit": 9.9,
          "orders": 2
        },
        {
          "amount": 10304,
          "pricePerUnit": 10.1,
          "orders": 12
        },
        {
          "amount": 118,
          "pricePerUnit": 11.1,
          "orders": 1
        },
        {
          "amount": 961,
          "pricePerUnit": 11.2,
          "orders": 2
        },
        {
          "amount": 64,
          "pricePerUnit": 11.9,
          "orders": 1
        },
        {
          "amount": 640,
          "pricePerUnit": 12.1,
          "orders": 1
        },
        {
          "amount": 391,
          "pricePerUnit": 12.2,
          "orders": 1
        },
        {
          "amount": 4304,
          "pricePerUnit": 12.4,
          "orders": 4
        },
        {
          "amount": 1408,
          "pricePerUnit": 12.5,
          "orders": 5
        },
        {
          "amount": 1486,
          "pricePerUnit": 12.6,
          "orders": 1
        }
      ],
      "quick_status": {
        "productId": "INK_SACK:3",
        "sellPrice": 3.636518201531347,
        "sellVolume": 1777058,
        "sellMovingWeek": 10469536,
        "sellOrders": 66,
        "buyPrice": 6.1,
        "buyVolume": 432601,
        "buyMovingWeek": 6663769,
        "buyOrders": 125
      }
    }
  }
}
```