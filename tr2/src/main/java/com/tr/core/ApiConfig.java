package com.tr.core;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-18 10:57
 */
public class ApiConfig {
    public ApiConfig() {
        Configuration configuration = new Configuration();
        configuration.setIsEnable(true);
        configuration.setDefaultKeyStorePassword("123456");
        configuration.setDefaultKeyStorePath("E:/JD_RES/https/CJD_DolfinWallet_XMLGW_MTF_160680/keystore.jks");
        configuration.enableSSL();
        new com.tr.core.encrypt.config.Configuration();
        new com.tr.core.oauth1.config.Configuration();
    }
}
