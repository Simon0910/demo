package com.tr.core.enums;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * reason of deleting token.
 * Must be one of:
 *
 * Created by daniel on 2019/6/28.
 */
public enum DeleteCausedByEnum {
    CARDHOLDER, TOKEN_REQUESTOR,
    ;
    /**
     *
     */
    private static final Map<String, DeleteCausedByEnum> MAPPINGS = new HashMap<>(2);

    static {
        for (DeleteCausedByEnum anEnum : values()) {
            MAPPINGS.put(anEnum.name(), anEnum);
        }
    }

    /**
     * get enum by key string
     * @param method
     * @return
     */

    public static DeleteCausedByEnum resolve(String method) {
        return (method != null ? MAPPINGS.get(method) : null);
    }


    /**
     * verify key string is exists in the enum
     * @param method
     * @return
     */
    public boolean matches(String method) {
        return (this == resolve(method));
    }

    //********************************************************************//

    /**
     * check the key string  exists
     * @param method
     * @return
     */
    public static boolean exists( String method) {
        return (resolve(method)!=null);
    }

    /**
     * get enum by key string
     * @param method
     * @return
     */
    public static DeleteCausedByEnum get(String method) {
        return resolve(method);
    }
}
