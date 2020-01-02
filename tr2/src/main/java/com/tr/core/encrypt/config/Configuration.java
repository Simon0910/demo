package com.tr.core.encrypt.config;

import com.tr.core.encrypt.encryption.FieldLevelEncryptionConfig;
import com.tr.core.encrypt.encryption.FieldLevelEncryptionConfigBuilder;
import com.tr.core.encrypt.utils.EncryptionUtils;

import java.security.PrivateKey;
import java.security.cert.Certificate;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-18 10:17
 */
public final class Configuration {
    private final static boolean isEnable = true;
    // Encryption keys from https://developer.mastercard.com/page/digital-enablement-api-sandbox-configuration
    // private final static String encryptionCertificateFilePath = "E:/IdeaProjects/demo/tr2/src/test/resources/cert/digital-enablement-sandbox-encryption-key.crt";
    private final static String encryptionCertificateFilePath = "E:/JD_RES/encrypt/20191003012215-caas-centraljd-mtf-mastercard-com.crt";
    // private final static String decryptionKeyFilePath = "E:/IdeaProjects/demo/tr2/src/test/resources/cert/digital-enablement-sandbox-decryption-key.key";
    private final static String decryptionKeyFilePath = "E:\\IdeaProjects\\demo\\tr2\\src\\main\\resources\\jd-mtf-mastercard-decrypt.key";

    public static FieldLevelEncryptionConfig config;

    static {
        try {
            if (isEnable) {
                Certificate encryptionCertificate = EncryptionUtils.loadEncryptionCertificate(encryptionCertificateFilePath);
                PrivateKey decryptionKey = EncryptionUtils.loadDecryptionKey(decryptionKeyFilePath);
                config = FieldLevelEncryptionConfigBuilder.aFieldLevelEncryptionConfig()
                        .withEncryptionPath("$.fundingAccountInfo.encryptedPayload.encryptedData", "$.fundingAccountInfo.encryptedPayload")
                        .withEncryptionPath("$.encryptedPayload.encryptedData", "$.encryptedPayload")
                        .withDecryptionPath("$.tokenDetail", "$.tokenDetail.encryptedData")
                        .withDecryptionPath("$.encryptedPayload", "$.encryptedPayload.encryptedData")
                        .withEncryptionCertificate(encryptionCertificate)
                        .withDecryptionKey(decryptionKey)
                        .withOaepPaddingDigestAlgorithm("SHA-512")
                        .withEncryptedValueFieldName("encryptedData")
                        .withEncryptedKeyFieldName("encryptedKey")
                        .withIvFieldName("iv")
                        .withOaepPaddingDigestAlgorithmFieldName("oaepHashingAlgorithm")
                        .withEncryptionCertificateFingerprintFieldName("publicKeyFingerprint")
                        .withEncryptionCertificateFingerprint("d8c58f5ea2b5885513a70f40108dba9a89d7b18b")
                        .withFieldValueEncoding(FieldLevelEncryptionConfig.FieldValueEncoding.HEX)
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
