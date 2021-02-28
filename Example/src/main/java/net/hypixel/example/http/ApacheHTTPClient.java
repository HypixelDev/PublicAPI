package net.hypixel.example.http;

import net.hypixel.api.http.HypixelHTTPClient;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApacheHTTPClient implements HypixelHTTPClient {
    private final ExecutorService executorService;
    private final HttpClient httpClient;

    public ApacheHTTPClient() {
        this.executorService = Executors.newCachedThreadPool();
        this.httpClient = HttpClientBuilder.create().build();
    }

    @Override
    public CompletableFuture<String> makeRequest(String url) {
        CompletableFuture<String> future = new CompletableFuture<>();
        this.executorService.submit(() -> {
            try {
                future.complete(this.httpClient.execute(new HttpGet(url), obj -> EntityUtils.toString(obj.getEntity(), "UTF-8")));
            } catch (Throwable t) {
                future.completeExceptionally(t);
            }
        });
        return future;
    }

    @Override
    public CompletableFuture<String> makeAuthenticatedRequest(String url, UUID apiKey) {
        CompletableFuture<String> future = new CompletableFuture<>();
        this.executorService.submit(() -> {
            try {
                HttpGet request = new HttpGet(url);
                request.addHeader("API-Key", apiKey.toString());
                future.complete(this.httpClient.execute(request, obj -> EntityUtils.toString(obj.getEntity(), "UTF-8")));
            } catch (Throwable t) {
                future.completeExceptionally(t);
            }
        });
        return future;
    }

    @Override
    public void shutdown() {
        this.executorService.shutdown();
    }

}
