/*
 * MDES for Merchants
 * The MDES APIs are designed as RPC style stateless web services where each API endpoint represents an operation to
 * be performed.  All request and response payloads are sent in the JSON (JavaScript Object Notation)
 * data-interchange format. Each endpoint in the API specifies the HTTP Method used to access it. All strings in
 * request and response objects are to be UTF-8 encoded.  Each API URI includes the major and minor version of API
 * that it conforms to.  This will allow multiple concurrent versions of the API to be deployed simultaneously. <br>
 __Authentication__ Mastercard uses OAuth 1.0a with body hash extension for authenticating the API clients. This
 * requires every request that you send to  Mastercard to be signed with an RSA private key. A private-public RSA key
 *  pair must be generated consisting of: <br> 1 . A private key for the OAuth signature for API requests. It is
 * recommended to keep the private key in a password-protected or hardware keystore. <br> 2. A public key is shared
 * with Mastercard during the project setup process through either a certificate signing request (CSR) or the API Key
 *  Generator. Mastercard will use the public key to verify the OAuth signature that is provided on every API call
 * .<br>  An OAUTH1.0a signer library is available on [GitHub](https://github.com/Mastercard/oauth1-signer-java) <br>
   __Encryption__<br>  All communications between Issuer web service and the Mastercard gateway is encrypted using
   * TLS. <br> __Additional Encryption of Sensitive Data__ In addition to the OAuth authentication, when using MDES
   * Digital Enablement Service, any PCI sensitive and all account holder Personally Identifiable Information (PII)
   * data must be encrypted. This requirement applies to the API fields containing encryptedData. Sensitive data is
   * encrypted using a symmetric session (one-time-use) key. The symmetric session key is then wrapped with an RSA
   * Public Key supplied by Mastercard during API setup phase (the Customer Encryption Key). <br>  Java Client
   * Encryption Library available on [GitHub](https://github.com/Mastercard/client-encryption-java)
 *
 * The version of the OpenAPI document: 1.2.9
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.example.mvc.rest.mc;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * GetTokenResponseSchema
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2019-08-16T09:54" +
        ":27.990+01:00[Europe/London]")
public class GetTokenResponseSchema extends BaseResponseSchema {

    public static final String SERIALIZED_NAME_TOKEN = "token";
    @SerializedName(SERIALIZED_NAME_TOKEN)
    private Token token = null;

    public static final String SERIALIZED_NAME_TOKEN_DETAIL = "tokenDetail";
    @SerializedName(SERIALIZED_NAME_TOKEN_DETAIL)
    private TokenDetail tokenDetail = null;


    public GetTokenResponseSchema responseId(String responseId) {
        this.responseId = responseId;
        return this;
    }

    public GetTokenResponseSchema token(Token token) {
        this.token = token;
        return this;
    }

    /**
     * Get token
     *
     * @return token
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "")
    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public GetTokenResponseSchema tokenDetail(TokenDetail tokenDetail) {
        this.tokenDetail = tokenDetail;
        return this;
    }

    /**
     * Get tokenDetail
     *
     * @return tokenDetail
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "")
    public TokenDetail getTokenDetail() {
        return tokenDetail;
    }

    public void setTokenDetail(TokenDetail tokenDetail) {
        this.tokenDetail = tokenDetail;
    }

    public GetTokenResponseSchema errors(List<Error> errors) {
        this.errors = errors;
        return this;
    }

    public GetTokenResponseSchema addErrorsItem(Error errorsItem) {
        if (this.errors == null) {
            this.errors = new ArrayList<Error>();
        }
        this.errors.add(errorsItem);
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetTokenResponseSchema getTokenResponseSchema = (GetTokenResponseSchema) o;
        return Objects.equals(this.responseId, getTokenResponseSchema.responseId) &&
                Objects.equals(this.token, getTokenResponseSchema.token) &&
                Objects.equals(this.tokenDetail, getTokenResponseSchema.tokenDetail) &&
                Objects.equals(this.errors, getTokenResponseSchema.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseId, token, tokenDetail, errors);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetTokenResponseSchema {");
        sb.append("    responseId: ").append(toIndentedString(responseId)).append("");
        sb.append("    token: ").append(toIndentedString(token)).append("");
        sb.append("    tokenDetail: ").append(toIndentedString(tokenDetail)).append("");
        sb.append("    errors: ").append(toIndentedString(errors)).append("");
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

