# GET /watchdogstats

## Descrption

Get watchdogs statistics

## Parameters

* key (string): API key

## Response

* success (bool): Response status
* watchdogSlastMinute (int): ?
* staff_rollingDaily (int): ?
* watchdog_total (int): ?
* watchdog_rollingDaily (int): ?
* staff_total (int): ?

## Example Response

Answer for: https://api.hypixel.net/watchdogstats?key=xxxxxxxxxx

```js
{
  "success": true,
  "watchdog_lastMinute": 1,
  "staff_rollingDaily": 1940,
  "watchdog_total": 5516980,
  "watchdog_rollingDaily": 3011,
  "staff_total": 1833675
}
```
