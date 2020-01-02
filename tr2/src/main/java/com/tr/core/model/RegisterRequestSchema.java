package com.tr.core.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-21 17:00
 */
public class RegisterRequestSchema {
    public static final String SERIALIZED_NAME_RESPONSE_HOST = "responseHost";
    @SerializedName(SERIALIZED_NAME_RESPONSE_HOST)
    private String responseHost;

    public static final String SERIALIZED_NAME_REQUEST_ID = "requestId";
    @SerializedName(SERIALIZED_NAME_REQUEST_ID)
    private String requestId;

    public static final String SERIALIZED_NAME_PAYMENT_APP_ID = "paymentAppId";
    @SerializedName(SERIALIZED_NAME_PAYMENT_APP_ID)
    private String paymentAppId;

    public static final String SERIALIZED_NAME_PAYMENT_APP_INSTANCE_ID = "paymentAppInstanceId";
    @SerializedName(SERIALIZED_NAME_PAYMENT_APP_INSTANCE_ID)
    private String paymentAppInstanceId;

    public static final String SERIALIZED_NAME_RNS_INFO = "rnsInfo";
    @SerializedName(SERIALIZED_NAME_RNS_INFO)
    private RnsInfo rnsInfo = null;

    public static final String SERIALIZED_NAME_PUBLIC_KEY_FINGERPRINT = "publicKeyFingerprint";
    @SerializedName(SERIALIZED_NAME_PUBLIC_KEY_FINGERPRINT)
    private String publicKeyFingerprint;

    public static final String SERIALIZED_NAME_RGK = "rgk";
    @SerializedName(SERIALIZED_NAME_RGK)
    private String rgk;

    public static final String SERIALIZED_NAME_DEVICE_FINGERPRINT = "deviceFingerprint";
    @SerializedName(SERIALIZED_NAME_DEVICE_FINGERPRINT)
    private String deviceFingerprint;

    public static final String SERIALIZED_NAME_NEW_MOBILE_PIN = "newMobilePin";
    @SerializedName(SERIALIZED_NAME_NEW_MOBILE_PIN)
    private String newMobilePin;


    public RegisterRequestSchema responseHost(String responseHost) {
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

    public RegisterRequestSchema requestId(String requestId) {
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

    public RegisterRequestSchema paymentAppId(String paymentAppId) {
        this.paymentAppId = paymentAppId;
        return this;
    }

    /**
     * Identifier for the Payment App, unique per app as assigned by Mastercard for this Payment App.
     *
     * @return paymentAppId
     */
    @ApiModelProperty(example = "WalletApp1", required = true, value = "Identifier for the Payment App, unique per app as assigned by Mastercard for this Payment App.      __Max Length:30__ ")
    public String getPaymentAppId() {
        return paymentAppId;
    }

    public void setPaymentAppId(String paymentAppId) {
        this.paymentAppId = paymentAppId;
    }

    public RegisterRequestSchema paymentAppInstanceId(String paymentAppInstanceId) {
        this.paymentAppInstanceId = paymentAppInstanceId;
        return this;
    }

    /**
     * Identifier for the specific Mobile Payment App instance, unique across a
     * given Wallet Identifier. This value cannot be changed after digitization.
     *
     * @return paymentAppInstanceId
     */
    @ApiModelProperty(example = "123456789", required = true, value = "Identifier for the specific Mobile Payment App instance, unique across a given Wallet Identifier. This value cannot be changed after digitization.      __Max Length:48__ ")
    public String getPaymentAppInstanceId() {
        return paymentAppInstanceId;
    }

    public void setPaymentAppInstanceId(String paymentAppInstanceId) {
        this.paymentAppInstanceId = paymentAppInstanceId;
    }

    public RegisterRequestSchema rnsInfo(RnsInfo rnsInfo) {
        this.rnsInfo = rnsInfo;
        return this;
    }

    /**
     * Get rnsInfo
     * Contains information about the Remote Notification Service to be used
     * to deliver credentials to the target application instance.
     * <p>
     * Conditional – required if remote notification is via Google Cloud
     * Messaging. Not present if remote notification is via a server using the
     * Send Remote Notification Message API (see Section 6.3.1).
     *
     * @return rnsInfo
     */
    @javax.annotation.Nullable
    @ApiModelProperty(required = true, value = "")
    public RnsInfo getRnsInfo() {
        return rnsInfo;
    }

    public void setRnsInfo(RnsInfo rnsInfo) {
        this.rnsInfo = rnsInfo;
    }

    public RegisterRequestSchema publicKeyFingerprint(String publicKeyFingerprint) {
        this.publicKeyFingerprint = publicKeyFingerprint;
        return this;
    }

    /**
     * The certificate fingerprint identifying the public key used to encrypt the randomly-generated AES key (‘rgk’).
     *
     * @return publicKeyFingerprint
     */
    @ApiModelProperty(example = "4c4ead5927f0df8117f178eea9308daa58e27c2b", required = true, value = "The certificate fingerprint identifying the public key used to encrypt the randomly-generated AES key (‘rgk’).      __Max Length:64__ ")
    public String getPublicKeyFingerprint() {
        return publicKeyFingerprint;
    }

    public void setPublicKeyFingerprint(String publicKeyFingerprint) {
        this.publicKeyFingerprint = publicKeyFingerprint;
    }

    public RegisterRequestSchema rgk(String rgk) {
        this.rgk = rgk;
        return this;
    }

    /**
     * The randomly-generated 128-bit AES key, encrypted by the Mastercard
     * public key (provided during onboarding) using OAEP with SHA-256
     * hashing algorithm.
     *
     * @return rgk
     */
    @ApiModelProperty(example = "4E4F54205245414C2044415441202D20746869732077696C6C2062652072616E646F6D206B6579206765" +
            "6E65726174656420627920746865204D504120616E6420656E63727970746564207573696E67207468652" +
            "04D617374657243617264207075626C6963206B6579", required = true, value = "The randomly-generated 128-bit AES key, encrypted by the Mastercard public key (provided during onboarding) using OAEP with SHA-256 hashing algorithm.       __Max Length:512__ ")
    public String getRgk() {
        return rgk;
    }

    public void setRgk(String rgk) {
        this.rgk = rgk;
    }

    public RegisterRequestSchema deviceFingerprint(String deviceFingerprint) {
        this.deviceFingerprint = deviceFingerprint;
        return this;
    }

    /**
     * The unique device fingerprint, which is a SHA-256 hash computed over
     * a list of data elements retrieved from the Mobile Payment App and the
     * Mobile Device.
     *
     * @return deviceFingerprint
     */
    @ApiModelProperty(example = "1bbefaa95b26b9e82e3fdd37b20050fc782b2f229a8f8bcbbcb6aa6abe4c851e", required = true, value = "The unique device fingerprint, which is a SHA-256 hash computed over a list of data elements retrieved from the Mobile Payment App and the Mobile Device.        __Max Length:64__ ")
    public String getDeviceFingerprint() {
        return deviceFingerprint;
    }

    public void setDeviceFingerprint(String deviceFingerprint) {
        this.deviceFingerprint = deviceFingerprint;
    }

    public RegisterRequestSchema newMobilePin(String newMobilePin) {
        this.newMobilePin = newMobilePin;
        return this;
    }

    /**
     * The new Mobile PIN value as chosen by the user, to be updated in MDES.
     * The Mobile PIN value is supplied as a Format 4 PIN Block using a substitute value for the PAN as specified in Section 5.1.3.2,
     * encrypted using the given ‘rgk’.
     * <p>
     * Conditional – optional if the Mobile Payment App supports Mobile PIN
     * and if the Mobile PIN is supplied by a server. Not present if the Mobile
     * Payment App does not support Mobile PIN. Not present if the Mobile
     * PIN is supplied directly by the Mobile Payment App using the Mobile
     * Payment API (see Section 5.3.6 – Change Mobile PIN).
     *
     * @return newMobilePin
     */
    @javax.annotation.Nullable
    @ApiModelProperty(example = "4E4F54205245414C2050494E20424C4F434B", value = "The new Mobile PIN value as chosen by the user, to be updated in MDES. The Mobile PIN value is supplied as a Format 4 PIN Block using a substitute value for the PAN as specified in Section 5.1.3.2, encrypted using the given ‘rgk’.        __Max Length:64__ ")
    public String getNewMobilePin() {
        return newMobilePin;
    }

    public void setNewMobilePin(String newMobilePin) {
        this.newMobilePin = newMobilePin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RegisterRequestSchema that = (RegisterRequestSchema) o;
        return Objects.equals(responseHost, that.responseHost) &&
                Objects.equals(requestId, that.requestId) &&
                Objects.equals(paymentAppId, that.paymentAppId) &&
                Objects.equals(paymentAppInstanceId, that.paymentAppInstanceId) &&
                Objects.equals(rnsInfo, that.rnsInfo) &&
                Objects.equals(publicKeyFingerprint, that.publicKeyFingerprint) &&
                Objects.equals(rgk, that.rgk) &&
                Objects.equals(deviceFingerprint, that.deviceFingerprint)
                && Objects.equals(newMobilePin, that.newMobilePin)
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseHost, requestId, paymentAppId, paymentAppInstanceId, rnsInfo, publicKeyFingerprint, rgk, deviceFingerprint, newMobilePin);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RegisterRequestSchema {");
        sb.append("    responseHost: ").append(toIndentedString(responseHost)).append("");
        sb.append("    requestId: ").append(toIndentedString(requestId)).append("");
        sb.append("    paymentAppId: ").append(toIndentedString(paymentAppId)).append("");
        sb.append("    paymentAppInstanceId: ").append(toIndentedString(paymentAppInstanceId)).append("");
        sb.append("    rnsInfo: ").append(toIndentedString(rnsInfo)).append("");
        sb.append("    publicKeyFingerprint: ").append(toIndentedString(publicKeyFingerprint)).append("");
        sb.append("    rgk: ").append(toIndentedString(rgk)).append("");
        sb.append("    deviceFingerprint: ").append(toIndentedString(deviceFingerprint)).append("");
        sb.append("    newMobilePin: ").append(toIndentedString(newMobilePin)).append("");
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
