package com.tr.core.model;

import com.google.gson.annotations.SerializedName;
import com.tr.core.enums.ActivateResultEnum;
import com.tr.core.model.common.BaseResponseSchema;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-24 11:01
 */
public class ActivateResponseSchema extends BaseResponseSchema {

    public static final String SERIALIZED_NAME_RESULT = "result";
    @SerializedName(SERIALIZED_NAME_RESULT)
    private String result;

    public ActivateResponseSchema responseHost(String responseHost) {
        this.responseHost = responseHost;
        return this;
    }

    public ActivateResponseSchema responseId(String responseId) {
        this.responseId = responseId;
        return this;
    }

    public ActivateResponseSchema errors(List<Error> errors) {
        this.errors = errors;
        return this;
    }

    public ActivateResponseSchema addErrorsItem(Error errorsItem) {
        if (this.errors == null) {
            this.errors = new ArrayList<Error>();
        }
        this.errors.add(errorsItem);
        return this;
    }

    public ActivateResponseSchema result(java.lang.String result) {
        this.result = result;
        return this;
    }

    /**
     * Description:  Whether the activation request was successful.
     * Success will result in MDES attempting to complete the provisioning
     * process. MDES will notify the Token Requestor when the Token is ready
     * to transact using the Notify Token Updated API (see Section 3.3.1).
     * Must be one of:
     * {@link ActivateResultEnum}
     * Data Type:  String
     * Max Length:  64
     * Required:  Yes
     *
     * @return result
     */
    @ApiModelProperty(example = "SUCCESS", required = true, value = "Whether the activation request was successful. " +
            "Success will result in MDES attempting to complete the provisioning process. MDES will notify the Token " +
            "Requestor when the Token is ready to transact using the Notify Token Updated API. ")
    public String getResult() {
        return result;
    }


    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        ActivateResponseSchema that = (ActivateResponseSchema) o;
        return Objects.equals(responseHost, that.responseHost) &&
                Objects.equals(responseId, that.responseId) &&
                Objects.equals(result, that.result) &&
                Objects.equals(errorCode, that.errorCode) &&
                Objects.equals(errorDescription, that.errorDescription) &&
                Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseHost, responseId, result, errors);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ActivateResponseSchema {");
        sb.append("    responseHost :").append(toIndentedString(responseHost)).append("");
        sb.append("    responseId :").append(toIndentedString(responseId)).append("");
        sb.append("    result :").append(toIndentedString(result)).append("");
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
