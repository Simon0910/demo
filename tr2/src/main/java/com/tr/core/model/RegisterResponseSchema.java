package com.tr.core.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-21 17:00
 */
public class RegisterResponseSchema {
    public static final String SERIALIZED_NAME_RESPONSE_HOST = "responseHost";
    @SerializedName(SERIALIZED_NAME_RESPONSE_HOST)
    private String responseHost;

    public static final String SERIALIZED_NAME_RESPONSE_ID = "responseId";
    @SerializedName(SERIALIZED_NAME_RESPONSE_ID)
    private String responseId;

    public static final String SERIALIZED_NAME_MOBILE_KEYSET_ID = "mobileKeysetId";
    @SerializedName(SERIALIZED_NAME_MOBILE_KEYSET_ID)
    private String mobileKeysetId;

    public static final String SERIALIZED_NAME_MOBILE_KEYS = "mobileKeys";
    @SerializedName(SERIALIZED_NAME_MOBILE_KEYS)
    private MobileKeys mobileKeys;

    public static final String SERIALIZED_NAME_REMOTE_MANAGEMENT_URL = "remoteManagementUrl";
    @SerializedName(SERIALIZED_NAME_REMOTE_MANAGEMENT_URL)
    private String remoteManagementUrl;

    public static final String SERIALIZED_NAME_ERRORS = "errors";
    @SerializedName(SERIALIZED_NAME_ERRORS)
    private List<Error> errors = new ArrayList<Error>();


    public RegisterResponseSchema responseHost(String responseHost) {
        this.responseHost = responseHost;
        return this;
    }

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

    public RegisterResponseSchema responseId(String responseId) {
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

    public RegisterResponseSchema mobileKeysetId(String mobileKeysetId) {
        this.mobileKeysetId = mobileKeysetId;
        return this;
    }

    /**
     * The identifier for the Mobile Keys used to manage the CLOUD
     * credentials, as assigned by MDES upon successful registration.
     *
     * @return mobileKeysetId
     */
    @ApiModelProperty(example = "e279760f-f4cd-4683-ba42-4d8053817a69", required = true, value = "The identifier for the Mobile Keys used to manage the CLOUD credentials, as assigned by MDES upon successful registration. ")
    public String getMobileKeysetId() {
        return mobileKeysetId;
    }

    public void setMobileKeysetId(String mobileKeysetId) {
        this.mobileKeysetId = mobileKeysetId;
    }

    public RegisterResponseSchema mobileKeys(MobileKeys mobileKeys) {
        this.mobileKeys = mobileKeys;
        return this;
    }

    /**
     * Get mobileKeys
     *
     * @return mobileKeys
     */
    @ApiModelProperty(required = true, value = "Contains the mobile keys used to secure the communication during subsequent remote management sessions.")
    public MobileKeys getMobileKeys() {
        return mobileKeys;
    }

    public void setMobileKeys(MobileKeys mobileKeys) {
        this.mobileKeys = mobileKeys;
    }

    public RegisterResponseSchema remoteManagementUrl(String remoteManagementUrl) {
        this.remoteManagementUrl = remoteManagementUrl;
        return this;
    }

    /**
     * The URL endpoint for subsequent remote management sessions. The
     * Mobile Payment App must store this URL for future use in order to be
     * able to request new remote management sessions.
     * Must be provided as:
     * host[:port][/contextRoot]
     * Where port and contextRoot are optional.
     * If contextRoot is not provided, the default (per the URL Scheme) is
     * assumed and must be used.
     *
     * @return remoteManagementUrl
     */
    @ApiModelProperty(example = "loadbalanced.Mastercard.com", required = true, value = "he URL endpoint for subsequent remote management sessions.")
    public String getRemoteManagementUrl() {
        return remoteManagementUrl;
    }

    public void setRemoteManagementUrl(String remoteManagementUrl) {
        this.remoteManagementUrl = remoteManagementUrl;
    }

    public RegisterResponseSchema errors(List<Error> errors) {
        this.errors = errors;
        return this;
    }

    public RegisterResponseSchema addErrorsItem(Error errorsItem) {
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
        RegisterResponseSchema that = (RegisterResponseSchema) o;
        return Objects.equals(responseHost, that.responseHost) &&
                Objects.equals(responseId, that.responseId) &&
                Objects.equals(mobileKeysetId, that.mobileKeysetId) &&
                Objects.equals(mobileKeys, that.mobileKeys) &&
                Objects.equals(remoteManagementUrl, that.remoteManagementUrl) &&
                Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseHost, responseId, mobileKeysetId, mobileKeys, remoteManagementUrl, errors);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RegisterResponseSchema {");
        sb.append("    responseHost: ").append(toIndentedString(responseHost)).append("");
        sb.append("    responseId: ").append(toIndentedString(responseId)).append("");
        sb.append("    mobileKeysetId: ").append(toIndentedString(mobileKeysetId)).append("");
        sb.append("    mobileKeys: ").append(toIndentedString(mobileKeys)).append("");
        sb.append("    remoteManagementUrl: ").append(toIndentedString(remoteManagementUrl)).append("");
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
