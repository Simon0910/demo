package com.tr.core.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-22 11:16
 */
public enum KtyEnum {
    EC, RSA;

    private static final Map<String, KtyEnum> MAPPINGS = new HashMap<>(2);

    static {
        for (KtyEnum anEnum : values()) {
            MAPPINGS.put(anEnum.name(), anEnum);
        }
    }

    public static KtyEnum resolve(String method) {
        return (method != null ? MAPPINGS.get(method) : null);
    }

    public boolean matches(String method) {
        return (this == resolve(method));
    }

    public static boolean exists(String method) {
        return (resolve(method) != null);
    }

    public static KtyEnum get(String method) {
        return resolve(method);
    }

}
