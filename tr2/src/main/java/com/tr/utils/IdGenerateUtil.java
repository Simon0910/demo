package com.tr.utils;

import java.util.UUID;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-12-02 09:38
 */
public class IdGenerateUtil {
    public static final String generateRequestId() {
        return UUID.randomUUID().toString();
    }
}
