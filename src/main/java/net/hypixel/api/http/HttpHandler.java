package net.hypixel.api.http;

import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import net.hypixel.api.util.Callback;

import java.nio.charset.Charset;

public class HttpHandler<T> extends SimpleChannelInboundHandler<HttpObject> {
    private final Callback<T> callback;
    private final StringBuilder buffer;
    private final Gson gson;

    public HttpHandler(Callback<T> callback, Gson gson) {
        this.callback = callback;
        this.buffer = new StringBuilder();
        this.gson = gson;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        try {
            callback.callback(cause, null);
        } finally {
            ctx.channel().close();
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpResponse) {
            HttpResponse response = (HttpResponse) msg;
            int responseCode = response.getStatus().code();

            if (responseCode == HttpResponseStatus.NO_CONTENT.code()) {
                done(ctx);
                return;
            }

            if (responseCode != HttpResponseStatus.OK.code()) {
                throw new IllegalStateException("Expected HTTP response 200 OK, got " + response.getStatus());
            }
        }
        if (msg instanceof HttpContent) {
            HttpContent content = (HttpContent) msg;
            buffer.append(content.content().toString(Charset.forName("UTF-8")));

            if (msg instanceof LastHttpContent) {
                done(ctx);
            }
        }
    }

    private void done(ChannelHandlerContext ctx) {
        try {
            callback.callback(null, gson.fromJson(buffer.toString(), callback.getClazz()));
        } catch (Throwable t) {
            throw new RuntimeException("Buffer "+buffer.toString(), t);
        } finally {
            ctx.channel().close();
        }
    }
}
