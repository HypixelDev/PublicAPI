# Documentation  
Within this folder, you will find detailed instructions on the various functions the Hypixel Public API allows.

## Date and Time  
Generally dates are stored as a Unix Epoch times in milliseconds.

## Response Format  
Responses are served in JSON format.

## UUID Parameters
It is reccomended for the UUID's to have no dashes. There really is no right or wrong for this.

``Reccomended Format: f7c77d999f154a66a87dc4a51ef30d19``

``Unreccomended Example: f7c77d99-9f15-4a66-a87d-c4a51ef30d19``

## SkyBlock items and inventories
Items and inventory data are stored as a base64 encoded string containing gzipped nbt data.
If a method is missing important information about an item or inventory, you should try checking this!
>Note: the base64 string may contain a unicode escape for non-alphabetical symbols (i.e. `=` will be displayed as `\u003d`), and some programming languages may have silent defects when interpreting the string.
