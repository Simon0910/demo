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

import java.math.BigDecimal;
import java.util.Objects;

/**
 * PhoneNumber
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2019-08-16T09:54:27.990+01:00[Europe/London]")
public class PhoneNumber {
    public static final String SERIALIZED_NAME_COUNTRY_DIAL_IN_CODE = "countryDialInCode";
    @SerializedName(SERIALIZED_NAME_COUNTRY_DIAL_IN_CODE)
    private BigDecimal countryDialInCode;

    public static final String SERIALIZED_NAME_PHONE_NUMBER = "phoneNumber";
    @SerializedName(SERIALIZED_NAME_PHONE_NUMBER)
    private BigDecimal phoneNumber;

    public PhoneNumber countryDialInCode(BigDecimal countryDialInCode) {
        this.countryDialInCode = countryDialInCode;
        return this;
    }

    /**
     * __(OPTIONAL)__ The country code for the phone number. E.g. 1 for US or 44 for UK.&lt;br&gt; __Max Length: 4__
     *
     * @return countryDialInCode
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "44.0", value = "__(OPTIONAL)__ The country code for the phone number. E.g. 1 for US or 44 for UK.<br> __Max Length: 4__ ")
    public BigDecimal getCountryDialInCode() {
        return countryDialInCode;
    }

    public void setCountryDialInCode(BigDecimal countryDialInCode) {
        this.countryDialInCode = countryDialInCode;
    }

    public PhoneNumber phoneNumber(BigDecimal phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    /**
     * __(OPTIONAL)__ The phone number of the account holder &lt;br&gt;  __Max Length: 20__
     *
     * @return phoneNumber
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "__(OPTIONAL)__ The phone number of the account holder <br>  __Max Length: 20__ ")
    public BigDecimal getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(BigDecimal phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PhoneNumber phoneNumber = (PhoneNumber) o;
        return Objects.equals(this.countryDialInCode, phoneNumber.countryDialInCode) &&
                Objects.equals(this.phoneNumber, phoneNumber.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryDialInCode, phoneNumber);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PhoneNumber {");
        sb.append("    countryDialInCode: ").append(toIndentedString(countryDialInCode)).append("");
        sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("");
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

