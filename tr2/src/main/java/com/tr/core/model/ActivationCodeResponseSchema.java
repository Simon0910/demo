package com.tr.core.model;

import com.tr.core.model.common.BaseResponseSchema;

import java.util.Objects;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-23 15:49
 */
public class ActivationCodeResponseSchema extends BaseResponseSchema {

    public ActivationCodeResponseSchema responseHost(String responseHost) {
        this.responseHost = responseHost;
        return this;
    }

    public ActivationCodeResponseSchema responseId(String responseId) {
        this.responseId = responseId;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        ActivationCodeResponseSchema that = (ActivationCodeResponseSchema) o;
        return Objects.equals(responseHost, that.responseHost)
                && Objects.equals(responseId, that.responseId)
                && Objects.equals(errorCode, that.errorCode)
                && Objects.equals(errorDescription, that.errorDescription)
                && Objects.equals(errors, that.errors)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseHost, responseId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ActivationCodeResponseSchema {");
        sb.append("    responseHost :").append(toIndentedString(responseHost)).append("");
        sb.append("    responseId :").append(toIndentedString(responseId)).append("");
        sb.append("    errorCode :").append(toIndentedString(errorCode)).append("");
        sb.append("    errorDescription :").append(toIndentedString(errorDescription)).append("");
        sb.append("    errors :").append(toIndentedString(errors)).append("");
        sb.append('}');
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
