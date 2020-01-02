package com.lzp.mastercard.security.oauth;

import com.lzp.mastercard.model.HttpMethod;
import com.lzp.mastercard.security.util.DataEncoding;
import oauth.signpost.http.HttpRequest;
import org.apache.http.entity.ContentType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;

public class OAuthRequest implements HttpRequest {

    private Map<String, String> headers;
    private String contentType;
    private String body;
    private String method;
    private String requestUrl;
    private Map<String, String> formParams;

    public OAuthRequest() {
        super();
        headers = new LinkedHashMap<String, String>();
        formParams = new LinkedHashMap<String, String>();
    }

    public Map<String, String> getFormParams() {
        return formParams;
    }

    public void addFormParam(String name, String value) {
        this.formParams.put(name, value);
    }

    public void setFormParams(Map<String, String> formParams) {
        this.formParams = formParams;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType.getMimeType();
    }

    public void setMethod(HttpMethod httpMethod) {
        this.method = httpMethod.getHttpMethodAsString();
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public String getRequestUrl() {
        return requestUrl;
    }

    @Override
    public void setRequestUrl(String url) {
        this.requestUrl = url;
    }

    @Override
    public void setHeader(String name, String value) {
        headers.put(name, value);
    }

    @Override
    public String getHeader(String name) {
        return headers.get(name);
    }

    @Override
    public Map<String, String> getAllHeaders() {
        return headers;
    }

    @Override
    public InputStream getMessagePayload() throws IOException {
        StringBuilder sb = new StringBuilder();

        for (String key : formParams.keySet()) {
            sb.append(key + "&" + formParams.get(key));
        }

        InputStream is = new ByteArrayInputStream(sb.toString().getBytes(OAuthConstants.UTF_8));

        return is;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public Object unwrap() {
        return this;
    }

    /**
     * Get OAuth Body Hash
     *
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public String getOauthBodyHash() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest digest;

        try {
            digest = MessageDigest.getInstance(OAuthConstants.SHA256);
        } catch (NoSuchAlgorithmException e) {
            throw e;
        }

        digest.reset();
        byte[] hash;

        try {
            byte[] byteArray = getBody().getBytes(OAuthConstants.UTF_8);
            hash = digest.digest(byteArray);
        } catch (UnsupportedEncodingException e) {
            throw e;
        }

        return DataEncoding.base64Encode(hash);
    }

}

