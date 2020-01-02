package com.demo; /**
 * Script-Name: example_asset_request
 */

import com.lzp.mastercard.ApiConfig;
import com.lzp.mastercard.api.GetAsset;
import com.lzp.mastercard.exception.ApiException;
import com.lzp.mastercard.model.Environment;
import com.lzp.mastercard.model.RequestMap;
import com.lzp.mastercard.model.map.SmartMap;
import com.lzp.mastercard.security.mdes.MDESCryptography;
import com.lzp.mastercard.security.oauth.OAuthAuthentication;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class example_asset_request {

    public static void main(String[] args) {

        String consumerKey = "SYxB5pWNgbA7znnsjqrW3j3cPQKswcoSVxJcVhHJcaf24271!1b1ee9c2908d41979333f37d5370c9b30000000000000000";
        String keyAlias = "keyalias";
        String keyPassword = "keystorepassword";
        InputStream is = example_asset_request.class.getClassLoader().getResourceAsStream("cert/token-reuqest-sandbox.p12");
        ApiConfig.setAuthentication(new OAuthAuthentication(consumerKey, is, keyAlias, keyPassword));

        ApiConfig.setDebug(true);
        ApiConfig.setEnvironment(Environment.MTF);

        // SANDBOX
        InputStream publicCertificate = RequestMap.class.getClassLoader().getResourceAsStream("cert/Public Key-Encrypt.crt");
        InputStream privateKey = RequestMap.class.getClassLoader().getResourceAsStream("cert/Private Key-Decrypt.key");
        String publicKeyFingerprint = "8FC11150A7508F14BACA07285703392A399CC57C";
        ApiConfig.addCryptographyInterceptor(new MDESCryptography(publicCertificate, privateKey, publicKeyFingerprint));

        // RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
        // // Limit the amount of time waiting for data
        // requestConfigBuilder.setSocketTimeout(1000);
        // // Limit the amount of time waiting for a connection to the server to be established
        // requestConfigBuilder.setConnectTimeout(1000);
        // // Limit the amount of time waiting for a connection from the connection pool
        // requestConfigBuilder.setConnectionRequestTimeout(1000);
        // ApiConfig.getHttpClientBuilder().setDefaultRequestConfig(requestConfigBuilder.build());

        try {
            RequestMap map = new RequestMap();
            map.set("AssetId", "3789637f-32a1-4810-a138-4bf34501c509");
            GetAsset response = GetAsset.read("", map);

            out(response, "mediaContents[0].type"); //-->image/png
            out(response, "mediaContents[0].height"); //-->375
            out(response, "mediaContents[0].width"); //-->375
            out(response, "mediaContents[0].data"); //-->iVBORw0KGgoAAAANSUhEUgAAAXcAAAF3CAIAAADRopypAAAABGdBTUEAANbY1E9YMgAAAAlwSFlzAAAASAAAAEgARslrPgAAGtNJREFUeNrt3W9oW+e9wPFTkx5MpIlItJKppVJHI7Ea1yY0ieskkKbM8VizS0lvy2CXezvaN3uxQTNYX/RCU7h90TcpdC
            out(response, "mediaContents[1].type"); //-->image/png
            out(response, "mediaContents[1].height"); //-->575
            out(response, "mediaContents[1].width"); //-->575
            out(response, "mediaContents[1].data"); //-->Ppefulr4Ydre1RYhpmkWCtOFwnRHIt7b28NSMbASV1RmplzJT1117eBlzYOfGTlNa4CVKK7MTLkyMXHZB9Pg+dbsHuhnDgUspKwyvunLohd1 e9wPFTkx5MpIlItJKppVJHI7Ea1yY0ieskkKbM8VizS0lvy2CXezvaN3uxQTNYX/RCU7h90TcpdC
            // This sample shows looping through mediaContents
            System.out.println("This sample shows looping through mediaContents");
            for (Map<String, Object> item : (List<Map<String, Object>>) response.get("mediaContents")) {
                out(item, "type");
                out(item, "height");
                out(item, "width");
                out(item, "data");
            }

        } catch (ApiException e) {
            err("HttpStatus: " + e.getHttpStatus());
            err("Message: " + e.getMessage());
            err("ReasonCode: " + e.getReasonCode());
            err("Source: " + e.getSource());
        }
    }

    public static void out(SmartMap response, String key) {
        System.out.println(key + "-->" + response.get(key));
    }

    public static void out(Map<String, Object> map, String key) {
        System.out.println(key + "--->" + map.get(key));
    }

    public static void err(String message) {
        System.err.println(message);
    }
}