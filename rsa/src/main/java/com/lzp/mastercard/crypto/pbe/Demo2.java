package com.lzp.mastercard.crypto.pbe;

import com.lzp.mastercard.crypto.util.CryptUtil;
import com.lzp.mastercard.crypto.util.DataEncoding;
import org.apache.commons.codec.DecoderException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;

/**
 * 算法名称	    密钥长	        块长	    速度	    说明
 * DES	        56	            64	    慢	    不安全, 不要使用
 * 3DES	        112/168	        64	    很慢	    中等安全, 适合加密较小的数据
 * AES	        128/192/256	    128	    快	    安全
 * Blowfish	    （4至56）*8	    64	    快	    应该安全, 在安全界尚未被充分分析、论证
 * RC4	        40-1024	        64	    很快	    安全性不明确
 * <p>
 * Cipher可能用到算法参数（AlgorithmParameterSpec或AlgorithmParameters）的情形:
 * a. DES, DES-EDE, and Blowfish使用feedback模式时(如CBC, CFB, OFB或PCBC), 将用到IV
 * b. PBEWithMD5AndDES将用到salt和iteration count
 *
 * @author liuzhipeng
 * @description
 * @create 2019-08-28 19:19
 */
public class Demo2 {
    // private final static String ALGORITHM = "DES";
    // private final static int KEY_SIZE = 56;
    // private final static String TRANSFORMATION = ALGORITHM + "/ECB/PKCS5Padding";

    // private final static String ALGORITHM = "AES";
    // private final static int KEY_SIZE = 128;
    // private final static String TRANSFORMATION = ALGORITHM + "/ECB/PKCS5Padding";

    // private final static String ALGORITHM = "AES";
    // private final static int KEY_SIZE = 128;
    // private final static String TRANSFORMATION = ALGORITHM + "/CBC/PKCS7Padding";

    private final static String ALGORITHM = "PBEWithMD5AndDES";
    private final static int KEY_SIZE = 128;
    private final static String TRANSFORMATION = "PBEWithMD5AndDES";


    private static String CHARSET_NAME = "UTF-8";

    static {
        // 通过BouncyCastle组件来让java里面支持PKCS7Padding填充
        Security.addProvider(new BouncyCastleProvider());
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, DecoderException {
        String str = "hello o ";
        String iv = generateIV();

        // AlgorithmParameterSpec algorithmParameterSpec = getAlgorithmParameterSpec(iv);
        // Key secretKey = getSecretKey();

        AlgorithmParameterSpec algorithmParameterSpec = getParamSpec();
        Key secretKey = getPBEKey();

        // String encrypt = encrypt(str);
        // String encrypt = encrypt(str, secretKey, getAlgorithmParameterSpec(iv));
        String encrypt = encrypt(str, secretKey, algorithmParameterSpec);
        System.out.println(encrypt);

        // String decrypt = decrypt(encrypt);
        // String decrypt = decrypt(encrypt, secretKey, getAlgorithmParameterSpec(iv));
        String decrypt = decrypt(encrypt, secretKey, algorithmParameterSpec);
        System.out.println(decrypt);
    }

    /**
     * 根据密钥{@link #getSecretKey()}对指定的明文
     * plainText
     * 进行加密.
     *
     * @param plainText 明文
     * @return 加密后的密文.
     */
    public static final String encrypt(String plainText, Key secretKey, AlgorithmParameterSpec algorithmParameterSpec) throws NoSuchAlgorithmException {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            // cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, algorithmParameterSpec);
            byte[] p = plainText.getBytes(CHARSET_NAME);
            byte[] result = cipher.doFinal(p);
            BASE64Encoder encoder = new BASE64Encoder();
            String encoded = encoder.encode(result);
            return encoded;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据密钥{@link #getSecretKey()}对指定的密文
     * cipherText
     * 进行解密.
     *
     * @param cipherText 密文
     * @return 解密后的明文.
     */
    public static final String decrypt(String cipherText, Key secretKey, AlgorithmParameterSpec algorithmParameterSpec) throws NoSuchAlgorithmException {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            // cipher.init(Cipher.DECRYPT_MODE, secretKey);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, algorithmParameterSpec);
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] c = decoder.decodeBuffer(cipherText);
            byte[] result = cipher.doFinal(c);
            String plainText = new String(result, CHARSET_NAME);
            return plainText;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateIV() throws NoSuchProviderException, NoSuchAlgorithmException {
        SecureRandom randomSecureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");
        byte[] ivBytes = new byte[16];
        randomSecureRandom.nextBytes(ivBytes);
        String iv = CryptUtil.byteArrayToString(ivBytes, DataEncoding.HEX);
        return CryptUtil.byteArrayToString(ivBytes, DataEncoding.HEX);
    }

    public static AlgorithmParameterSpec getAlgorithmParameterSpec(String iv) throws DecoderException {
        byte[] ivBytes = CryptUtil.stringToByteArray(iv, DataEncoding.HEX);
        return new IvParameterSpec(ivBytes);
    }

    public static SecretKey getSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
        generator.init(KEY_SIZE);
        SecretKey key = generator.generateKey();
        return key;
    }

    /**
     * 获取PBE算法的密钥. 注意PBE密钥由用户提供的口令构造出来的,
     * 用户提供的口令务必使用char数组, 而不能使用字符串, 字符数
     * 组用完即清空.
     *
     * @return PBE算法的密钥.
     */
    private static final Key getPBEKey() {
        // TODO come from db or System.in, NOTE: MUST be char array, not java.lang.String
        char[] pwd = {'%', '_', 'A', 's', '9', 'K'};
        int count = 20;
        SecretKey pbeKey = null;
        PBEKeySpec pbeKeySpec = new PBEKeySpec(pwd);
        try {
            SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            pbeKey = keyFac.generateSecret(pbeKeySpec);
            return pbeKey;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            Arrays.fill(pwd, ' ');
        }
    }

    /**
     * 获取PBE的算法参数, 涉及salt和iterate count两个参数.
     *
     * @return PBE的算法参数.
     */
    private static final PBEParameterSpec getParamSpec() {
        byte[] salt = {(byte) 0xab, (byte) 0x58, (byte) 0xa1, (byte) 0x8c,
                (byte) 0x3e, (byte) 0xc8, (byte) 0x9d, (byte) 0x7a};
        int count = 20;
        PBEParameterSpec paramSpec = new PBEParameterSpec(salt, count);
        return paramSpec;
    }


}
