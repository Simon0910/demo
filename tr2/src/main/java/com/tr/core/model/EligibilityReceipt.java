package com.tr.core.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * @author liuzhipeng
 * @description EligibilityReceipt
 * @create 2019-10-21 14:12
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2019-10-21T09:54:27.990+01:00[Europe/London]")
public class EligibilityReceipt {

    public static final String SERIALIZED_NAME_VALUE = "value";
    @SerializedName(SERIALIZED_NAME_VALUE)
    private String value;

    public static final String SERIALIZED_NAME_VALID_FOR_MINUTES = "validForMinutes";
    @SerializedName(SERIALIZED_NAME_VALID_FOR_MINUTES)
    public String validForMinutes;

    public EligibilityReceipt value(String value) {
        this.value = value;
        return this;
    }

    /**
     * The Eligibility Receipt value to be passed back to MDES in the Digitize API.
     * MDES guarantees the Eligibility Receipt value to be a cryptographically strong random number.
     * Opaque value to be passed back to MDES as is.
     *
     * @return value
     */
    @ApiModelProperty(example = "f9f027e5-629d-11e3-949a-0800200c9a66", required = true, value = "The Eligibility Receipt value to be passed back to MDES in the Digitize API. MDES guarantees the Eligibility Receipt value to be a cryptographically strong random number. Opaque value to be passed back to MDES as is. ")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public EligibilityReceipt validForMinutes(String validForMinutes) {
        this.validForMinutes = validForMinutes;
        return this;
    }

    /**
     * How long this Eligibility Receipt is valid for, in minutes.
     *
     * @return validForMinutes
     */
    @ApiModelProperty(example = "30", required = true, value = "How long this Eligibility Receipt is valid for, in minutes. ")
    public String getValidForMinutes() {
        return validForMinutes;
    }

    public void setValidForMinutes(String validForMinutes) {
        this.validForMinutes = validForMinutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        EligibilityReceipt that = (EligibilityReceipt) o;
        return Objects.equals(value, that.value) &&
                Objects.equals(validForMinutes, that.validForMinutes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, validForMinutes);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EligibilityReceipt {");
        sb.append("    value: ").append(toIndentedString(value)).append("");
        sb.append("    validForMinutes: ").append(toIndentedString(validForMinutes)).append("");
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
