package com.example.mvc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author dataochen
 * @Description 唯一id生成器
 * @date: 2019/7/5 12:55
 */
public class IdGenerateUtils {

    /**
     *
     * @return
     */
    public static String getUuidFor32Bit() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成业务单号
     *
     * @return
     */
    public static String getBusinessNo() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date()) + String.valueOf((int) (89999999 * Math.random() + 10000000));
    }

    public static void main(String[] args) {
        System.out.println(getBusinessNo());
        System.out.println(String.valueOf((int) (89999999 * Math.random() + 10000000)));
    }


}
