package net.hypixel.api.request;

import com.google.common.collect.Maps;
import net.hypixel.api.exceptions.ParamTypeException;

import java.util.Map;

@SuppressWarnings("unused")
public class RequestBuilder {
    private RequestType requestType = null;
    private Map<RequestParam, Object> params = Maps.newHashMap();

    private RequestBuilder(RequestType requestType) {
        this.requestType = requestType;
    }

    public static RequestBuilder newBuilder(RequestType requestType) {
        return new RequestBuilder(requestType);
    }

    public RequestBuilder addParam(RequestParam param, Object value) {
        if (!validate(param, value)) {
            throw new ParamTypeException(param, value);
        }
        this.params.put(param, value);
        return this;
    }

    /**
     * Validate value for param, making sure it is of
     * the correct RequestParam type and
     * can be used for given RequestType
     */
    private boolean validate(RequestParam param, Object value) {
        if (value != null && value.getClass().equals(param.getValueClass())) {
            if (param.getRequestType() == requestType) {
                return true;
            }
        }
        return false;
    }

    /**
     * Builds a request from the builder
     */
    public Request createRequest() {
        return new Request(requestType, params);
    }
}