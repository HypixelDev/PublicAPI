# Friends

| Endpoint | Authentication Required |
| --- | --- |
| https://api.hypixel.net/friends | yes |

## Description
Returns friendships for a given player.

## Parameters

?> `byName` or `byUuid` is required

| Param | Example | |
| --- | --- | --- |
| [`key`](README.md#api-key) | `36ab954b-4200-6969-aaaa-2aa15f189bad` |
| uuid | `41dae80633f442f0982f6c274dcf8a84` | Player UUID |

## Example Data

```js
{
  "success": true,
  "records": [
    {
      "_id": "5eb97d170cf22f431e8d6170",
      "uuidSender": "20934ef9488c465180a78f861586b4cf",
      "uuidReceiver": "7486aa03aca5470e888dde8a43eb8c10",
      "started": 1589214487454
    }
  ]
}
```
