# Key

| Endpoint | Authentication Required |
| --- | --- |
| https://api.hypixel.net/key | yes |

## Description
Returns information regarding given key.

## Parameters

| Param | Example |
| --- | --- |
| [`key`](README.md#api-key) | `36ab954b-4200-6969-aaaa-2aa15f189bad` |

## Example Data

```js
{
  "success": true,
  "record": {
    "key": "e1513542-f4c7-483d-bf1b-3b29d4e59903",
    "owner": "8ffb79fa-620e-45fe-8d62-381abd5bc60f",
    "limit": 120, // Limit of requests per minute
    "queriesInPastMin": 0, // Does not include this query
    "totalQueries": 849
  }
}
```
