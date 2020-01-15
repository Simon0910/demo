package com.example.mvc.rest;

/**
 * @author liuzhipeng
 * @description
 * @create 2020-01-15 10:13
 */
public enum ResponseCodeEnum {
    SUCCESS("SUCCESS", "SUCCESS"),
    ERROR("ERROR", "ERROR"),
    FAIL("FAIL", "FAIL"),
    ;

    String code;
    String desc;

    ResponseCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}