//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.tr.core.oauth1.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

public final class AuthenticationUtils {
    private AuthenticationUtils() {
    }

    public static PrivateKey loadSigningKey(String pkcs12KeyFilePath, String signingKeyAlias, String signingKeyPassword) throws IOException, NoSuchProviderException, KeyStoreException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException {
        KeyStore pkcs12KeyStore = KeyStore.getInstance("PKCS12", "SunJSSE");
        pkcs12KeyStore.load(new FileInputStream(pkcs12KeyFilePath), signingKeyPassword.toCharArray());
        return (PrivateKey) pkcs12KeyStore.getKey(signingKeyAlias, signingKeyPassword.toCharArray());
    }

    public static PrivateKey loadSigningKey(InputStream pkcs12KeyFile, String signingKeyAlias, String signingKeyPassword) throws IOException, NoSuchProviderException, KeyStoreException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException {
        KeyStore pkcs12KeyStore = KeyStore.getInstance("PKCS12", "SunJSSE");
        pkcs12KeyStore.load(pkcs12KeyFile, signingKeyPassword.toCharArray());
        return (PrivateKey) pkcs12KeyStore.getKey(signingKeyAlias, signingKeyPassword.toCharArray());
    }
}
