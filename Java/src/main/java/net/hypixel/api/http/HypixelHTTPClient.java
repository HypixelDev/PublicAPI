package net.hypixel.api.http;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface HypixelHTTPClient {

    CompletableFuture<String> makeRequest(String url);

    CompletableFuture<String> makeAuthenticatedRequest(String url, UUID apiKey);

    void shutdown();

}
