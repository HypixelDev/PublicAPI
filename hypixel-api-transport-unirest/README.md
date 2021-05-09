Hypixel Public API - Unirest Transport
======

```xml
<dependency>
    <groupId>net.hypixel</groupId>
    <artifactId>hypixel-api-transport-unirest</artifactId>
    <version>4.0.0</version>
</dependency>
```

```java
public class Main {
    public static void main(String[] args) {
        HypixelHTTPClient client = new UnirestHTTPClient(UUID.fromString("your-api-key-here"));
        HypixelAPI hypixelAPI = new HypixelAPI(client);
        hypixelAPI.getPlayerByName("Hypixel").thenAccept(System.out::println);
    }
}
```

### Dependencies

This transport depends on the following:

* [Google Gson library](https://mvnrepository.com/artifact/com.google.code.gson/gson) (for hypixel-api-core)
* [Unirest Java](https://mvnrepository.com/artifact/com.konghq/unirest-java)