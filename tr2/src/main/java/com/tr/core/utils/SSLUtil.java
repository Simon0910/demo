package com.tr.core.utils;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;

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
                    if (t != null) {
                        t.addSuppressed(e);
                    }
                }
            } else {
                keyStore = null;
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

}
