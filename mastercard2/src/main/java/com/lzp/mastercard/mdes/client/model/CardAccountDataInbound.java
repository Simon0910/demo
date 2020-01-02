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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * __(CONDITIONAL)__ &lt;br&gt; Required in Tokenize or Search Tokens unless a valid panUniqueReference, tokenUniqueReference or pushAccountReceipt is also given in FundingAccountInfo.
 */
@ApiModel(description = "__(CONDITIONAL)__ <br> Required in Tokenize or Search Tokens unless a valid panUniqueReference, tokenUniqueReference or pushAccountReceipt is also given in FundingAccountInfo. ")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2019-08-16T09:54:27.990+01:00[Europe/London]")
public class CardAccountDataInbound {
    public static final String SERIALIZED_NAME_ACCOUNT_NUMBER = "accountNumber";
    @SerializedName(SERIALIZED_NAME_ACCOUNT_NUMBER)
    private String accountNumber;

    public static final String SERIALIZED_NAME_EXPIRY_MONTH = "expiryMonth";
    @SerializedName(SERIALIZED_NAME_EXPIRY_MONTH)
    private String expiryMonth;

    public static final String SERIALIZED_NAME_EXPIRY_YEAR = "expiryYear";
    @SerializedName(SERIALIZED_NAME_EXPIRY_YEAR)
    private String expiryYear;

    public static final String SERIALIZED_NAME_SECURITY_CODE = "securityCode";
    @SerializedName(SERIALIZED_NAME_SECURITY_CODE)
    private String securityCode;

    public CardAccountDataInbound accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    /**
     * The account number of the credit or debit card.   __(CONDITIONAL)__ &lt;br&gt;required in a Tokenize, or Get Digital Asset request, unless a valid panUniqueReference or tokenUniqueReference or pushAccountReceipt was given in FundingAccountInfo.  &lt;/br&gt;&lt;/br&gt;  __Min Length:9 (See note)__&lt;/br&gt; __Max Length:19__ &lt;/br&gt; &lt;/br&gt; __NOTE__: Only 6 digits can be supplied in SearchTokens if the TokenUniqueReference is provided in fundingAccountInfo.
     *
     * @return accountNumber
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "5123456789012345", value = "The account number of the credit or debit card.   __(CONDITIONAL)__ <br>required in a Tokenize, or Get Digital Asset request, unless a valid panUniqueReference or tokenUniqueReference or pushAccountReceipt was given in FundingAccountInfo.  </br></br>  __Min Length:9 (See note)__</br> __Max Length:19__ </br> </br> __NOTE__: Only 6 digits can be supplied in SearchTokens if the TokenUniqueReference is provided in fundingAccountInfo. ")
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public CardAccountDataInbound expiryMonth(String expiryMonth) {
        this.expiryMonth = expiryMonth;
        return this;
    }

    /**
     * The expiry month for the account. Two numeric digits must be supplied.  &lt;br&gt;Only supplied in tokenization requests if panUniqueReference or tokenUniqueReferenceForPanInfo or pushAccountReceipt is not present.  __Format: MM__&lt;br&gt; __Exact Length:2__
     *
     * @return expiryMonth
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "9.0", value = "  The expiry month for the account. Two numeric digits must be supplied.  <br>Only supplied in tokenization requests if panUniqueReference or tokenUniqueReferenceForPanInfo or pushAccountReceipt is not present.  __Format: MM__<br> __Exact Length:2__ ")
    public String getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(String expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public CardAccountDataInbound expiryYear(String expiryYear) {
        this.expiryYear = expiryYear;
        return this;
    }

    /**
     * __(Required as minimum for Tokenization)__  The expiry year for the account. &lt;br&gt;Only supplied in tokenization requests if panUniqueReference or tokenUniqueReferenceForPanInfo or pushAccountReceipt is not present.   __Format: YY__ &lt;br&gt; __Exact Length:2__
     *
     * @return expiryYear
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "21", value = "__(Required as minimum for Tokenization)__  The expiry year for the account. <br>Only supplied in tokenization requests if panUniqueReference or tokenUniqueReferenceForPanInfo or pushAccountReceipt is not present.   __Format: YY__ <br> __Exact Length:2__ ")
    public String getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(String expiryYear) {
        this.expiryYear = expiryYear;
    }

    public CardAccountDataInbound securityCode(String securityCode) {
        this.securityCode = securityCode;
        return this;
    }

    /**
     * __(OPTIONAL)__ The security code for the account can optionally be provided for Tokenization. If provided, the validity will be checked. &lt;br&gt; Optional in a Tokenize request, not present otherwise. __Max Length:3__
     *
     * @return securityCode
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "123", value = "__(OPTIONAL)__ The security code for the account can optionally be provided for Tokenization. If provided, the validity will be checked. <br> Optional in a Tokenize request, not present otherwise. __Max Length:3__ ")
    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CardAccountDataInbound cardAccountDataInbound = (CardAccountDataInbound) o;
        return Objects.equals(this.accountNumber, cardAccountDataInbound.accountNumber) &&
                Objects.equals(this.expiryMonth, cardAccountDataInbound.expiryMonth) &&
                Objects.equals(this.expiryYear, cardAccountDataInbound.expiryYear) &&
                Objects.equals(this.securityCode, cardAccountDataInbound.securityCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, expiryMonth, expiryYear, securityCode);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CardAccountDataInbound {");
        sb.append("    accountNumber: ").append(toIndentedString(accountNumber)).append("");
        sb.append("    expiryMonth: ").append(toIndentedString(expiryMonth)).append("");
        sb.append("    expiryYear: ").append(toIndentedString(expiryYear)).append("");
        sb.append("    securityCode: ").append(toIndentedString(securityCode)).append("");
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

