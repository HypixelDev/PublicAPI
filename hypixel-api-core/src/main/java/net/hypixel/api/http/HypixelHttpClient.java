package net.hypixel.api.http;

import java.util.concurrent.CompletableFuture;

public interface HypixelHttpClient {

    String DEFAULT_USER_AGENT = "Hypixel PublicAPI/4.0";

    CompletableFuture<HypixelHttpResponse> makeRequest(String url);

    CompletableFuture<HypixelHttpResponse> makeAuthenticatedRequest(String url);

    void shutdown();

}
