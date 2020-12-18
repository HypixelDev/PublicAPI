# skyblock/auctions_ended

## Description
Returns SkyBlock auctions which ended in the last 60 seconds (More precisely, whatever time in seconds is defined in the "Age" header of the response, if it is present).

## Example Response
```js
{
  "success": true,
  "lastUpdated": 1607456463916,
  "auctions": [
    {
    "auction_id": "015fe0c67e6041e69797bbe0c2725a21",
    "seller": "fc76242bf64a4698ae0ebc136d900929",
    "seller_profile": "85b96cd3e73e4580b8379162ec059141",
    "buyer": "c1eff55de0d24ec6b44848799e9323ba",
    "timestamp": 1607456400329,
    "price": 190000,
    "bin": true,
    "item_bytes": "H4sIAAAAAAAAAEWR3W7TQBCFx2mBxKgtSH2ArUACZKL6L9jtndUYBdHQyGlV7qq1PXZX9U+03kB6yYNw7ffwo/AgiHEC4m7mmz1nz87qACPQhA4A2gAGItUGGjy5qNeV0nTYUzzX4PlNFUvkDzwuUNuD0Uyk+LHgeUOi3zo8S0WzKvjjCPYva4lDoofwsmu9KS95juesaxPDN+GY0FJJrHJ1v4PWxKTDAfGQy4rQB8M2WVKLqqHGy2RdsrKuGoWSPYiiaNjbS/yGBQ3RMo3+TF0Vj+/I5NXWj+2u7Acr0hCbmGbfbdWgd60/5xtmOBM4JPq5p9sofY43W4tfP3+wfyn/++Bfn+91nYJBxS0VlLQoMFGCIvYu6FnvXcenindtMQ++htMT8u11dPH1vWiYUFiyhFcsRiYxq2WO6Qm86NozUkRBFLLl7VU0HcL+F14iHNAg4rRuyYINgg5H4UZJHiglRbxW2AxhVEuRi+qa53CwnF0t7hY30cUsWIbD/jdBj4JP0zC6ozBkul4Teu34Tua7vj124tQduz73xtxOvbGVoZtMbN/xnIyMlSixUbxcwZFln/qn9DX2ue2wxRxgAE93q6b3wR9BYJa/RAIAAA=="
    }
  ]
}
```
