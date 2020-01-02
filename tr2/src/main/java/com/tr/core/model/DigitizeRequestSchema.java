package com.tr.core.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * @author liuzhipeng
 * @description DigitizeRequestSchema
 * @create 2019-10-21 11:44
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2019-10-21T21:18:23.871+01:00[Europe/London]")
public class DigitizeRequestSchema {
    public static final String SERIALIZED_NAME_RESPONSE_HOST = "responseHost";
    @SerializedName(SERIALIZED_NAME_RESPONSE_HOST)
    private String responseHost;

    public static final String SERIALIZED_NAME_REQUEST_ID = "requestId";
    @SerializedName(SERIALIZED_NAME_REQUEST_ID)
    private String requestId;

    public static final String SERIALIZED_NAME_PAYMENT_APP_INSTANCE_ID = "paymentAppInstanceId";
    @SerializedName(SERIALIZED_NAME_PAYMENT_APP_INSTANCE_ID)
    private String paymentAppInstanceId;

    public static final String SERIALIZED_NAME_ELIGIBILITY_RECEIPT = "eligibilityReceipt";
    @SerializedName(SERIALIZED_NAME_ELIGIBILITY_RECEIPT)
    private EligibilityReceipt eligibilityReceipt;

    public static final String SERIALIZED_NAME_TASK_ID = "taskId";
    @SerializedName(SERIALIZED_NAME_TASK_ID)
    private String taskId;

    public static final String SERIALIZED_NAME_TERMS_AND_CONDITIONS_ASSET_ID = "termsAndConditionsAssetId";
    @SerializedName(SERIALIZED_NAME_TERMS_AND_CONDITIONS_ASSET_ID)
    private String termsAndConditionsAssetId;

    public static final String SERIALIZED_NAME_TERMS_AND_CONDITIONS_ACCEPTED_TIMESTAMP = "termsAndConditionsAcceptedTimestamp";
    @SerializedName(SERIALIZED_NAME_TERMS_AND_CONDITIONS_ACCEPTED_TIMESTAMP)
    private String termsAndConditionsAcceptedTimestamp;

    public static final String SERIALIZED_NAME_FUNDING_ACCOUNT_INFO = "fundingAccountInfo";
    @SerializedName(SERIALIZED_NAME_FUNDING_ACCOUNT_INFO)
    private FundingAccountInfo fundingAccountInfo = null;

    public static final String SERIALIZED_NAME_TOKENIZATION_AUTHENTICATION_VALUE = "tokenizationAuthenticationValue";
    @SerializedName(SERIALIZED_NAME_TOKENIZATION_AUTHENTICATION_VALUE)
    private String tokenizationAuthenticationValue;

    public static final String SERIALIZED_NAME_DECISIONING_DATA = "decisioningData";
    @SerializedName(SERIALIZED_NAME_DECISIONING_DATA)
    private DecisioningData decisioningData = null;


    public DigitizeRequestSchema responseHost(String responseHost) {
        this.responseHost = responseHost;
        return this;
    }

    /**
     * \&quot;The host that originated the request. Future calls in the same conversation may be routed to this host. Must be provided as: host[:port][/contextRoot] Where port and contextRoot are optional. If contextRoot is not provided, the default (per the URL Scheme) is assumed and must be used.\&quot;
     *
     * @return responseHost
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "site1.your-server.com", value = "\"The host that originated the request. Future calls in the same conversation may be routed to this host. Must be provided as: host[:port][/contextRoot] Where port and contextRoot are optional. If contextRoot is not provided, the default (per the URL Scheme) is assumed and must be used.\" ")
    public String getResponseHost() {
        return responseHost;
    }

    public void setResponseHost(String responseHost) {
        this.responseHost = responseHost;
    }

    public DigitizeRequestSchema requestId(String requestId) {
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


    public DigitizeRequestSchema paymentAppInstanceId(String paymentAppInstanceId) {
        this.paymentAppInstanceId = paymentAppInstanceId;
        return this;
    }

    /**
     * Identifier for the specific Mobile Payment App instance, unique across a given Wallet Identifier.
     * This value cannot be changed after digitization.
     * This field is alphanumeric and additionally web-safe base64 characters per RFC 4648 (minus "-", underscore "_") up to a maximum length of 48, = should not be URL encoded.
     *
     * @return paymentAppInstanceId
     */
    @javax.annotation.Nullable
    @ApiModelProperty(example = "123456789", value = "Identifier for the specific Mobile Payment App instance, unique across a given Wallet Identifier. This value cannot be changed after digitization. This field is alphanumeric and additionally web-safe base64 characters per RFC 4648 (minus \"-\", underscore \"_\") up to a maximum length of 48, = should not be URL encoded. ")
    public String getPaymentAppInstanceId() {
        return paymentAppInstanceId;
    }

    public void setPaymentAppInstanceId(String paymentAppInstanceId) {
        this.paymentAppInstanceId = paymentAppInstanceId;
    }

    public DigitizeRequestSchema eligibilityReceipt(EligibilityReceipt eligibilityReceipt) {
        this.eligibilityReceipt = eligibilityReceipt;
        return this;
    }

    /**
     * Identifier for the specific Mobile Payment App instance,
     * unique across a given Wallet Identifier. This value cannot be changed after digitization.
     * This field is alphanumeric and additionally web-safe base64 characters per RFC 4648 (minus "-", underscore "_") up to a maximum length of 48, = should not be URL encoded.
     * <p>
     * Get EligibilityReceipt
     *
     * @return eligibilityReceipt
     */
    @ApiModelProperty(required = true, value = "")
    public EligibilityReceipt getEligibilityReceipt() {
        return eligibilityReceipt;
    }

    public void setEligibilityReceipt(EligibilityReceipt eligibilityReceipt) {
        this.eligibilityReceipt = eligibilityReceipt;
    }

    public DigitizeRequestSchema taskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    /**
     * Identifier for this task as assigned by the Wallet Provider, unique across a given Wallet Identifier.
     * May be used in the Get Task Status API (see Section 3.2.11) to query the status of this task.
     *
     * @return taskId
     */
    @ApiModelProperty(example = "123456", value = "Identifier for this task as assigned by the Wallet Provider, unique across a given Wallet Identifier. May be used in the Get Task Status API (see Section 3.2.11) to query the status of this task.")
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public DigitizeRequestSchema termsAndConditionsAssetId(String termsAndConditionsAssetId) {
        this.termsAndConditionsAssetId = termsAndConditionsAssetId;
        return this;
    }

    /**
     * The Terms and Conditions as presented to and accepted by the account holder. Must be a valid Asset ID.
     *
     * @return termsAndConditionsAssetId
     */
    @ApiModelProperty(example = "123456", value = "The Terms and Conditions as presented to and accepted by the account holder. Must be a valid Asset ID. ")
    public String getTermsAndConditionsAssetId() {
        return termsAndConditionsAssetId;
    }

    public void setTermsAndConditionsAssetId(String termsAndConditionsAssetId) {
        this.termsAndConditionsAssetId = termsAndConditionsAssetId;
    }

    public DigitizeRequestSchema termsAndConditionsAcceptedTimestamp(String termsAndConditionsAcceptedTimestamp) {
        this.termsAndConditionsAcceptedTimestamp = termsAndConditionsAcceptedTimestamp;
        return this;
    }

    /**
     * The date/time stamp when the account holder accepted the Terms and Conditions.
     * Must be expressed in ISO 8601 extended format as one of the following:
     * YYYY-MM-DDThh:mm:ss[.sss]Z
     * YYYY-MM-DDThh:mm:ss[.sss]±hh:mm
     * Where [.sss] is optional and can be 1 to 3 digits.
     *
     * @return termsAndConditionsAcceptedTimestamp
     */
    @ApiModelProperty(example = "123456", value = "The date/time stamp when the account holder accepted the Terms and Conditions. Must be expressed in ISO 8601 extended format as one of the following: YYYY-MM-DDThh:mm:ss[.sss]Z YYYY-MM-DDThh:mm:ss[.sss]±hh:mm Where [.sss] is optional and can be 1 to 3 digits.")
    public String getTermsAndConditionsAcceptedTimestamp() {
        return termsAndConditionsAcceptedTimestamp;
    }

    public void setTermsAndConditionsAcceptedTimestamp(String termsAndConditionsAcceptedTimestamp) {
        this.termsAndConditionsAcceptedTimestamp = termsAndConditionsAcceptedTimestamp;
    }

    public DigitizeRequestSchema fundingAccountInfo(FundingAccountInfo fundingAccountInfo) {
        this.fundingAccountInfo = fundingAccountInfo;
        return this;
    }

    /**
     * Get fundingAccountInfo
     *
     * @return fundingAccountInfo
     **/
    @ApiModelProperty(required = true, value = "")
    public FundingAccountInfo getFundingAccountInfo() {
        return fundingAccountInfo;
    }

    public void setFundingAccountInfo(FundingAccountInfo fundingAccountInfo) {
        this.fundingAccountInfo = fundingAccountInfo;
    }

    public DigitizeRequestSchema tokenizationAuthenticationValue(String tokenizationAuthenticationValue) {
        this.tokenizationAuthenticationValue = tokenizationAuthenticationValue;
        return this;
    }

    /**
     * The Tokenization Authentication Value (TAV) as cryptographically signed by the Issuer to authorize this digitization request.      __Max Length:2048__
     *
     * @return tokenizationAuthenticationValue
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "RHVtbXkgYmFzZSA2NCBkYXRhIC0gdGhpcyBpcyBub3QgYSByZWFsIFRBViBleGFtcGxl", value = "The Tokenization Authentication Value (TAV) as cryptographically signed by the Issuer to authorize this digitization request.      __Max Length:2048__ ")
    public String getTokenizationAuthenticationValue() {
        return tokenizationAuthenticationValue;
    }

    public void setTokenizationAuthenticationValue(String tokenizationAuthenticationValue) {
        this.tokenizationAuthenticationValue = tokenizationAuthenticationValue;
    }

    public DigitizeRequestSchema decisioningData(DecisioningData decisioningData) {
        this.decisioningData = decisioningData;
        return this;
    }

    /**
     * Get decisioningData
     *
     * @return decisioningData
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "")
    public DecisioningData getDecisioningData() {
        return decisioningData;
    }

    public void setDecisioningData(DecisioningData decisioningData) {
        this.decisioningData = decisioningData;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DigitizeRequestSchema that = (DigitizeRequestSchema) o;
        return Objects.equals(responseHost, that.responseHost) &&
                Objects.equals(requestId, that.requestId) &&
                Objects.equals(paymentAppInstanceId, that.paymentAppInstanceId) &&
                Objects.equals(eligibilityReceipt, that.eligibilityReceipt) &&
                Objects.equals(taskId, that.taskId) &&
                Objects.equals(termsAndConditionsAssetId, that.termsAndConditionsAssetId) &&
                Objects.equals(termsAndConditionsAcceptedTimestamp, that.termsAndConditionsAcceptedTimestamp) &&
                Objects.equals(fundingAccountInfo, that.fundingAccountInfo) &&
                Objects.equals(tokenizationAuthenticationValue, that.tokenizationAuthenticationValue) &&
                Objects.equals(decisioningData, that.decisioningData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseHost, requestId, paymentAppInstanceId, eligibilityReceipt, taskId, termsAndConditionsAssetId, termsAndConditionsAcceptedTimestamp, fundingAccountInfo, tokenizationAuthenticationValue, decisioningData);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DigitizeRequestSchema {");
        sb.append("    responseHost: ").append(toIndentedString(responseHost)).append("");
        sb.append("    requestId: ").append(toIndentedString(requestId)).append("");
        sb.append("    paymentAppInstanceId: ").append(toIndentedString(paymentAppInstanceId)).append("");
        sb.append("    eligibilityReceipt: ").append(toIndentedString(eligibilityReceipt)).append("");
        sb.append("    taskId: ").append(toIndentedString(taskId)).append("");
        sb.append("    termsAndConditionsAssetId: ").append(toIndentedString(termsAndConditionsAssetId)).append("");
        sb.append("    termsAndConditionsAcceptedTimestamp: ").append(toIndentedString(termsAndConditionsAcceptedTimestamp)).append("");
        sb.append("    fundingAccountInfo: ").append(toIndentedString(fundingAccountInfo)).append("");
        sb.append("    tokenizationAuthenticationValue: ").append(toIndentedString(tokenizationAuthenticationValue)).append("");
        sb.append("    decisioningData: ").append(toIndentedString(decisioningData)).append("");
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
