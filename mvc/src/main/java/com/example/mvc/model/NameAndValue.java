package com.example.mvc.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-11-25 17:33
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2019-10-21T09:54:27.990+01:00[Europe/London]")
public class NameAndValue {

    private String name;

    public static final String SERIALIZED_NAME_VALUE = "value";
    @SerializedName(SERIALIZED_NAME_VALUE)
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NameAndValue value(String value) {
        this.value = value;
        return this;
    }

    @ApiModelProperty(example = "f9f027e5-629d-11e3-949a-0800200c9a66", required = true, value = "The Eligibility Receipt value to be passed back to MDES in the Digitize API. MDES guarantees the Eligibility Receipt value to be a cryptographically strong random number. Opaque value to be passed back to MDES as is. ")
    @NotBlank
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
