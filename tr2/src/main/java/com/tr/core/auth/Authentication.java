package com.tr.core.auth;

import com.tr.core.Pair;

import java.util.List;
import java.util.Map;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-14 17:13
 */
public interface Authentication {
    /**
     * Apply authentication settings to header and query params.
     *
     * @param queryParams  List of query parameters
     * @param headerParams Map of header parameters
     */
    void applyToParams(List<Pair> queryParams, Map<String, String> headerParams);
}
