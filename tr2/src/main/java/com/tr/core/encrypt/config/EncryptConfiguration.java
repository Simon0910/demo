// package com.jdd.thf.tr.channel.master.core.encrypt.config;
//
// import com.jdd.thf.tr.channel.master.core.encrypt.encryption.FieldLevelEncryptionConfig;
// import com.jdd.thf.tr.channel.master.core.encrypt.encryption.FieldLevelEncryptionConfigBuilder;
// import com.jdd.thf.tr.channel.master.core.encrypt.utils.EncryptionUtils;
// import org.springframework.beans.factory.annotation.Value;
//
// import javax.annotation.PostConstruct;
// import java.io.InputStream;
// import java.security.PrivateKey;
// import java.security.cert.Certificate;
//
// /**
//  * @author liuzhipeng
//  * @description
//  * @create 2019-10-18 10:17
//  */
// @org.springframework.context.annotation.Configuration
// public class EncryptConfiguration {
//     private static boolean isEnable = false;
//     private static String encryptionCertificateFilePath;
//     private static String decryptionKeyFilePath;
//     private static FieldLevelEncryptionConfig config;
//
//     private static String classpathEncryptFileName;
//     private static String classpathDecryptFileName;
//
//     @Value("${master.encrypt.enable:false}")
//     private void setIsEnable(boolean isEnable) {
//         EncryptConfiguration.isEnable = isEnable;
//     }
//
//     @Value("${master.encrypt.classpathEncryptFileName:encryption-key.crt}")
//     public void setClasspathEncryptFileName(String classpathEncryptFileName) {
//         EncryptConfiguration.classpathEncryptFileName = classpathEncryptFileName;
//     }
//
//     @Value("${master.encrypt.classpathDecryptFileName:decryption-key.key}")
//     public void setClasspathDecryptFileName(String classpathDecryptFileName) {
//         EncryptConfiguration.classpathDecryptFileName = classpathDecryptFileName;
//     }
//
//     @Value("${master.encrypt.eyncryptkey:yourKeyPath}")
//     private void setEncryptionCertificateFilePath(String encryptionCertificateFilePath) {
//         EncryptConfiguration.encryptionCertificateFilePath = encryptionCertificateFilePath;
//     }
//
//     @Value("${master.encrypt.decryptKey:yourKeyPath}")
//     private void setDecryptionKeyFilePath(String decryptionKeyFilePath) {
//         EncryptConfiguration.decryptionKeyFilePath = decryptionKeyFilePath;
//     }
//
//     @PostConstruct
//     private static void init() {
//         try {
//             if (isEnable) {
//                 InputStream inEncrypt = EncryptConfiguration.class.getClassLoader().getResourceAsStream(classpathEncryptFileName);
//                 Certificate encryptionCertificate = EncryptionUtils.loadEncryptionCertificate(inEncrypt);
//                 InputStream inDecrypt = EncryptConfiguration.class.getClassLoader().getResourceAsStream(classpathDecryptFileName);
//                 PrivateKey decryptionKey = EncryptionUtils.loadDecryptionKey(inDecrypt);
//
//                 // encryptionCertificate = EncryptionUtils.loadEncryptionCertificate(encryptionCertificateFilePath);
//                 // PrivateKey decryptionKey = EncryptionUtils.loadDecryptionKey(decryptionKeyFilePath);
//
//                 config = FieldLevelEncryptionConfigBuilder.aFieldLevelEncryptionConfig()
//                         .withEncryptionPath("$.fundingAccountInfo.encryptedPayload.encryptedData", "$.fundingAccountInfo.encryptedPayload")
//                         .withEncryptionPath("$.encryptedPayload.encryptedData", "$.encryptedPayload")
//                         .withDecryptionPath("$.tokenDetail", "$.tokenDetail.encryptedData")
//                         .withDecryptionPath("$.encryptedPayload", "$.encryptedPayload.encryptedData")
//                         .withEncryptionCertificate(encryptionCertificate)
//                         .withDecryptionKey(decryptionKey)
//                         .withOaepPaddingDigestAlgorithm("SHA-512")
//                         .withEncryptedValueFieldName("encryptedData")
//                         .withEncryptedKeyFieldName("encryptedKey")
//                         .withIvFieldName("iv")
//                         .withOaepPaddingDigestAlgorithmFieldName("oaepHashingAlgorithm")
//                         .withEncryptionCertificateFingerprintFieldName("publicKeyFingerprint")
//                         .withFieldValueEncoding(FieldLevelEncryptionConfig.FieldValueEncoding.HEX)
//                         .build();
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
//
//     public static FieldLevelEncryptionConfig getConfig() {
//         return config;
//     }
// }
