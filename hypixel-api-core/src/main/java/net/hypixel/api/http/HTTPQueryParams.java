package net.hypixel.api.http;

import java.util.HashMap;
import java.util.Map;

public class HTTPQueryParams {

    public static HTTPQueryParams create() {
        return new HTTPQueryParams();
    }

    private final Map<String, Object> params = new HashMap<>();

    private HTTPQueryParams() {

    }

    public HTTPQueryParams add(String key, Object value) {
        this.params.put(key, value);
        return this;
    }

    public String getAsQueryString(String base) {
        StringBuilder url = new StringBuilder(base);
        boolean startedQuery = false;

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (!startedQuery) {
                startedQuery = true;
                url.append("?");
            } else {
                url.append("&");
            }

            url.append(entry.getKey()).append("=").append(entry.getValue());
        }

        return url.toString();
    }
}
