package com.tr.utils;

import com.tr.core.encrypt.encryption.EncryptionException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;
import java.util.Arrays;

/**
 * Created by daniel on 2019/7/24 11:08.
 */
public class CryptoUtil {
    private static final Integer SYMMETRIC_KEY_SIZE = 128;
    private static final String SYMMETRIC_KEY_TYPE = "AES";
    private static final String BC = "BC";
    private static final String SUN_JCE = "SunJCE";
    private static final String SYMMETRIC_CYPHER = "AES/CBC/PKCS5Padding";
    private static final String ASYMMETRIC_CYPHER = "RSA/ECB/OAEPWith{ALG}AndMGF1Padding";

    private static final String ALG = "{ALG}";
    private static final String OAEPWith = "OAEPWith";
    private static final String And = "And";
    private static final String Padding = "Padding";

    static {
        // 通过BouncyCastle组件来让java里面支持PKCS7Padding填充
        // Security.addProvider(new BouncyCastleProvider());
    }

    public static SecretKey generateSecretKey(String digestAlgorithm) throws EncryptionException {
        try {
            KeyGenerator generator = KeyGenerator.getInstance(SYMMETRIC_KEY_TYPE);
            generator.init(SYMMETRIC_KEY_SIZE);
            if (digestAlgorithm != null) {
                // return generator.generateKey();
                MessageDigest messageDigest = MessageDigest.getInstance(digestAlgorithm, "SUN");
                messageDigest.reset();
                byte[] keyValue = messageDigest.digest(generator.generateKey().getEncoded());
                keyValue = Arrays.copyOf(keyValue, 16);
                return new SecretKeySpec(keyValue, SYMMETRIC_KEY_TYPE);
            }
            return generator.generateKey();
        } catch (GeneralSecurityException e) {
            throw new EncryptionException("Failed to generate a secret key!", e);
        }
    }

    public static IvParameterSpec generateIv() throws EncryptionException {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            byte[] ivBytes = new byte[16];
            secureRandom.nextBytes(ivBytes);
            return new IvParameterSpec(ivBytes);
        } catch (GeneralSecurityException e) {
            throw new EncryptionException("Failed to generate an IV value!", e);
        }
    }

    public static byte[] encrypt(Key key, AlgorithmParameterSpec iv, byte[] bytes) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(SYMMETRIC_CYPHER);
        if (iv != null) {
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, key);
        }
        return cipher.doFinal(bytes);
    }

    public static byte[] decrypt(Key key, AlgorithmParameterSpec iv, byte[] bytes) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(SYMMETRIC_CYPHER);
        if (iv != null) {
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
        } else {
            cipher.init(Cipher.DECRYPT_MODE, key);
        }
        return cipher.doFinal(bytes);
    }

    public static byte[] wrapSecretKey(Key publicEncryptionKey, String oaepPaddingDigestAlgorithm, Key key) throws EncryptionException {
        MGF1ParameterSpec mgf1ParameterSpec = new MGF1ParameterSpec(oaepPaddingDigestAlgorithm);
        String asymmetricCipher = ASYMMETRIC_CYPHER.replace(ALG, mgf1ParameterSpec.getDigestAlgorithm());
        return wrap(asymmetricCipher, null, mgf1ParameterSpec, publicEncryptionKey, key);
    }

    public static Key unwrapSecretKey(Key decryptionKey, String oaepDigestAlgorithm, byte[] keyBytes) throws EncryptionException {
        if (!oaepDigestAlgorithm.contains("-")) {
            oaepDigestAlgorithm = oaepDigestAlgorithm.replace("SHA", "SHA-");
        }
        MGF1ParameterSpec mgf1ParameterSpec = new MGF1ParameterSpec(oaepDigestAlgorithm);
        String asymmetricCipher = ASYMMETRIC_CYPHER.replace(ALG, mgf1ParameterSpec.getDigestAlgorithm());
        return unwrap(asymmetricCipher, null, mgf1ParameterSpec, decryptionKey, keyBytes, SYMMETRIC_KEY_TYPE,
                Cipher.SECRET_KEY);

    }

    public static byte[] wrap(String asymmetricCipher, final String provider,
                              final MGF1ParameterSpec mgf1ParameterSpec, final Key key, final Key keyToWrap) throws EncryptionException {
        Cipher cipher = null;
        try {
            if (provider != null) {
                cipher = Cipher.getInstance(asymmetricCipher, provider);
            } else {
                cipher = Cipher.getInstance(asymmetricCipher);
            }

            if (asymmetricCipher.contains(OAEPWith)) {
                cipher.init(Cipher.WRAP_MODE, key, getOaepParameterSpec(asymmetricCipher, mgf1ParameterSpec));
            } else {
                cipher.init(Cipher.WRAP_MODE, key);
            }
            return cipher.wrap(keyToWrap);
        } catch (GeneralSecurityException e) {
            throw new EncryptionException("Failed to wrap secret key!", e);
        }
    }

    public static Key unwrap(String asymmetricCipher, String provider, MGF1ParameterSpec mgf1ParameterSpec, Key key,
                             byte[] keyToUnwrap, String keyAlgorithmToUnwrap, final int keyTypeToUnwrap) throws EncryptionException {
        Cipher cipher = null;
        try {
            if (provider != null) {
                cipher = Cipher.getInstance(asymmetricCipher, provider);
            } else {
                cipher = Cipher.getInstance(asymmetricCipher);
            }

            if (asymmetricCipher.contains(OAEPWith)) {
                cipher.init(Cipher.UNWRAP_MODE, key, getOaepParameterSpec(asymmetricCipher, mgf1ParameterSpec));
            } else {
                cipher.init(Cipher.UNWRAP_MODE, key);
            }
            return cipher.unwrap(keyToUnwrap, keyAlgorithmToUnwrap, keyTypeToUnwrap);
        } catch (GeneralSecurityException e) {
            throw new EncryptionException("Failed to unwrap secret key!", e);
        }
    }

    private static OAEPParameterSpec getOaepParameterSpec(String asymmetricCipher,
                                                          MGF1ParameterSpec mgf1ParameterSpec) {
        if (asymmetricCipher.contains(OAEPWith)) {
            int startDigest = asymmetricCipher.indexOf(OAEPWith) + 8;
            int endDigest = asymmetricCipher.indexOf(And);
            int startPadding = endDigest + 3;
            int endPadding = asymmetricCipher.indexOf(Padding);
            String digest = asymmetricCipher.substring(startDigest, endDigest);
            String padding = asymmetricCipher.substring(startPadding, endPadding);
            return mgf1ParameterSpec != null
                    ? new OAEPParameterSpec(mgf1ParameterSpec.getDigestAlgorithm(), padding, mgf1ParameterSpec,
                    PSource.PSpecified.DEFAULT)
                    : new OAEPParameterSpec(digest, padding, new MGF1ParameterSpec(digest), PSource.PSpecified.DEFAULT);
        } else {
            return null;
        }
    }

    public static byte[] generateFingerprint(String algorithm, Key key) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        messageDigest.reset();
        messageDigest.update(key.getEncoded());
        return messageDigest.digest();
    }

    public static byte[] generateFingerprint(String algorithm, Certificate certificate) throws CertificateEncodingException, NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        messageDigest.reset();
        messageDigest.update(certificate.getEncoded());
        return messageDigest.digest();
    }

    public static Key getSecretKey(Key decryptionKey, String oaepHashingAlgorithm, byte[] encryptedSecretKeyBytes) throws EncryptionException {
        try {
            // Decrypt the AES secret key
            Key secretKey = unwrapSecretKey(decryptionKey, oaepHashingAlgorithm, encryptedSecretKeyBytes);
            return secretKey;
        } catch (EncryptionException e) {
            throw e;
        } catch (Exception e) {
            throw new EncryptionException("Failed to decode and unwrap the provided secret key value!", e);
        }
    }

    public static IvParameterSpec getIvSpec(byte[] ivByteArray) throws EncryptionException {
        try {
            // Decode the IV
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivByteArray);
            return ivParameterSpec;
        } catch (Exception e) {
            throw new EncryptionException("Failed to decode the provided IV value!", e);
        }
    }

    public static void main(String[] args) throws Exception {
        SecretKey secretKey = generateSecretKey("SHA-256");
        IvParameterSpec ivParameterSpec = generateIv();
        String s = "hello aes!";
        byte[] encryptBytes = encrypt(secretKey, ivParameterSpec, s.getBytes("UTF-8"));
        // publicKey
        byte[] bytes = wrapSecretKey(null, null, secretKey);

        // privateKey
        Key getSecretKey = getSecretKey(null, "SHA-256", bytes);
        IvParameterSpec ivSpec = getIvSpec(ivParameterSpec.getIV());
        byte[] decrypt = decrypt(getSecretKey, ivSpec, encryptBytes);
        System.out.println(new String(decrypt));
    }
}
