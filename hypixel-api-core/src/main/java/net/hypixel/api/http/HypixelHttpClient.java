package net.hypixel.api.http;

import java.util.concurrent.CompletableFuture;

public interface HypixelHttpClient {

    CompletableFuture<HypixelHttpResponse> makeRequest(String url);

    CompletableFuture<HypixelHttpResponse> makeAuthenticatedRequest(String url);

    void shutdown();

}
