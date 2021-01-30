# Find Guild

| Endpoint | Authentication Required |
| --- | --- |
| https://api.hypixel.net/findGuild | yes |

## Description
Returns guild ID of requested guild

## Parameters

?> `byName` or `byUuid` is required

| Param | Example | |
| --- | --- | --- |
| [`key`](README.md#api-key) | `36ab954b-4200-6969-aaaa-2aa15f189bad` |
| byName | `The Summerhouse` | Name of the Guild |
| byUuid | `41dae80633f442f0982f6c274dcf8a84` | Player UUID |

## Example Data

```js
{
  "success": true,
  "guild": "553490650cf26f12ae5bac8f"
}
```
