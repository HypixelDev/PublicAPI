package net.hypixel.example.http;

import net.hypixel.api.http.HypixelHTTPClient;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApacheHTTPClient implements HypixelHTTPClient {
    private final UUID apiKey;
    private final ExecutorService executorService;
    private final HttpClient httpClient;

    public ApacheHTTPClient(UUID apiKey) {
        this.apiKey = apiKey;
        this.executorService = Executors.newCachedThreadPool();
        this.httpClient = HttpClientBuilder.create().build();
    }

    @Override
    public CompletableFuture<String> makeRequest(String url) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return this.httpClient.execute(new HttpGet(url), obj -> EntityUtils.toString(obj.getEntity(), "UTF-8"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, this.executorService);
    }

    @Override
    public CompletableFuture<String> makeAuthenticatedRequest(String url) {
        return CompletableFuture.supplyAsync(() -> {
            HttpGet request = new HttpGet(url);
            request.addHeader("API-Key", this.apiKey.toString());
            try {
                return this.httpClient.execute(request, obj -> EntityUtils.toString(obj.getEntity(), "UTF-8"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, this.executorService);
    }

    @Override
    public void shutdown() {
        this.executorService.shutdown();
    }

}
