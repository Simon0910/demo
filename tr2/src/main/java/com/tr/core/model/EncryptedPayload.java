/*
 * MDES for Merchants
 * The MDES APIs are designed as RPC style stateless web services where each API endpoint represents an operation to be performed.  All request and response payloads are sent in the JSON (JavaScript Object Notation) data-interchange format. Each endpoint in the API specifies the HTTP Method used to access it. All strings in request and response objects are to be UTF-8 encoded.  Each API URI includes the major and minor version of API that it conforms to.  This will allow multiple concurrent versions of the API to be deployed simultaneously. <br> __Authentication__ Mastercard uses OAuth 1.0a with body hash extension for authenticating the API clients. This requires every request that you send to  Mastercard to be signed with an RSA private key. A private-public RSA key pair must be generated consisting of: <br> 1 . A private key for the OAuth signature for API requests. It is recommended to keep the private key in a password-protected or hardware keystore. <br> 2. A public key is shared with Mastercard during the project setup process through either a certificate signing request (CSR) or the API Key Generator. Mastercard will use the public key to verify the OAuth signature that is provided on every API call.<br>  An OAUTH1.0a signer library is available on [GitHub](https://github.com/Mastercard/oauth1-signer-java) <br>  __Encryption__<br>  All communications between Issuer web service and the Mastercard gateway is encrypted using TLS. <br> __Additional Encryption of Sensitive Data__ In addition to the OAuth authentication, when using MDES Digital Enablement Service, any PCI sensitive and all account holder Personally Identifiable Information (PII) data must be encrypted. This requirement applies to the API fields containing encryptedData. Sensitive data is encrypted using a symmetric session (one-time-use) key. The symmetric session key is then wrapped with an RSA Public Key supplied by Mastercard during API setup phase (the Customer Encryption Key). <br>  Java Client Encryption Library available on [GitHub](https://github.com/Mastercard/client-encryption-java)
 *
 * The version of the OpenAPI document: 1.2.9
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.tr.core.model;

import com.google.gson.annotations.SerializedName;
import com.tr.core.model.inbound.NotifyTokenUpdatedRequest;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * EncryptedPayload
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2019-08-16T09:54:27.990+01:00[Europe/London]")
public class EncryptedPayload {
    public static final String SERIALIZED_NAME_PUBLIC_KEY_FINGERPRINT = "publicKeyFingerprint";
    @SerializedName(SERIALIZED_NAME_PUBLIC_KEY_FINGERPRINT)
    private String publicKeyFingerprint;

    public static final String SERIALIZED_NAME_ENCRYPTED_KEY = "encryptedKey";
    @SerializedName(SERIALIZED_NAME_ENCRYPTED_KEY)
    private String encryptedKey;

    public static final String SERIALIZED_NAME_OAEP_HASHING_ALGORITHM = "oaepHashingAlgorithm";
    @SerializedName(SERIALIZED_NAME_OAEP_HASHING_ALGORITHM)
    private String oaepHashingAlgorithm;

    public static final String SERIALIZED_NAME_IV = "iv";
    @SerializedName(SERIALIZED_NAME_IV)
    private String iv;

    public static final String SERIALIZED_NAME_ENCRYPTED_DATA = "encryptedData";
    @SerializedName(SERIALIZED_NAME_ENCRYPTED_DATA)
    private NotifyTokenUpdatedRequest encryptedData = null;

    public EncryptedPayload publicKeyFingerprint(String publicKeyFingerprint) {
        this.publicKeyFingerprint = publicKeyFingerprint;
        return this;
    }

    /**
     * The fingerprint of the public key used to encrypt the ephemeral AES key.     __Max Length:64__
     *
     * @return publicKeyFingerprint
     **/
    @ApiModelProperty(example = "4c4ead5927f0df8117f178eea9308daa58e27c2b", required = true, value = "The fingerprint of the public key used to encrypt the ephemeral AES key.     __Max Length:64__ ")
    public String getPublicKeyFingerprint() {
        return publicKeyFingerprint;
    }

    public void setPublicKeyFingerprint(String publicKeyFingerprint) {
        this.publicKeyFingerprint = publicKeyFingerprint;
    }

    public EncryptedPayload encryptedKey(String encryptedKey) {
        this.encryptedKey = encryptedKey;
        return this;
    }

    /**
     * One-time use AES key encrypted by the MasterCard public key (as identified by publicKeyFingerprint) using the OAEP or PKCS#1 v1.5 scheme (depending on the value of oaepHashingAlgorithm.     __Max Length:512__
     *
     * @return encryptedKey
     **/
    @ApiModelProperty(example = "A1B2C3D4E5F6112233445566", required = true, value = "One-time use AES key encrypted by the MasterCard public key (as identified by publicKeyFingerprint) using the OAEP or PKCS#1 v1.5 scheme (depending on the value of oaepHashingAlgorithm.     __Max Length:512__ ")
    public String getEncryptedKey() {
        return encryptedKey;
    }

    public void setEncryptedKey(String encryptedKey) {
        this.encryptedKey = encryptedKey;
    }

    public EncryptedPayload oaepHashingAlgorithm(String oaepHashingAlgorithm) {
        this.oaepHashingAlgorithm = oaepHashingAlgorithm;
        return this;
    }

    /**
     * Hashing algorithm used with the OAEP scheme. Must be either SHA256 or SHA512.
     *
     * @return oaepHashingAlgorithm
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "SHA512", value = "Hashing algorithm used with the OAEP scheme. Must be either SHA256 or SHA512. ")
    public String getOaepHashingAlgorithm() {
        return oaepHashingAlgorithm;
    }

    public void setOaepHashingAlgorithm(String oaepHashingAlgorithm) {
        this.oaepHashingAlgorithm = oaepHashingAlgorithm;
    }

    public EncryptedPayload iv(String iv) {
        this.iv = iv;
        return this;
    }

    /**
     * The initialization vector used when encrypting data using the one-time use AES key. Must be exactly 16 bytes (32 character hex string) to match the block size. If not present, an IV of zero is assumed. Length - 32.
     *
     * @return iv
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "NA", value = "The initialization vector used when encrypting data using the one-time use AES key. Must be exactly 16 bytes (32 character hex string) to match the block size. If not present, an IV of zero is assumed. Length - 32. ")
    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public EncryptedPayload encryptedData(NotifyTokenUpdatedRequest encryptedData) {
        this.encryptedData = encryptedData;
        return this;
    }

    /**
     * Get encryptedData
     *
     * @return encryptedData
     **/
    @ApiModelProperty(required = true, value = "")
    public NotifyTokenUpdatedRequest getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(NotifyTokenUpdatedRequest encryptedData) {
        this.encryptedData = encryptedData;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EncryptedPayload encryptedPayload = (EncryptedPayload) o;
        return Objects.equals(this.publicKeyFingerprint, encryptedPayload.publicKeyFingerprint) &&
                Objects.equals(this.encryptedKey, encryptedPayload.encryptedKey) &&
                Objects.equals(this.oaepHashingAlgorithm, encryptedPayload.oaepHashingAlgorithm) &&
                Objects.equals(this.iv, encryptedPayload.iv) &&
                Objects.equals(this.encryptedData, encryptedPayload.encryptedData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publicKeyFingerprint, encryptedKey, oaepHashingAlgorithm, iv, encryptedData);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EncryptedPayload {");
        sb.append("    publicKeyFingerprint: ").append(toIndentedString(publicKeyFingerprint)).append("");
        sb.append("    encryptedKey: ").append(toIndentedString(encryptedKey)).append("");
        sb.append("    oaepHashingAlgorithm: ").append(toIndentedString(oaepHashingAlgorithm)).append("");
        sb.append("    iv: ").append(toIndentedString(iv)).append("");
        sb.append("    encryptedData: ").append(toIndentedString(encryptedData)).append("");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("", "");
    }

}

