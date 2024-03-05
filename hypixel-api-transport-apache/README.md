Hypixel Public API - Apache Transport
======

### Usage

```xml

<dependency>
    <groupId>net.hypixel</groupId>
    <artifactId>hypixel-api-transport-apache</artifactId>
    <version>4.4</version>
</dependency>
```

Can also be included with Gradle.

```gradle
dependencies {
    implementation 'net.hypixel:hypixel-api-transport-apache:4.4'
}
```

### Example code

```java
public class Main {
    public static void main(String[] args) {
        HypixelHttpClient client = new ApacheHttpClient(UUID.fromString("your-api-key-here"));
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
* [Apache HttpClient - 4.5.14](https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient)