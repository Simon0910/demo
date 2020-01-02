package com.lzp.mastercard.crypto.rsa;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSACipher {
    private final static String CRYPTO_METHOD = "RSA";
    private final static int CRYPTO_BITS = 2048;
    private static String PUBLIC_KEY;
    private static String PRIVATE_KEY;
    private final static String CYPHER = "RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING";
    // private final static String CYPHER = "RSA";
    private static String CHARSET = "UTF-8";
    /*private final static int CRYPTO_BITS = 4096; This will encrypt in 4093bits, note however that is slower.*/

    static {
        KeyPair kp = getKeyPair();
        PublicKey publicKey = kp.getPublic();
        byte[] publicKeyBytes = publicKey.getEncoded();
        PUBLIC_KEY = Base64.encodeBase64String(publicKeyBytes);
        //Save the public key so it is not generated each and every time
        PrivateKey privateKey = kp.getPrivate();
        byte[] privateKeyBytes = privateKey.getEncoded();
        PRIVATE_KEY = Base64.encodeBase64String(privateKeyBytes);
        //Also Save the private key so it is not generated each and every time
    }

    public static KeyPair getKeyPair() {
        KeyPair kp = null;
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(CRYPTO_METHOD);
            kpg.initialize(CRYPTO_BITS);
            kp = kpg.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kp;
    }

    public static Key generatePublic() {
        Key key = null;
        try {
            KeyFactory keyFac = KeyFactory.getInstance(CRYPTO_METHOD);
            KeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(PUBLIC_KEY));
            key = keyFac.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    public static Key generatePrivate() {
        Key key = null;
        try {
            KeyFactory keyFac = KeyFactory.getInstance(CRYPTO_METHOD);
            KeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(PRIVATE_KEY));
            key = keyFac.generatePrivate(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    public static String encrypt(String clearText) {
        String encryptedBase64 = "";
        try {
            final Cipher cipher = Cipher.getInstance(CYPHER);
            cipher.init(Cipher.ENCRYPT_MODE, generatePublic());
            byte[] encryptedBytes = cipher.doFinal(clearText.getBytes(CHARSET));
            encryptedBase64 = new String(Base64.encodeBase64String(encryptedBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedBase64.replaceAll("(\\r\\n|)", "");
    }

    public static String decrypt(String encryptedBase64) {
        String decryptedString = "";
        try {
            final Cipher cipher = Cipher.getInstance(CYPHER);
            cipher.init(Cipher.DECRYPT_MODE, generatePrivate());
            byte[] encryptedBytes = Base64.decodeBase64(encryptedBase64);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            decryptedString = new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedString;
    }

    public static void main(String[] args) {

    }
}