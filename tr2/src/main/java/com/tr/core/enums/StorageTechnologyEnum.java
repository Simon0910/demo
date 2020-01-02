package com.tr.core.enums;


import java.util.HashMap;
import java.util.Map;

/**
 * The architecture or technology used for token storage.
 * Must be one of:
 * <p>
 * Created by daniel on 2019/6/28.
 */
public enum StorageTechnologyEnum {
    DEVICE_MEMORY, DEVICE_MEMORY_PROTECTED_TPM, TEE, SE, SERVER, VEE;
    /**
     *
     */
    private static final Map<String, StorageTechnologyEnum> MAPPINGS = new HashMap<>(8);

    static {
        for (StorageTechnologyEnum anEnum : values()) {
            MAPPINGS.put(anEnum.name(), anEnum);
        }
    }

    /**
     * get enum by key string
     *
     * @param method
     * @return
     */
    public static StorageTechnologyEnum resolve(String method) {
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
    public static StorageTechnologyEnum get(String method) {
        return resolve(method);
    }
}
