package net.hypixel.api;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import net.hypixel.api.util.ResourceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class TestResources {

    static Stream<Arguments> getResourceTypes() {
        return Stream.of(ResourceType.values())
                .map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("getResourceTypes")
    void testResource(ResourceType resourceType) {
        String url = String.format("%sresources/%s", HypixelAPI.BASE_URL, resourceType.getPath());
        HttpResponse<JsonNode> response = Unirest.get(url).asJson();
        Assertions.assertEquals(200, response.getStatus(), String.format("Got an invalid status code for %s", resourceType));
    }

}
