package com.lzp.mastercard.crypto.aes;

import com.lzp.mastercard.crypto.util.CryptUtil;
import com.lzp.mastercard.crypto.util.DataEncoding;
import org.apache.commons.codec.DecoderException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;

/**
 * Created by daniel on 2019/7/24 11:08.
 */
public class AESCipher {

    private static final String CRYPTO_METHOD = "AES";
    private static final int CRYPTO_BITS = 256;
    // private static final String CYPHER = "AES/CBC/PKCS7Padding";
    private static final String CYPHER = "AES/CBC/PKCS5PADDING";
    private static final String CHARSET = "UTF-8";

    private static final String digestAlgorithm = "SHA-256";

    static {
        // 通过BouncyCastle组件来让java里面支持PKCS7Padding填充
        // Security.addProvider(new BouncyCastleProvider());
    }

    public static String generateKey() throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(CRYPTO_METHOD);
        keyGenerator.init(CRYPTO_BITS);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] keyBytes = secretKey.getEncoded();

        // 公钥指纹哈希 通过SHA-256对密钥进行摘要
        // MessageDigest messageDigest = MessageDigest.getInstance(digestAlgorithm, "SUN");
        // messageDigest.reset();
        // byte[] keyValue = messageDigest.digest(keyBytes);
        // keyValue = Arrays.copyOf(keyValue, 16);
        // String key = CryptUtil.byteArrayToString(keyValue, DataEncoding.HEX);

        String key = CryptUtil.byteArrayToString(keyBytes, DataEncoding.HEX);
        return key;
    }

    public static String generateIV() throws NoSuchProviderException, NoSuchAlgorithmException {
        SecureRandom randomSecureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");
        byte[] ivBytes = new byte[16];
        randomSecureRandom.nextBytes(ivBytes);
        String iv = CryptUtil.byteArrayToString(ivBytes, DataEncoding.HEX);
        return CryptUtil.byteArrayToString(ivBytes, DataEncoding.HEX);
    }

    public static Key getKey(String key) throws DecoderException {
        byte[] keyBytes = CryptUtil.stringToByteArray(key, DataEncoding.HEX);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, CRYPTO_METHOD);
        return secretKeySpec;
    }

    public static AlgorithmParameterSpec getIv(String iv) throws DecoderException {
        byte[] ivBytes = CryptUtil.stringToByteArray(iv, DataEncoding.HEX);
        return new IvParameterSpec(ivBytes);
    }

    public static String encrypt(String src, String key, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance(CYPHER);
        cipher.init(Cipher.ENCRYPT_MODE, getKey(key), getIv(iv));
        byte[] enc = cipher.doFinal(src.getBytes(CHARSET));
        return CryptUtil.byteArrayToString(enc, DataEncoding.HEX);
    }

    public static String decrypt(String enc, String key, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance(CYPHER);
        cipher.init(Cipher.DECRYPT_MODE, getKey(key), getIv(iv));
        byte[] encBytes = CryptUtil.stringToByteArray(enc, DataEncoding.HEX);
        byte[] src = cipher.doFinal(encBytes);
        return new String(src);
    }

    public static void main(String[] args) throws Exception {
        String key = generateKey();
        System.out.println("key=" + key);

        String iv = generateIV();
        System.out.println("iv=" + iv);

        String origin = "123";
        String encrypt = encrypt(origin, key, iv);
        System.out.println("encrypt=" + encrypt);

        String decrypt = decrypt(encrypt, key, iv);
        System.out.println("decrypt=" + decrypt);

    }

}
