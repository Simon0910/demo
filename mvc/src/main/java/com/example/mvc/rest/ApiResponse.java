package com.example.mvc.rest;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author liuzhipeng
 * @description
 * @create 2020-01-08 16:38
 */
@Data
public class ApiResponse<T> {
    public static final Integer UNKNOWN_ERROR = 999999;
    final private int statusCode;
    final private String message;
    final private Map<String, List<String>> headers;
    final private T data;

    public ApiResponse(int statusCode, String message, Map<String, List<String>> headers, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.headers = headers;
        this.data = data;
    }
}
