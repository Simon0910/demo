package com.tr.core.enums;


import java.util.HashMap;
import java.util.Map;

/**
 * The form factor of the target provisioned device (or device being provisioned).
 * Must be one of:
 * <p>
 * Created by daniel on 2019/6/28.
 */
public enum OsNameEnum {
    ANDROID, WINDOWS, TIZEN, PAGARE_EMBEDDED_OS, ANDROID_WEAR, EMBEDDED_OS;
    /**
     *
     */
    private static final Map<String, OsNameEnum> MAPPINGS = new HashMap<>(8);

    static {
        for (OsNameEnum anEnum : values()) {
            MAPPINGS.put(anEnum.name(), anEnum);
        }
    }

    /**
     * get enum by key string
     *
     * @param method
     * @return
     */
    public static OsNameEnum resolve(String method) {
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
    public static OsNameEnum get(String method) {
        return resolve(method);
    }
}
