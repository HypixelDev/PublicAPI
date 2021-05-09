package net.hypixel.api.unirest;

import kong.unirest.Unirest;
import net.hypixel.api.http.HypixelHttpClient;
import net.hypixel.api.http.HypixelHttpResponse;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class UnirestHttpClient implements HypixelHttpClient {

    private final UUID apiKey;

    public UnirestHttpClient(UUID apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public CompletableFuture<HypixelHttpResponse> makeRequest(String url) {
        return Unirest.get(url).asStringAsync().thenApply(res -> new HypixelHttpResponse(res.getStatus(), res.getBody()));
    }

    @Override
    public CompletableFuture<HypixelHttpResponse> makeAuthenticatedRequest(String url) {
        return Unirest.get(url).header("API-Key", this.apiKey.toString()).asStringAsync().thenApply(res -> new HypixelHttpResponse(res.getStatus(), res.getBody()));
    }

    @Override
    public void shutdown() {
        Unirest.shutDown();
    }

}
