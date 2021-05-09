Hypixel Public API - Apache Transport
======

### Usage

```xml
<dependency>
    <groupId>net.hypixel</groupId>
    <artifactId>hypixel-api-transport-apache</artifactId>
    <version>4.0.0</version>
</dependency>
```

Can also be included with Gradle.

```gradle
dependencies {
    implementation 'net.hypixel:hypixel-api-transport-apache:4.0.0'
}
```

### Example code

```java
public class Main {
    public static void main(String[] args) {
        HypixelHTTPClient client = new ApacheHTTPClient(UUID.fromString("your-api-key-here"));
        HypixelAPI hypixelAPI = new HypixelAPI(client);
        hypixelAPI.getPlayerByName("Hypixel")
                .exceptionally(throwable -> {
                    // Handle exceptions here
                    throwable.printStackTrace();
                    return null;
                })
                .thenAccept(System.out::println);
    }
}
```

### Dependencies

This transport depends on the following:

* [Google Gson library](https://mvnrepository.com/artifact/com.google.code.gson/gson) (for hypixel-api-core)
* [Apache HttpClient](https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient)