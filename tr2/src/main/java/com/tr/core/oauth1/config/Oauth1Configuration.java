// package com.jdd.thf.tr.channel.master.core.oauth1.config;
//
// import com.jdd.thf.tr.channel.master.core.oauth1.utils.AuthenticationUtils;
// import org.springframework.beans.factory.annotation.Value;
//
// import javax.annotation.PostConstruct;
// import java.io.IOException;
// import java.io.InputStream;
// import java.security.*;
// import java.security.cert.CertificateException;
//
// /**
//  * @author liuzhipeng
//  * @description
//  * @create 2019-10-18 10:17
//  */
// @org.springframework.context.annotation.Configuration
// public class Oauth1Configuration {
//     private static boolean isEnable = false;
//     private static String consumerKey;
//     private static String signingKeyAlias;
//     private static String signingKeyPassword;
//     private static String signingKeyPkcs12FilePath;
//     private static PrivateKey signingKey;
//
//     private static String signingKeyPkcs12FileClasspathName;
//
//     @Value("${master.oauth1.pkcs12.classpathFileName:sign.p12}")
//     public void setSigningKeyPkcs12FileClasspathName(String signingKeyPkcs12FileClasspathName) {
//         Oauth1Configuration.signingKeyPkcs12FileClasspathName = signingKeyPkcs12FileClasspathName;
//     }
//
//     @Value("${master.oauth1.enable:false}")
//     private void setIsEnable(boolean isEnable) {
//         Oauth1Configuration.isEnable = isEnable;
//     }
//
//     @Value("${master.oauth1.consumerKey:yourConsumerKey}")
//     private void setConsumerKey(String consumerKey) {
//         Oauth1Configuration.consumerKey = consumerKey;
//     }
//
//     @Value("${master.oauth1.pkcs12.alias:keyAlias}")
//     private void setSigningKeyAlias(String signingKeyAlias) {
//         Oauth1Configuration.signingKeyAlias = signingKeyAlias;
//     }
//
//     @Value("${master.oauth1.pkcs12.pass:keyPassword}")
//     private void setSigningKeyPassword(String signingKeyPassword) {
//         Oauth1Configuration.signingKeyPassword = signingKeyPassword;
//     }
//
//     @Value("${master.oauth1.pkcs12.path:yourKeyPath}")
//     private void setSigningKeyPkcs12FilePath(String signingKeyPkcs12FilePath) {
//         Oauth1Configuration.signingKeyPkcs12FilePath = signingKeyPkcs12FilePath;
//     }
//
//     @PostConstruct
//     private static void init() {
//         try {
//             if (isEnable) {
//                 InputStream in = Oauth1Configuration.class.getClassLoader().getResourceAsStream(signingKeyPkcs12FileClasspathName);
//                 signingKey = AuthenticationUtils.loadSigningKey(in, signingKeyAlias, signingKeyPassword);
//                 // signingKey = AuthenticationUtils.loadSigningKey(signingKeyPkcs12FilePath, signingKeyAlias,
//                 // signingKeyPassword);
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         } catch (NoSuchProviderException e) {
//             e.printStackTrace();
//         } catch (KeyStoreException e) {
//             e.printStackTrace();
//         } catch (CertificateException e) {
//             e.printStackTrace();
//         } catch (NoSuchAlgorithmException e) {
//             e.printStackTrace();
//         } catch (UnrecoverableKeyException e) {
//             e.printStackTrace();
//         }
//     }
//
//
//     public static String getConsumerKey() {
//         return consumerKey;
//     }
//
//     public static PrivateKey getSigningKey() {
//         return signingKey;
//     }
// }
