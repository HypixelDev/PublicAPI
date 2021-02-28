package net.hypixel.example.http;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import net.hypixel.api.http.HypixelHTTPClient;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class UnirestHTTPClient implements HypixelHTTPClient {

    @Override
    public CompletableFuture<String> makeRequest(String url) {
        return Unirest.get(url).asStringAsync().thenApply(HttpResponse::getBody);
    }

    @Override
    public CompletableFuture<String> makeAuthenticatedRequest(String url, UUID apiKey) {
        return Unirest.get(url).header("API-Key", apiKey.toString()).asStringAsync().thenApply(HttpResponse::getBody);
    }

    @Override
    public void shutdown() {
        Unirest.shutDown();
    }

}
