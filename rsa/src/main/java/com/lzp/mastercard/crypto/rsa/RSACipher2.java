package com.lzp.mastercard.crypto.rsa;


import com.lzp.mastercard.crypto.util.CryptUtil;
import com.lzp.mastercard.crypto.util.DataEncoding;

import javax.crypto.Cipher;
import javax.crypto.ExemptionMechanism;
import java.security.*;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by daniel on 2019/7/24 14:58.
 */
public class RSACipher2 {

    private final static String CRYPTO_METHOD = "RSA";
    private final static int CRYPTO_BITS = 2048;
    private static String PUBLIC_KEY;
    private static String PRIVATE_KEY;
    // private final static String CYPHER = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";
    private final static String CYPHER = "RSA/ECB/OAEPWithSHA-512AndMGF1Padding";
    private static String CHARSET = "UTF-8";

    static {
        KeyPair kp = getKeyPair();
        PublicKey publicKey = kp.getPublic();
        byte[] publicKeyBytes = publicKey.getEncoded();
        PUBLIC_KEY = CryptUtil.byteArrayToString(publicKeyBytes, DataEncoding.HEX);
        //Save the public key so it is not generated each and every time
        PrivateKey privateKey = kp.getPrivate();
        byte[] privateKeyBytes = privateKey.getEncoded();
        PRIVATE_KEY = CryptUtil.byteArrayToString(privateKeyBytes, DataEncoding.HEX);
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

    public static Key generatePublic(String publicKey) {
        Key key = null;
        try {
            KeyFactory keyFac = KeyFactory.getInstance(CRYPTO_METHOD);
            KeySpec keySpec = new X509EncodedKeySpec(CryptUtil.stringToByteArray(publicKey, DataEncoding.HEX));
            key = keyFac.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    public static Key generatePrivate(String privateKey) {
        Key key = null;
        try {
            KeyFactory keyFac = KeyFactory.getInstance(CRYPTO_METHOD);
            KeySpec keySpec = new PKCS8EncodedKeySpec(CryptUtil.stringToByteArray(privateKey, DataEncoding.HEX));
            key = keyFac.generatePrivate(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    /**
     * RSA公钥加密
     *
     * @param originStr
     * @param publicKey
     * @return
     */
    public static String encrypt(String originStr, String publicKey) {
         return encrypt(originStr, generatePublic(publicKey));
    }

    public static String encrypt(String originStr, Key publicKey) {
        String encryptedBase64 = "";
        try {
            final Cipher cipher = Cipher.getInstance(CYPHER);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedBytes = cipher.doFinal(originStr.getBytes(CHARSET));

            String algorithm = cipher.getAlgorithm();
            byte[] iv = cipher.getIV();
            Provider provider = cipher.getProvider();
            int blockSize = cipher.getBlockSize();
            ExemptionMechanism exemptionMechanism = cipher.getExemptionMechanism();
            AlgorithmParameters parameters = cipher.getParameters();

            encryptedBase64 = CryptUtil.byteArrayToString(encryptedBytes, DataEncoding.HEX);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedBase64;
    }

    /**
     * RSA私钥解密
     *
     * @param encrypt    加密字符串
     * @param privateKey 私钥
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String encrypt, String privateKey) {
        return decrypt(encrypt, generatePrivate(privateKey));
    }

    public static String decrypt(String encrypt, Key privateKey) {
        String decryptedString = "";
        try {
            final Cipher cipher = Cipher.getInstance(CYPHER);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] encryptedBytes = CryptUtil.stringToByteArray(encrypt, DataEncoding.HEX);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            String algorithm = cipher.getAlgorithm();
            byte[] iv = cipher.getIV();
            Provider provider = cipher.getProvider();
            int blockSize = cipher.getBlockSize();
            ExemptionMechanism exemptionMechanism = cipher.getExemptionMechanism();
            AlgorithmParameters parameters = cipher.getParameters();

            decryptedString = new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedString;
    }

    public static void main(String[] args) throws Exception {
        // PUBLIC_KEY = Cerdemo.getPublicKey();
        // PRIVATE_KEY = Cerdemo.getPrivateKey();
        System.out.println("publicKey=" + PUBLIC_KEY);
        System.out.println("privateKey=" + PRIVATE_KEY);

        String origin = "hehe";
        String encrypt = encrypt(origin, PUBLIC_KEY);
        System.out.println("encrypt=" + encrypt);

        String decrypt = decrypt(encrypt, PRIVATE_KEY);
        System.out.println("decrypt=" + decrypt);
    }

}
