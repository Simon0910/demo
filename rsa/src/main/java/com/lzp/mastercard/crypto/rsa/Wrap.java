package com.lzp.mastercard.crypto.rsa;

import javax.crypto.*;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.MGF1ParameterSpec;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-09-02 15:34
 */
public class Wrap {
    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";

    private static final String asymmetricCipher = "RSA/ECB/OAEPWithSHA-512AndMGF1Padding";



    public static void main(String[] args) throws Exception {
        test02();
    }

    private static void test02() throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException {
        //生成KEY
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] keyBytes = secretKey.getEncoded();
        //key转换
        Key key = new SecretKeySpec(keyBytes, "AES");

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = kpg.generateKeyPair();
        PrivateKey aPrivate = keyPair.getPrivate();
        PublicKey aPublic = keyPair.getPublic();

        //wrap
        Cipher cipher = Cipher.getInstance(asymmetricCipher, "SunJCE");
        if (asymmetricCipher.contains("OAEPWith")) {
            cipher.init(3, aPublic, getOAEPParameterSpec(asymmetricCipher, null));
        } else {
            cipher.init(Cipher.WRAP_MODE, aPublic);
        }
        byte[] wraped = cipher.wrap(key);
        System.out.println();

        //unwrap
        if (asymmetricCipher.contains("OAEPWith")) {
            cipher.init(Cipher.UNWRAP_MODE, aPrivate, getOAEPParameterSpec(asymmetricCipher, null));
        } else {
            cipher.init(Cipher.UNWRAP_MODE, aPrivate);
        }
        Key key2 = cipher.unwrap(wraped, "AES", 3);
        byte[] keyBytes2 = key2.getEncoded();
        System.out.println("");
    }

    private static OAEPParameterSpec getOAEPParameterSpec(String algorithm, String oaepHashingAlgorithm) {
        if (algorithm.contains("OAEPWith")) {
            int startDigest = algorithm.indexOf("OAEPWith") + 8;
            int endDigest = algorithm.indexOf("And");
            int startPadding = endDigest + 3;
            int endPadding = algorithm.indexOf("Padding");
            String digest = algorithm.substring(startDigest, endDigest);
            String padding = algorithm.substring(startPadding, endPadding);
            return oaepHashingAlgorithm != null ? new OAEPParameterSpec(digest, padding, new MGF1ParameterSpec(oaepHashingAlgorithm), PSource.PSpecified.DEFAULT) : new OAEPParameterSpec(digest, padding, new MGF1ParameterSpec(digest), PSource.PSpecified.DEFAULT);
        } else {
            return null;
        }
    }


    private static void test01() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException {
        //生成KEY
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] keyBytes = secretKey.getEncoded();
        //key转换
        Key key = new SecretKeySpec(keyBytes, "AES");

        //wrap
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.WRAP_MODE, key);
        byte[] wrap = cipher.wrap(key);
        System.out.println();

        //unwrap
        cipher.init(Cipher.UNWRAP_MODE, key);
        Key aes = cipher.unwrap(wrap, "AES", 3);
        byte[] encoded1 = aes.getEncoded();
        System.out.println("");
    }

}
