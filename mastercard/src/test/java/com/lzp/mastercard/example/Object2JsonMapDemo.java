package com.lzp.mastercard.example;

import com.lzp.mastercard.model.map.SmartMap;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-09-29 15:15
 */
public class Object2JsonMapDemo {
    public static void main(String[] args) {
        Map<String, Object> encryptedData = new HashMap<>();
        encryptedData.put("encryptedData", "v1");
        Map<String, Object> encryptedPayload = new HashMap<>();
        encryptedPayload.put("encryptedPayload", encryptedData);
        Map<String, Object> pushFundingAccount = new HashMap<>();
        pushFundingAccount.put("pushFundingAccount", encryptedPayload);

        JSONObject jsonObject = new JSONObject(pushFundingAccount);
        String s = jsonObject.toString();
        System.out.println(s);

        SmartMap smartMap = new SmartMap(s);
        System.out.println(smartMap);
    }
}
