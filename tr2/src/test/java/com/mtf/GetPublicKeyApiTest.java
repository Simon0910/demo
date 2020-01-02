package com.mtf;

import com.tr.core.ApiClient;
import com.tr.core.ApiConfig;
import com.tr.core.Environment;
import com.tr.core.api.GetAssetApi;
import com.tr.core.api.GetPublicKeyApi;
import com.tr.core.model.AssetResponseSchema;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-11-13 14:11
 */
public class GetPublicKeyApiTest {

    public static void main(String[] args) throws Exception {
        // 初始化配置
        new ApiConfig();

        // 使用默认配置
        // GetAssetApi api = new GetAssetApi();

        // 自定义配置
        ApiClient client = new ApiClient();
        // This attribute takes precedence over the environment variable
        client
            // .setBasePath("https://mtf.services.mastercard.com/mtf")
            // the environment variable
            .setCurrentEnvironment(Environment.MTF)
            // The default is false
            .setDebugging(true)
        .setConnectTimeout(10000)
        .setReadTimeout(10000)
        ;

        GetPublicKeyApi api = new GetPublicKeyApi(client);
        String publicKey = api.getPublicKey();

        System.out.println(publicKey);
    }
}
