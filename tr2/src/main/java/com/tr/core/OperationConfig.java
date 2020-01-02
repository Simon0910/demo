package com.tr.core;

import java.util.List;

/**
 * Created by andrearizzini on 24/08/2016.
 */
public class OperationConfig {

    String resourcePath;
    List<String> queryParams;
    List<String> headerParams;

    public String getResourcePath() {
        return resourcePath;
    }


    public List<String> getQueryParams() {
        return queryParams;
    }

    public List<String> getHeaderParams() {
        return headerParams;
    }

    public OperationConfig(String resourcePath, List<String> queryParams,
                           List<String> headerParams) {
        this.resourcePath = resourcePath;
        this.queryParams = queryParams;
        this.headerParams = headerParams;
    }


}
