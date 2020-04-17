# skyblock/bazaar/product
 **Note**: This method is deprecated and will be removed at a later date.

## Description
Returns information about a bazaar product by product id. 

Due to the way the data is stored in the backend, the "buy" and "sell" fields are all the wrong way round so these will need to be flipped. This is not the case on the new [skyblock/bazaar](https://github.com/HypixelDev/PublicAPI/blob/master/Documentation/methods/skyblock/bazaar.md) endpoint.

The returned info has 4 main fields:
- buy_summary
- sell_summary
- quick_status
- week_historic

buy_summary and sell_summary are the current top 30 orders for each transaction type. This data:
[stonks](https://i.imgur.com/SjRONxq.png)

quick_status is a computed summary of the live state of the product (used for advanced mode view in the bazaar):
- volumes are the sum of item amounts in all orders
- prices are the weighted average of the top 2% of orders by volume
- movingWeek is the historic *transacted* volume from last 7d + live state
- orders is the count of active orders

week_historic is a list of data points at a 30 minutes interval from the last 7 days, used in the graphs in-game:
- sells/buys is the number of instant sell/buys
- buyVolume is the sum of *transacted* item amounts for this transaction type
- buyCoins is the sum of *transacted* (volume x unit price) for this transaction type
- nowBuyVolume is the sum of item amounts in all orders at the timestamp for this transaction type

You can compute the avg unit price of instant buy on a historic data point using sellCoins / sellVolume.

## Parameters
- key
- productId

## Example Response
```php
{
  "success": true,
  "product_info": {
    "product_id": "STOCK_OF_STONKS",
    "buy_summary": [
      {
        "amount": 64,
        "pricePerUnit": 16500.1,
        "orders": 1
      },
      {
        "amount": 895,
        "pricePerUnit": 16500.0,
        "orders": 1
      },
      {
        "amount": 64,
        "pricePerUnit": 16350.1,
        "orders": 1
      },
      {
        "amount": 160,
        "pricePerUnit": 16330.0,
        "orders": 1
      },
      {
        "amount": 2,
        "pricePerUnit": 16300.2,
        "orders": 1
      },
      {
        "amount": 179,
        "pricePerUnit": 16300.0,
        "orders": 2
      },
      {
        "amount": 118,
        "pricePerUnit": 16000.0,
        "orders": 1
      },
      {
        "amount": 20,
        "pricePerUnit": 15666.2,
        "orders": 1
      },
      {
        "amount": 160,
        "pricePerUnit": 15049.2,
        "orders": 1
      },
      {
        "amount": 20,
        "pricePerUnit": 14999.2,
        "orders": 1
      },
      {
        "amount": 1,
        "pricePerUnit": 14200.0,
        "orders": 1
      },
      {
        "amount": 63,
        "pricePerUnit": 14150.0,
        "orders": 1
      },
      {
        "amount": 64,
        "pricePerUnit": 13841.0,
        "orders": 1
      },
      {
        "amount": 64,
        "pricePerUnit": 13650.1,
        "orders": 1
      },
      {
        "amount": 64,
        "pricePerUnit": 13650.0,
        "orders": 1
      },
      {
        "amount": 2,
        "pricePerUnit": 13580.2,
        "orders": 1
      },
      {
        "amount": 5,
        "pricePerUnit": 13569.6,
        "orders": 1
      },
      {
        "amount": 20,
        "pricePerUnit": 13552.0,
        "orders": 2
      },
      {
        "amount": 64,
        "pricePerUnit": 13502.1,
        "orders": 1
      },
      {
        "amount": 1,
        "pricePerUnit": 13500.2,
        "orders": 1
      },
      {
        "amount": 5,
        "pricePerUnit": 13500.1,
        "orders": 1
      },
      {
        "amount": 12,
        "pricePerUnit": 13050.1,
        "orders": 1
      },
      {
        "amount": 24,
        "pricePerUnit": 13000.500000000002,
        "orders": 1
      },
      {
        "amount": 64,
        "pricePerUnit": 13000.400000000001,
        "orders": 1
      },
      {
        "amount": 192,
        "pricePerUnit": 13000.300000000001,
        "orders": 3
      },
      {
        "amount": 192,
        "pricePerUnit": 13000.2,
        "orders": 3
      },
      {
        "amount": 203,
        "pricePerUnit": 13000.1,
        "orders": 2
      },
      {
        "amount": 609,
        "pricePerUnit": 13000.0,
        "orders": 2
      },
      {
        "amount": 160,
        "pricePerUnit": 12811.300000000003,
        "orders": 2
      },
      {
        "amount": 64,
        "pricePerUnit": 12802.0,
        "orders": 2
      }
    ],
    "sell_summary": [
      {
        "amount": 64,
        "pricePerUnit": 16879.9,
        "orders": 1
      },
      {
        "amount": 636,
        "pricePerUnit": 16899.800000000003,
        "orders": 1
      },
      {
        "amount": 125,
        "pricePerUnit": 16899.9,
        "orders": 1
      },
      {
        "amount": 3,
        "pricePerUnit": 16900.0,
        "orders": 1
      },
      {
        "amount": 512,
        "pricePerUnit": 16999.0,
        "orders": 1
      },
      {
        "amount": 1783,
        "pricePerUnit": 17000.0,
        "orders": 10
      },
      {
        "amount": 51,
        "pricePerUnit": 17249.800000000003,
        "orders": 1
      },
      {
        "amount": 294,
        "pricePerUnit": 17250.0,
        "orders": 2
      },
      {
        "amount": 680,
        "pricePerUnit": 17262.300000000003,
        "orders": 1
      },
      {
        "amount": 32,
        "pricePerUnit": 17262.5,
        "orders": 1
      },
      {
        "amount": 63,
        "pricePerUnit": 17280.0,
        "orders": 1
      },
      {
        "amount": 64,
        "pricePerUnit": 17290.0,
        "orders": 1
      },
      {
        "amount": 320,
        "pricePerUnit": 17292.800000000003,
        "orders": 1
      },
      {
        "amount": 496,
        "pricePerUnit": 17300.0,
        "orders": 2
      },
      {
        "amount": 25,
        "pricePerUnit": 17320.0,
        "orders": 1
      },
      {
        "amount": 127,
        "pricePerUnit": 17350.0,
        "orders": 1
      },
      {
        "amount": 225,
        "pricePerUnit": 17400.0,
        "orders": 2
      },
      {
        "amount": 64,
        "pricePerUnit": 17450.0,
        "orders": 1
      },
      {
        "amount": 1152,
        "pricePerUnit": 17498.0,
        "orders": 1
      },
      {
        "amount": 2514,
        "pricePerUnit": 17500.0,
        "orders": 6
      },
      {
        "amount": 241,
        "pricePerUnit": 17675.0,
        "orders": 1
      },
      {
        "amount": 128,
        "pricePerUnit": 17740.0,
        "orders": 1
      },
      {
        "amount": 120,
        "pricePerUnit": 17798.800000000003,
        "orders": 1
      },
      {
        "amount": 865,
        "pricePerUnit": 17800.0,
        "orders": 4
      },
      {
        "amount": 84,
        "pricePerUnit": 17950.0,
        "orders": 1
      },
      {
        "amount": 64,
        "pricePerUnit": 17997.9,
        "orders": 1
      },
      {
        "amount": 10,
        "pricePerUnit": 17998.0,
        "orders": 1
      },
      {
        "amount": 744,
        "pricePerUnit": 17999.0,
        "orders": 1
      },
      {
        "amount": 6095,
        "pricePerUnit": 18000.0,
        "orders": 44
      },
      {
        "amount": 20,
        "pricePerUnit": 18007.9,
        "orders": 1
      }
    ],
    "quick_status": {
      "productId": "STOCK_OF_STONKS",
      "buyPrice": 10607.645381069093,
      "buyVolume": 1698076,
      "buyMovingWeek": 9685852,
      "buyOrders": 1001,
      "sellPrice": 17209.50264099716,
      "sellVolume": 392880,
      "sellMovingWeek": 9791083,
      "sellOrders": 1612
    },
    "week_historic": [
      {
        "productId": "STOCK_OF_STONKS",
        "timestamp": 1583794545062,
        "nowBuyVolume": 841303,
        "nowSellVolume": 139556,
        "buyCoins": 2.7012628704000086E8,
        "buyVolume": 733603,
        "buys": 4940,
        "sellCoins": 2.286093577199999E8,
        "sellVolume": 399294,
        "sells": 8033
      },
      {
        "productId": "STOCK_OF_STONKS",
        "timestamp": 1583796345061,
        "nowBuyVolume": 3019097,
        "nowSellVolume": 588862,
        "buyCoins": 2.85753079073E9,
        "buyVolume": 1918748,
        "buys": 9195,
        "sellCoins": 2.6475463886900206E9,
        "sellVolume": 1558126,
        "sells": 33749
      },
      {
        "productId": "STOCK_OF_STONKS",
        "timestamp": 1583798145061,
        "nowBuyVolume": 2980017,
        "nowSellVolume": 584549,
        "buyCoins": 3876837.8,
        "buyVolume": 3106,
        "buys": 27,
        "sellCoins": 6107869.500000003,
        "sellVolume": 3569,
        "sells": 140
      }
    ]
  }
}
```
