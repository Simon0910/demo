package com.example.mvc.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Slf4j
public class ExtractRestTemplate extends FilterRestTemplate {
    private RestTemplate restTemplate;

    public ExtractRestTemplate(RestTemplate restTemplate) {
        super(restTemplate);
        this.restTemplate = restTemplate;
    }

    public <T> ApiResponse<T> exchange(String url,
                                       Object localVarPostBody,
                                       HttpHeaders httpHeaders,
                                       HttpMethod httpMethod,
                                       Class<T> responseType,
                                       Object... uriVariables) throws ApiException {
            checkParams(httpMethod, responseType);
            String requestBody = null;
            HttpEntity<Object> httpEntity = null;
            switch (httpMethod) {
                case POST:
                case PUT:
                    requestBody = JSONObject.toJSONString(localVarPostBody);
                    httpEntity = new HttpEntity<>(requestBody, httpHeaders);
                    break;
                case GET:
                    httpEntity = new HttpEntity<>(null, httpHeaders);
                    break;
                default:
                    throw new ApiException("HttpMethod \"" + httpMethod.toString() + "\" is not supported");
            }
            ResponseEntity<String> response = restTemplate.exchange(url, httpMethod, httpEntity, String.class,
                    uriVariables);
            return handleResponse(response, responseType);
    }

    private <T> void checkParams(HttpMethod httpMethod, Class<T> responseType) throws ApiException {
        if (Objects.isNull(responseType)) {
            // the returnType is not defined,
            throw new ApiException("responseType can not be null");
        }
        if (httpMethod == null) {
            throw new ApiException("httpMethod can not be null");
        }
    }

    private <T> ApiResponse<T> handleResponse(ResponseEntity<String> response, Class<T> responseType) throws ApiException {
        if (response == null) {
            return new ApiResponse<T>(ResponseCodeEnum.FAIL.getCode(), ResponseCodeEnum.FAIL.getDesc(), null, null);
        }
        HttpStatus statusCode = response.getStatusCode();
        Map<String, List<String>> headers = convertHttpHeaders(response.getHeaders());
        T data = convertBody(response.getBody(), responseType);
        if (statusCode.is2xxSuccessful()) {
            if (HttpStatus.NO_CONTENT == statusCode) {
                // the status code is 204 (No Content)
                // or body is null
                return new ApiResponse<T>(ResponseCodeEnum.FAIL.getCode(), ResponseCodeEnum.FAIL.getDesc(),
                        headers, data);
            }
            return new ApiResponse<T>(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getDesc(),
                    headers, data);
        }
        return new ApiResponse<T>(ResponseCodeEnum.FAIL.getCode(), ResponseCodeEnum.FAIL.getDesc(), headers, data);
    }

    private <T> T convertBody(String body, Class<T> responseType) throws ApiException {
        if (Objects.isNull(body)) {
            return null;
        }
        return JSON.parseObject(body, responseType);
    }

    private Map<String, List<String>> convertHttpHeaders(HttpHeaders headers) {
        Map<String, List<String>> result = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        if (headers == null) {
            return result;
        }
        Set<Map.Entry<String, List<String>>> entries = headers.entrySet();
        if (entries == null) {
            return result;
        }
        for (Map.Entry<String, List<String>> map : entries) {
            String name = map.getKey().toLowerCase(Locale.US);
            List<String> values = result.get(name);
            if (values == null) {
                result.put(name, map.getValue());
            } else {
                values.addAll(map.getValue());
                result.put(name, values);
            }
        }
        return result;
    }

}