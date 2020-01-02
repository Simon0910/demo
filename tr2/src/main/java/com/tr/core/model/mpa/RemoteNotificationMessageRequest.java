package com.tr.core.model.mpa;

import com.tr.core.model.common.BaseRequestSchema;
import lombok.Data;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-12-04 15:30
 */
@Data
public class RemoteNotificationMessageRequest extends BaseRequestSchema {

    /**
     * Description:  Globally unique identifier for the Wallet Provider, as assigned by MDES.
     * Commonly known as the Wallet Identifier.
     * Data Type:  String
     * Max Length:  64
     * Required:  Yes
     */
    private String paymentAppProviderId;


    /**
     * Description:  Identifier for this specific Mobile Payment App instance, unique across a
     * given Wallet Identifier.
     * Data Type:  String
     * Max Length:  48
     * Required:  Yes
     */
    private String paymentAppInstanceId;

    /**
     * Description:  Contains the Base64 encoded NotificationData object. This is the
     * message that will be sent to the device.
     * Data Type:  String. Base64-encoded data.
     * Max Length:  N/A
     * Required:  Yes
     */
    private String notificationData;

}
