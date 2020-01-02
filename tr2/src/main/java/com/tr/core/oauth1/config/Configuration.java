package com.tr.core.oauth1.config;

import com.tr.core.oauth1.utils.AuthenticationUtils;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-18 10:17
 */
public final class Configuration {
    private final static boolean isEnable = true;
    public final static String consumerKey = "SYxB5pWNgbA7znnsjqrW3j3cPQKswcoSVxJcVhHJcaf24271!1b1ee9c2908d41979333f37d5370c9b30000000000000000";
    public static PrivateKey signingKey;
    private final static String signingKeyAlias = "keyalias";
    private final static String signingKeyPassword = "keystorepassword";
    private final static String signingKeyPkcs12FilePath = "E:/IdeaProjects/demo/tr2/src/test/resources/cert/token-reuqest-sandbox.p12";

    static {
        try {
            if (isEnable) {
                signingKey = AuthenticationUtils.loadSigningKey(signingKeyPkcs12FilePath, signingKeyAlias, signingKeyPassword);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        }
    }
}
