Hypixel Public API (Java)
======
This is a Java implementation of the Hypixel API

### Documentation
Hypixel PublicAPI documentation can be found in the [Documentation](https://github.com/HypixelDev/PublicAPI/tree/master/Documentation).
Java documentation can be found in the code.

### Usage
You can use this API as a dependency via the public Hypixel maven repo.

```xml
<repository>
    <id>Hypixel</id>
    <url>https://repo.hypixel.net/repository/Hypixel/</url>
</repository>
```

```xml
<dependency>
    <groupId>net.hypixel</groupId>
    <artifactId>HypixelAPI</artifactId>
    <version>3.0.0</version>
</dependency>
```

### Query Limitations
The API server has a request limit of 120 queries per minute. Any abuse of the API or intentions to bypass this limit (such as with multiple API keys) will lead to your API key being reset or banned.

If you require a higher limit than the above you can open a support ticket at https://support.hypixel.net and provide your use case and why you require a higher limit.

### Obtaining an API Key
You can obtain an API key by joining ```mc.hypixel.net``` with a valid Minecraft account and running the /api command. You will then be assigned a unique key that is to remain **private**.

### Dependencies
The Hypixel PublicAPI has the following dependencies:
* Google Gson library
* Apache HttpClient

### Bug Reporting
You can create an issue here on GitHub to report a bug with the API or to suggest enhancements by going [here](https://github.com/HypixelDev/PublicAPI/issues/new)
