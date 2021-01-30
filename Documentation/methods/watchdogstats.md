# watchdogstats

| Endpoint | Authentication Required |
| --- | --- |
| https://api.hypixel.net/watchdogstats | yes |

## Description

Returns some statistics about Watchdog & bans.

## Parameters

| Param | Example |
| --- | --- |
| [`key`](README.md#api-key) | `36ab954b-4200-6969-aaaa-2aa15f189bad` |

## Example Response
```js
{
  "success":true,
  "watchdog_lastMinute":3, // Watchdog's bans in the last minute.
  "staff_rollingDaily":2246, // Staff bans in the last day.
  "watchdog_total":6114594, // Total Watchdog bans, ever.
  "watchdog_rollingDaily":7704, // Watchdog bans in the last day.
  "staff_total":2140519 // Total staff bans, ever.
}
```
