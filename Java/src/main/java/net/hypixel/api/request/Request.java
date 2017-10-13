/* © 2017 Hypixel Inc. All Rights Reserved. */
package net.hypixel.api.request;

import net.hypixel.api.HypixelAPI;

import java.util.Map;

public class Request {

    private static final String BASE_URL = "https://api.hypixel.net/";

    private final RequestType requestType;
    private final Map<RequestParam, Object> params;

    public Request(RequestType requestType, Map<RequestParam, Object> params) {
        this.requestType = requestType;
        this.params = params;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public Map<RequestParam, Object> getParams() {
        return params;
    }

    public String getURL(HypixelAPI hypixelAPI) {
        String url = BASE_URL;

        url += requestType.getKey();
        url += "?" + RequestParam.KEY.getQueryField() + "=" + hypixelAPI.getApiKey();

        for (Map.Entry<RequestParam, Object> entry : params.entrySet()) {
            url += "&" + entry.getKey().getQueryField() + "=" + entry.getKey().serialize(entry.getValue());
        }

        return url;
    }
}
