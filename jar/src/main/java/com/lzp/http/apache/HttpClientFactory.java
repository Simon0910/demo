package com.lzp.http.apache;

import com.lzp.utils.Global;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.X509Certificate;

// import org.apache.commons.logging.LogFactory;

/**
 *
 */
public class HttpClientFactory {
    private static CloseableHttpClient httpClient;
    private static String keyStorePath = Global.getConfig("sslKeyStorePath");
    private static String keyStorePassword = Global.getConfig("sslKeyStorePassword");
    private static String keyStoreType = Global.getConfig("sslKeyStoreType");
    private static String trustStorePath = Global.getConfig("sslTrustStore");
    private static String trustStorePassword = Global.getConfig("sslTrustStorePassword");
    private static String trustStoreType = Global.getConfig("sslTrustStoreType");


    public synchronized static CloseableHttpClient getCloseableHttpClient() {
        if (httpClient == null) {
            SSLConnectionSocketFactory sslConnectionSocketFactory = null;
            FileInputStream keyin = null;
            FileInputStream trustin = null;
            try {
                keyin = new FileInputStream(new File(keyStorePath));
                KeyStore keystore = KeyStore.getInstance(keyStoreType);
                keystore.load(keyin, keyStorePassword.toCharArray());
                // KeyManagerFactory keyFactory = KeyManagerFactory.getInstance("SunX509", "SunJSSE");
                KeyManagerFactory keyFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                keyFactory.init(keystore, keyStorePassword.toCharArray());
                KeyManager[] keyManagers = keyFactory.getKeyManagers();

                trustin = new FileInputStream(new File(trustStorePath));
                KeyStore trustKeystore = KeyStore.getInstance(trustStoreType);
                trustKeystore.load(trustin, trustStorePassword.toCharArray());
                // TrustManagerFactory trustFactory = TrustManagerFactory.getInstance("SunX509", "SunJSSE");
                // TrustManagerFactory trustFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                TrustManagerFactory trustFactory = TrustManagerFactory.getInstance("SunX509");
                trustFactory.init(trustKeystore);
                TrustManager[] trustManagers = trustFactory.getTrustManagers();
                // trustManagers = trustManager();

                SSLContext sslcontext = SSLContext.getInstance("TLS");
                // sslcontext.init(keyManagers, trustManagers, null);
                sslcontext.init(keyManagers, null, null);

                // SSLContext sslcontext = SSLContexts.custom()
                //         .loadKeyMaterial(keystore, keyStorePassword.toCharArray())
                //         .loadTrustMaterial(trustKeystore, new TrustSelfSignedStrategy())
                //         .build();

                sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                        sslcontext,
                        new String[]{"TLSv1.2" },
                        null,
                        SSLConnectionSocketFactory.getDefaultHostnameVerifier());
                // new HttpsHostnameVerifier());


            } catch (Exception e) {
                // LogFactory.getLog(HttpClientFactory.class).error(e);
                System.out.println(e);
            } finally {
                if (keyin != null) {
                    try {
                        keyin.close();
                    } catch (IOException e) {
                        keyin = null;
                    }
                }
                if (trustin != null) {
                    try {
                        trustin.close();
                    } catch (IOException e) {
                        trustin = null;
                    }
                }
            }

            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", sslConnectionSocketFactory)
                    .build();

            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
            // Configure total max or per route limits for persistent connections
            // that can be kept in the pool or leased by the connection manager.
            // 个性化设置某个url的连接
            //connManager.setMaxPerRoute(new HttpRoute(new HttpHost("www.y.com", 80)), 20);
            connectionManager.setMaxTotal(200);
            connectionManager.setDefaultMaxPerRoute(20);

            HttpClientBuilder httpClientBuilder = HttpClients.custom()
                    .setConnectionManager(connectionManager);

            httpClient = httpClientBuilder.build();
        }
        return httpClient;
    }


    private static TrustManager[] trustManager() {
        //Implementation of a trust manager for X509 certificates
        X509TrustManager tm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType)
                    throws java.security.cert.CertificateException {
                System.out.println(chain);
                System.out.println(authType);
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType)
                    throws java.security.cert.CertificateException {
                System.out.println(chain);
                System.out.println(authType);
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        TrustManager[] trustManagers = new TrustManager[]{tm};
        return trustManagers;
    }
}