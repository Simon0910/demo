package com.tr.core.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-22 15:26
 */
public class ApplicableCardInfo {
    public static final String SERIALIZED_NAME_IS_SECURITY_CODE_APPLICABLE = "isSecurityCodeApplicable";
    @SerializedName(SERIALIZED_NAME_IS_SECURITY_CODE_APPLICABLE)
    private String isSecurityCodeApplicable;

    public ApplicableCardInfo isSecurityCodeApplicable(String isSecurityCodeApplicable) {
        this.isSecurityCodeApplicable = isSecurityCodeApplicable;
        return this;
    }

    /**
     * Description:  Whether a CVC2 is applicable for this card product being digitized.
     * Must be one of:
     * true  CVC2 is applicable
     * false  CVC2 is not applicable
     * Data Type:  Boolean
     * Max Length:  5
     * Required:  Yes
     *
     * @return isSecurityCodeApplicable
     */
    @ApiModelProperty(example = "true", required = true, value = "Whether a CVC2 is applicable for this card product being digitized.")
    public String getIsSecurityCodeApplicable() {
        return isSecurityCodeApplicable;
    }

    public void setIsSecurityCodeApplicable(String isSecurityCodeApplicable) {
        this.isSecurityCodeApplicable = isSecurityCodeApplicable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApplicableCardInfo that = (ApplicableCardInfo) o;
        return Objects.equals(isSecurityCodeApplicable, that.isSecurityCodeApplicable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isSecurityCodeApplicable);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ApplicableCardInfo {");
        sb.append("    isSecurityCodeApplicable: ").append(toIndentedString(isSecurityCodeApplicable)).append("");
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
