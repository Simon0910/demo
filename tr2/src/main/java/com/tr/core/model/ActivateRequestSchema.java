package com.tr.core.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-24 10:47
 */
public class ActivateRequestSchema {
    public static final String SERIALIZED_NAME_RESPONSE_HOST = "responseHost";
    @SerializedName(SERIALIZED_NAME_RESPONSE_HOST)
    private String responseHost;

    public static final String SERIALIZED_NAME_REQUEST_ID = "requestId";
    @SerializedName(SERIALIZED_NAME_REQUEST_ID)
    private String requestId;

    public static final String SERIALIZED_NAME_PAYMENT_APP_INSTANCE_ID = "paymentAppInstanceId";
    @SerializedName(SERIALIZED_NAME_PAYMENT_APP_INSTANCE_ID)
    private String paymentAppInstanceId;

    public static final String SERIALIZED_NAME_TOKEN_UNIQUE_REFERENCE = "tokenUniqueReference";
    @SerializedName(SERIALIZED_NAME_TOKEN_UNIQUE_REFERENCE)
    private String tokenUniqueReference;

    public static final String SERIALIZED_NAME_AUTHENTICATION_CODE = "authenticationCode";
    @SerializedName(SERIALIZED_NAME_AUTHENTICATION_CODE)
    private String authenticationCode;

    public static final String SERIALIZED_NAME_TOKENIZATION_AUTHENTICATION_VALUE = "tokenizationAuthenticationValue";
    @SerializedName(SERIALIZED_NAME_TOKENIZATION_AUTHENTICATION_VALUE)
    private String tokenizationAuthenticationValue;

    public ActivateRequestSchema responseHost(java.lang.String responseHost) {
        this.responseHost = responseHost;
        return this;
    }

    /**
     * @return responseHost
     */
    @Nullable
    @ApiModelProperty(example = "site1.your-server.com", value = "\"The host that originated the request. Future " +
            "calls in the same conversation may be routed to this host. Must be provided as: " +
            "host[:port][/contextRoot] Where port and contextRoot are optional. If contextRoot is not provided, the " +
            "default (per the URL Scheme) is assumed and must be used.\" ")
    public String getResponseHost() {
        return responseHost;
    }

    public void setResponseHost(String responseHost) {
        this.responseHost = responseHost;
    }

    public ActivateRequestSchema requestId(java.lang.String requestId) {
        this.requestId = requestId;
        return this;
    }

    /**
     * @return requestId
     */
    @Nullable
    @ApiModelProperty(example = "123456", value = "Unique identifier for the request. ")
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public ActivateRequestSchema paymentAppInstanceId(java.lang.String paymentAppInstanceId) {
        this.paymentAppInstanceId = paymentAppInstanceId;
        return this;
    }

    /**
     * Description:  Identifier for the specific Mobile Payment App instance, unique across a
     * given Wallet Identifier. This value cannot be changed after digitization.
     * This field is alphanumeric and additionally web-safe base64 characters
     * per RFC 4648 (minus "-", underscore "_") up to a maximum length of
     * 48, = should not be URL encoded.
     * Data Type:  String
     * Max Length:  48
     * Required:  Conditional – not applicable for server-based tokens. Required
     * otherwise.
     *
     * @return paymentAppInstanceId
     */
    @Nullable
    @ApiModelProperty(example = "example", value = "value")
    public String getPaymentAppInstanceId() {
        return paymentAppInstanceId;
    }

    public void setPaymentAppInstanceId(String paymentAppInstanceId) {
        this.paymentAppInstanceId = paymentAppInstanceId;
    }

    public ActivateRequestSchema tokenUniqueReference(java.lang.String tokenUniqueReference) {
        this.tokenUniqueReference = tokenUniqueReference;
        return this;
    }

    /**
     * Description:  A unique reference assigned following the allocation of a token used to
     * identify the token for the duration of its lifetime. Must be a valid
     * reference as assigned by MDES.
     * Data Type:  String
     * Max Length:  64
     * Required:  Yes.
     *
     * @return tokenUniqueReference
     */
    @ApiModelProperty(example = "DWSPMC000000000132d72d4fcb2f4136a0532d3093ff1a45", required = true, value = "A " +
            "unique reference assigned following the allocation of a token used to identify the token for the " +
            "duration of its lifetime. Must be a valid reference as assigned by MDES. ")
    public String getTokenUniqueReference() {
        return tokenUniqueReference;
    }

    public void setTokenUniqueReference(String tokenUniqueReference) {
        this.tokenUniqueReference = tokenUniqueReference;
    }

    public ActivateRequestSchema authenticationCode(java.lang.String authenticationCode) {
        this.authenticationCode =
                authenticationCode;
        return this;
    }

    /**
     * Description:  The Authentication Code as entered by the account holder (or entered
     * by other means) to activate the Token.
     * Data Type:  String
     * Max Length:  32
     * Required:  Conditional – one of either the ‘authenticationCode’ or the
     * ‘tokenizationAuthenticationValue’ is required.
     *
     * @return authenticationCode
     */
    @ApiModelProperty(example = "A1B2C3D4", value = "The Authentication Code as entered by the account holder (or " +
            "entered by other means) to activate the Token.")
    public String getAuthenticationCode() {
        return authenticationCode;
    }

    public void setAuthenticationCode(String authenticationCode) {
        this.authenticationCode = authenticationCode;
    }

    public ActivateRequestSchema tokenizationAuthenticationValue(java.lang.String tokenizationAuthenticationValue) {
        this.tokenizationAuthenticationValue = tokenizationAuthenticationValue;
        return this;
    }

    /**
     * Description:  The Tokenization Authentication Value (TAV) as cryptographically
     * signed by the Issuer to activate this Token.
     * Data Type:  String
     * Max Length:  2048
     * Required:  Conditional – one of either the ‘authenticationCode’ or the
     * ‘tokenizationAuthenticationValue’ is required.
     *
     * @return tokenizationAuthenticationValue
     */
    @Nullable
    @ApiModelProperty(example = "example", value = "The Tokenization Authentication Value (TAV) as cryptographically " +
            "signed by the Issuer to activate this Token.")
    public String getTokenizationAuthenticationValue() {
        return tokenizationAuthenticationValue;
    }

    public void setTokenizationAuthenticationValue(String tokenizationAuthenticationValue) {
        this.tokenizationAuthenticationValue = tokenizationAuthenticationValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        ActivateRequestSchema that = (ActivateRequestSchema) o;
        return Objects.equals(responseHost, that.responseHost) &&
                Objects.equals(requestId, that.requestId) &&
                Objects.equals(paymentAppInstanceId, that.paymentAppInstanceId) &&
                Objects.equals(tokenUniqueReference, that.tokenUniqueReference) &&
                Objects.equals(authenticationCode, that.authenticationCode) &&
                Objects.equals(tokenizationAuthenticationValue, that.tokenizationAuthenticationValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseHost, requestId, paymentAppInstanceId, tokenUniqueReference, authenticationCode,
                tokenizationAuthenticationValue);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ActivateRequestSchema {");
        sb.append("    responseHost :").append(toIndentedString(responseHost)).append("");
        sb.append("    requestId :").append(toIndentedString(requestId)).append("");
        sb.append("    paymentAppInstanceId :").append(toIndentedString(paymentAppInstanceId)).append("");
        sb.append("    tokenUniqueReference :").append(toIndentedString(tokenUniqueReference)).append("");
        sb.append("    authenticationCode :").append(toIndentedString(authenticationCode)).append("");
        sb.append("    tokenizationAuthenticationValue :").append(toIndentedString(tokenizationAuthenticationValue)).append("");
        sb.append('}');
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
