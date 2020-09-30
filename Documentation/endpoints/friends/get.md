# GET /friends

## Description

Return friendship for given player

## Parameters

* key (string): API key
* uuid (string): Player UUID

## Response

* success (bool): Response status
* records (list): List of [Friend](../../object/friend.md)

## Example Response

Answer for: https://api.hypixel.net/friends?key=xxxxxxxxxx&uuid=20934ef9488c465180a78f861586b4cf

```js
{
  "success": true,
  "records": [
    {
      "_id": "5eb97d170cf22f431e8d6170",
      "uuidSender": "20934ef9488c465180a78f861586b4cf",
      "uuidReceiver": "7486aa03aca5470e888dde8a43eb8c10",
      "started": 1589214487454
    },
    {
      "_id": "5eb97d180cf22f431e8d6171",
      "uuidSender": "20934ef9488c465180a78f861586b4cf",
      "uuidReceiver": "9926753a434e4b46a07625993a07b8ef",
      "started": 1589214488015
    },
    [...]
  ]
}
```
