# watchdogstats

## Description

Returns some statistics about Watchdog & bans.

## Parameters
- key

## Example Watchdog-Stats
```php
[
    {
        "watchdog_lastMinute": "1", // Watchdog's bans in the last minute.
        "staff_rollingDaily": 123, // Staff bans in the last day.
        "watchdog_total": 1234567, // Total Watchdog bans, ever.
        "watchdog_rollingDaily": 1234, // Watchdog bans in the last day.
        "staff_total": 123456, // Total staff bans, ever.
    },
    ...
]
```
