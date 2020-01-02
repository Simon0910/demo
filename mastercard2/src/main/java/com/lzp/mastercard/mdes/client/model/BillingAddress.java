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
 * BillingAddress
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2019-08-16T09:54:27.990+01:00[Europe/London]")
public class BillingAddress {
    public static final String SERIALIZED_NAME_LINE1 = "line1";
    @SerializedName(SERIALIZED_NAME_LINE1)
    private String line1;

    public static final String SERIALIZED_NAME_LINE2 = "line2";
    @SerializedName(SERIALIZED_NAME_LINE2)
    private String line2;

    public static final String SERIALIZED_NAME_CITY = "city";
    @SerializedName(SERIALIZED_NAME_CITY)
    private String city;

    public static final String SERIALIZED_NAME_COUNTRY_SUBDIVISION = "countrySubdivision";
    @SerializedName(SERIALIZED_NAME_COUNTRY_SUBDIVISION)
    private String countrySubdivision;

    public static final String SERIALIZED_NAME_POSTAL_CODE = "postalCode";
    @SerializedName(SERIALIZED_NAME_POSTAL_CODE)
    private String postalCode;

    public static final String SERIALIZED_NAME_COUNTRY = "country";
    @SerializedName(SERIALIZED_NAME_COUNTRY)
    private String country;

    public BillingAddress line1(String line1) {
        this.line1 = line1;
        return this;
    }

    /**
     * __(OPTIONAL)__&lt;br&gt; The first line of the street address for the billing address.&lt;br&gt; __Max Length:64__
     *
     * @return line1
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "100 1st Street", value = "__(OPTIONAL)__<br> The first line of the street address for the billing address.<br> __Max Length:64__ ")
    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public BillingAddress line2(String line2) {
        this.line2 = line2;
        return this;
    }

    /**
     * __(OPTIONAL)__ The second line of the street address for the billing address.&lt;br&gt; __Max Length:64__
     *
     * @return line2
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "Apt. 4B", value = "__(OPTIONAL)__ The second line of the street address for the billing address.<br> __Max Length:64__ ")
    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public BillingAddress city(String city) {
        this.city = city;
        return this;
    }

    /**
     * __(OPTIONAL)__&lt;br&gt; The city of the billing address.&lt;br&gt; __Max Length:32__
     *
     * @return city
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "St. Louis", value = "__(OPTIONAL)__<br> The city of the billing address.<br> __Max Length:32__ ")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BillingAddress countrySubdivision(String countrySubdivision) {
        this.countrySubdivision = countrySubdivision;
        return this;
    }

    /**
     * __(OPTIONAL)__&lt;br&gt; The state or country subdivision of the billing address.&lt;br&gt; __Max Length:12__
     *
     * @return countrySubdivision
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "MO", value = "__(OPTIONAL)__<br> The state or country subdivision of the billing address.<br> __Max Length:12__ ")
    public String getCountrySubdivision() {
        return countrySubdivision;
    }

    public void setCountrySubdivision(String countrySubdivision) {
        this.countrySubdivision = countrySubdivision;
    }

    public BillingAddress postalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    /**
     * __(OPTIONAL)__&lt;br&gt; The postal of code of the billing address.&lt;br&gt; __Max Length:16__
     *
     * @return postalCode
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "61000", value = "__(OPTIONAL)__<br> The postal of code of the billing address.<br> __Max Length:16__ ")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public BillingAddress country(String country) {
        this.country = country;
        return this;
    }

    /**
     * __(OPTIONAL)__ The country of the billing address. &lt;br&gt; __Max Length:3__
     *
     * @return country
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "USA", value = "__(OPTIONAL)__ The country of the billing address. <br> __Max Length:3__ ")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BillingAddress billingAddress = (BillingAddress) o;
        return Objects.equals(this.line1, billingAddress.line1) &&
                Objects.equals(this.line2, billingAddress.line2) &&
                Objects.equals(this.city, billingAddress.city) &&
                Objects.equals(this.countrySubdivision, billingAddress.countrySubdivision) &&
                Objects.equals(this.postalCode, billingAddress.postalCode) &&
                Objects.equals(this.country, billingAddress.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(line1, line2, city, countrySubdivision, postalCode, country);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BillingAddress {");
        sb.append("    line1: ").append(toIndentedString(line1)).append("");
        sb.append("    line2: ").append(toIndentedString(line2)).append("");
        sb.append("    city: ").append(toIndentedString(city)).append("");
        sb.append("    countrySubdivision: ").append(toIndentedString(countrySubdivision)).append("");
        sb.append("    postalCode: ").append(toIndentedString(postalCode)).append("");
        sb.append("    country: ").append(toIndentedString(country)).append("");
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

