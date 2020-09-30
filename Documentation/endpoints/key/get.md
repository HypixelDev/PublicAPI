# GET /key

## Description

Get information regarding given key

## Parameters

* key (string): API key

## Response

* success (bool): Response status
* record (object):
  * key (string): API key
  * owner (string): ID of the owner
  * limit (int): Query limit per minutes
  * queriesInPastMin (int): Queries in past minute (do not include this one)
  * totalQueries (int): Total of queries

## Example Response

Answer for: https://api.hypixel.net/key?key=e1513542-f4c7-483d-bf1b-3b29d4e59903

```js
{
  "success": true,
  "record": {
    "key": "e1513542-f4c7-483d-bf1b-3b29d4e59903",
    "owner": "8ffb79fa-620e-45fe-8d62-381abd5bc60f",
    "limit": 120,
    "queriesInPastMin": 0,
    "totalQueries": 849
  }
}
```
