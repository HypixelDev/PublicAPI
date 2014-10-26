package net.hypixel.api.http;

import com.google.common.net.MediaType;
import com.google.gson.Gson;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import net.hypixel.api.util.Callback;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

public class HTTPClient {
    private static final int CONNECT_TIMEOUT = 5000;
    private static final Gson gson = new Gson();
    private final Bootstrap bootstrap;
    private final NioEventLoopGroup eventLoop;

    public HTTPClient() {
        this(0);
    }

    public HTTPClient(int nThreads) {
        eventLoop = new NioEventLoopGroup(nThreads);
        bootstrap = new Bootstrap()
                .channel(NioSocketChannel.class)
                .group(eventLoop)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECT_TIMEOUT);
    }

    public void close() {
        eventLoop.shutdownGracefully();
    }

    private String getPath(URI uri) {
        return uri.getRawPath() + ((uri.getRawQuery() == null) ? "" : "?" + uri.getRawQuery());
    }

    public <T> void post(String url, ByteBuf content, MediaType mediaType, final Callback<T> callback) {
        URI uri = URI.create(url);
        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, url, content);
        request.headers().set("Content-Length", (long) content.readableBytes());
        request.headers().set("Content-Type", mediaType);
        request(uri, request, callback);
    }

    public <T> void get(String url, final Callback<T> callback) {
        URI uri = URI.create(url);
        request(uri, new DefaultHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, getPath(uri)), callback);
    }

    public <T> void request(final URI uri, final HttpRequest request, final Callback<T> callback) {
        int port = uri.getPort();
        boolean ssl = uri.getScheme().equals("https");
        if (port == -1) {
            switch (uri.getScheme()) {
                case "http":
                    port = 80;
                    break;
                case "https":
                    port = 443;
                    break;
                default:
                    throw new RuntimeException("Unknown scheme " + uri.getScheme());
            }
        }
        InetAddress inetAddress;
        try {
            inetAddress = InetAddress.getByName(uri.getHost());
        } catch (UnknownHostException e) {
            callback.callback(e, null);
            return;
        }
        ChannelFutureListener future = new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    request.headers().set(HttpHeaders.Names.HOST, uri.getHost());
                    future.channel().writeAndFlush(request);
                } else {
                    callback.callback(future.cause(), null);
                }
            }
        };
        bootstrap.handler(new HttpInitializer<>(ssl, callback, gson)).connect(inetAddress, port).addListener(future);
    }
}
