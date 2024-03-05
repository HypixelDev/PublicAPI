Hypixel Public API - Reactive Transport
======

### Usage

```xml

<dependency>
    <groupId>net.hypixel</groupId>
  <artifactId>hypixel-api-transport-reactor</artifactId>
  <version>4.4</version>
</dependency>
```

Can also be included with Gradle.

```gradle
dependencies {
    implementation 'net.hypixel:hypixel-api-transport-reactor:4.4'
}
```

### Example code

```java
public class Main {
    public static void main(String[] args) {
        HypixelHttpClient client = new ReactorHttpClient(UUID.fromString("your-api-key-here"));
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
* [Reactor Core 3.4.5](https://mvnrepository.com/artifact/io.projectreactor/reactor-core) (for reactor netty)
* Reactor Netty [(project-reactor)](https://projectreactor.io/docs):
    * [Netty Core 1.0.6](https://mvnrepository.com/artifact/io.projectreactor.netty/reactor-netty-core)
    * [Netty Http 1.0.6](https://mvnrepository.com/artifact/io.projectreactor.netty/reactor-netty-http)