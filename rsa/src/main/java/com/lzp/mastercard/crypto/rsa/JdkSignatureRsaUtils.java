package com.lzp.mastercard.crypto.rsa;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author java小工匠
 * <p>
 * 1、RSA数字签名概述
 * 使用RSA非对称加密技术实现的数字签名。
 * <p>
 * 2、RSA数字签名算法分类
 * （1）MD (MD2withRSA、MD5withRSA)
 * （2）SHA (SHA1withRSA、SHA256withRSA、SHA384withRSA、SHA512withRSA)
 * <p>
 * 3、RSA数字签名实现
 * 3.1 JDK实现
 */
public class JdkSignatureRsaUtils {
    private final static String CRYPTO_METHOD = "RSA";
    private final static int CRYPTO_BITS = 2048;

    public static final String MD5withRSA = "MD5withRSA";
    public static final String SHA256withRSA = "SHA256withRSA";


    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};


    // 初始化密钥对
    public static KeyPair initKey() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance(CRYPTO_METHOD);
            // 512 -65536 && 64 的倍数
            generator.initialize(CRYPTO_BITS);
            return generator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // 获取公钥
    public static byte[] getPublicKey(KeyPair keyPair) {
        byte[] bytes = keyPair.getPublic().getEncoded();
        return bytes;
    }

    // 获取公钥
    public static String getPublicKeyStr(KeyPair keyPair) {
        byte[] bytes = keyPair.getPublic().getEncoded();
        return encodeHex(bytes);
    }

    // 获取私钥
    public static byte[] getPrivateKey(KeyPair keyPair) {
        byte[] bytes = keyPair.getPrivate().getEncoded();
        return bytes;
    }

    // 获取私钥
    public static String getPrivateKeyStr(KeyPair keyPair) {
        byte[] bytes = keyPair.getPrivate().getEncoded();
        return encodeHex(bytes);
    }

    // 签名
    public static byte[] sign(byte[] data, byte[] privateKey, String type) {
        try {
            // 还原使用
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey);
            KeyFactory keyFactory = KeyFactory.getInstance(CRYPTO_METHOD);
            PrivateKey priKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            // 1、实例化Signature
            Signature signature = Signature.getInstance(type);
            // 2、初始化Signature
            signature.initSign(priKey);
            // 3、更新数据
            signature.update(data);
            // 4、签名
            return signature.sign();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 验证
    public static boolean verify(byte[] data, byte[] publicKey, byte[] sign, String type) {
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey);
            KeyFactory keyFactory = KeyFactory.getInstance(CRYPTO_METHOD);
            PublicKey pubKey = keyFactory.generatePublic(x509EncodedKeySpec);
            // 1、实例化Signature
            Signature signature = Signature.getInstance(type);
            // 2、初始化Signature
            signature.initVerify(pubKey);
            // 3、更新数据
            signature.update(data);
            // 4、签名
            return signature.verify(sign);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    // 数据准16进制编码
    public static String encodeHex(final byte[] data) {
        return encodeHex(data, true);
    }

    // 数据转16进制编码
    public static String encodeHex(final byte[] data, final boolean toLowerCase) {
        final char[] toDigits = toLowerCase ? DIGITS_LOWER : DIGITS_UPPER;
        final int l = data.length;
        final char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return new String(out);
    }

    public static void main(String[] args) {
        String str = "java小工匠";
        byte[] data = str.getBytes();
        // 初始化密钥度
        KeyPair keyPair = initKey();
        byte[] publicKey = getPublicKey(keyPair);
        byte[] privateKey = getPrivateKey(keyPair);
        // 签名
        String type = SHA256withRSA;
        byte[] sign = sign(data, privateKey, type);
        // 验证
        boolean b = verify(data, publicKey, sign, type);
        System.out.println("验证:" + b);

    }
}