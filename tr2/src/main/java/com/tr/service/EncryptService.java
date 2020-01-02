package com.tr.service;

import com.tr.core.encrypt.encryption.EncryptionException;
import com.tr.core.encrypt.utils.EncryptionUtils;
import com.tr.utils.CryptoUtil;
import com.tr.utils.EncodingUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-08-23 15:39
 */
// @Component
public class EncryptService {
    // private static String publicKeyFilename = "encrypt-key-api.crt";
    private static String publicKeyFilename = "encrypt-key-old.crt";
    private static String publicKeyFingerprint = "d2d9fa7869cef60eb09fa13922f469fe455f431b";
    // private static String publicKeyFingerprint = "1234567";

    private static String oaepPaddingDigestAlgorithm = "SHA-512";
    private static PublicKey mastercardPublicKey;
    private static SecretKey secretKey;
    private static String rgk;

    // @Value("${master.encrypt.classpathEncryptFileName:}")
    public void setPublicKeyFilename(String publicKeyFilename) {
        EncryptService.publicKeyFilename = publicKeyFilename;
    }

    // @Value("${master.encrypt.publicKeyFingerprint:}")
    public void setPublicKeyFingerprint(String publicKeyFingerprint) {
        EncryptService.publicKeyFingerprint = publicKeyFingerprint;
    }


    @PostConstruct
    private void init() throws CertificateException, FileNotFoundException, NoSuchProviderException,
            NoSuchAlgorithmException, EncryptionException {
        InputStream in = EncryptService.class.getClassLoader().getResourceAsStream(publicKeyFilename);
        Certificate encryptionCertificate = EncryptionUtils.loadEncryptionCertificate(in);
        mastercardPublicKey = encryptionCertificate.getPublicKey();
        // provide or calculate
        if (StringUtils.isBlank(publicKeyFingerprint)) {
            byte[] bytes = CryptoUtil.generateFingerprint("SHA-256", encryptionCertificate);
            publicKeyFingerprint = EncodingUtils.hexEncode(bytes);
        }

        // use unite aes key
        secretKey = CryptoUtil.generateSecretKey(null);
        byte[] secretKeyBytes = CryptoUtil.wrapSecretKey(mastercardPublicKey, oaepPaddingDigestAlgorithm, secretKey);
        rgk = EncodingUtils.hexEncode(secretKeyBytes);
    }

    // public void encryptPIN(RegisterRequestSchema requestSchema) throws EncryptionException,
    //         UnsupportedEncodingException, GeneralSecurityException, FileNotFoundException {
    //     if (StringUtils.isNotBlank(requestSchema.getNewMobilePin())) {
    //         if (mastercardPublicKey == null || secretKey == null) { init(); }
    //         byte[] encryptData = CryptoUtil.encrypt(secretKey, null, requestSchema.getNewMobilePin().getBytes("UTF-8"));
    //         requestSchema.setPublicKeyFingerprint(publicKeyFingerprint);
    //         requestSchema.setRgk(rgk);
    //         // requestSchema.setOaepHashingAlgorithm(oaepPaddingDigestAlgorithm);
    //         // requestSchema.setIv("0");
    //         requestSchema.setNewMobilePin(EncodingUtils.hexEncode(encryptData));
    //     }
    // }
}
