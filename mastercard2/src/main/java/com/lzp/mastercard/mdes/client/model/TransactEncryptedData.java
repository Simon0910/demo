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


package com.lzp.mastercard.mdes.client.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * TransactEncryptedData
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2019-08-15T21:18:23.871+01:00[Europe/London]")
public class TransactEncryptedData {
    public static final String SERIALIZED_NAME_ACCOUNT_NUMBER = "accountNumber";
    @SerializedName(SERIALIZED_NAME_ACCOUNT_NUMBER)
    private String accountNumber;

    public static final String SERIALIZED_NAME_APPLICATION_EXPIRY_DATE = "applicationExpiryDate";
    @SerializedName(SERIALIZED_NAME_APPLICATION_EXPIRY_DATE)
    private String applicationExpiryDate;

    public static final String SERIALIZED_NAME_PAN_SEQUENCE_NUMBER = "panSequenceNumber";
    @SerializedName(SERIALIZED_NAME_PAN_SEQUENCE_NUMBER)
    private String panSequenceNumber;

    public static final String SERIALIZED_NAME_TRACK2_EQUIVALENT = "track2Equivalent";
    @SerializedName(SERIALIZED_NAME_TRACK2_EQUIVALENT)
    private String track2Equivalent;

    public static final String SERIALIZED_NAME_DE48SE43_DATA = "de48se43Data";
    @SerializedName(SERIALIZED_NAME_DE48SE43_DATA)
    private String de48se43Data;

    public static final String SERIALIZED_NAME_DE55_DATA = "de55Data";
    @SerializedName(SERIALIZED_NAME_DE55_DATA)
    private String de55Data;

    public TransactEncryptedData accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    /**
     * The Primary Account Number for the transaction – this is the Token PAN.  &lt;br&gt;__Min Length: 9__ &lt;br&gt;__Max Length: 19__
     *
     * @return accountNumber
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "5480981500100002", value = "The Primary Account Number for the transaction – this is the Token PAN.  <br>__Min Length: 9__ <br>__Max Length: 19__ ")
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public TransactEncryptedData applicationExpiryDate(String applicationExpiryDate) {
        this.applicationExpiryDate = applicationExpiryDate;
        return this;
    }

    /**
     * Application expiry date for the Token. Expressed in YYMMDD format.  &lt;br&gt; __Length: 6__
     *
     * @return applicationExpiryDate
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "210931", value = "Application expiry date for the Token. Expressed in YYMMDD format.  <br> __Length: 6__ ")
    public String getApplicationExpiryDate() {
        return applicationExpiryDate;
    }

    public void setApplicationExpiryDate(String applicationExpiryDate) {
        this.applicationExpiryDate = applicationExpiryDate;
    }

    public TransactEncryptedData panSequenceNumber(String panSequenceNumber) {
        this.panSequenceNumber = panSequenceNumber;
        return this;
    }

    /**
     * Application PAN sequence number for the Token &lt;br&gt;  __Length: 2__
     *
     * @return panSequenceNumber
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "01", value = "Application PAN sequence number for the Token <br>  __Length: 2__ ")
    public String getPanSequenceNumber() {
        return panSequenceNumber;
    }

    public void setPanSequenceNumber(String panSequenceNumber) {
        this.panSequenceNumber = panSequenceNumber;
    }

    public TransactEncryptedData track2Equivalent(String track2Equivalent) {
        this.track2Equivalent = track2Equivalent;
        return this;
    }

    /**
     * Track 2 equivalent data for the Token. Expressed according to ISO/IEC 7813, excluding start sentinel, end sentinel, and Longitudinal Redundancy Check (LRC), using hex nibble &#39;D&#39; as field separator, and padded to whole bytes using one hex nibble &#39;F&#39; as needed.  &lt;br&gt;   __Max Length: 38__
     *
     * @return track2Equivalent
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "5480981500100002D18112011000000000000F", value = "Track 2 equivalent data for the Token. Expressed according to ISO/IEC 7813, excluding start sentinel, end sentinel, and Longitudinal Redundancy Check (LRC), using hex nibble 'D' as field separator, and padded to whole bytes using one hex nibble 'F' as needed.  <br>   __Max Length: 38__ ")
    public String getTrack2Equivalent() {
        return track2Equivalent;
    }

    public void setTrack2Equivalent(String track2Equivalent) {
        this.track2Equivalent = track2Equivalent;
    }

    public TransactEncryptedData de48se43Data(String de48se43Data) {
        this.de48se43Data = de48se43Data;
        return this;
    }

    /**
     * Data for DE 48 Subelement 43 containing the cryptogram.&lt;br&gt; __Max Length: 32__
     *
     * @return de48se43Data
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "1.122334455667789E37", value = "Data for DE 48 Subelement 43 containing the cryptogram.<br> __Max Length: 32__ ")
    public String getDe48se43Data() {
        return de48se43Data;
    }

    public void setDe48se43Data(String de48se43Data) {
        this.de48se43Data = de48se43Data;
    }

    public TransactEncryptedData de55Data(String de55Data) {
        this.de55Data = de55Data;
        return this;
    }

    /**
     * Data for DE 55 if present&lt;br&gt; __Max Length: 200__
     *
     * @return de55Data
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "1.122334455667789E99", value = "Data for DE 55 if present<br> __Max Length: 200__ ")
    public String getDe55Data() {
        return de55Data;
    }

    public void setDe55Data(String de55Data) {
        this.de55Data = de55Data;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TransactEncryptedData transactEncryptedData = (TransactEncryptedData) o;
        return Objects.equals(this.accountNumber, transactEncryptedData.accountNumber) &&
                Objects.equals(this.applicationExpiryDate, transactEncryptedData.applicationExpiryDate) &&
                Objects.equals(this.panSequenceNumber, transactEncryptedData.panSequenceNumber) &&
                Objects.equals(this.track2Equivalent, transactEncryptedData.track2Equivalent) &&
                Objects.equals(this.de48se43Data, transactEncryptedData.de48se43Data) &&
                Objects.equals(this.de55Data, transactEncryptedData.de55Data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, applicationExpiryDate, panSequenceNumber, track2Equivalent, de48se43Data, de55Data);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TransactEncryptedData {");
        sb.append("    accountNumber: ").append(toIndentedString(accountNumber)).append("");
        sb.append("    applicationExpiryDate: ").append(toIndentedString(applicationExpiryDate)).append("");
        sb.append("    panSequenceNumber: ").append(toIndentedString(panSequenceNumber)).append("");
        sb.append("    track2Equivalent: ").append(toIndentedString(track2Equivalent)).append("");
        sb.append("    de48se43Data: ").append(toIndentedString(de48se43Data)).append("");
        sb.append("    de55Data: ").append(toIndentedString(de55Data)).append("");
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

