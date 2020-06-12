# key

## Description
Returns information regarding given key.

## Parameters
- key

## Example Response
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
