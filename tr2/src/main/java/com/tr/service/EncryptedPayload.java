package com.tr.service;

import lombok.Data;

import java.io.Serializable;

/**
 * A.15 230
 * Created by daniel on 2019/7/22 18:12.
 */
@Data
public class EncryptedPayload implements Serializable {
    private static final long serialVersionUID = 6340817995609879514L;
    /**
     * The fingerprint of the public key used to encrypt the ephemeral AES key.
     */
    private String publicKeyFingerprint;
    /**
     * One-time use AES key encrypted by the Mastercard public key (as identified by 'publicKeyFingerprint') using the OAEP or RSA Encryption Standard PKCS #1 v1.5 (depending on the value of 'oaepHashingAlgorithm'.
     * Requirement is for a 128-bit key (with 256-bit key supported as an option).
     */
    private String encryptedKey;

    /**
     * Hashing algorithm used with the OAEP scheme.
     * If omitted, then the RSA Encryption Standard PKCS #1 v1.5 will be used.
     * Must be one of:
     * SHA256/SHA512
     * refer to com.jdd.thf.tr.constant.master.OaepHashingAlgorithmEnum
     */
    private String oaepHashingAlgorithm;
    /**
     * The initialization vector used when encrypting data using the one-time use AES key. Must be exactly 16 bytes (32 character hex string) to match the block size.
     * If not present, an IV of zero is assumed
     */
    private String iv = "0";
    /**
     * Contains an encrypted json object. Encrypted by the ephemeral AES key using CBC mode
     * (IV as provided in 'iv', or zero if none provided) and PKCS#7 padding.
     * The JSON object being encrypted will be defined in the context of the API call.
     */
    private String encryptedData;


}
