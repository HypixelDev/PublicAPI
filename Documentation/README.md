# Hypixel API

Hypixel API documentation.

See the list of endpoints in the [endpoints](endpoints/) folder

All answer are JSON formatted.

## Date & Time

Generally dates are stored as a Unix Epoch times in milliseconds.

## UUID Parameters

All uuid parameters are exprected ta hove the UUID without dashes.


## Skyblock items and inventories

Items and inventory data are stored as a base64 encoded string containing gzipped nbt data. If a method is missing important information about an item or inventory, you should try checking this!
> Note: the base64 string may contain a unicode escape for non-alphabetical symbols (i.e. = will be displayed as \u003d), and some programming languages may have silent defects when interpreting the string.
