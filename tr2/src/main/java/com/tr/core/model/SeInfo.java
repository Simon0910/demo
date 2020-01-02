package com.tr.core.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * A.35     P252
 * Created by daniel on 2019/6/28.
 */
public class SeInfo {
    public static final String SERIALIZED_NAME_SE_ID = "seId";
    @SerializedName(SERIALIZED_NAME_SE_ID)
    private String seId;

    public static final String SERIALIZED_NAME_SE_CAPABILITIES = "seCapabilities";
    @SerializedName(SERIALIZED_NAME_SE_CAPABILITIES)
    private SeCapabilities seCapabilities;

    public SeInfo seId(String seId) {
        this.seId = seId;
        return this;
    }

    /**
     * Identifier of the target SE to be provisioned.       __Max Length:128__
     *
     * @return seId
     */
    @ApiModelProperty(required = true, value = "Identifier of the target SE to be provisioned.        __Max Length:128__")
    public String getSeId() {
        return seId;
    }

    public void setSeId(String seId) {
        this.seId = seId;
    }

    public SeInfo seCapabilities(SeCapabilities seCapabilities) {
        this.seCapabilities = seCapabilities;
        return this;
    }

    /**
     * @return seCapabilities
     */
    @javax.annotation.Nullable
    @ApiModelProperty(value = "Contains information about the capabilities of this SE, which are checked for device eligibility.")
    public SeCapabilities getSeCapabilities() {
        return seCapabilities;
    }

    public void setSeCapabilities(SeCapabilities seCapabilities) {
        this.seCapabilities = seCapabilities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SeInfo seInfo = (SeInfo) o;
        return Objects.equals(seId, seInfo.seId) &&
                Objects.equals(seCapabilities, seInfo.seCapabilities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seId, seCapabilities);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SeInfo {");
        sb.append("    seId: ").append(toIndentedString(seId)).append("");
        sb.append("    seCapabilities: ").append(toIndentedString(seCapabilities)).append("");
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
