# GET /leaderboards

## Description

Get a list of the official leaderboards and their current standings for gomes on the network

## Parameters

* key (string): API Key

## Response

* success (bool): Response status
* leaderboards (dict): Dictionnary with [GameType Name](../../object/gametype.md) as key and [leaderboards](../../object/leaderboards.md) object as value

## Example Response

Answer for: https://api.hypixel.net/leaderboards?key=xxxxxxxxxxx

```js
{
  "success": true,
  "leaderboards": {
    "ARENA": [
      {
        "path": "arena_rating_b",
        "prefix": "Overall",
        "title": "Rating",
        "location": "-2554,55,715",
        "count": 10,
        "leaders": [
          "d1e0f224-fb2f-43cb-b644-e385aaa1bbe8",
          "1b14b620-73c2-4827-bfe7-2673592d4b04",
          "e8dd1375-bf54-4941-a808-67e2a6fa6491",
          "da8d7d4b-9589-40ec-99c7-9095fba8fb7a",
          "73f69444-ac77-4374-831e-879938fa251b",
          "0e944d6c-c7e3-43d0-a1b1-24b66e5eb10f",
          "7e43a3f5-4aa7-4e64-b742-c7aa984560a6",
          "df17892b-349b-4821-a82a-a419a6c664c5",
          "d42416aa-bede-4bc1-8d2d-831fbc7e270a",
          "1e6b15de-b333-43a6-bcfa-12464bd9998f",
          "485259d1-82db-42a7-bceb-9ff84fb19a29"
        ]
      },
      {
        "path": "wins_1v1",
        "prefix": "Overall",
        "title": "1v1 Wins",
        "location": "-2554,54,715",
        "count": 10,
        "leaders": [
          "425fa076-cf95-4174-b3fc-c57943ebddba",
          "42d17101-d916-4b8a-a70b-93dc6ccbee27",
          "576749eb-6728-4773-a41b-b418a62f9c01",
          "e5dcd129-69dc-479a-8d32-4fc423d755b9",
          "6128be08-73d0-4b73-b594-8f4bdc120c6c",
          "19ec6d74-86f9-4305-a3a4-29fd802a5de0",
          "fd5d47e6-ee34-4fbb-a126-a446f7ea5e62",
          "4b65e633-1ba9-41fb-8467-322de5d56ae3",
          "6fe069a5-5d8f-40b6-96db-aeaf21a3c4c1",
          "d19b009b-8feb-4fb9-95db-3316737c211f",
          "69334518-3a2c-4152-af43-8cd909862950"
        ]
      }
    ],
    "MCGO": [
      {
        "path": "kills",
        "prefix": "Overall",
        "title": "Kills",
        "location": "-2550,55,715",
        "count": 10,
        "leaders": [
          "7b1bb9c3-188d-4c7b-811b-90da2f670665",
          "c8d0e91a-f6a1-42f4-94ee-c3fe21991435",
          "da30c949-7de4-4131-ad3e-609f5f277eb9",
          "1266f943-0704-411b-b544-bde93fee8b14",
          "85a528f4-d614-4170-96c6-0e7c93ee91d8",
          "66ec4826-2911-45a8-81c3-f955c136683f",
          "8a0a815f-006d-4b2e-acd6-918d981ad9b1",
          "4d891348-6be6-497c-8d41-4d19f26d8e32",
          "14d56c40-de10-4051-8369-e729ebc81feb",
          "39aea766-a8db-473b-a80d-a72413b52fbb",
          "7b8992e1-220f-4dde-ac02-6bc1d6b050b2"
        ]
      },
      {
        "path": "normal_wins_2",
        "prefix": "Monthly",
        "title": "Defusal Wins",
        "location": "0,0,0",
        "count": 10,
        "leaders": [
          "f179aeca-773f-4fc1-9d0e-ed075f9d4920",
          "6385fa08-b66e-453c-9210-90b5fe22891a",
          "992de01e-2723-4888-9d73-170a7e18f917",
          "a6c532de-47c9-415b-906a-7ede7b56581e",
          "a08838e6-3113-4eac-b4a2-1d314aa24e89",
          "acc2746a-46b5-4432-9319-c5439d2958c7",
          "4b043e88-c8d9-41db-bdd7-5d0ea04e550f",
          "806b6da3-6b41-441f-bb85-706a155191dd",
          "217a0d10-f950-48b8-ae2d-81508f31be38",
          "b02c7d6c-46b5-4ce7-a90d-eb2377313ff8",
          "f9422ceb-b4df-44d5-b372-b7ec65de5dc4"
        ]
      },
      {
        "path": "normal_wins_1",
        "prefix": "Weekly",
        "title": "Defusal Wins",
        "location": "0,0,0",
        "count": 10,
        "leaders": [
          "6385fa08-b66e-453c-9210-90b5fe22891a",
          "0ad1edc3-b5f9-40ef-bb16-5180e9aa9e6f",
          "217a0d10-f950-48b8-ae2d-81508f31be38",
          "f179aeca-773f-4fc1-9d0e-ed075f9d4920",
          "a5cf4396-f855-4526-8e76-4b3a488a9284",
          "e366b411-bd33-4597-afe1-d664332e212b",
          "992de01e-2723-4888-9d73-170a7e18f917",
          "f9f7cda1-07db-4c53-896d-1857603606b2",
          "6756ee86-09e1-403f-b7ad-1eab2e78c82e",
          "93d1a8be-4780-4059-aa5f-a8d30a38bc8c",
          "acc2746a-46b5-4432-9319-c5439d2958c7"
        ]
      },
      {
        "path": "deathmatch_wins_2",
        "prefix": "Monthly",
        "title": "Deathmatch Wins",
        "location": "0,0,0",
        "count": 10,
        "leaders": [
          "fc72c6a1-f363-4c64-90f5-1e674e713c48",
          "1756b0ca-ea87-4304-ba4d-892344827484",
          "6385fa08-b66e-453c-9210-90b5fe22891a",
          "f179aeca-773f-4fc1-9d0e-ed075f9d4920",
          "3d12de07-7cb5-48ff-8a9f-63b7c40bc2a3",
          "710e83a7-882c-427d-a2b4-72befd853522",
          "1d3c6ebc-d822-4087-880d-28d4ccacc0a5",
          "5cbbe660-6d1c-474f-8c46-699a795075b2",
          "a70bd221-a5c8-4fb8-9ea6-4deb0c90ceb0",
          "a7688573-3274-4ab9-a9dd-31ca6da55fb6",
          "6e912148-cb8c-48b2-b1d9-ab62930dc175"
        ]
      },
      {
        "path": "deathmatch_wins_1",
        "prefix": "Weekly",
        "title": "Deathmatch Wins",
        "location": "0,0,0",
        "count": 10,
        "leaders": [
          "b5d64e2e-efe2-4fe8-a818-f6666d933542",
          "1e66e17f-c8c4-4890-96be-b26f7d588548",
          "6756ee86-09e1-403f-b7ad-1eab2e78c82e",
          "acc2746a-46b5-4432-9319-c5439d2958c7",
          "79bd5a2a-6320-40c2-ba28-8b9f06b31be8",
          "ed03446e-6d8b-4d3c-87a5-6a3afc197a8b",
          "00e44a73-d753-4e4b-861e-5a5ee6dfc12b",
          "6e379375-05d8-42da-a9a7-02496878cdea",
          "32675f3b-3250-40ad-a53d-ac262399f95c",
          "fce01da6-a381-4f7c-ae7a-cc0f31c58eae",
          "c17aba20-da72-4a19-954e-6b4ed8f06471"
        ]
      }
    ]
  }
}
```
