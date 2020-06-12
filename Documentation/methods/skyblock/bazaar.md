# skyblock/bazaar

## Description
Returns the list of [products](#product-description) along with their sell summary, buy summary and quick status.

## Parameters
- key

## Example Response
```js
{
  "success": true,
  "lastUpdated": 1590854517479,
  "products": {
    "INK_SACK:3": {
      "product_id": "INK_SACK:3",
      "sell_summary": [
        {
          "amount": 20569,
          "pricePerUnit": 4.2,
          "orders": 1
        },
        {
          "amount": 140326,
          "pricePerUnit": 3.8,
          "orders": 2
        }
      ],
      "buy_summary": [
        {
          "amount": 640,
          "pricePerUnit": 4.8,
          "orders": 1
        },
        {
          "amount": 640,
          "pricePerUnit": 4.9,
          "orders": 1
        },
        {
          "amount": 25957,
          "pricePerUnit": 5,
          "orders": 3
        }
      ],
      "quick_status": {
        "productId": "INK_SACK:3",
        "sellPrice": 4.2,
        "sellVolume": 409855,
        "sellMovingWeek": 8301075,
        "sellOrders": 11,
        "buyPrice": 4.99260315136572,
        "buyVolume": 1254854,
        "buyMovingWeek": 5830656,
        "buyOrders": 85
      }
    }
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
