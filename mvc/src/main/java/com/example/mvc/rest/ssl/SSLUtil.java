package com.example.mvc.rest.ssl;

import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Collection;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-14 18:03
 */
public class SSLUtil {

    public static KeyStore getKeyStore(InputStream keyStore, String keyStoreType, String keyStorePassword) throws Exception {
        KeyStore keystore = null;
        Throwable t = null;
        try {
            keystore = KeyStore.getInstance(keyStoreType);
            keystore.load(keyStore, keyStorePassword.toCharArray());
            return keystore;
        } catch (Exception e) {
            t = e;
            throw e;
        } finally {
            if (keyStore != null) {
                try {
                    keyStore.close();
                } catch (IOException e) {
                    keystore = null;
                    if (t != null) {
                        t.addSuppressed(e);
                    }
                }
            }

        }
    }

    public static KeyStore getKeyStore(String keyStorePath, String keyStoreType, String keyStorePassword) throws Exception {
        File file = new File(keyStorePath);
        if (!file.exists() || !file.isFile()) {
            throw new RuntimeException(keyStorePath + "文件未找到");
        }
        InputStream keyin = new FileInputStream(file);
        return getKeyStore(keyin, keyStoreType, keyStorePassword);
    }


    public static KeyStore getCacertsKeyStore(String cacertsKeyStorePath, String keyStoreType) throws Exception {
        File file = new File(cacertsKeyStorePath);
        if (!file.exists()) {
            return null;
        }
        InputStream cacerts = new FileInputStream(file);
        try {
            KeyStore keystore = KeyStore.getInstance(keyStoreType);
            keystore.load(cacerts, (char[]) null);
            return keystore;
        } catch (Exception e) {
            throw e;
        } finally {
            if (cacerts != null) {
                cacerts.close();
            }
        }
    }

    public static KeyManager[] getKeyManagers(KeyStore keyStore, String keyStorePass) throws Exception {
        KeyManagerFactory keyFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyFactory.init(keyStore, keyStorePass.toCharArray());
        KeyManager[] keyManagers = keyFactory.getKeyManagers();
        return keyManagers;
    }

    public static TrustManager[] getTrustManagers(KeyStore trustKeyStore) throws Exception {
        TrustManagerFactory trustFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustFactory.init(trustKeyStore);
        return trustFactory.getTrustManagers();
    }


    public static TrustManager[] getTrustManagers(InputStream sslCaCert) throws Exception {
        // Any password will work.
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        Collection<? extends Certificate> certificates = certificateFactory.generateCertificates(sslCaCert);
        if (certificates.isEmpty()) {
            throw new IllegalArgumentException("expected non-empty set of trusted certificates");
        }
        KeyStore caKeyStore = getKeyStore((InputStream) null, null, "password");
        int index = 0;
        for (Certificate certificate : certificates) {
            String certificateAlias = "ca" + Integer.toString(index++);
            caKeyStore.setCertificateEntry(certificateAlias, certificate);
        }
        TrustManagerFactory trustManagerFactory =
                TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(caKeyStore);
        return trustManagerFactory.getTrustManagers();
    }

    public static SSLContext getSSLContext() {
        InputStream keyStoreInputStream = SSLUtil.class.getClassLoader()
                .getResourceAsStream("env/dev/jd-mtf-mastercard-keystore.jks");
        KeyStore keyStore = null;
        try {
            keyStore = getKeyStore(keyStoreInputStream, "jks", "123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // KeyManager[] keyManagers = null;
        // try {
        //     keyManagers = getKeyManagers(keyStore, "123456");
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        // SSLContext sslContext = SSLContext.getInstance("TLS");
        // // sslContext.init(new KeyManager[]{}, new TrustManager[]{}, new SecureRandom());
        // sslContext.init(keyManagers, null, new SecureRandom());

        try {
            // Trust own CA and all self-signed certs
            SSLContext sslContext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, "123456".toCharArray())
                    .build();

            return sslContext;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
