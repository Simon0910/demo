package com.example.mvc.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-11-25 17:41
 */
@Data
@ApiModel(value = "DigitizeRequest", description = "Digitize Request")
public class HasNAV {

    @ApiModelProperty(value = "The Eligibility Receipt value as provided by MDES in the Check Eligibility response (see Section 3.2.1.5).")
    @javax.annotation.Nullable
    @Valid
    private NameAndValue nameAndValue;
}
