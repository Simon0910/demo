/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.lzp.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 全局配置类
 *
 * @author jeeplus
 * @version 2014-06-25
 */
public class Global {

    /**
     * 当前对象实例
     */
    private static Global global = new Global();

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = new ConcurrentHashMap<>();

    /**
     * 属性文件加载对象
     */
    private static PropertiesLoader loader = new PropertiesLoader("config.properties");


    /**
     * 获取当前对象实例
     */
    public static Global getInstance() {
        return global;
    }

    /**
     * 获取配置
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null) {
            value = loader.getProperty(key);
            map.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }


    /**
     * 写入properties文件 和 map全局属性值
     *
     * @param key   名称
     * @param value 值
     */
    public static void modifyConfig(String key, String value) {
        try {
            // 从输入流中读取属性列表（键和元素对）
            Properties prop = getProperties();
            prop.setProperty(key, value);
            String path = Global.class.getResource("config.properties").getPath();
            FileOutputStream outputFile = new FileOutputStream(path);
            prop.store(outputFile, "modify");
            outputFile.close();
            outputFile.flush();
            map.put(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回　Properties
     *
     * @param
     * @return
     */
    public static Properties getProperties() {
        Properties prop = new Properties();
        try {
            InputStream inputStream = Global.class.getResourceAsStream("config.properties");
            prop.load(inputStream);
        } catch (Exception e) {
            return null;
        }
        return prop;
    }

}
