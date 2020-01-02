//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lzp.mastercard.security.fle;

import com.lzp.mastercard.exception.SdkException;
import com.lzp.mastercard.model.RequestMap;
import com.lzp.mastercard.security.CryptographyInterceptor;
import com.lzp.mastercard.security.util.CryptUtil;
import com.lzp.mastercard.security.util.KeyType;
import org.json.simple.JSONValue;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FieldLevelEncryption implements CryptographyInterceptor {
    protected final PrivateKey privateKey;
    protected final Certificate publicCertificate;
    protected final Config config;
    protected final String publicKeyFingerprint;

    public FieldLevelEncryption(InputStream publicCertificate, InputStream keystore, String privateKeyAlias, String privateKeyPassword, Config config, String publicKeyFingerprint) throws SdkException {
        try {
            if (publicCertificate != null) {
                this.publicCertificate = CryptUtil.loadCertificate("X.509", publicCertificate);
            } else {
                this.publicCertificate = null;
            }

            if (keystore != null) {
                this.privateKey = (PrivateKey) CryptUtil.loadKey(KeyType.PRIVATE, "PKCS12", keystore, privateKeyAlias, privateKeyPassword);
            } else {
                this.privateKey = null;
            }

            this.config = config;
            this.publicKeyFingerprint = publicKeyFingerprint;
        } catch (Exception var8) {
            throw new SdkException(var8.getMessage(), var8);
        }
    }

    public FieldLevelEncryption(InputStream publicCertificate, InputStream privateKey, Config config, String publicKeyFingerprint) throws SdkException {
        try {
            if (publicCertificate != null) {
                this.publicCertificate = CryptUtil.loadCertificate("X.509", publicCertificate);
            } else {
                this.publicCertificate = null;
            }

            if (privateKey != null) {
                this.privateKey = CryptUtil.loadPrivateKey("RSA", privateKey);
            } else {
                this.privateKey = null;
            }

            this.config = config;
            this.publicKeyFingerprint = publicKeyFingerprint;
        } catch (Exception var6) {
            throw new SdkException(var6.getMessage(), var6);
        }
    }

    @Override
    public List<String> getTriggeringEndPath() {
        return this.config.triggeringEndPath;
    }

    @Override
    public Map<String, Object> encrypt(Map<String, Object> map) throws SdkException {
        try {
            RequestMap smartMap = new RequestMap(map);
            if (this.publicCertificate != null) {
                Iterator var3 = this.config.fieldsToEncrypt.iterator();

                while (var3.hasNext()) {
                    String fieldToEncrypt = (String) var3.next();
                    if (smartMap.containsKey(fieldToEncrypt)) {
                        Object tmpObjectToEncrypt = smartMap.remove(fieldToEncrypt);
                        String payload = null;
                        if (tmpObjectToEncrypt instanceof Map) {
                            payload = JSONValue.toJSONString(tmpObjectToEncrypt);
                            payload = CryptUtil.sanitizeJson(payload);
                        } else {
                            payload = String.valueOf(tmpObjectToEncrypt);
                        }

                        IvParameterSpec iv = CryptUtil.generateIv();
                        String ivValue = CryptUtil.byteArrayToString(iv.getIV(), this.config.dataEncoding);
                        SecretKey secretKey = CryptUtil.generateSecretKey(this.config.symmetricCipher, this.config.symmetricKeysize, this.config.publicKeyFingerprintHashing);
                        byte[] encryptedData = CryptUtil.crypt(1, this.config.symmetricAlgorithm, "SunJCE", secretKey, iv, payload.getBytes(StandardCharsets.UTF_8));
                        String encryptedDataValue = CryptUtil.byteArrayToString(encryptedData, this.config.dataEncoding);
                        byte[] encryptedSecretKey = CryptUtil.wrap(this.config.asymmetricCipher, "SunJCE", this.publicCertificate.getPublicKey(), secretKey);
                        String encryptedKeyValue = CryptUtil.byteArrayToString(encryptedSecretKey, this.config.dataEncoding);
                        String fingerprintValue;
                        if (this.publicKeyFingerprint == null) {
                            byte[] certificateFingerprint = CryptUtil.generateFingerprint(this.config.publicKeyFingerprintHashing, this.publicCertificate);
                            fingerprintValue = CryptUtil.byteArrayToString(certificateFingerprint, this.config.dataEncoding);
                        } else {
                            fingerprintValue = this.publicKeyFingerprint;
                        }

                        String baseKey = "";
                        if (fieldToEncrypt.indexOf(46) > 0) {
                            baseKey = fieldToEncrypt.substring(0, fieldToEncrypt.lastIndexOf("."));
                            baseKey = baseKey + ".";
                        }

                        if (this.config.publicKeyFingerprintFiledName != null) {
                            smartMap.put(baseKey + this.config.publicKeyFingerprintFiledName, fingerprintValue);
                        }

                        if (this.config.oaepHashingAlgorithmFieldName != null) {
                            smartMap.put(baseKey + this.config.oaepHashingAlgorithmFieldName, this.config.oaepHashingAlgorithm);
                        }

                        smartMap.put(baseKey + this.config.ivFieldName, ivValue);
                        smartMap.put(baseKey + this.config.encryptedKeyFiledName, encryptedKeyValue);
                        smartMap.put(baseKey + this.config.encryptedDataFieldName, encryptedDataValue);
                        break;
                    }
                }
            }

            return smartMap;
        } catch (Exception var16) {
            throw new SdkException(var16.getMessage(), var16);
        }
    }

    @Override
    public Map<String, Object> decrypt(Map<String, Object> map) throws SdkException {
        try {
            RequestMap smartMap = new RequestMap(map);
            if (this.privateKey != null) {
                Iterator var3 = this.config.fieldsToDecrypt.iterator();

                while (var3.hasNext()) {
                    String fieldToDecrypt = (String) var3.next();
                    if (smartMap.containsKey(fieldToDecrypt)) {
                        String baseKey = "";
                        String encryptedDataMapField = fieldToDecrypt;
                        Object enclosingBlock;
                        if (fieldToDecrypt.indexOf(46) > 0) {
                            baseKey = fieldToDecrypt.substring(0, fieldToDecrypt.lastIndexOf("."));
                            encryptedDataMapField = fieldToDecrypt.substring(fieldToDecrypt.lastIndexOf(".") + 1);
                            enclosingBlock = (Map) smartMap.get(baseKey);
                        } else {
                            enclosingBlock = smartMap;
                        }

                        String encryptedKey = (String) ((Map) enclosingBlock).remove(this.config.encryptedKeyFiledName);
                        byte[] encryptedKeyByteArray = CryptUtil.stringToByteArray(encryptedKey, this.config.dataEncoding);
                        SecretKey secretKey = null;
                        String ivString;
                        if (!((Map) enclosingBlock).containsKey(this.config.oaepHashingAlgorithmFieldName)) {
                            secretKey = (SecretKey) CryptUtil.unwrap(this.config.asymmetricCipher, "SunJCE", this.privateKey, encryptedKeyByteArray, this.config.symmetricCipher, 3);
                        } else {
                            ivString = (String) ((Map) enclosingBlock).remove(this.config.oaepHashingAlgorithmFieldName);
                            if (!ivString.contains("-")) {
                                ivString = ivString.replace("SHA", "SHA-");
                            }

                            secretKey = (SecretKey) CryptUtil.unwrap("RSA/ECB/OAEPWith" + ivString + "AndMGF1Padding", "SunJCE", this.privateKey, encryptedKeyByteArray, this.config.symmetricCipher, 3);
                        }

                        ivString = (String) ((Map) enclosingBlock).remove(this.config.ivFieldName);
                        ((Map) enclosingBlock).remove(this.config.publicKeyFingerprintFiledName);

                        byte[] ivByteArray = CryptUtil.stringToByteArray(ivString, this.config.dataEncoding);
                        IvParameterSpec iv = new IvParameterSpec(ivByteArray);
                        String encryptedData = (String) ((Map) enclosingBlock).remove(encryptedDataMapField);
                        byte[] encryptedDataByteArray = CryptUtil.stringToByteArray(encryptedData, this.config.dataEncoding);
                        byte[] decryptedDataArray = CryptUtil.crypt(2, this.config.symmetricAlgorithm, "SunJCE", secretKey, iv, encryptedDataByteArray);
                        String decryptedDataString = new String(decryptedDataArray, StandardCharsets.UTF_8);
                        if (decryptedDataString.startsWith("{")) {
                            Map<String, Object> decryptedDataMap = (Map) JSONValue.parse(decryptedDataString);
                            Iterator var19 = decryptedDataMap.entrySet().iterator();

                            while (var19.hasNext()) {
                                Entry<String, Object> entry = (Entry) var19.next();
                                smartMap.put(baseKey + "." + encryptedDataMapField + "." + entry.getKey(), entry.getValue());
                            }

                            return smartMap;
                        } else {
                            smartMap.put(baseKey + "." + encryptedDataMapField, decryptedDataString);
                            break;
                        }
                    }
                }
            }

            return smartMap;
        } catch (Exception var21) {
            throw new SdkException(var21.getMessage(), var21);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            FieldLevelEncryption otherMdes = (FieldLevelEncryption) o;
            return this.config.equals(otherMdes.config);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.config.hashCode();
    }
}
