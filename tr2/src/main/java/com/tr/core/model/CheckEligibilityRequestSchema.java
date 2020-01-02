package com.tr.core.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-22 09:56
 */
public class CheckEligibilityRequestSchema {
    public static final String SERIALIZED_NAME_RESPONSE_HOST = "responseHost";
    @SerializedName(SERIALIZED_NAME_RESPONSE_HOST)
    private String responseHost;

    public static final String SERIALIZED_NAME_REQUEST_ID = "requestId";
    @SerializedName(SERIALIZED_NAME_REQUEST_ID)
    private String requestId;

    public static final String SERIALIZED_NAME_TOKEN_TYPE = "tokenType";
    @SerializedName(SERIALIZED_NAME_TOKEN_TYPE)
    private String tokenType;

    public static final String SERIALIZED_NAME_PAYMENT_APP_INSTANCEID = "paymentAppInstanceId";
    @SerializedName(SERIALIZED_NAME_PAYMENT_APP_INSTANCEID)
    private String paymentAppInstanceId;

    public static final String SERIALIZED_NAME_PAYMENT_APP_ID = "paymentAppId";
    @SerializedName(SERIALIZED_NAME_PAYMENT_APP_ID)
    private String paymentAppId;

    public static final String SERIALIZED_NAME_DEVICE_INFO = "deviceInfo";
    @SerializedName(SERIALIZED_NAME_DEVICE_INFO)
    private DeviceInfo deviceInfo = null;

    public static final String SERIALIZED_NAME_SE_INFO = "seInfo";
    @SerializedName(SERIALIZED_NAME_SE_INFO)
    private SeInfo seInfo = null;

    public static final String SERIALIZED_NAME_FUNDING_ACCOUNT_INFO = "fundingAccountInfo";
    @SerializedName(SERIALIZED_NAME_FUNDING_ACCOUNT_INFO)
    private FundingAccountInfo fundingAccountInfo = null;

    public static final String SERIALIZED_NAME_CARDLET_ID = "cardletId";
    @SerializedName(SERIALIZED_NAME_CARDLET_ID)
    private String cardletId;

    public static final String SERIALIZED_NAME_SPSD_INFO = "spsdInfo";
    @SerializedName(SERIALIZED_NAME_SPSD_INFO)
    private SpsdInfo spsdInfo = null;

    public static final String SERIALIZED_NAME_CONSUMER_LANGUAGE = "consumerLanguage";
    @SerializedName(SERIALIZED_NAME_CONSUMER_LANGUAGE)
    private String consumerLanguage;

    public CheckEligibilityRequestSchema responseHost(String responseHost) {
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

    public CheckEligibilityRequestSchema requestId(String requestId) {
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

    public CheckEligibilityRequestSchema tokenType(String tokenType) {
        this.tokenType = tokenType;
        return this;
    }

    /**
     * The type of Token requested. Must be CLOUD       __Max Length:32__
     *
     * @return tokenType
     **/
    @ApiModelProperty(example = "CLOUD", required = true, value = "The type of Token requested. Must be CLOUD       __Max Length:32__    ")
    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public CheckEligibilityRequestSchema paymentAppInstanceId(String paymentAppInstanceId) {
        this.paymentAppInstanceId = paymentAppInstanceId;
        return this;
    }

    /**
     * Identifier for the specific Mobile Payment App instance, unique across a given Wallet Identifier.
     * This value cannot be changed after digitization.
     * This field is alphanumeric and additionally web-safe base64 characters
     * per RFC 4648 (minus "-", underscore "_") up to a maximum length of 48, = should not be URL encoded.
     * <p>
     * Conditional – not applicable for server-based tokens. Required otherwise.
     *
     * @return paymentAppInstanceId
     */
    @ApiModelProperty(example = "123456789", value = "Identifier for the specific Mobile Payment App instance, unique across a given Wallet Identifier. This value cannot be changed after digitization. This field is alphanumeric and additionally web-safe base64 characters per RFC 4648 (minus \"-\", underscore \"_\") up to a maximum length of 48, = should not be URL encoded. Conditional – not applicable for server-based tokens. Required otherwise.")
    public String getPaymentAppInstanceId() {
        return paymentAppInstanceId;
    }

    public void setPaymentAppInstanceId(String paymentAppInstanceId) {
        this.paymentAppInstanceId = paymentAppInstanceId;
    }

    public CheckEligibilityRequestSchema paymentAppId(String paymentAppId) {
        this.paymentAppId = paymentAppId;
        return this;
    }

    /**
     * Identifier for the Payment App, unique per app as assigned by Mastercard for this Payment App.
     *
     * @return paymentAppId
     */
    @ApiModelProperty(example = "WalletApp1", required = true, value = "Identifier for the Payment App, unique per app as assigned by Mastercard for this Payment App. ")
    public String getPaymentAppId() {
        return paymentAppId;
    }

    public void setPaymentAppId(String paymentAppId) {
        this.paymentAppId = paymentAppId;
    }

    public CheckEligibilityRequestSchema deviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
        return this;
    }

    /**
     * Contains information about the target device to be provisioned.
     * <p>
     * Conditional – may be omitted if performing account availability checks.
     * Otherwise required. (See B.1).
     *
     * @return deviceInfo
     */
    @javax.annotation.Nullable
    @ApiModelProperty(example = "", value = "Contains information about the target device to be provisioned. Conditional – may be omitted if performing account availability checks. Otherwise required. (See B.1).")
    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public CheckEligibilityRequestSchema seInfo(SeInfo seInfo) {
        this.seInfo = seInfo;
        return this;
    }

    /**
     * Contains information about the target Secure Element to be provisioned.
     * required if tokenType = EMBEDDED_SE in order to check device eligibility,
     * may be omitted if only performing account availability checks.
     * <p>
     * Conditional – required if tokenType = EMBEDDED_SE in order to check
     * device eligibility, may be omitted if only performing account availability
     * checks.
     *
     * @return seInfo
     */
    @javax.annotation.Nullable
    @ApiModelProperty(example = "", value = "Contains information about the target Secure Element to be provisioned.")
    public SeInfo getSeInfo() {
        return seInfo;
    }

    public void setSeInfo(SeInfo seInfo) {
        this.seInfo = seInfo;
    }

    public CheckEligibilityRequestSchema fundingAccountInfo(FundingAccountInfo fundingAccountInfo) {
        this.fundingAccountInfo = fundingAccountInfo;
        return this;
    }

    /**
     * Contains the information of the funding account to be tokenized. This
     * could be a credit card, debit card, bank account, or other type of
     * financial account.
     * <p>
     * Conditional – required in order to check account availability, may be
     * omitted if only performing device eligibility checks.
     *
     * @return fundingAccountInfo
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "", value = "required in order to check account availability, may be omitted if only performing device eligibility checks.")
    public FundingAccountInfo getFundingAccountInfo() {
        return fundingAccountInfo;
    }

    public void setFundingAccountInfo(FundingAccountInfo fundingAccountInfo) {
        this.fundingAccountInfo = fundingAccountInfo;
    }

    public CheckEligibilityRequestSchema cardletId(String cardletId) {
        this.cardletId = cardletId;
        return this;
    }

    /**
     * An identifier representing the cardlet being used (as assigned during onboarding).      __Max Length:10__
     *
     * @return cardletId
     */
    @ApiModelProperty(example = "", required = true, value = "An identifier representing the cardlet being used (as assigned during onboarding).      __Max Length:10__ ")
    public String getCardletId() {
        return cardletId;
    }

    public void setCardletId(String cardletId) {
        this.cardletId = cardletId;
    }

    public CheckEligibilityRequestSchema spsdInfo(SpsdInfo spsdInfo) {
        this.spsdInfo = spsdInfo;
        return this;
    }

    /**
     * Contains information about the Service Provider Security Domain.
     * <p>
     * Conditional – required if tokenType = EMBEDDED_SE.
     *
     * @return spsdInfo
     */
    @javax.annotation.Nullable
    @ApiModelProperty(value = "Contains information about the Service Provider Security Domain.")
    public SpsdInfo getSpsdInfo() {
        return spsdInfo;
    }

    public void setSpsdInfo(SpsdInfo spsdInfo) {
        this.spsdInfo = spsdInfo;
    }

    public CheckEligibilityRequestSchema consumerLanguage(String consumerLanguage) {
        this.consumerLanguage = consumerLanguage;
        return this;
    }

    /**
     * Language preference selected by the consumer. Formatted as an ISO- 639-1 two-letter language code.    __Max Length:2__
     *
     * @return consumerLanguage
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(example = "en", value = "Language preference selected by the consumer. Formatted as an ISO- 639-1 two-letter language code.    __Max Length:2__ ")
    public String getConsumerLanguage() {
        return consumerLanguage;
    }

    public void setConsumerLanguage(String consumerLanguage) {
        this.consumerLanguage = consumerLanguage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CheckEligibilityRequestSchema that = (CheckEligibilityRequestSchema) o;
        return Objects.equals(responseHost, that.responseHost) &&
                Objects.equals(requestId, that.requestId) &&
                Objects.equals(tokenType, that.tokenType) &&
                Objects.equals(paymentAppInstanceId, that.paymentAppInstanceId) &&
                Objects.equals(paymentAppId, that.paymentAppId) &&
                Objects.equals(deviceInfo, that.deviceInfo) &&
                Objects.equals(seInfo, that.seInfo) &&
                Objects.equals(fundingAccountInfo, that.fundingAccountInfo) &&
                Objects.equals(cardletId, that.cardletId) &&
                Objects.equals(spsdInfo, that.spsdInfo) &&
                Objects.equals(consumerLanguage, that.consumerLanguage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseHost, requestId, tokenType, paymentAppInstanceId, paymentAppId, deviceInfo, seInfo, fundingAccountInfo, cardletId, spsdInfo, consumerLanguage);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CheckEligibilityRequestSchema {");
        sb.append("    responseHost: ").append(toIndentedString(responseHost)).append("");
        sb.append("    requestId: ").append(toIndentedString(requestId)).append("");
        sb.append("    tokenType: ").append(toIndentedString(tokenType)).append("");
        sb.append("    paymentAppId: ").append(toIndentedString(paymentAppId)).append("");
        sb.append("    paymentAppInstanceId: ").append(toIndentedString(paymentAppInstanceId)).append("");
        sb.append("    deviceInfo: ").append(toIndentedString(deviceInfo)).append("");
        sb.append("    seInfo: ").append(toIndentedString(seInfo)).append("");
        sb.append("    fundingAccountInfo: ").append(toIndentedString(fundingAccountInfo)).append("");
        sb.append("    cardletId: ").append(toIndentedString(cardletId)).append("");
        sb.append("    spsdInfo: ").append(toIndentedString(spsdInfo)).append("");
        sb.append("    consumerLanguage: ").append(toIndentedString(consumerLanguage)).append("");
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
