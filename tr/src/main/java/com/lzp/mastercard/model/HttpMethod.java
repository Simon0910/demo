//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lzp.mastercard.model;

import org.apache.http.client.methods.*;

public enum HttpMethod {
    GET(HttpGet.class),
    PUT(HttpPut.class),
    POST(HttpPost.class),
    DELETE(HttpDelete.class),
    HEAD(HttpHead.class),
    PATCH(HttpPatch.class);

    private final Class<? extends HttpRequestBase> requestType;

    public Class<? extends HttpRequestBase> getRequestType() {
        return this.requestType;
    }

    HttpMethod(Class<? extends HttpRequestBase> type) {
        this.requestType = type;
    }

    public String getHttpMethodAsString() {
        try {
            return this.getRequestType().newInstance().getMethod().toUpperCase();
        } catch (Exception var2) {
            throw new IllegalArgumentException(var2);
        }
    }

    public static HttpMethod fromAction(Action action) {
        HttpMethod httpMethod = null;
        switch (action) {
            case query:
                httpMethod = GET;
                break;
            case read:
                httpMethod = GET;
                break;
            case list:
                httpMethod = GET;
                break;
            case update:
                httpMethod = PUT;
                break;
            case create:
                httpMethod = POST;
                break;
            case delete:
                httpMethod = DELETE;
        }

        return httpMethod;
    }
}
