//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.tr.core.oauth1.signers;

import com.tr.core.oauth1.oauth.OAuth;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okio.Buffer;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;

public class OkHttpSigner extends AbstractSigner {
    public OkHttpSigner(String consumerKey, PrivateKey signingKey) {
        super(StandardCharsets.UTF_8, consumerKey, signingKey);
    }

    public OkHttpSigner(Charset charset, String consumerKey, PrivateKey signingKey) {
        super(charset, consumerKey, signingKey);
    }

    public void sign(Builder req) throws IOException {
        Request builtRequest = req.build();
        URI uri = builtRequest.url().uri();
        String method = builtRequest.method();
        String payload = null;
        RequestBody body = builtRequest.body();
        if (null != body && body.contentLength() > 0L) {
            Buffer buffer = new Buffer();
            builtRequest.body().writeTo(buffer);
            payload = buffer.readUtf8();
        }

        String authHeader = OAuth.getAuthorizationHeader(uri, method, payload, this.charset, this.consumerKey, this.signingKey);
        req.addHeader("Authorization", authHeader);
    }
}
