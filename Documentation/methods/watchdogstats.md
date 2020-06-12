# watchdogstats

## Description

Returns some statistics about Watchdog & bans.

## Parameters
- key

## Example Response
```js
{
  "success": true,
  "watchdog_lastMinute": 5, // Watchdog's bans in the last minute.
  "staff_rollingDaily": 1356, // Staff bans in the last day.
  "watchdog_total": 4924740, // Total Watchdog bans, ever.
  "watchdog_rollingDaily": 7679, // Watchdog bans in the last day.
  "staff_total": 1608360 // Total staff bans, ever.
}
```
