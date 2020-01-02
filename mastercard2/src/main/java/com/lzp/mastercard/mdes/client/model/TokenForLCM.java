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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * TokenForLCM
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2019-08-15T21:18:23.871+01:00[Europe/London]")
public class TokenForLCM {
    public static final String SERIALIZED_NAME_TOKEN_UNIQUE_REFERENCE = "tokenUniqueReference";
    @SerializedName(SERIALIZED_NAME_TOKEN_UNIQUE_REFERENCE)
    private String tokenUniqueReference;

    public static final String SERIALIZED_NAME_STATUS = "status";
    @SerializedName(SERIALIZED_NAME_STATUS)
    private String status;

    public static final String SERIALIZED_NAME_SUSPENDED_BY = "suspendedBy";
    @SerializedName(SERIALIZED_NAME_SUSPENDED_BY)
    private List<String> suspendedBy = new ArrayList<String>();

    public static final String SERIALIZED_NAME_STATUS_TIMESTAMP = "statusTimestamp";
    @SerializedName(SERIALIZED_NAME_STATUS_TIMESTAMP)
    private String statusTimestamp;

    public static final String SERIALIZED_NAME_ERROR_CODE = "errorCode";
    @SerializedName(SERIALIZED_NAME_ERROR_CODE)
    private String errorCode;

    public static final String SERIALIZED_NAME_ERROR_DESCRIPTION = "errorDescription";
    @SerializedName(SERIALIZED_NAME_ERROR_DESCRIPTION)
    private String errorDescription;

    public static final String SERIALIZED_NAME_ERRORS = "errors";
    @SerializedName(SERIALIZED_NAME_ERRORS)
    private List<Error> errors = new ArrayList<Error>();

    public TokenForLCM tokenUniqueReference(String tokenUniqueReference) {
        this.tokenUniqueReference = tokenUniqueReference;
        return this;
    }

    /**
     * The unique reference allocated to the Token which is always present even if an error occurs. &lt;br&gt;      __Max Length:64__
     *
     * @return tokenUniqueReference
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "DWSPMC000000000132d72d4fcb2f4136a0532d3093ff1a45", value = "The unique reference allocated to the Token which is always present even if an error occurs. <br>      __Max Length:64__ ")
    public String getTokenUniqueReference() {
        return tokenUniqueReference;
    }

    public void setTokenUniqueReference(String tokenUniqueReference) {
        this.tokenUniqueReference = tokenUniqueReference;
    }

    public TokenForLCM status(String status) {
        this.status = status;
        return this;
    }

    /**
     * The current status of Token. Must be either:    * &#39;INACTIVE&#39; (Token has not yet been activated)  * &#39;ACTIVE&#39; (Token is active and ready to transact)  * &#39;SUSPENDED&#39; (Token is suspended and unable to transact)  * &#39;DEACTIVATED&#39; (Token has been permanently deactivated).&lt;br&gt;      __Max Length:32__
     *
     * @return status
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "SUSPENDED", value = "The current status of Token. Must be either:    * 'INACTIVE' (Token has not yet been activated)  * 'ACTIVE' (Token is active and ready to transact)  * 'SUSPENDED' (Token is suspended and unable to transact)  * 'DEACTIVATED' (Token has been permanently deactivated).<br>      __Max Length:32__ ")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TokenForLCM suspendedBy(List<String> suspendedBy) {
        this.suspendedBy = suspendedBy;
        return this;
    }

    public TokenForLCM addSuspendedByItem(String suspendedByItem) {
        if (this.suspendedBy == null) {
            this.suspendedBy = new ArrayList<String>();
        }
        this.suspendedBy.add(suspendedByItem);
        return this;
    }

    /**
     * (CONDITIONAL only supplied if status is SUSPENDED) Who or what caused the Token to be suspended One or more values of:     * ISSUER - Suspended by the Issuer.    * TOKEN_REQUESTOR - Suspended by the Token Requestor     * MOBILE_PIN_LOCKED - Suspended due to the Mobile PIN being locked    * CARDHOLDER - Suspended by the Cardholder &lt;br&gt;          __Max Length__: N/A
     *
     * @return suspendedBy
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "(CONDITIONAL only supplied if status is SUSPENDED) Who or what caused the Token to be suspended One or more values of:     * ISSUER - Suspended by the Issuer.    * TOKEN_REQUESTOR - Suspended by the Token Requestor     * MOBILE_PIN_LOCKED - Suspended due to the Mobile PIN being locked    * CARDHOLDER - Suspended by the Cardholder <br>          __Max Length__: N/A     ")
    public List<String> getSuspendedBy() {
        return suspendedBy;
    }

    public void setSuspendedBy(List<String> suspendedBy) {
        this.suspendedBy = suspendedBy;
    }

    public TokenForLCM statusTimestamp(String statusTimestamp) {
        this.statusTimestamp = statusTimestamp;
        return this;
    }

    /**
     * The date and time the token status was last updated. Expressed in ISO 8601 extended format as one of the following:     * YYYY-MM-DDThh:mm:ss[.sss]Z    * YYYY-MM-DDThh:mm:ss[.sss]±hh:mm    * Where [.sss] is optional and can be 1 to 3 digits. &lt;br&gt;  __Max Length:29__
     *
     * @return statusTimestamp
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "The date and time the token status was last updated. Expressed in ISO 8601 extended format as one of the following:     * YYYY-MM-DDThh:mm:ss[.sss]Z    * YYYY-MM-DDThh:mm:ss[.sss]±hh:mm    * Where [.sss] is optional and can be 1 to 3 digits. <br>  __Max Length:29__   ")
    public String getStatusTimestamp() {
        return statusTimestamp;
    }

    public void setStatusTimestamp(String statusTimestamp) {
        this.statusTimestamp = statusTimestamp;
    }

    public TokenForLCM errorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    /**
     * __CONDITIONAL__&lt;br&gt; Returned in the event of an error and contains the reason the operation failed. Only use if errors object is not present.&lt;br&gt; __Max Length: 32__
     *
     * @return errorCode
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "__CONDITIONAL__<br> Returned in the event of an error and contains the reason the operation failed. Only use if errors object is not present.<br> __Max Length: 32__ ")
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public TokenForLCM errorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
        return this;
    }

    /**
     * __CONDITIONAL__&lt;br&gt; Returned in the event of an error and contains a description of why the operation failed. Only use if errors object is not present.&lt;br&gt; __Max Length: 32__
     *
     * @return errorDescription
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "__CONDITIONAL__<br> Returned in the event of an error and contains a description of why the operation failed. Only use if errors object is not present.<br> __Max Length: 32__   ")
    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public TokenForLCM errors(List<Error> errors) {
        this.errors = errors;
        return this;
    }

    public TokenForLCM addErrorsItem(Error errorsItem) {
        if (this.errors == null) {
            this.errors = new ArrayList<Error>();
        }
        this.errors.add(errorsItem);
        return this;
    }

    /**
     * Get errors
     *
     * @return errors
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "")
    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TokenForLCM tokenForLCM = (TokenForLCM) o;
        return Objects.equals(this.tokenUniqueReference, tokenForLCM.tokenUniqueReference) &&
                Objects.equals(this.status, tokenForLCM.status) &&
                Objects.equals(this.suspendedBy, tokenForLCM.suspendedBy) &&
                Objects.equals(this.statusTimestamp, tokenForLCM.statusTimestamp) &&
                Objects.equals(this.errorCode, tokenForLCM.errorCode) &&
                Objects.equals(this.errorDescription, tokenForLCM.errorDescription) &&
                Objects.equals(this.errors, tokenForLCM.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokenUniqueReference, status, suspendedBy, statusTimestamp, errorCode, errorDescription, errors);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TokenForLCM {");
        sb.append("    tokenUniqueReference: ").append(toIndentedString(tokenUniqueReference)).append("");
        sb.append("    status: ").append(toIndentedString(status)).append("");
        sb.append("    suspendedBy: ").append(toIndentedString(suspendedBy)).append("");
        sb.append("    statusTimestamp: ").append(toIndentedString(statusTimestamp)).append("");
        sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("");
        sb.append("    errorDescription: ").append(toIndentedString(errorDescription)).append("");
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

