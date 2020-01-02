//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lzp.mastercard.http;

import com.lzp.mastercard.ApiConfig;
import org.apache.http.*;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.auth.AuthSchemeProvider;
import org.apache.http.client.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.InputStreamFactory;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Lookup;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.SchemePortResolver;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.util.TextUtils;

import javax.net.ssl.*;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@NotThreadSafe
public class CustomHttpClientBuilder {
    private HttpClientBuilder builder = HttpClientBuilder.create();
    private static String[] SUPPORTED_TLS = new String[]{"TLSv1.1", "TLSv1.2"};

    public static CustomHttpClientBuilder create() {
        return new CustomHttpClientBuilder();
    }

    protected CustomHttpClientBuilder() {
    }

    final CustomHttpClientBuilder setRequestExecutor(HttpRequestExecutor requestExec) {
        this.builder.setRequestExecutor(requestExec);
        return this;
    }

    public final CustomHttpClientBuilder setSSLHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.builder.setSSLHostnameVerifier(hostnameVerifier);
        return this;
    }

    final CustomHttpClientBuilder setPublicSuffixMatcher(PublicSuffixMatcher publicSuffixMatcher) {
        this.builder.setPublicSuffixMatcher(publicSuffixMatcher);
        return this;
    }

    public final CustomHttpClientBuilder setMaxConnTotal(int maxConnTotal) {
        this.builder.setMaxConnTotal(maxConnTotal);
        return this;
    }

    public final CustomHttpClientBuilder setMaxConnPerRoute(int maxConnPerRoute) {
        this.builder.setMaxConnPerRoute(maxConnPerRoute);
        return this;
    }

    public final CustomHttpClientBuilder setDefaultSocketConfig(SocketConfig config) {
        this.builder.setDefaultSocketConfig(config);
        return this;
    }

    public final CustomHttpClientBuilder setDefaultConnectionConfig(ConnectionConfig config) {
        this.builder.setDefaultConnectionConfig(config);
        return this;
    }

    public final CustomHttpClientBuilder setConnectionTimeToLive(long connTimeToLive, TimeUnit connTimeToLiveTimeUnit) {
        this.builder.setConnectionTimeToLive(connTimeToLive, connTimeToLiveTimeUnit);
        return this;
    }

    public final CustomHttpClientBuilder setConnectionManager(HttpClientConnectionManager connManager) {
        this.builder.setConnectionManager(connManager);
        return this;
    }

    public final CustomHttpClientBuilder setConnectionManagerShared(boolean shared) {
        this.builder.setConnectionManagerShared(shared);
        return this;
    }

    public final CustomHttpClientBuilder setConnectionReuseStrategy(ConnectionReuseStrategy reuseStrategy) {
        this.builder.setConnectionReuseStrategy(reuseStrategy);
        return this;
    }

    public final CustomHttpClientBuilder setKeepAliveStrategy(ConnectionKeepAliveStrategy keepAliveStrategy) {
        this.builder.setKeepAliveStrategy(keepAliveStrategy);
        return this;
    }

    public CustomHttpClientBuilder setTargetAuthenticationStrategy(AuthenticationStrategy targetAuthStrategy) {
        this.builder.setTargetAuthenticationStrategy(targetAuthStrategy);
        return this;
    }

    public final CustomHttpClientBuilder setProxyAuthenticationStrategy(AuthenticationStrategy proxyAuthStrategy) {
        this.builder.setProxyAuthenticationStrategy(proxyAuthStrategy);
        return this;
    }

    final CustomHttpClientBuilder setUserTokenHandler(UserTokenHandler userTokenHandler) {
        this.builder.setUserTokenHandler(userTokenHandler);
        return this;
    }

    public final CustomHttpClientBuilder disableConnectionState() {
        this.builder.disableConnectionState();
        return this;
    }

    public final CustomHttpClientBuilder setSchemePortResolver(SchemePortResolver schemePortResolver) {
        this.builder.setSchemePortResolver(schemePortResolver);
        return this;
    }

    final CustomHttpClientBuilder setUserAgent(String userAgent) {
        this.builder.setUserAgent(userAgent);
        return this;
    }

    final CustomHttpClientBuilder setDefaultHeaders(Collection<? extends Header> defaultHeaders) {
        this.builder.setDefaultHeaders(defaultHeaders);
        return this;
    }

    final CustomHttpClientBuilder addInterceptorFirst(HttpResponseInterceptor itcp) {
        this.builder.addInterceptorFirst(itcp);
        return this;
    }

    final CustomHttpClientBuilder addInterceptorLast(HttpResponseInterceptor itcp) {
        this.builder.addInterceptorLast(itcp);
        return this;
    }

    final CustomHttpClientBuilder addInterceptorFirst(HttpRequestInterceptor itcp) {
        this.builder.addInterceptorFirst(itcp);
        return this;
    }

    final CustomHttpClientBuilder addInterceptorLast(HttpRequestInterceptor itcp) {
        this.builder.addInterceptorLast(itcp);
        return this;
    }

    public final CustomHttpClientBuilder disableCookieManagement() {
        this.builder.disableCookieManagement();
        return this;
    }

    public final CustomHttpClientBuilder disableContentCompression() {
        this.builder.disableContentCompression();
        return this;
    }

    public final CustomHttpClientBuilder disableAuthCaching() {
        this.builder.disableAuthCaching();
        return this;
    }

    final CustomHttpClientBuilder setHttpProcessor(HttpProcessor httpprocessor) {
        this.builder.setHttpProcessor(httpprocessor);
        return this;
    }

    public final CustomHttpClientBuilder setDnsResolver(DnsResolver dnsResolver) {
        this.builder.setDnsResolver(dnsResolver);
        return this;
    }

    public final CustomHttpClientBuilder setRetryHandler(HttpRequestRetryHandler retryHandler) {
        this.builder.setRetryHandler(retryHandler);
        return this;
    }

    public final CustomHttpClientBuilder disableAutomaticRetries() {
        this.builder.disableAutomaticRetries();
        return this;
    }

    public final CustomHttpClientBuilder setProxy(HttpHost proxy) {
        this.builder.setProxy(proxy);
        return this;
    }

    public final CustomHttpClientBuilder setRoutePlanner(HttpRoutePlanner routePlanner) {
        this.builder.setRoutePlanner(routePlanner);
        return this;
    }

    public final CustomHttpClientBuilder setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.builder.setRedirectStrategy(redirectStrategy);
        return this;
    }

    public final CustomHttpClientBuilder disableRedirectHandling() {
        this.builder.disableRedirectHandling();
        return this;
    }

    public final CustomHttpClientBuilder setConnectionBackoffStrategy(ConnectionBackoffStrategy connectionBackoffStrategy) {
        this.builder.setConnectionBackoffStrategy(connectionBackoffStrategy);
        return this;
    }

    public final CustomHttpClientBuilder setBackoffManager(BackoffManager backoffManager) {
        this.builder.setBackoffManager(backoffManager);
        return this;
    }

    public final CustomHttpClientBuilder setServiceUnavailableRetryStrategy(ServiceUnavailableRetryStrategy serviceUnavailStrategy) {
        this.builder.setServiceUnavailableRetryStrategy(serviceUnavailStrategy);
        return this;
    }

    final CustomHttpClientBuilder setDefaultCookieStore(CookieStore cookieStore) {
        this.builder.setDefaultCookieStore(cookieStore);
        return this;
    }

    public final CustomHttpClientBuilder setDefaultCredentialsProvider(CredentialsProvider credentialsProvider) {
        this.builder.setDefaultCredentialsProvider(credentialsProvider);
        return this;
    }

    public final CustomHttpClientBuilder setDefaultAuthSchemeRegistry(Lookup<AuthSchemeProvider> authSchemeRegistry) {
        this.builder.setDefaultAuthSchemeRegistry(authSchemeRegistry);
        return this;
    }

    public final CustomHttpClientBuilder setDefaultCookieSpecRegistry(Lookup<CookieSpecProvider> cookieSpecRegistry) {
        this.builder.setDefaultCookieSpecRegistry(cookieSpecRegistry);
        return this;
    }

    public final CustomHttpClientBuilder setContentDecoderRegistry(Map<String, InputStreamFactory> contentDecoderMap) {
        this.builder.setContentDecoderRegistry(contentDecoderMap);
        return this;
    }

    public final CustomHttpClientBuilder setDefaultRequestConfig(RequestConfig config) {
        this.builder.setDefaultRequestConfig(config);
        return this;
    }

    public final CustomHttpClientBuilder useSystemProperties() {
        this.builder.useSystemProperties();
        return this;
    }

    public final CustomHttpClientBuilder evictExpiredConnections() {
        this.builder.evictExpiredConnections();
        return this;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public final CustomHttpClientBuilder evictIdleConnections(Long maxIdleTime, TimeUnit maxIdleTimeUnit) {
        return this.evictIdleConnections(maxIdleTime, maxIdleTimeUnit);
    }

    public final CustomHttpClientBuilder evictIdleConnections(long maxIdleTime, TimeUnit maxIdleTimeUnit) {
        this.builder.evictIdleConnections(maxIdleTime, maxIdleTimeUnit);
        return this;
    }

    private static String[] split(String s) {
        return TextUtils.isBlank(s) ? null : s.split(" *, *");
    }

    public CloseableHttpClient build() {
        this.builder.useSystemProperties();
        this.builder.disableCookieManagement();
        String[] supportedProtocols = SUPPORTED_TLS;
        String[] supportedCipherSuites = split(System.getProperty("https.cipherSuites"));
        if (ApiConfig.isDebug()) {
            this.disableContentCompression();
        }

        if (ApiConfig.ignoreSSLErrors()) {
            try {
                SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init((KeyManager[]) null, new TrustManager[]{new X509TrustManager() {
                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }}, new SecureRandom());
                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, supportedProtocols, supportedCipherSuites, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                this.builder.setSSLSocketFactory(sslsf);
                return this.builder.build();
            } catch (Exception var5) {
            }
        } else {
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory((SSLSocketFactory) SSLSocketFactory.getDefault(), supportedProtocols, supportedCipherSuites, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            this.builder.setSSLSocketFactory(sslsf);
        }

        return this.builder.build();
    }
}
