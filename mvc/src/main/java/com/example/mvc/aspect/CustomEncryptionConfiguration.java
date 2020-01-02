package com.example.mvc.aspect;

import com.example.mvc.config.MvcFieldLevelEncryptionConfig;
import com.example.mvc.config.SecretConfiguration;
import com.example.mvc.utils.EncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-25 16:55
 */
@Configuration
public class CustomEncryptionConfiguration {

    @Autowired
    private SecretConfiguration secretConfiguration;

    @Bean
    public MvcFieldLevelEncryptionConfig mvcFieldLevelEncryptionConfig() {
        if (!secretConfiguration.isEnabled()) { return null; }
        PrivateKey decryptionKey = null;
        try {
            decryptionKey = EncryptionUtils.loadDecryptionKey(secretConfiguration.getDecryptionKeyFilePath());
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MvcFieldLevelEncryptionConfig config = new MvcFieldLevelEncryptionConfig();
        config.fieldValueEncoding = MvcFieldLevelEncryptionConfig.FieldValueEncoding.HEX;
        Map<String, String> decryptionPaths = new HashMap<>();
        decryptionPaths.put("$.encryptedPayload", "$.encryptedPayload.encryptedData");
        config.decryptionPaths = decryptionPaths;
        config.decryptionKey = decryptionKey;
        config.oaepPaddingDigestAlgorithmFieldName = "oaepHashingAlgorithm";
        config.oaepPaddingDigestAlgorithm = "SHA-512";
        config.encryptedKeyFieldName = "encryptedKey";
        config.ivFieldName = "iv";
        config.encryptedValueFieldName = "encryptedData";
        config.encryptionCertificateFingerprintFieldName = "publicKeyFingerprint";
        return config;
    }
}
