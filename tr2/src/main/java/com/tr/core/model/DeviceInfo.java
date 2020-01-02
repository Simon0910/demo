package com.tr.core.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-22 10:06
 */
public class DeviceInfo {
    public static final String SERIALIZED_NAME_DEVICE_NAME = "deviceName";
    @SerializedName(SERIALIZED_NAME_DEVICE_NAME)
    private String deviceName;

    public static final String SERIALIZED_NAME_SERIAL_NUMBER = "serialNumber";
    @SerializedName(SERIALIZED_NAME_SERIAL_NUMBER)
    private String serialNumber;

    public static final String SERIALIZED_NAME_FORM_FACTOR = "formFactor";
    @SerializedName(SERIALIZED_NAME_FORM_FACTOR)
    private String formFactor;

    public static final String SERIALIZED_NAME_STORAGE_TECHNOLOGY = "storageTechnology";
    @SerializedName(SERIALIZED_NAME_STORAGE_TECHNOLOGY)
    private String storageTechnology;

    public static final String SERIALIZED_NAME_OS_NAME = "osName";
    @SerializedName(SERIALIZED_NAME_OS_NAME)
    private String osName;

    public static final String SERIALIZED_NAME_OS_VERSION = "osVersion";
    @SerializedName(SERIALIZED_NAME_OS_VERSION)
    private String osVersion;

    public static final String SERIALIZED_NAME_NFC_CAPABLE = "nfcCapable";
    @SerializedName(SERIALIZED_NAME_NFC_CAPABLE)
    private Boolean nfcCapable;

    public static final String SERIALIZED_NAME_IMEI = "imei";
    @SerializedName(SERIALIZED_NAME_IMEI)
    private String imei;

    public static final String SERIALIZED_NAME_MSISDN = "msisdn";
    @SerializedName(SERIALIZED_NAME_MSISDN)
    private String msisdn;


    public DeviceInfo deviceName(String deviceName) {
        this.deviceName = deviceName;
        return this;
    }

    /**
     * The name that the Account holder has associated to the target
     * provisioned device (or device being provisioned).
     * <p>
     * Conditional – required in a Check Eligibility request, except when
     * Storage Technology is specified as "SERVER".
     *
     * @return deviceName
     */
    @javax.annotation.Nullable
    @ApiModelProperty(example = "", value = "The name that the Account holder has associated to the target provisioned device (or device being provisioned).")
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public DeviceInfo serialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    /**
     * The serial number of the target provisioned device (or device being provisioned). May be masked.
     *
     * @return serialNumber
     */
    @javax.annotation.Nullable
    @ApiModelProperty(example = "", value = "The serial number of the target provisioned device (or device being provisioned). May be masked.")
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public DeviceInfo formFactor(String formFactor) {
        this.formFactor = formFactor;
        return this;
    }

    /**
     * The form factor of the target provisioned device (or device being provisioned).
     * Must be one of:
     * {@link com.tr.core.enums.FormFactorEnum}
     *
     * @return formFactor
     */
    @javax.annotation.Nullable
    @ApiModelProperty(example = "", value = "The form factor of the target provisioned device (or device being provisioned).")
    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public DeviceInfo storageTechnology(String storageTechnology) {
        this.storageTechnology = storageTechnology;
        return this;
    }

    /**
     * The architecture or technology used for token storage.
     * Must be one of:
     * {@link com.tr.core.enums.StorageTechnologyEnum}
     *
     * @return storageTechnology
     */
    @ApiModelProperty(example = "", required = true, value = "The architecture or technology used for token storage. Must be one of:")
    public String getStorageTechnology() {
        return storageTechnology;
    }

    public void setStorageTechnology(String storageTechnology) {
        this.storageTechnology = storageTechnology;
    }

    public DeviceInfo osName(String osName) {
        this.osName = osName;
        return this;
    }

    /**
     * The name of the operating system of the target provisioned device (or device being provisioned).
     * Must be one of:
     * {@link com.tr.core.enums.OsNameEnum}
     *
     * @return osName
     */
    @javax.annotation.Nullable
    @ApiModelProperty(example = "", value = "The name of the operating system of the target provisioned device (or device being provisioned).")
    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public DeviceInfo osVersion(String osVersion) {
        this.osVersion = osVersion;
        return this;
    }

    /**
     * The version of the operating system of the target provisioned device (or device being provisioned).
     *
     * @return osVersion
     */
    @javax.annotation.Nullable
    @ApiModelProperty(example = "", value = "The version of the operating system of the target provisioned device (or device being provisioned).")
    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public DeviceInfo nfcCapable(Boolean nfcCapable) {
        this.nfcCapable = nfcCapable;
        return this;
    }

    /**
     * Whether the target provisioned device (or device being provisioned) has NFC capability.
     *
     * @return nfcCapable
     */
    @javax.annotation.Nullable
    @ApiModelProperty(example = "true", value = "Whether the target provisioned device (or device being provisioned) has NFC capability.")
    public Boolean getNfcCapable() {
        return nfcCapable;
    }

    public void setNfcCapable(Boolean nfcCapable) {
        this.nfcCapable = nfcCapable;
    }

    public DeviceInfo imei(String imei) {
        this.imei = imei;
        return this;
    }

    /**
     * The IMEI number of the target provisioned device (or device being provisioned).
     *
     * @return imei
     */
    @javax.annotation.Nullable
    @ApiModelProperty(example = "true", value = "The IMEI number of the target provisioned device (or device being provisioned).")
    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public DeviceInfo msisdn(String msisdn) {
        this.msisdn = msisdn;
        return this;
    }

    /**
     * The MSISDN of the target provisioned device (or device being provisioned).
     *
     * @return msisdn
     */
    @javax.annotation.Nullable
    @ApiModelProperty(example = "true", value = "The MSISDN of the target provisioned device (or device being provisioned).")
    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeviceInfo that = (DeviceInfo) o;
        return Objects.equals(deviceName, that.deviceName) &&
                Objects.equals(serialNumber, that.serialNumber) &&
                Objects.equals(formFactor, that.formFactor) &&
                Objects.equals(storageTechnology, that.storageTechnology) &&
                Objects.equals(osName, that.osName) &&
                Objects.equals(osVersion, that.osVersion) &&
                Objects.equals(nfcCapable, that.nfcCapable) &&
                Objects.equals(imei, that.imei) &&
                Objects.equals(msisdn, that.msisdn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceName, serialNumber, formFactor, storageTechnology, osName, osVersion, nfcCapable, imei, msisdn);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DeviceInfo {");
        sb.append("    deviceName: ").append(toIndentedString(deviceName)).append("");
        sb.append("    serialNumber: ").append(toIndentedString(serialNumber)).append("");
        sb.append("    formFactor: ").append(toIndentedString(formFactor)).append("");
        sb.append("    storageTechnology: ").append(toIndentedString(storageTechnology)).append("");
        sb.append("    osName: ").append(toIndentedString(osName)).append("");
        sb.append("    osVersion: ").append(toIndentedString(osVersion)).append("");
        sb.append("    nfcCapable: ").append(toIndentedString(nfcCapable)).append("");
        sb.append("    imei: ").append(toIndentedString(imei)).append("");
        sb.append("    msisdn: ").append(toIndentedString(msisdn)).append("");
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
