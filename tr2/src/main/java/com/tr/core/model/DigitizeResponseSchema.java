package com.tr.core.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author liuzhipeng
 * @description DigitizeResponseSchema
 * @create 2019-10-21 14:58
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2019-10-21T21:18:23.871+01:00[Europe/London]")
public class DigitizeResponseSchema {
    public static final String SERIALIZED_NAME_RESPONSE_HOST = "responseHost";
    @SerializedName(SERIALIZED_NAME_RESPONSE_HOST)
    private String responseHost;

    public static final String SERIALIZED_NAME_RESPONSE_ID = "responseId";
    @SerializedName(SERIALIZED_NAME_RESPONSE_ID)
    private String responseId;

    public static final String SERIALIZED_NAME_DECISION = "decision";
    @SerializedName(SERIALIZED_NAME_DECISION)
    private String decision;

    public static final String SERIALIZED_NAME_AUTHENTICATION_METHODS = "authenticationMethods";
    @SerializedName(SERIALIZED_NAME_AUTHENTICATION_METHODS)
    private List<AuthenticationMethod> authenticationMethods = new ArrayList<AuthenticationMethod>();

    public static final String SERIALIZED_NAME_TOKEN_UNIQUE_REFERENCE = "tokenUniqueReference";
    @SerializedName(SERIALIZED_NAME_TOKEN_UNIQUE_REFERENCE)
    private String tokenUniqueReference;

    public static final String SERIALIZED_NAME_PAN_UNIQUE_REFERENCE = "panUniqueReference";
    @SerializedName(SERIALIZED_NAME_PAN_UNIQUE_REFERENCE)
    private String panUniqueReference;

    public static final String SERIALIZED_NAME_PRODUCT_CONFIG = "productConfig";
    @SerializedName(SERIALIZED_NAME_PRODUCT_CONFIG)
    private ProductConfig productConfig = null;

    public static final String SERIALIZED_NAME_TOKEN_INFO = "tokenInfo";
    @SerializedName(SERIALIZED_NAME_TOKEN_INFO)
    private TokenInfo tokenInfo = null;

    public static final String SERIALIZED_NAME_TDS_REGISTRATION_URL = "tdsRegistrationUrl";
    @SerializedName(SERIALIZED_NAME_TDS_REGISTRATION_URL)
    private String tdsRegistrationUrl;

    public static final String SERIALIZED_NAME_ENCRYPTED_PAYLOAD = "encryptedPayload";
    @SerializedName(SERIALIZED_NAME_ENCRYPTED_PAYLOAD)
    private EncryptedPayload encryptedPayload = null;

    public static final String SERIALIZED_NAME_ERRORS = "errors";
    @SerializedName(SERIALIZED_NAME_ERRORS)
    private List<Error> errors = new ArrayList<Error>();


    /**
     * The MasterCard host that originated the request. Future calls in the same conversation may be routed to this host.
     *
     * @return responseHost
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "site2.payment-app-provider.com", value = "The MasterCard host that originated the request. Future calls in the same conversation may be routed to this host.  ")
    public String getResponseHost() {
        return responseHost;
    }

    public void setResponseHost(String responseHost) {
        this.responseHost = responseHost;
    }

    public DigitizeResponseSchema responseId(String responseId) {
        this.responseId = responseId;
        return this;
    }

    /**
     * Unique identifier for the response.
     *
     * @return responseId
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "123456", value = "Unique identifier for the response. ")
    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public DigitizeResponseSchema decision(String decision) {
        this.decision = decision;
        return this;
    }

    /**
     * The tokenization decision for this digitization request. Must be either APPROVED (Digitization request was approved), DECLINED (Digitization request was declined) OR REQUIRE_ADDITIONAL_AUTHENTICATION Digitization request was approved but optionally requires additional authentication. One or more Authentication methods may be provided).
     *
     * @return decision
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "APPROVED", value = "The tokenization decision for this digitization request. Must be either APPROVED (Digitization request was approved), DECLINED (Digitization request was declined) OR REQUIRE_ADDITIONAL_AUTHENTICATION Digitization request was approved but optionally requires additional authentication. One or more Authentication methods may be provided). ")
    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public DigitizeResponseSchema authenticationMethods(List<AuthenticationMethod> authenticationMethods) {
        this.authenticationMethods = authenticationMethods;
        return this;
    }

    public DigitizeResponseSchema addAuthenticationMethodsItem(AuthenticationMethod authenticationMethodsItem) {
        if (this.authenticationMethods == null) {
            this.authenticationMethods = new ArrayList<AuthenticationMethod>();
        }
        this.authenticationMethods.add(authenticationMethodsItem);
        return this;
    }

    /**
     * Get authenticationMethods
     *
     * @return authenticationMethods
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "")
    public List<AuthenticationMethod> getAuthenticationMethods() {
        return authenticationMethods;
    }

    public void setAuthenticationMethods(List<AuthenticationMethod> authenticationMethods) {
        this.authenticationMethods = authenticationMethods;
    }

    public DigitizeResponseSchema tokenUniqueReference(String tokenUniqueReference) {
        this.tokenUniqueReference = tokenUniqueReference;
        return this;
    }

    /**
     * The unique reference allocated to the new Token. Serves as a unique identifier for all subsequent queries or management functions relating to this Token. Provided if the decision was APPROVED or REQUIRE_ADDITIONAL_AUTHENTICATION.    __Max Length:64__
     *
     * @return tokenUniqueReference
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "DWSPMC000000000132d72d4fcb2f4136a0532d3093ff1a45", value = "The unique reference allocated to the new Token. Serves as a unique identifier for all subsequent queries or management functions relating to this Token. Provided if the decision was APPROVED or REQUIRE_ADDITIONAL_AUTHENTICATION.    __Max Length:64__ ")
    public String getTokenUniqueReference() {
        return tokenUniqueReference;
    }

    public void setTokenUniqueReference(String tokenUniqueReference) {
        this.tokenUniqueReference = tokenUniqueReference;
    }

    public DigitizeResponseSchema panUniqueReference(String panUniqueReference) {
        this.panUniqueReference = panUniqueReference;
        return this;
    }

    /**
     * The unique reference allocated to the Account Primary Account Number. Provided if the decision was APPROVED or REQUIRE_ADDITIONAL_AUTHENTICATION.  __Max Length:64__
     *
     * @return panUniqueReference
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "FWSPMC000000000159f71f703d2141efaf04dd26803f922b", value = "The unique reference allocated to the Account Primary Account Number. Provided if the decision was APPROVED or REQUIRE_ADDITIONAL_AUTHENTICATION.  __Max Length:64__ ")
    public String getPanUniqueReference() {
        return panUniqueReference;
    }

    public void setPanUniqueReference(String panUniqueReference) {
        this.panUniqueReference = panUniqueReference;
    }

    public DigitizeResponseSchema productConfig(ProductConfig productConfig) {
        this.productConfig = productConfig;
        return this;
    }

    /**
     * Get productConfig
     *
     * @return productConfig
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "")
    public ProductConfig getProductConfig() {
        return productConfig;
    }

    public void setProductConfig(ProductConfig productConfig) {
        this.productConfig = productConfig;
    }

    public DigitizeResponseSchema tokenInfo(TokenInfo tokenInfo) {
        this.tokenInfo = tokenInfo;
        return this;
    }

    /**
     * Get tokenInfo
     *
     * @return tokenInfo
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "")
    public TokenInfo getTokenInfo() {
        return tokenInfo;
    }

    public void setTokenInfo(TokenInfo tokenInfo) {
        this.tokenInfo = tokenInfo;
    }

    public DigitizeResponseSchema tdsRegistrationUrl(String tdsRegistrationUrl) {
        this.tdsRegistrationUrl = tdsRegistrationUrl;
        return this;
    }

    /**
     * The URL endpoint for the Transaction Details Service.
     * Must be provided as:
     * host[:port][/contextRoot]
     * Where port and contextRoot are optional.
     * If contextRoot is not provided, the default (per the URL Scheme) is assumed and must be used.
     *
     * @return tdsRegistrationUrl
     */
    @javax.annotation.Nullable
    @ApiModelProperty(value = "The URL endpoint for the Transaction Details Service. Must be provided as: host[:port][/contextRoot] Where port and contextRoot are optional. If contextRoot is not provided, the default (per the URL Scheme) is assumed and must be used. ")
    public String getTdsRegistrationUrl() {
        return tdsRegistrationUrl;
    }

    public void setTdsRegistrationUrl(String tdsRegistrationUrl) {
        this.tdsRegistrationUrl = tdsRegistrationUrl;
    }

    public DigitizeResponseSchema encryptedPayload(EncryptedPayload encryptedPayload) {
        this.encryptedPayload = encryptedPayload;
        return this;
    }

    /**
     * Get encryptedPayload
     *
     * @return encryptedPayload
     **/
    @ApiModelProperty(required = true, value = "")
    public EncryptedPayload getEncryptedPayload() {
        return encryptedPayload;
    }

    public void setEncryptedPayload(EncryptedPayload encryptedPayload) {
        this.encryptedPayload = encryptedPayload;
    }

    public DigitizeResponseSchema errors(List<Error> errors) {
        this.errors = errors;
        return this;
    }

    public DigitizeResponseSchema addErrorsItem(Error errorsItem) {
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
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        DigitizeResponseSchema that = (DigitizeResponseSchema) o;
        return Objects.equals(responseHost, that.responseHost) &&
                Objects.equals(responseId, that.responseId) &&
                Objects.equals(decision, that.decision) &&
                Objects.equals(authenticationMethods, that.authenticationMethods) &&
                Objects.equals(tokenUniqueReference, that.tokenUniqueReference) &&
                Objects.equals(panUniqueReference, that.panUniqueReference) &&
                Objects.equals(productConfig, that.productConfig) &&
                Objects.equals(tokenInfo, that.tokenInfo) &&
                Objects.equals(tdsRegistrationUrl, that.tdsRegistrationUrl) &&
                Objects.equals(encryptedPayload, that.encryptedPayload) &&
                Objects.equals(this.errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseHost, responseId, decision, authenticationMethods, tokenUniqueReference, panUniqueReference, productConfig, tokenInfo, tdsRegistrationUrl, encryptedPayload, errors);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DigitizeResponseSchema {");
        sb.append("    responseHost: ").append(toIndentedString(responseHost)).append("");
        sb.append("    responseId: ").append(toIndentedString(responseId)).append("");
        sb.append("    decision: ").append(toIndentedString(decision)).append("");
        sb.append("    authenticationMethods: ").append(toIndentedString(authenticationMethods)).append("");
        sb.append("    tokenUniqueReference: ").append(toIndentedString(tokenUniqueReference)).append("");
        sb.append("    panUniqueReference: ").append(toIndentedString(panUniqueReference)).append("");
        sb.append("    productConfig: ").append(toIndentedString(productConfig)).append("");
        sb.append("    tokenInfo: ").append(toIndentedString(tokenInfo)).append("");
        sb.append("    tdsRegistrationUrl: ").append(toIndentedString(tdsRegistrationUrl)).append("");
        sb.append("    encryptedPayload: ").append(toIndentedString(encryptedPayload)).append("");
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
