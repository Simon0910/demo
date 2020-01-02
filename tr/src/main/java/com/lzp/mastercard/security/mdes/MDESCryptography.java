//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lzp.mastercard.security.mdes;


import com.lzp.mastercard.exception.SdkException;
import com.lzp.mastercard.security.fle.Config;
import com.lzp.mastercard.security.fle.FieldLevelEncryption;
import com.lzp.mastercard.security.util.DataEncoding;

import java.io.InputStream;
import java.util.Arrays;

public class MDESCryptography extends FieldLevelEncryption {
    public MDESCryptography(InputStream publicCertificate, InputStream keystore, String privateKeyAlias, String privateKeyPassword, String publicKeyFingerprint) throws SdkException {
        super(publicCertificate, keystore, privateKeyAlias, privateKeyPassword, config(), publicKeyFingerprint);
    }

    public MDESCryptography(InputStream publicCertificate, InputStream masterCardPrivateKey, String publicKeyFingerprint) throws SdkException {
        super(publicCertificate, masterCardPrivateKey, config(), publicKeyFingerprint);
    }

    public MDESCryptography(InputStream publicCertificate, InputStream keystore, String privateKeyAlias, String privateKeyPassword) throws SdkException {
        super(publicCertificate, keystore, privateKeyAlias, privateKeyPassword, config(), (String) null);
    }

    public MDESCryptography(InputStream publicCertificate, InputStream masterCardPrivateKey) throws SdkException {
        super(publicCertificate, masterCardPrivateKey, config(), (String) null);
    }

    private static final Config config() {
        Config tmpConfig = new Config();
        tmpConfig.triggeringEndPath = Arrays.asList("/tokenize", "/searchTokens", "/getToken", "/transact", "/notifyTokenUpdated", "/pushAccount");
        tmpConfig.fieldsToEncrypt = Arrays.asList("cardInfo.encryptedData", "encryptedPayload.encryptedData", "pushFundingAccount.encryptedPayload.encryptedData");
        tmpConfig.fieldsToDecrypt = Arrays.asList("encryptedPayload.encryptedData", "tokenDetail.encryptedData");
        tmpConfig.symmetricAlgorithm = "AES/CBC/PKCS5Padding";
        tmpConfig.symmetricCipher = "AES";
        tmpConfig.symmetricKeysize = 128;
        tmpConfig.asymmetricCipher = "RSA/ECB/OAEPWithSHA-512AndMGF1Padding";
        tmpConfig.oaepHashingAlgorithm = "SHA512";
        tmpConfig.publicKeyFingerprintHashing = "SHA-256";
        tmpConfig.ivFieldName = "iv";
        tmpConfig.oaepHashingAlgorithmFieldName = "oaepHashingAlgorithm";
        tmpConfig.encryptedKeyFiledName = "encryptedKey";
        tmpConfig.encryptedDataFieldName = "encryptedData";
        tmpConfig.publicKeyFingerprintFiledName = "publicKeyFingerprint";
        tmpConfig.dataEncoding = DataEncoding.HEX;
        return tmpConfig;
    }
}
