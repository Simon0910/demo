package com.tr.service;

import com.tr.core.encrypt.encryption.EncryptionException;
import com.tr.core.encrypt.utils.EncryptionUtils;
import com.tr.utils.CryptoUtil;
import com.tr.utils.EncodingUtils;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.PrivateKey;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-11-08 09:51
 */
// @Component
@Slf4j
public class DecryptService {
    private static String classpathDecryptFileName = "jd-mtf-mastercard-decrypt.key";
    private static PrivateKey clientPrivateKey;

    // @Value("${master.encrypt.classpathDecryptFileName:}")
    public void setClasspathDecryptFileName(String classpathDecryptFileName) {
        DecryptService.classpathDecryptFileName = classpathDecryptFileName;
    }

    @PostConstruct
    private void init() throws GeneralSecurityException, IOException {
        InputStream in = DecryptService.class.getClassLoader().getResourceAsStream(classpathDecryptFileName);
        clientPrivateKey = EncryptionUtils.loadDecryptionKey(in);
    }

    public String decrytEncryptedPayload(EncryptedPayload encryptedPayload) throws GeneralSecurityException,
            IOException {
        if (encryptedPayload == null) {
            return null;
        }
        if (clientPrivateKey == null) {
            init();
        }

        String encryptedKey = encryptedPayload.getEncryptedKey();
        String iv = encryptedPayload.getIv();
        String oaepHashingAlgorithm = encryptedPayload.getOaepHashingAlgorithm();
        String publicKeyFingerprint = encryptedPayload.getPublicKeyFingerprint();
        String encryptedData = encryptedPayload.getEncryptedData();

        if (encryptedKey == null || iv == null || oaepHashingAlgorithm == null || encryptedData == null) {
            return null;
        }

        try {
            Key secretKey = CryptoUtil.getSecretKey(clientPrivateKey, oaepHashingAlgorithm, EncodingUtils.hexDecode(encryptedKey));
            IvParameterSpec ivSpec = CryptoUtil.getIvSpec(EncodingUtils.hexDecode(iv));
            byte[] decrypt = CryptoUtil.decrypt(secretKey, ivSpec, EncodingUtils.hexDecode(encryptedData));
            return new String(decrypt, "UTF-8");
        } catch (EncryptionException e) {
            log.error("get secretKey or ivSpec error : {}", e.getMessage(), e);
            // throw new BizException(TRErrorReasonCodesEnum.SECRET_KEY_ERROR);
        } catch (GeneralSecurityException e) {
            log.error("decrypt encryptedPayload error : {}", e.getMessage(), e);
            // throw new BizException(TRErrorReasonCodesEnum.CRYPTOGRAPHY_ERROR);
        } catch (UnsupportedEncodingException e) {
            log.error("unsupported encoding for encryptedData : {}", e.getMessage(), e);
            // throw new BizException(TRErrorReasonCodesEnum.UNSUPPORTED_ENCODING);
        }

        throw new RuntimeException();
    }

}
