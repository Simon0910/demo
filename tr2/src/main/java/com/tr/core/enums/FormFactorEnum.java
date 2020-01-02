package com.tr.core.enums;


import java.util.HashMap;
import java.util.Map;

/**
 * The form factor of the target provisioned device (or device being provisioned).
 * Must be one of:
 * <p>
 * Created by daniel on 2019/6/28.
 */
public enum FormFactorEnum {
    PHONE/*Mobile phone.*/,
    TABLET_OR_EREADER/*Tablet computer or e-reader.*/,
    WATCH_OR_WRISTBAND/*Watch or wristband, including a fitness band, smart strap, disposable band, watch add-on, security / ID Band*/,
    CARD/*Card*/,
    STICKER/*Sticker*/,
    PC/*PC or Laptop*/,
    DEVICE_PERIPHERAL/*Device peripherals, such as a mobile phone case or sleeve*/,
    TAG/*Tag, such as a key fob or mobile tag*/,
    JEWELRY/*Jewelry, such as a ring, bracelet, necklace and cuff links*/,
    FASHION_ACCESSORY/*Fashion accessory, such as a handbag, bag charm, glasses*/,
    GARMENT/*Garment, such as a dress*/,
    DOMESTIC_APPLIANCE/*Domestic appliance, such as a refrigerator, washing machine*/,
    VEHICLE/*Vehicle, including vehicle attached devices*/,
    MEDIA_OR_GAMING_DEVICE/*Media or gaming device, including a set top box, media player, television*/,
    ;
    /**
     *
     */
    private static final Map<String, FormFactorEnum> MAPPINGS = new HashMap<>(16);

    static {
        for (FormFactorEnum anEnum : values()) {
            MAPPINGS.put(anEnum.name(), anEnum);
        }
    }

    /**
     * get enum by key string
     *
     * @param method
     * @return
     */
    public static FormFactorEnum resolve(String method) {
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
    public static FormFactorEnum get(String method) {
        return resolve(method);
    }
}
