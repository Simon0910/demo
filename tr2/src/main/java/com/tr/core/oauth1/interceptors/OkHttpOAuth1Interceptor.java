//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.tr.core.oauth1.interceptors;

import com.tr.core.oauth1.signers.OkHttpSigner;
import okhttp3.Interceptor;
import okhttp3.Request.Builder;
import okhttp3.Response;

import java.io.IOException;
import java.security.PrivateKey;

public class OkHttpOAuth1Interceptor implements Interceptor {
    private final OkHttpSigner signer;

    public OkHttpOAuth1Interceptor(String consumerKey, PrivateKey signingKey) {
        this.signer = new OkHttpSigner(consumerKey, signingKey);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Builder builder = chain.request().newBuilder();
        this.signer.sign(builder);
        return chain.proceed(builder.build());
    }
}
