package com.lzp.mastercard.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrearizzini on 22/11/2016.
 */
public enum Environment {
    PRODUCTION, SANDBOX, SANDBOX_STATIC, SANDBOX_MTF, SANDBOX_IFT, STAGE, DEV, PRODUCTION_MTF, PRODUCTION_ITF, STAGE_MTF, STAGE_ITF, ITF, PERF, LOCALHOST, OTHER,
    MTF;

    public static Environment parse(String value) {
        if (value != null) {
            for (Environment environment : Environment.values()) {
                if (value.equalsIgnoreCase(environment.name())) {
                    return environment;
                }
            }
        }
        return null;
    }

    public static final Map<Environment, String[]> MAPPINGS = new HashMap();

    static {
        MAPPINGS.put(Environment.PRODUCTION, new String[]{"https://api.mastercard.com", null});
        MAPPINGS.put(Environment.PRODUCTION_MTF, new String[]{"https://api.mastercard.com", "mtf"});
        MAPPINGS.put(Environment.PRODUCTION_ITF, new String[]{"https://api.mastercard.com", "itf"});

        MAPPINGS.put(Environment.SANDBOX, new String[]{"https://sandbox.api.mastercard.com", null});
        MAPPINGS.put(Environment.SANDBOX_MTF, new String[]{"https://sandbox.api.mastercard.com", "mtf"});
        MAPPINGS.put(Environment.SANDBOX_IFT, new String[]{"https://sandbox.api.mastercard.com", "itf"});
        MAPPINGS.put(Environment.SANDBOX_STATIC, new String[]{"https://sandbox.api.mastercard.com", "static"});

        MAPPINGS.put(Environment.STAGE, new String[]{"https://stage.api.mastercard.com", null});
        MAPPINGS.put(Environment.STAGE_MTF, new String[]{"https://stage.api.mastercard.com", "mtf"});
        MAPPINGS.put(Environment.STAGE_ITF, new String[]{"https://stage.api.mastercard.com", "itf"});

        MAPPINGS.put(Environment.DEV, new String[]{"https://dev.api.mastercard.com", null});
        MAPPINGS.put(Environment.LOCALHOST, new String[]{"http://localhost:8081", null});

        //@arizzini: STP new environment
        MAPPINGS.put(Environment.ITF, new String[]{"https://itf.api.mastercard.com", null});
        MAPPINGS.put(Environment.PERF, new String[]{"https://perf.api.mastercard.com", null});

        MAPPINGS.put(Environment.MTF, new String[]{"https://mtf.services.mastercard.com", "mtf"});
    }

    // https://itf.api.mastercard.com
}
