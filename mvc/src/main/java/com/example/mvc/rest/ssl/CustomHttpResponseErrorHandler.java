package com.example.mvc.rest.ssl;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * @author liuzhipeng
 * @description
 * @create 2020-01-08 17:59
 */
public class CustomHttpResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        HttpStatus statusCode = clientHttpResponse.getStatusCode();
        System.out.println("hasError statusCode = " + statusCode);
        int unknownStatusCode = clientHttpResponse.getRawStatusCode();
        HttpStatus statusCode2 = HttpStatus.resolve(unknownStatusCode);
        return statusCode2 != null ? hasError(statusCode2) : hasError(unknownStatusCode);
    }

    private boolean hasError(int unknownStatusCode) {
        HttpStatus.Series series = HttpStatus.Series.resolve(unknownStatusCode);
        return (series == HttpStatus.Series.CLIENT_ERROR || series == HttpStatus.Series.SERVER_ERROR);
    }

    private boolean hasError(HttpStatus statusCode) {
        return statusCode.isError();
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        // 默认处理非200的返回，会抛异常
        HttpStatus statusCode = clientHttpResponse.getStatusCode();
        System.out.println("handleError statusCode = " + statusCode);
        int unknownStatusCode = clientHttpResponse.getRawStatusCode();
        System.out.println(unknownStatusCode);
        HttpStatus statusCode2 = HttpStatus.resolve(unknownStatusCode);
        System.out.println(statusCode2);
    }


}
