package com.example.mvc.aop;

import lombok.Data;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-28 18:38
 */
@Data
public class ValidateResult {
    public String field;
    public String errorMessage;

    public Boolean isValidatePass = true;
    public String message;

    public ValidateResult(Boolean isValidatePass, String field, String errorMessage) {
        this.isValidatePass = isValidatePass;
        this.field = field;
        this.errorMessage = errorMessage;
    }

    public ValidateResult(Boolean isValidatePass, String message) {
        this.isValidatePass = isValidatePass;
        this.message = message;
    }

    public ValidateResult() {}

    /**
     * @return isValidatePass
     */
    public Boolean isValidatePass() {
        return isValidatePass;
    }

}
