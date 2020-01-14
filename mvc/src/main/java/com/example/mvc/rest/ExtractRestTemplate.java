package com.example.mvc.rest;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

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
                                       Object... uriVariables) throws RestClientException {
        try {
            String requestBody = JSONObject.toJSONString(localVarPostBody);
            HttpEntity<Object> httpEntity = new HttpEntity<>(requestBody, httpHeaders);
            ResponseEntity<T> response = restTemplate.exchange(url, httpMethod, httpEntity, responseType, uriVariables);
            return new ApiResponse<T>(response.getStatusCodeValue(), response.getStatusCode().name(), convertHttpHeaders(response.getHeaders()), response.getBody());
        } catch (Exception e) {
            return new ApiResponse<T>(ApiResponse.UNKNOWN_ERROR, "unknown error", null, null);
        }
    }

    Map<String, List<String>> convertHttpHeaders(HttpHeaders headers) {
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