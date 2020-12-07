# Documentation  
Within this folder, you will find detailed instructions on the various functions the Hypixel Public API allows.

## Date and Time  
Generally, date-times are stored as Unix epoch times in milliseconds.

## Response Format  
Responses are served in JSON format.

## UUID Parameters
Methods which require a UUID in the API ask for a Java `UUID` object, which can be built using `UUID#fromString`. 

**Note:** This method will **only** accept UUIDs in dashed format.

``Correct Format: f7c77d99-9f15-4a66-a87d-c4a51ef30d19``

``Incorrect Format: f7c77d999f154a66a87dc4a51ef30d19``


## Where can I find someone's UUID?
The easiest way is to go on [NameMC](https://namemc.com/), search up the desired profile, and the UUID will show up under 'Minecraft Profile'. [Click here for a visual](https://prnt.sc/vwbqsd).

## SkyBlock Items and Inventories
Items and inventory data are stored as a base64 encoded string containing gzipped nbt data.
If a method is missing important information about an item or inventory, you should try checking this!
>Note: the base64 string may contain a unicode escape for non-alphabetical symbols (i.e. `=` will be displayed as `\u003d`), and some programming languages may have silent defects when interpreting the string.
