package com.tr.core.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * A.36 SpsdInfo
 * <p>
 * Created by daniel on 2019/6/28.
 */
public class SpsdInfo {
    public static final String SERIALIZED_NAME_AID = "aid";
    @SerializedName(SERIALIZED_NAME_AID)
    private String aid;

    public static final String SERIALIZED_NAME_APPLET_INSTANCE_AID = "appletInstanceAid";
    @SerializedName(SERIALIZED_NAME_APPLET_INSTANCE_AID)
    private String appletInstanceAid;

    public static final String SERIALIZED_NAME_SPSD_SEQUENCE_COUNTER = "spsdSequenceCounter";
    @SerializedName(SERIALIZED_NAME_SPSD_SEQUENCE_COUNTER)
    private String spsdSequenceCounter;

    public static final String SERIALIZED_NAME_RGK = "rgk";
    @SerializedName(SERIALIZED_NAME_RGK)
    private String rgk;

    public static final String SERIALIZED_NAME_CASD_PK_CERTIFICATE = "casdPkCertificate";
    @SerializedName(SERIALIZED_NAME_CASD_PK_CERTIFICATE)
    private String casdPkCertificate;

    public static final String SERIALIZED_NAME_CASD_PK_JWK = "casdPkJwk";
    @SerializedName(SERIALIZED_NAME_CASD_PK_JWK)
    private JsonWebKey casdPkJwk;

    public static final String SERIALIZED_NAME_SEMS_PK_CERTIFICATE = "semsPkCertificate";
    @SerializedName(SERIALIZED_NAME_SEMS_PK_CERTIFICATE)
    private String semsPkCertificate;

    public SpsdInfo aid(String aid) {
        this.aid = aid;
        return this;
    }

    /**
     * Description:  The AID for the Service Provider Security Domain.
     * Data Type:  String. Hex-encoded data (case-insensitive).
     * Max Length:  64
     * Conditional – required in a Check Eligibility request.
     *
     * @return aid
     */
    @javax.annotation.Nullable
    @ApiModelProperty(example = "", value = "The AID for the Service Provider Security Domain. Max Length:  64")
    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public SpsdInfo appletInstanceAid(String appletInstanceAid) {
        this.appletInstanceAid = appletInstanceAid;
        return this;
    }

    /**
     * Description:  The AID for the Mastercard applet instance.
     * Data Type:  String. Hex-encoded data (case-insensitive).
     * Max Length:  64
     * Required:  Yes
     *
     * @return appletInstanceAid
     */
    @ApiModelProperty(required = true, value = "The AID for the Mastercard applet instance. Max Length:  64")
    public String getAppletInstanceAid() {
        return appletInstanceAid;
    }

    public void setAppletInstanceAid(String appletInstanceAid) {
        this.appletInstanceAid = appletInstanceAid;
    }

    public SpsdInfo spsdSequenceCounter(String spsdSequenceCounter) {
        this.spsdSequenceCounter = spsdSequenceCounter;
        return this;
    }

    /**
     * Description:  The current value of Secure Channel Sequence Counter as maintained
     * by the Security Domain.
     * Data Type:  String. Hex-encoded data (case-insensitive).
     * Max Length:  32
     * Required:  Conditional – required in a Check Eligibility request.
     *
     * @return spsdSequenceCounter
     */
    @javax.annotation.Nullable
    @ApiModelProperty(value = "The current value of Secure Channel Sequence Counter as maintained by the Security Domain. Max Length:  32")
    public String getSpsdSequenceCounter() {
        return spsdSequenceCounter;
    }

    public void setSpsdSequenceCounter(String spsdSequenceCounter) {
        this.spsdSequenceCounter = spsdSequenceCounter;
    }

    public SpsdInfo rgk(String rgk) {
        this.rgk = rgk;
        return this;
    }

    /**
     * Description:  The randomly-generated key per GlobalPlatform specifications,
     * encrypted by the Mastercard public key (PK.AP.CT) and signed by the
     * CASD; as described in the Mastercard Digital Enablement Service –
     * Embedded Mobile PayPass Payments Configuration Description.
     * Data Type:  String. Hex-encoded data (case-insensitive).
     * Max Length:  2000
     * Required:  Conditional – required in a Check Eligibility request.
     *
     * @return spsdSequenceCounter
     */
    @javax.annotation.Nullable
    @ApiModelProperty(example = "", value = "The randomly-generated key per GlobalPlatform specifications, encrypted by the Mastercard public key (PK.AP.CT) and signed by the CASD; as described in the Mastercard Digital Enablement Service – Embedded Mobile PayPass Payments Configuration Description. Max Length:  2000")
    public String getRgk() {
        return rgk;
    }

    public void setRgk(String rgk) {
        this.rgk = rgk;
    }

    public SpsdInfo casdPkCertificate(String casdPkCertificate) {
        this.casdPkCertificate = casdPkCertificate;
        return this;
    }

    /**
     * Description:  The public key certificate for the CASD which signs the RGK, used to
     * verify the signature of the RGK. This is CERT.CASD.AUT as described in
     * the Mastercard Digital Enablement Service – Embedded Mobile PayPass
     * Payments Configuration Description.
     * Data Type:  String. Hex-encoded data (case-insensitive).
     * Max Length:  2000
     * Required:  Conditional – one of 'casdPkCertificate', 'casdPkJwk', or
     * 'semsPkCertificate' is required in a Check Eligibility request.
     *
     * @return casdPkCertificate
     */
    @javax.annotation.Nullable
    @ApiModelProperty(example = "", value = "The public key certificate for the CASD which signs the RGK, used to verify the signature of the RGK. This is CERT.CASD.AUT as described in the Mastercard Digital Enablement Service – Embedded Mobile PayPass Payments Configuration Description. Max Length:  2000")
    public String getCasdPkCertificate() {
        return casdPkCertificate;
    }

    public void setCasdPkCertificate(String casdPkCertificate) {
        this.casdPkCertificate = casdPkCertificate;
    }

    public SpsdInfo casdPkJwk(JsonWebKey casdPkJwk) {
        this.casdPkJwk = casdPkJwk;
        return this;
    }

    /**
     * Description:  The public key certificate chain for the CASD which signs the RGK, used
     * to verify the signature of the RGK, provided in JSON Web Key (JWK)
     * format.
     * Note that the trusted root CA is not part of the JWK.
     * Data Type:  JsonWebKey object.
     * Max Length:  N/A
     * Required:  Conditional – one of 'casdPkCertificate', 'casdPkJwk', or
     * 'semsPkCertificate' is required in a Check Eligibility request
     * <p>
     * {@link JsonWebKey}
     *
     * @return casdPkJwk
     */
    @javax.annotation.Nullable
    @ApiModelProperty(example = "", value = "The public key certificate chain for the CASD which signs the RGK, used to verify the signature of the RGK, provided in JSON Web Key (JWK) format.")
    public JsonWebKey getCasdPkJwk() {
        return casdPkJwk;
    }

    public void setCasdPkJwk(JsonWebKey casdPkJwk) {
        this.casdPkJwk = casdPkJwk;
    }

    public SpsdInfo semsPkCertificate(String semsPkCertificate) {
        this.semsPkCertificate = semsPkCertificate;
        return this;
    }

    /**
     * Description:  The public key certificate for a Secure Element Management Service
     * provisioning.
     * Data Type:  String. Hex-encoded data (case-insensitive).
     * Max Length:  2000
     * Required:  Conditional – one of 'casdPkCertificate', 'casdPkJwk', or
     * 'semsPkCertificate' is required in a Check Eligibility request
     *
     * @return semsPkCertificate
     */
    @javax.annotation.Nullable
    @ApiModelProperty(example = "", value = "The public key certificate for a Secure Element Management Service provisioning.")
    public String getSemsPkCertificate() {
        return semsPkCertificate;
    }

    public void setSemsPkCertificate(String semsPkCertificate) {
        this.semsPkCertificate = semsPkCertificate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SpsdInfo spsdInfo = (SpsdInfo) o;
        return Objects.equals(aid, spsdInfo.aid) &&
                Objects.equals(appletInstanceAid, spsdInfo.appletInstanceAid) &&
                Objects.equals(spsdSequenceCounter, spsdInfo.spsdSequenceCounter) &&
                Objects.equals(rgk, spsdInfo.rgk) &&
                Objects.equals(casdPkCertificate, spsdInfo.casdPkCertificate) &&
                Objects.equals(casdPkJwk, spsdInfo.casdPkJwk) &&
                Objects.equals(semsPkCertificate, spsdInfo.semsPkCertificate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aid, appletInstanceAid, spsdSequenceCounter, rgk, casdPkCertificate, casdPkJwk, semsPkCertificate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SpsdInfo {");
        sb.append("    aid: ").append(toIndentedString(aid)).append("");
        sb.append("    appletInstanceAid: ").append(toIndentedString(appletInstanceAid)).append("");
        sb.append("    spsdSequenceCounter: ").append(toIndentedString(spsdSequenceCounter)).append("");
        sb.append("    rgk: ").append(toIndentedString(rgk)).append("");
        sb.append("    casdPkCertificate: ").append(toIndentedString(casdPkCertificate)).append("");
        sb.append("    casdPkJwk: ").append(toIndentedString(casdPkJwk)).append("");
        sb.append("    semsPkCertificate: ").append(toIndentedString(semsPkCertificate)).append("");
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
