//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lzp.mastercard.security.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource.PSpecified;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.*;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;

public class CryptUtil {
    public CryptUtil() {
    }

    public static String sanitizeJson(String json) {
        return json.replaceAll("", "").replaceAll("\r", "").replaceAll("\t", "");
    }

    public static String byteArrayToString(byte[] bytes, DataEncoding encoding) {
        return encoding == DataEncoding.HEX ? new String(Hex.encodeHex(bytes)) : DataEncoding.base64Encode(bytes);
    }

    public static byte[] stringToByteArray(String s, DataEncoding encoding) throws DecoderException {
        return encoding == DataEncoding.HEX ? Hex.decodeHex(s.toCharArray()) : DataEncoding.base64Decode(s);
    }

    public static IvParameterSpec generateIv() throws NoSuchAlgorithmException, NoSuchProviderException {
        SecureRandom randomSecureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");
        byte[] ivBytes = new byte[16];
        randomSecureRandom.nextBytes(ivBytes);
        return new IvParameterSpec(ivBytes);
    }

    public static SecretKey generateSecretKey(String algorithm, int size, String digestAlgorithm) throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyGenerator keyGen = KeyGenerator.getInstance(algorithm, "SunJCE");
        keyGen.init(size);
        MessageDigest messageDigest = MessageDigest.getInstance(digestAlgorithm, "SUN");
        messageDigest.reset();
        byte[] keyValue = messageDigest.digest(keyGen.generateKey().getEncoded());
        keyValue = Arrays.copyOf(keyValue, 16);
        return new SecretKeySpec(keyValue, algorithm);
    }

    public static SecretKey generateSecretKey(String algorithm, String provider, int size) throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyGenerator keyGen = KeyGenerator.getInstance(algorithm, provider);
        keyGen.init(size);
        return keyGen.generateKey();
    }

    public static byte[] generateFingerprint(String algorithm, Key key) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        messageDigest.reset();
        messageDigest.update(key.getEncoded());
        return messageDigest.digest();
    }

    public static byte[] generateFingerprint(String algorithm, Certificate certificate) throws NoSuchAlgorithmException, CertificateEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        messageDigest.reset();
        messageDigest.update(certificate.getEncoded());
        return messageDigest.digest();
    }

    public static byte[] crypt(int operation, String algorithm, Key key, AlgorithmParameterSpec iv, byte[] clearText) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
        Cipher currentCipher = null;
        currentCipher = Cipher.getInstance(algorithm);
        if (iv == null) {
            currentCipher.init(operation, key);
        } else {
            currentCipher.init(operation, key, iv);
        }

        return currentCipher.doFinal(clearText);
    }

    public static byte[] wrap(String algorithm, String provider, Key key, Key keyToWrap) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, NoSuchProviderException {
        Cipher currentCipher = null;
        if (provider != null) {
            currentCipher = Cipher.getInstance(algorithm, provider);
        } else {
            currentCipher = Cipher.getInstance(algorithm);
        }

        if (algorithm.contains("OAEPWith")) {
            currentCipher.init(3, key, getOAEPParameterSpec(algorithm, null));
        } else {
            currentCipher.init(3, key);
        }

        return currentCipher.wrap(keyToWrap);
    }

    private static OAEPParameterSpec getOAEPParameterSpec(String algorithm, String oaepHashingAlgorithm) {
        if (algorithm.contains("OAEPWith")) {
            int startDigest = algorithm.indexOf("OAEPWith") + 8;
            int endDigest = algorithm.indexOf("And");
            int startPadding = endDigest + 3;
            int endPadding = algorithm.indexOf("Padding");
            String digest = algorithm.substring(startDigest, endDigest);
            String padding = algorithm.substring(startPadding, endPadding);
            return oaepHashingAlgorithm != null ? new OAEPParameterSpec(digest, padding, new MGF1ParameterSpec(oaepHashingAlgorithm), PSpecified.DEFAULT) : new OAEPParameterSpec(digest, padding, new MGF1ParameterSpec(digest), PSpecified.DEFAULT);
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        getOAEPParameterSpec("RSA/ECB/OAEPWithSHA-512AndMGF1Padding", null);
    }

    public static Key unwrap(String algorithm, String provider, Key key, byte[] keyToUnwrap, String keyAlgorithmToUnwrap, int keyTypeToUnwrap) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, NoSuchProviderException {
        Cipher currentCipher = null;
        if (provider != null) {
            currentCipher = Cipher.getInstance(algorithm, provider);
        } else {
            currentCipher = Cipher.getInstance(algorithm);
        }

        if (algorithm.contains("OAEPWith")) {
            currentCipher.init(4, key, getOAEPParameterSpec(algorithm, null));
        } else {
            currentCipher.init(4, key);
        }

        return currentCipher.unwrap(keyToUnwrap, keyAlgorithmToUnwrap, keyTypeToUnwrap);
    }

    public static Key unwrap(String algorithm, String oaepHashingAlgorithm, String provider, Key key, byte[] keyToUnwrap, String keyAlgorithmToUnwrap, int keyTypeToUnwrap) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, NoSuchProviderException {
        Cipher currentCipher = null;
        if (provider != null) {
            currentCipher = Cipher.getInstance(algorithm, provider);
        } else {
            currentCipher = Cipher.getInstance(algorithm);
        }

        if (algorithm.contains("OAEPWith")) {
            currentCipher.init(3, key, getOAEPParameterSpec(algorithm, oaepHashingAlgorithm));
        } else {
            currentCipher.init(4, key);
        }

        return currentCipher.unwrap(keyToUnwrap, keyAlgorithmToUnwrap, keyTypeToUnwrap);
    }

    public static byte[] crypt(int operation, String algorithm, String provider, Key key, AlgorithmParameterSpec iv, byte[] clearText) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, NoSuchProviderException {
        if (operation != 4 && operation != 3) {
            Cipher currentCipher = null;
            if (provider != null) {
                currentCipher = Cipher.getInstance(algorithm, provider);
            } else {
                currentCipher = Cipher.getInstance(algorithm);
            }

            if (iv == null) {
                currentCipher.init(operation, key);
            } else {
                currentCipher.init(operation, key, iv);
            }

            return currentCipher.doFinal(clearText);
        } else {
            throw new InvalidAlgorithmParameterException("Cannot use Wrap/UnWrap in a crypt method");
        }
    }

    public static Key unwrap(int decryptMode, String algorithm, String provider, Key key, byte[] keyToUnwrap, String keyAlgorithmToUnwrap, int keyTypeToUnwrap) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException, NoSuchProviderException {
        Cipher currentCipher = null;
        if (provider != null) {
            currentCipher = Cipher.getInstance(algorithm, provider);
        } else {
            currentCipher = Cipher.getInstance(algorithm);
        }

        currentCipher.init(4, key);
        return currentCipher.unwrap(keyToUnwrap, keyAlgorithmToUnwrap, keyTypeToUnwrap);
    }

    public static Certificate loadCertificate(String instance, InputStream is) throws CertificateException, NoSuchProviderException {
        CertificateFactory factory = CertificateFactory.getInstance(instance, "SUN");
        return factory.generateCertificate(is);
    }

    public static Certificate loadCertificate(String instance, String provider, InputStream is) throws CertificateException, NoSuchProviderException {
        CertificateFactory factory = CertificateFactory.getInstance(instance, provider);
        return factory.generateCertificate(is);
    }

    public static PrivateKey loadPrivateKey(String instance, InputStream is) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, NoSuchProviderException {
        byte[] keyBytes = getBytesFromInputStream(is);
        KeyFactory kf = KeyFactory.getInstance(instance, "SunRsaSign");
        PKCS8EncodedKeySpec kspec = new PKCS8EncodedKeySpec(keyBytes);
        return kf.generatePrivate(kspec);
    }

    public static PrivateKey loadPrivateKey(String instance, String provider, InputStream is) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, NoSuchProviderException {
        byte[] keyBytes = getBytesFromInputStream(is);
        KeyFactory kf = KeyFactory.getInstance(instance, provider);
        PKCS8EncodedKeySpec kspec = new PKCS8EncodedKeySpec(keyBytes);
        return kf.generatePrivate(kspec);
    }

    public static byte[] getBytesFromInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        int len;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }

        os.flush();
        return os.toByteArray();
    }

    public static Key loadKey(KeyType type, String instance, InputStream p12, String alias, String password) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, UnrecoverableKeyException {
        KeyStore ks = KeyStore.getInstance(instance);
        ks.load(p12, password.toCharArray());
        Key key = ks.getKey(alias, password.toCharArray());
        if (key instanceof PrivateKey) {
            if (type.name().compareTo(KeyType.PRIVATE.name()) == 0) {
                return key;
            }

            if (type.name().compareTo(KeyType.PUBLIC.name()) == 0) {
                X509Certificate cert = (X509Certificate) ks.getCertificate(alias);
                return cert.getPublicKey();
            }
        }

        return null;
    }

    public static Key loadKey(KeyType type, String instance, String provider, InputStream p12, String alias, String password) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, UnrecoverableKeyException, NoSuchProviderException {
        KeyStore ks;
        if (provider == null) {
            ks = KeyStore.getInstance(instance);
        } else {
            ks = KeyStore.getInstance(instance, provider);
        }

        ks.load(p12, password.toCharArray());
        Key key = ks.getKey(alias, password.toCharArray());
        if (key instanceof PrivateKey) {
            if (type.name().compareTo(KeyType.PRIVATE.name()) == 0) {
                return key;
            }

            if (type.name().compareTo(KeyType.PUBLIC.name()) == 0) {
                X509Certificate cert = (X509Certificate) ks.getCertificate(alias);
                return cert.getPublicKey();
            }
        }

        return null;
    }
}
