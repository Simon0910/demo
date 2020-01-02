/*
 * Copyright 2015 MasterCard International.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of
 * conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials
 * provided with the distribution.
 * Neither the name of the MasterCard International Incorporated nor the names of its
 * contributors may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT
 * SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER
 * IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 * IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 */

package com.lzp.mastercard;

import com.lzp.mastercard.exception.ApiException;
import com.lzp.mastercard.exception.SdkException;
import com.lzp.mastercard.model.*;
import com.lzp.mastercard.security.Authentication;
import com.lzp.mastercard.security.CryptographyInterceptor;
import com.lzp.mastercard.security.HttpRequestCryptographyInterceptor;
import org.apache.http.*;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiController {

    // User agent string sent with requests.
    private static String USER_AGENT = null;
    private static String HEADER_SEPARATOR = ";";

    public static final String ENVIRONMENT_IDENTIFIER = "#env";

    private static final String[] CORRELATION_HEADERS = {"correlation-id", "RequestId"};

    /**
     *
     */
    public ApiController() {

    }


    /**
     * Append parameter to URL
     *
     * @param s              instance of StringBuilder
     * @param stringToAppend e.g. max=10
     * @return StringBuilder
     */
    private StringBuilder appendToQueryString(StringBuilder s, String stringToAppend) {
        if (s.indexOf("?") == -1) {
            s.append("?");
        }

        if (s.indexOf("?") != s.length() - 1) {
            s.append("&");
        }

        s.append(stringToAppend);

        return s;
    }

    String urlEncode(Object stringToEncode) {
        try {
            return URLEncoder.encode(stringToEncode.toString(), "UTF-8")
                    .replace("+", "%20")
                    .replace("*", "%2A")
                    .replace("%7E", "~");
        } catch (UnsupportedEncodingException e) {
            return stringToEncode.toString();
        }
    }

    /**
     * This is the method which is used to replace {pathid} in the url with the values in the map.
     * Once the value in the map is used, it is removed.
     *
     * @param url       - url where the values need to be replaced.
     * @param objectMap - map containing the values which can be replace.
     * @return formatted string
     */
    String getPathWithReplacedPath(String url, Map<String, Object> objectMap) throws IllegalStateException {

        String regexToRemovePathParameters = "\\{(.*?)\\}";
        Pattern pattern = Pattern.compile(regexToRemovePathParameters);
        Matcher matcher = pattern.matcher(url);

        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String group = matcher.group(1);
            if (objectMap.containsKey(group)) {
                matcher.appendReplacement(sb, objectMap.remove(group).toString());
            } else {
                throw new IllegalStateException("Error: required parameter='" + group + "' was not found in the RequestMap ");
            }
        }
        matcher.appendTail(sb);


        String tmpResult = sb.length() > 0 ? sb.toString().replaceAll("//", "/").replaceAll("/$", "") : url;

        //arizzini: need to make sure that the correct format is maintained.
        if (!tmpResult.startsWith("/")) {
            tmpResult = "/" + tmpResult;
        }

        return tmpResult;
    }

    private URI getURI(OperationConfig operationConfig, OperationMetadata operationMetadata, RequestMap requestObject) {
        URI uri;

        //arizzini: if host config or environment config changes betweeen calls
        // we need to update the host
        String host = operationMetadata.getHost();
        try {
            new URL(host);
        } catch (MalformedURLException e) {
            throw new IllegalStateException("Invalid URL supplied for host=" + host, e);
        }

        String resourcePath = operationConfig.getResourcePath();
        if (resourcePath.contains(ENVIRONMENT_IDENTIFIER)) {
            String context = "";
            if (operationMetadata.getContext() != null) {
                context = operationMetadata.getContext();
            }
            resourcePath = resourcePath.replace(ENVIRONMENT_IDENTIFIER, context);
            //don't worry of //, they will be removed in the getPathWithReplacedPath
        }

        //arizzini: need to replace all the path variables
        String updatedType = getPathWithReplacedPath(resourcePath, requestObject);

        StringBuilder s = new StringBuilder("%s%s");

        List<Object> objectList = new ArrayList<Object>();

        //arizzini: host override, this takes precedence over all other scenarios.
        if (operationMetadata.getHost() == null) {
            //arizzini: removing last slash (/)
            objectList.add(host.replaceAll("/$", ""));
        } else {
            //arizzini: removing last slash (/)
            objectList.add(operationMetadata.getHost().replaceAll("/$", ""));
        }

        //arizzini: removing last slash (/)
        objectList.add(updatedType.replaceAll("/$", ""));

        // Add Query Params
        switch (operationConfig.getAction()) {
            case read:
            case delete:
            case list:
            case query:
                Iterator it = requestObject.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    s = appendToQueryString(s, "%s=%s");
                    objectList.add(urlEncode(entry.getKey().toString()));
                    objectList.add(urlEncode(entry.getValue().toString()));
                }
                break;
        }

        // create and update may have Query and Body parameters as part of the request.
        // Check additionalQueryParametersList
        if (operationConfig.getQueryParams().size() > 0) {
            switch (operationConfig.getAction()) {
                case create:
                case update:
                    // Get the submap of query parameters which also removes the values from objectMap
                    Map<String, Object> queryMap = subMap(requestObject, operationConfig.getQueryParams());
                    Iterator it = queryMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry entry = (Map.Entry) it.next();
                        s = appendToQueryString(s, "%s=%s");
                        objectList.add(urlEncode(entry.getKey().toString()));
                        objectList.add(urlEncode(entry.getValue().toString()));
                    }

                    break;
            }
        }

        // Use JSON (If JSON native passing this parameter can cause issues)
        if (!operationMetadata.isJsonNative()) {
            s = appendToQueryString(s, "Format=JSON");
        }

        try {
            uri = new URI(String.format(s.toString(), objectList.toArray()));
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Failed to build URI", e);
        }

        return uri;
    }

    private ContentType getContentType(OperationMetadata operationMetadata) {
        ContentType contentType = ContentType.APPLICATION_JSON;
        if (operationMetadata.getContentTypeOverride() != null) {
            contentType = ContentType.create(operationMetadata.getContentTypeOverride(), Consts.UTF_8);
        }
        return contentType;
    }

    protected HttpRequestBase getRequest(Authentication authentication, OperationConfig operationConfig,
                                         OperationMetadata operationMetadata, RequestMap requestObject) {


        Map<String, Object> headerMap = subMap(requestObject, operationConfig.getHeaderParams());
        URI uri = getURI(operationConfig, operationMetadata, requestObject);
        URI deliveryUri;
        if (ApiConfig.getReverseProxy() != null) {
            deliveryUri = URI.create(uri.toString().replace(operationMetadata.getHost(), ApiConfig.getReverseProxy()));
        } else {
            deliveryUri = uri;
        }

        //this is what is left from the parameters.
        Map<String, Object> objectMap = new LinkedHashMap<String, Object>(requestObject);
        CryptographyInterceptor interceptor = ApiConfig.getCryptographyInterceptor(uri.getPath());


        ContentType contentType = getContentType(operationMetadata);

        HttpRequestBase message = null;

        // Try set default authentication if no authentication provided
        if (authentication == null) {
            if (ApiConfig.getAuthentication() == null) {
                throw new SdkException("Authentication is null. Set \"ApiConfig.authentication\" or pass an instance of com.mastercard.api.core.security.Authentication to the method call");
            }

            authentication = ApiConfig.getAuthentication();
        }

        String payload = null;

        switch (operationConfig.getAction()) {
            case create:

                if (interceptor != null) {
                    objectMap = interceptor.encrypt(objectMap);
                    if (interceptor instanceof HttpRequestCryptographyInterceptor) {
                        headerMap = ((HttpRequestCryptographyInterceptor) interceptor).addCustomHeaders(headerMap, objectMap);
                    }
                }

                payload = JSONValue.toJSONString(objectMap);
                message = new HttpPost(deliveryUri);

                HttpEntity createEntity = new StringEntity(payload, contentType);
                ((HttpPost) message).setEntity(createEntity);
                message.setHeader(createEntity.getContentType());

                break;

            case delete:
                message = new HttpDelete(deliveryUri);
                break;

            case update:

                if (interceptor != null) {
                    objectMap = interceptor.encrypt(objectMap);
                    if (interceptor instanceof HttpRequestCryptographyInterceptor) {
                        headerMap = ((HttpRequestCryptographyInterceptor) interceptor).addCustomHeaders(headerMap, objectMap);
                    }
                }

                payload = JSONValue.toJSONString(objectMap);
                message = new HttpPut(deliveryUri);

                HttpEntity updateEntity = new StringEntity(payload, contentType);
                ((HttpPut) message).setEntity(updateEntity);
                message.setHeader(updateEntity.getContentType());

                break;

            case read:
            case list:
            case query:
                message = new HttpGet(deliveryUri);
                break;
        }

        message.setHeader(HttpHeaders.ACCEPT, contentType.toString());

        // Set other headers
        for (Map.Entry<String, Object> entry : headerMap.entrySet()) {
            //arizzini: checking for NPE, if value is null, then we simply skipp it.
            if (entry.getValue() != null) {
                message.setHeader(entry.getKey(), entry.getValue().toString());
            }
        }

        // Add user agent
        String userAgent = Constants.getCoreVersion() + "/" + operationMetadata.getApiVersion();
        if (USER_AGENT != null) {
            userAgent = userAgent + " " + USER_AGENT;
        }
        message.setHeader("User-Agent", userAgent);

        // Sign the request
        authentication.sign(uri, HttpMethod.fromAction(operationConfig.getAction()), contentType, payload, message);

        return message;
    }

    public Map<? extends String, ? extends Object> execute(Authentication auth, OperationConfig operationConfig, OperationMetadata operationMetadata, RequestMap requestObject)
            throws ApiException {

        CloseableHttpClient httpClient = createHttpClient();

        try {

            HttpRequestBase request = getRequest(auth, operationConfig, operationMetadata, requestObject);
            CryptographyInterceptor interceptor = ApiConfig.getCryptographyInterceptor(
                    request.getURI().getPath());

            int port = request.getURI().getPort();
            String scheme = request.getURI().getScheme();
            HttpHost host = new HttpHost(request.getURI().getHost(), port, scheme);

            ResponseHandler<ApiControllerResponse> responseHandler = createResponseHandler(getContentType(operationMetadata));

            ApiControllerResponse apiResponse = httpClient.execute(host, request, responseHandler);

            if (apiResponse.hasPayload()) {

                Object response = JSONValue.parse(apiResponse.getPayload());

                if (apiResponse.getStatus() < 300) {
                    if (operationConfig.getAction() == Action.list) {

                        Map<String, Object> map = new HashMap<String, Object>();
                        List list = null;

                        //arizzini:  if the response is an object we need to convert this into a map
                        if (response instanceof JSONObject) {
                            list = convertToList((Map<? extends String, ? extends Object>) response);
                        }
                        //arizzini:  if the response is an array we need simply case to a List of Maps.
                        else {
                            list = ((List<Map<? extends String, ? extends Object>>) response);
                        }
                        map.put("list", list);
                        return map;
                    } else {

                        Map<String, Object> map = null;
                        if (response instanceof JSONObject) {
                            map = (Map<String, Object>) response;

                            if (interceptor == null) {
                                return map;
                            } else {
                                if (interceptor instanceof HttpRequestCryptographyInterceptor) {
                                    map = ((HttpRequestCryptographyInterceptor) interceptor).removeCustomHeaders(apiResponse.getHttpResponse().getAllHeaders(), map);
                                }

                                return interceptor.decrypt(map);
                            }

                        } else {
                            map = new HashMap<String, Object>();
                            map.put("list", ((List<Map<? extends String, ? extends Object>>) response));
                            return map;
                        }
                    }
                } else {
                    throw new ApiException(apiResponse.getStatus(), response, getRequestId(apiResponse));
                }
            }

            return null;

        } catch (HttpResponseException e) {
            throw new ApiException("Failed to communicate with response code " + String.format("%d", e.getStatusCode()), e);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException(e.getMessage(), e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get RequestId or correlation-id header from response
     */
    private String getRequestId(ApiControllerResponse apiResponse) {
        for (String headerName : CORRELATION_HEADERS) {
            Header header = apiResponse.httpResponse.getFirstHeader(headerName);
            if (header != null) {
                return header.getValue();
            }
        }
        return null;
    }

    protected static Map<String, Object> subMap(Map<String, Object> inputMap, List<String> keyList) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<String, Object>();
        for (Map.Entry entry : inputMap.entrySet()) {
            if (keyList.contains(entry.getKey())) {
                resultMap.put(entry.getKey().toString(), entry.getValue());
            }
        }

        //arizzini: removing the values which have been filtered
        inputMap.keySet().removeAll(keyList);

        return resultMap;
    }

    /**
     * Converts an XML Gateway response to a List
     *
     * @param response
     * @return
     */
    private List convertToList(Map<? extends String, ? extends Object> response) {
        List list = new ArrayList();

        if (response.keySet().iterator().hasNext()) {
            String key = response.keySet().iterator().next();
            Map<? extends String, ? extends Object> level1 = response.get(key) instanceof Map ?
                    (Map<? extends String, ? extends Object>) response.get(key) :
                    null;

            if (level1 != null && level1.keySet().iterator().hasNext()) {
                key = level1.keySet().iterator().next();
                list = level1.get(key) instanceof List ? (List) level1.get(key) : new ArrayList<Object>();
            }
        }

        return list;
    }


    CloseableHttpClient createHttpClient() {
        return ApiConfig.getHttpClientBuilder().build();
    }

    ResponseHandler<ApiControllerResponse> createResponseHandler(final ContentType contentType) {
        return new ResponseHandler<ApiControllerResponse>() {
            @Override
            public ApiControllerResponse handleResponse(HttpResponse httpResponse) throws IOException {
                ApiControllerResponse apiResponse = new ApiControllerResponse();
                apiResponse.setHttpResponse(httpResponse);

                StatusLine statusLine = httpResponse.getStatusLine();
                apiResponse.setStatus(statusLine.getStatusCode());
                HttpEntity entity = httpResponse.getEntity();

                //arizzini: entity == null when HTTP 200
                if (entity != null) {
                    String payload = EntityUtils.toString(entity, contentType.getCharset());

                    //arizzini: if we have content, we try to parse it
                    if (!payload.isEmpty()) {
                        String responseContentType;
                        Header header = httpResponse.getFirstHeader(HttpHeaders.CONTENT_TYPE);
                        if (header == null) {
                            throw new IllegalStateException("Unknown content type. Missing Content-Type header");
                        } else {
                            responseContentType = header.getValue();
                        }

                        if (ContentType.parse(responseContentType).getMimeType().contains("json")) {
                            apiResponse.setPayload(payload);
                        } else {
                            throw new IOException(
                                    "Response was not json, it was: "
                                            + responseContentType + ". Unable to process payload. " +
                                            "Response: [ " + payload + " + ]");
                        }
                    } else {
                        //arizzini: 200 with no content like a delete.
                        apiResponse.setPayload("");
                    }
                } else {
                    //arizzini: 204 with no content like a delete.
                    apiResponse.setPayload("");
                }

                return apiResponse;

            }
        };
    }

    protected class ApiControllerResponse {
        private HttpResponse httpResponse;
        private String payload;
        private int status;

        public HttpResponse getHttpResponse() {
            return httpResponse;
        }

        public void setHttpResponse(HttpResponse httpResponse) {
            this.httpResponse = httpResponse;
        }

        public String getPayload() {
            return payload;
        }

        public void setPayload(String payload) {
            this.payload = payload;
        }

        public boolean hasPayload() {
            return payload != null && payload.length() > 0;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

}
