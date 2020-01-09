package com.example.mvc.rest;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class ExtractRestTemplate extends FilterRestTemplate {
    private RestTemplate restTemplate;

    public ExtractRestTemplate(RestTemplate restTemplate) {
        super(restTemplate);
        this.restTemplate = restTemplate;
    }

    public <T> RestResponseDTO<T> exchange(String url, Object request, Class<T> responseType,
                                           Object... uriVariables) throws RestClientException {
        RestResponseDTO<T> restResponseDTO = new RestResponseDTO<T>();
        try {
            HttpMethod httpMethod = HttpMethod.GET;

            HttpHeaders httpHeaders = new HttpHeaders();
            // httpHeaders.set("x-request-id", "uuidFor32Bit");
            // httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            // httpHeaders.setContentType(MediaType.TEXT_HTML);

            String requestBody = JSONObject.toJSONString(""); ;

            HttpEntity<Object> httpEntity = new HttpEntity<>(requestBody, httpHeaders);
            ResponseEntity<T> responseEntity = restTemplate.exchange(url, httpMethod, httpEntity, responseType, uriVariables);

            restResponseDTO.setData(responseEntity.getBody());
            restResponseDTO.setMessage(responseEntity.getStatusCode().name());
            restResponseDTO.setStatusCode(responseEntity.getStatusCodeValue());

        } catch (Exception e) {
            restResponseDTO.setStatusCode(RestResponseDTO.UNKNOWN_ERROR);
            restResponseDTO.setMessage(e.getMessage());
            restResponseDTO.setData(null);
        }

        return restResponseDTO;
    }
}