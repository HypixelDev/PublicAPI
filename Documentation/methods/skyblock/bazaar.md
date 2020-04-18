# skyblock/bazaar

## Description
Returns the list of [products](#product-description) along with their sell summary, buy summary and quick status.

## Parameters
- key

## Example Response
```php
{
  "success": true,
  "lastUpdated": 1587237498970,
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
        // ...
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
        // ...
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
    },
    // ...
  }
}
```

## Product Description

The returned product info has 3 main fields:
- `buy_summary`
- `sell_summary`
- `quick_status`

`buy_summary` and `sell_summary` are the current top 30 orders for each transaction type (in-game example:
[Stock of Stonks](https://i.imgur.com/SjRONxq.png)).

`quick_status` is a computed summary of the live state of the product (used for advanced mode view in the bazaar):
- `sellVolume` and `buyVolume` are the sum of item amounts in all orders.
- `sellPrice` and `buyPrice` are the weighted average of the top 2% of orders by volume.
- `movingWeek` is the historic *transacted* volume from last 7d + live state.
- `sellOrders` and `buyOrders` are the count of active orders.
