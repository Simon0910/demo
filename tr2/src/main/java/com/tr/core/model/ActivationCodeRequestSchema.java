package com.tr.core.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-23 15:18
 */
public class ActivationCodeRequestSchema {
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

    public static final String SERIALIZED_NAME_AUTHENTICATION_METHOD = "authenticationMethod";
    @SerializedName(SERIALIZED_NAME_AUTHENTICATION_METHOD)
    private AuthenticationMethod authenticationMethod = null;

    public ActivationCodeRequestSchema responseHost(String responseHost) {
        this.responseHost = responseHost;
        return this;
    }

    /**
     * \&quot;The host that originated the request. Future calls in the same conversation may be routed to this host.
     * Must be provided as: host[:port][/contextRoot] Where port and contextRoot are optional. If contextRoot is not
     * provided, the default (per the URL Scheme) is assumed and must be used.\&quot;
     *
     * @return responseHost
     **/
    @javax.annotation.Nullable
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

    public ActivationCodeRequestSchema requestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    /**
     * Unique identifier for the request.
     *
     * @return requestId
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "123456", value = "Unique identifier for the request. ")
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public ActivationCodeRequestSchema paymentAppInstanceId(String paymentAppInstanceId) {
        this.paymentAppInstanceId = paymentAppInstanceId;
        return this;
    }

    /**
     * Identifier for the specific Mobile Payment App instance, unique across a given Wallet Identifier.
     * This value cannot be changed after digitization.
     * This field is alphanumeric and additionally web-safe base64 characters per RFC 4648 (minus "-", underscore
     * "_") up to a maximum length of 48, = should not be URL encoded.
     *
     * @return paymentAppInstanceId
     */
    @javax.annotation.Nullable
    @ApiModelProperty(example = "123456789", value = "Identifier for the specific Mobile Payment App instance, unique" +
            " across a given Wallet Identifier. This value cannot be changed after digitization. This field is " +
            "alphanumeric and additionally web-safe base64 characters per RFC 4648 (minus \"-\", underscore \"_\") up" +
            " to a maximum length of 48, = should not be URL encoded. ")
    public String getPaymentAppInstanceId() {
        return paymentAppInstanceId;
    }

    public void setPaymentAppInstanceId(String paymentAppInstanceId) {
        this.paymentAppInstanceId = paymentAppInstanceId;
    }

    public ActivationCodeRequestSchema tokenUniqueReference(String tokenUniqueReference) {
        this.tokenUniqueReference = tokenUniqueReference;
        return this;
    }

    /**
     * Description:  A unique reference assigned following the allocation of a token used to
     * identify the token for the duration of its lifetime. Must be a valid
     * reference as assigned by MDES.
     * Data Type:  String
     * Max Length:  64
     * Required:  Yes
     *
     * @return tokenUniqueReference
     */
    @ApiModelProperty(example = "123456789", required = true, value = "A unique reference assigned following the " +
            "allocation of a token used to identify the token for the duration of its lifetime. Must be a valid " +
            "reference as assigned by MDES.")
    public String getTokenUniqueReference() {
        return tokenUniqueReference;
    }

    public void setTokenUniqueReference(String tokenUniqueReference) {
        this.tokenUniqueReference = tokenUniqueReference;
    }

    public ActivationCodeRequestSchema authenticationMethod(AuthenticationMethod authenticationMethod) {
        this.authenticationMethod = authenticationMethod;
        return this;
    }

    /**
     * Description:  Identifies the AuthenticationMethod chosen by (or on behalf of) the
     * account holder from the list of AuthenticationMethods returned by
     * MDES in the Digitize response (see Section 3.2.2.5) for this Token.
     * Data Type:  AuthenticationMethod object
     * Max Length:  N/A
     * Required:  Yes
     *
     * @return authenticationMethod
     */
    @ApiModelProperty(example = "123456789", required = true, value = "Identifies the AuthenticationMethod chosen by " +
            "(or on behalf of) the account holder from the list of AuthenticationMethods returned by MDES in the " +
            "Digitize response for this Token.")
    public AuthenticationMethod getAuthenticationMethod() {
        return authenticationMethod;
    }

    public void setAuthenticationMethod(AuthenticationMethod authenticationMethod) {
        this.authenticationMethod = authenticationMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ActivationCodeRequestSchema that = (ActivationCodeRequestSchema) o;
        return Objects.equals(responseHost, that.responseHost) &&
                Objects.equals(requestId, that.requestId) &&
                Objects.equals(paymentAppInstanceId, that.paymentAppInstanceId) &&
                Objects.equals(tokenUniqueReference, that.tokenUniqueReference) &&
                Objects.equals(authenticationMethod, that.authenticationMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseHost, requestId, paymentAppInstanceId, tokenUniqueReference, authenticationMethod);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ActivationCodeRequestSchema {");
        sb.append("    responseHost: ").append(toIndentedString(responseHost)).append("");
        sb.append("    requestId: ").append(toIndentedString(requestId)).append("");
        sb.append("    paymentAppInstanceId: ").append(toIndentedString(paymentAppInstanceId)).append("");
        sb.append("    tokenUniqueReference: ").append(toIndentedString(tokenUniqueReference)).append("");
        sb.append("    authenticationMethod: ").append(toIndentedString(authenticationMethod)).append("");
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
