package com.mtf;

import com.alibaba.fastjson.JSON;
import com.tr.core.ApiClient;
import com.tr.core.ApiConfig;
import com.tr.core.Configuration;
import com.tr.core.Environment;
import com.tr.core.api.GetAssetApi;
import com.tr.core.model.AssetResponseSchema;
import io.swagger.annotations.Api;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-14 17:51
 */
public class GetAssetApiTest {

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
            ;

        GetAssetApi api = new GetAssetApi(client);

        // AssetResponseSchema asset = api.getAsset("3789637f-32a1-4810-a138-4bf34501c509");
        AssetResponseSchema asset = api.getAsset("8904093a-556d-4636-aa19-f298084193ed");
        System.out.println(JSON.toJSONString(asset));
    }
}
