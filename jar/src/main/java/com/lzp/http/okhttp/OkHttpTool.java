package com.lzp.http.okhttp;

import okhttp3.*;

import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

public class OkHttpTool {

    public static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");
    public static final String AUTH_FILE_URL = "E:/JD_RES/https/CJD_DolfinWallet_XMLGW_MTF_160680/mastercard-ca.crt";//配置的地址
    private static String keyStorePath = "E:/JD_RES/https/CJD_DolfinWallet_XMLGW_MTF_160680/keystore.jks";
    private static String keyStorePassword = "123456";
    private static String keyStoreType = "jks";

    public static String post(String url, String json, String token) {
        String result = "";
        InputStream caInput = null;
        Response response = null;
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(AUTH_FILE_URL));
            Certificate ca = cf.generateCertificate(bufferedInputStream);
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(keyStore);


            InputStream keyin = new FileInputStream(new File(keyStorePath));
            KeyStore keystore = KeyStore.getInstance(keyStoreType);
            keystore.load(keyin, keyStorePassword.toCharArray());
            // KeyManagerFactory keyFactory = KeyManagerFactory.getInstance("SunX509", "SunJSSE");
            KeyManagerFactory keyFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyFactory.init(keystore, keyStorePassword.toCharArray());
            KeyManager[] keyManagers = keyFactory.getKeyManagers();

            SSLContext context = SSLContext.getInstance("TLS");
            // context.init(null, tmf.getTrustManagers(), null);
            context.init(keyManagers, null, null);

            // RequestBody body = RequestBody.create(JSON_TYPE, json);
            // Request request = new Request.Builder().url(url).post(body)
            //         .addHeader("clientId", "204")
            //         .addHeader("Client-Type", "android")
            //         .addHeader("Client-Version", "2.2.6")
            //         .addHeader("plain-text-transfer", "true")
            //         .addHeader("token", StringUtils.isBlank(token) ? "" : token).build();

            Request request = new Request.Builder().url(url).get()
                    // .addHeader("clientId", "204")
                    // .addHeader("Client-Type", "android")
                    // .addHeader("Client-Version", "2.2.6")
                    // .addHeader("plain-text-transfer", "true")
                    .build();

            OkHttpClient client = new OkHttpClient.Builder()
                    .sslSocketFactory(context.getSocketFactory())
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .hostnameVerifier(new TrustAnyHostnameVerifier())
                    .build();

            response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                result = response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }

            // caInput.close();
            response.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (caInput != null) {
                try {
                    caInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return result;
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    public static void main(String[] args) {
        String url = "https://mtf.services.mastercard.com/mtf/mdes/assets/1/0/asset/3789637f-32a1-4810-a138-4bf34501c509";
        String post = post(url, null, null);
        System.out.println(post);

    }
}