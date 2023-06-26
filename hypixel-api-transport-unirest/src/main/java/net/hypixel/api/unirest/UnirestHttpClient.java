package net.hypixel.api.unirest;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import net.hypixel.api.http.HypixelHttpClient;
import net.hypixel.api.http.HypixelHttpResponse;
import net.hypixel.api.http.RateLimit;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class UnirestHttpClient implements HypixelHttpClient {

    private final UUID apiKey;

    public UnirestHttpClient(UUID apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public CompletableFuture<HypixelHttpResponse> makeRequest(String url) {
        return Unirest.get(url)
                .header("User-Agent", DEFAULT_USER_AGENT)
                .asStringAsync()
                .thenApply(res -> new HypixelHttpResponse(res.getStatus(), res.getBody(), null));
    }

    @Override
    public CompletableFuture<HypixelHttpResponse> makeAuthenticatedRequest(String url) {
        return Unirest.get(url)
                .header("User-Agent", DEFAULT_USER_AGENT)
                .header("API-Key", this.apiKey.toString())
                .asStringAsync()
                .thenApply(res -> new HypixelHttpResponse(res.getStatus(), res.getBody(), createRateLimitResponse(res)));
    }

    private RateLimit createRateLimitResponse(HttpResponse<String> response) {
        if (response.getStatus() != 200) {
            return null;
        }

        int limit = Integer.parseInt(response.getHeaders().getFirst("RateLimit-Limit"));
        int remaining = Integer.parseInt(response.getHeaders().getFirst("RateLimit-Remaining"));
        int reset = Integer.parseInt(response.getHeaders().getFirst("RateLimit-Reset"));
        return new RateLimit(limit, remaining, reset);
    }

    @Override
    public void shutdown() {
        Unirest.shutDown();
    }

}
