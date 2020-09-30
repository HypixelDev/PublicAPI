# GET /findGuild

## Descrption

Retrieve guild id via name or uuid

## Parameters

* key (string): API key
* byName (string): Find by Name 
* byUuid (string): Find by Uuid

## Reponses

* success (bool): Response status
* guild (string): Guild's id

## Example Response

Answer for: https://api.hypixel.net/findGuild?key=xxxxxxxxx&byName=Mini%20Squid

```js
{
  "success": true,
  "guild": "553490650cf26f12ae5bac8f"
}
```
