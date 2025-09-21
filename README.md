Hypixel Public API (Java)
======
[![Maven Package](https://github.com/HypixelDev/PublicAPI/actions/workflows/maven.yml/badge.svg)](https://github.com/HypixelDev/PublicAPI/actions/workflows/maven.yml)

This is a Java implementation of the Hypixel API. For discussing the API, requesting help or suggestions you can use the
GitHub [Discussions](https://github.com/HypixelDev/PublicAPI/discussions).

### Documentation

Hypixel Public API documentation can be found at [https://api.hypixel.net/](https://api.hypixel.net/). Java
documentation can be found in the code.

### Issues

GitHub issues should only be used to report bugs with the Java implementation of the Public API. If you have
issues or questions with using the Public API, you should post in the GitHub discussions, or use the Hypixel
[Code Creations](https://hypixel.net/forums/code-creations.65/) forum.

If you have an issue with the Public API itself, such as an error when trying to use a specific endpoint, you
can report this on the Hypixel Forums under the "Website" -> "Public API" option [here](https://hypixel.net/bug-reports/create).

### Usage

You can use this API as a dependency via the public Hypixel maven repo. You can also use
the [Example Code](https://github.com/HypixelDev/PublicAPI/tree/master/hypixel-api-example) as a good starting point.

#### Hypixel Maven Repo

```xml

<repository>
    <id>Hypixel</id>
    <url>https://repo.hypixel.net/repository/Hypixel/</url>
</repository>
```

This repo can also be used with Gradle.

```gradle
repositories {
    maven { url 'https://repo.hypixel.net/repository/Hypixel/' }
}
```

#### Transports

We include three built-in options for communicating with the Hypixel API, you can include either of these or even
include the core API directly and create your own instance of HypixelHTTPClient.

* [Apache HttpClient Transport](hypixel-api-transport-apache/README.md)
* [Unirest Java Transport](hypixel-api-transport-unirest/README.md)
* [Project Reactor Transport](hypixel-api-transport-reactor/README.md) (automatic rate-limiting by default)

### Dependencies

The Hypixel API Core implementation has the following dependencies:

* [Google Gson library - 2.10.1](https://mvnrepository.com/artifact/com.google.code.gson/gson)

Transports will also have dependencies where required.

### Contributing

When contributing changes to the Java API please provide as much detail on the changes and the reasons for them. We will

not accept changes that have no meaningful contribution to the project.
