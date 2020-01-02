package com.tr.core.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-22 15:06
 */
public class CheckEligibilityResponseSchema {
    public static final String SERIALIZED_NAME_RESPONSE_HOST = "responseHost";
    @SerializedName(SERIALIZED_NAME_RESPONSE_HOST)
    private String responseHost;

    public static final String SERIALIZED_NAME_RESPONSE_ID = "responseId";
    @SerializedName(SERIALIZED_NAME_RESPONSE_ID)
    private String responseId;

    public static final String SERIALIZED_NAME_ELIGIBILITY_RECEIPT = "eligibilityReceipt";
    @SerializedName(SERIALIZED_NAME_ELIGIBILITY_RECEIPT)
    private EligibilityReceipt eligibilityReceipt;

    public static final String SERIALIZED_NAME_DEVICE_NOT_ELIGIBLE_REASONS = "deviceNotEligibleReasons";
    @SerializedName(SERIALIZED_NAME_DEVICE_NOT_ELIGIBLE_REASONS)
    private List<String> deviceNotEligibleReasons;

    public static final String SERIALIZED_NAME_TERMS_AND_CONDITIONS_ASSETID = "termsAndConditionsAssetId";
    @SerializedName(SERIALIZED_NAME_TERMS_AND_CONDITIONS_ASSETID)
    private String termsAndConditionsAssetId;

    public static final String SERIALIZED_NAME_APPLICABLE_CARD_INFO = "applicableCardInfo";
    @SerializedName(SERIALIZED_NAME_APPLICABLE_CARD_INFO)
    private ApplicableCardInfo applicableCardInfo;

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

    public CheckEligibilityResponseSchema responseId(String responseId) {
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

    public CheckEligibilityResponseSchema eligibilityReceipt(EligibilityReceipt eligibilityReceipt) {
        this.eligibilityReceipt = eligibilityReceipt;
        return this;
    }

    /**
     * Description:  Contains the Eligibility Receipt, provided by MDES if approipriate
     * eligibility checks were successful. Both account and device eligibilty
     * checks are undertaken for device based tokens while only account
     * checks are undertaken for server based tokens. The client must provide
     * the Eligibility Receipt value back to MDES in the Digitize API (see Section
     * 3.2.2) to proceed with digitization.
     * Data Type:  EligibilityReceipt object.
     * Max Length:  N/A
     * Required:  Conditional – required if eligibility checks were successful.
     *
     * <p>
     * Get EligibilityReceipt
     *
     * @return eligibilityReceipt
     */
    @javax.annotation.Nullable
    @ApiModelProperty(value = "Contains the Eligibility Receipt, provided by MDES if approipriate eligibility checks were successful. Both account and device eligibilty checks are undertaken for device based tokens while only account checks are undertaken for server based tokens. The client must provide the Eligibility Receipt value back to MDES in the Digitize API to proceed with digitization. ")
    public EligibilityReceipt getEligibilityReceipt() {
        return eligibilityReceipt;
    }

    public void setEligibilityReceipt(EligibilityReceipt eligibilityReceipt) {
        this.eligibilityReceipt = eligibilityReceipt;
    }

    public CheckEligibilityResponseSchema deviceNotEligibleReasons(List<String> deviceNotEligibleReasons) {
        this.deviceNotEligibleReasons = deviceNotEligibleReasons;
        return this;
    }

    /**
     * The reasons why the device was deemed not eligible for the service.
     * Data Type:  Array[String]
     * Max Length:  N/A
     * Required:  Conditional – required if device eligibility checks failed.
     *
     * @return deviceNotEligibleReasons
     */
    @javax.annotation.Nullable
    @ApiModelProperty(value = "The reasons why the device was deemed not eligible for the service.")
    public List<String> getDeviceNotEligibleReasons() {
        return deviceNotEligibleReasons;
    }

    public void setDeviceNotEligibleReasons(List<String> deviceNotEligibleReasons) {
        this.deviceNotEligibleReasons = deviceNotEligibleReasons;
    }

    public CheckEligibilityResponseSchema termsAndConditionsAssetId(String termsAndConditionsAssetId) {
        this.termsAndConditionsAssetId = termsAndConditionsAssetId;
        return this;
    }

    /**
     * Description:  The Terms and Conditions to be presented to the account holder.
     * Provided as an Asset ID – use the Get Asset API (See Section 3.2.5) to
     * retrieve the actual asset.
     * Data Type:  String
     * Max Length:  64
     * Required:  Conditional – required if eligibility checks were successful.
     *
     * @return termsAndConditionsAssetId
     */
    @javax.annotation.Nullable
    @ApiModelProperty(value = "The Terms and Conditions to be presented to the account holder. Provided as an Asset ID – use the Get Asset API (See Section 3.2.5) to retrieve the actual asset. Max Length:  64")
    public String getTermsAndConditionsAssetId() {
        return termsAndConditionsAssetId;
    }

    public void setTermsAndConditionsAssetId(String termsAndConditionsAssetId) {
        this.termsAndConditionsAssetId = termsAndConditionsAssetId;
    }

    public CheckEligibilityResponseSchema applicableCardInfo(ApplicableCardInfo applicableCardInfo) {
        this.applicableCardInfo = applicableCardInfo;
        return this;
    }

    /**
     * Description:  Contains flags to indicate what additional card information is applicable
     * for this product and may be provided in the Digitize request.
     * Data Type:  ApplicableCardInfo object
     * Max Length:  N/A
     * Required:  Conditional – required if eligibility checks were successful.
     *
     * @return applicableCardInfo
     */
    @javax.annotation.Nullable
    @ApiModelProperty(value = "Contains flags to indicate what additional card information is applicable for this product and may be provided in the Digitize request.")
    public ApplicableCardInfo getApplicableCardInfo() {
        return applicableCardInfo;
    }

    public void setApplicableCardInfo(ApplicableCardInfo applicableCardInfo) {
        this.applicableCardInfo = applicableCardInfo;
    }


    public CheckEligibilityResponseSchema errors(List<Error> errors) {
        this.errors = errors;
        return this;
    }

    public CheckEligibilityResponseSchema addErrorsItem(Error errorsItem) {
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
        CheckEligibilityResponseSchema that = (CheckEligibilityResponseSchema) o;
        return Objects.equals(responseHost, that.responseHost) &&
                Objects.equals(responseId, that.responseId) &&
                Objects.equals(eligibilityReceipt, that.eligibilityReceipt) &&
                Objects.equals(deviceNotEligibleReasons, that.deviceNotEligibleReasons) &&
                Objects.equals(termsAndConditionsAssetId, that.termsAndConditionsAssetId) &&
                Objects.equals(applicableCardInfo, that.applicableCardInfo) &&
                Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseHost, responseId, eligibilityReceipt, deviceNotEligibleReasons, termsAndConditionsAssetId, applicableCardInfo, errors);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CheckEligibilityResponseSchema {");
        sb.append("    responseHost: ").append(toIndentedString(responseHost)).append("");
        sb.append("    responseId: ").append(toIndentedString(responseId)).append("");
        sb.append("    eligibilityReceipt: ").append(toIndentedString(eligibilityReceipt)).append("");
        sb.append("    deviceNotEligibleReasons: ").append(toIndentedString(deviceNotEligibleReasons)).append("");
        sb.append("    termsAndConditionsAssetId: ").append(toIndentedString(termsAndConditionsAssetId)).append("");
        sb.append("    applicableCardInfo: ").append(toIndentedString(applicableCardInfo)).append("");
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
