package com.example.mvc.rest;

import lombok.Data;

/**
 * @author liuzhipeng
 * @description
 * @create 2020-01-08 16:38
 */
@Data
public class RestResponseDTO<T> {
    public static final Integer UNKNOWN_ERROR = 999999;
    private Integer statusCode;
    private String message;
    private T Data;
}
