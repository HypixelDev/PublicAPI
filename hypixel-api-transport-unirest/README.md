Hypixel Public API - Unirest Transport
======

### Usage

```xml

<dependency>
    <groupId>net.hypixel</groupId>
    <artifactId>hypixel-api-transport-unirest</artifactId>
    <version>4.4</version>
</dependency>
```

Can also be included with Gradle.

```gradle
dependencies {
    implementation 'net.hypixel:hypixel-api-transport-unirest:4.4'
}
```

### Example code

```java
public class Main {
    public static void main(String[] args) {
        HypixelHttpClient client = new UnirestHttpClient(UUID.fromString("your-api-key-here"));
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

* [Google Gson library - 2.10.1](https://mvnrepository.com/artifact/com.google.code.gson/gson) (for hypixel-api-core)
* [Unirest Java - 3.14.4](https://mvnrepository.com/artifact/com.konghq/unirest-java)