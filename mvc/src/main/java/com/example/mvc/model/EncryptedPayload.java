package com.example.mvc.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class EncryptedPayload implements Serializable {
    private static final long serialVersionUID = -5792733685490511090L;

    private String publicKeyFingerprint;

    private String encryptedKey;

    private String oaepHashingAlgorithm;

    private String iv = "0";

    private String encryptedData;
}
