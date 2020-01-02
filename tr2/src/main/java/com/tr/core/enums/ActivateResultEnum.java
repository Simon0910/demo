package com.tr.core.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Success will result in MDES attempting to complete the provisioning process. MDES will notify the Token Requestor
 * when the Token is ready to transact using the Notify Token Updated API (see Section 3.3.1).
 * Must be one of:
 * <p>
 * Created by daniel on 2019/6/28.
 */
public enum ActivateResultEnum {
    SUCCESS,
    INCORRECT_CODE,
    INCORRECT_CODE_RETRIES_EXCEEDED,
    EXPIRED_CODE,
    INCORRECT_TAV,
    EXPIRED_SESSION,
    ;
    /**
     *
     */
    private static final Map<String, ActivateResultEnum> MAPPINGS = new HashMap<>(8);

    static {
        for (ActivateResultEnum anEnum : values()) {
            MAPPINGS.put(anEnum.name(), anEnum);
        }
    }

    /**
     * get enum by key string
     *
     * @param method
     * @return
     */

    public static ActivateResultEnum resolve(String method) {
        return (method != null ? MAPPINGS.get(method) : null);
    }


    /**
     * verify key string is exists in the enum
     *
     * @param method
     * @return
     */
    public boolean matches(String method) {
        return (this == resolve(method));
    }

    //********************************************************************//

    /**
     * check the key string  exists
     *
     * @param method
     * @return
     */
    public static boolean exists(String method) {
        return (resolve(method) != null);
    }

    /**
     * get enum by key string
     *
     * @param method
     * @return
     */
    public static ActivateResultEnum get(String method) {
        return resolve(method);
    }
}
