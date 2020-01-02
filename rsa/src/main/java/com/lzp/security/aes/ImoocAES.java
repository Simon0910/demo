package com.lzp.security.aes;

import com.lzp.mastercard.crypto.util.CryptUtil;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

public class ImoocAES {

    private static String src = "123";
    // private static String src = "{" +
    // 	"\"cardAccountData\" : {" +
    // 	"\"accountNumber\" : \"5123456789012345\"," +
    // 	"\"expiryMonth\" : \"12\" ," +
    // 	"\"expiryYear\" : \"18\"" +
    // 	"}," +
    // 	"\"accountHolderData\" : {" +
    // 	"\"accountHolderName\" : \"John Doe\"" +
    // 	"}," +
    // 	"\"source\" : \"ACCOUNT_ON_FILE\"" +
    // 	"}";

    //	private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String ALGORITHM = "AES/CBC/PKCS7Padding";

    public static void main(String[] args) throws NoSuchProviderException, NoSuchAlgorithmException, UnsupportedEncodingException, DecoderException {
        jdkAES();

    }

    public static void jdkAES() {

        try {
            // 通过BouncyCastle组件来让java里面支持PKCS7Padding填充
            Security.addProvider(new BouncyCastleProvider());

            //生成KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            //key转换
            Key key = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivParameterSpec = CryptUtil.generateIv();

            //加密
            Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
//			cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec("0".getBytes()));
            cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("jdk aes encrypt : " + Base64.encodeBase64String(result));

            //解密
//			cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec("0".getBytes()));
            cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
            result = cipher.doFinal(result);
            System.out.println("jdk aes desrypt : " + new String(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bcAES() {
        //TODO
    }

}
