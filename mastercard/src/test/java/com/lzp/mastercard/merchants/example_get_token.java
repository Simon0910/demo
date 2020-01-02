package com.lzp.mastercard.merchants; /**
 * Script-Name: example_get_token
 */

import com.lzp.mastercard.ApiConfig;
import com.lzp.mastercard.exception.ApiException;
import com.lzp.mastercard.model.Environment;
import com.lzp.mastercard.model.RequestMap;
import com.lzp.mastercard.model.map.SmartMap;
import com.lzp.mastercard.security.mdes.MDESCryptography;
import com.lzp.mastercard.security.oauth.OAuthAuthentication;
import com.mastercard.api.mdesmerchants.GetToken;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

public class example_get_token {

    public static void main(String[] args) throws Exception {

        String consumerKey = "your consumer key";   // You should copy this from "My Keys" on your project page e.g. UTfbhDCSeNYvJpLL5l028sWL9it739PYh6LU5lZja15xcRpY!fd209e6c579dc9d7be52da93d35ae6b6c167c174690b72fa
        String keyAlias = "keyalias";   // For production: change this to the key alias you chose when you created your production key
        String keyPassword = "keystorepassword";   // For production: change this to the key alias you chose when you created your production key
        InputStream is = new FileInputStream("path to your .p12 private key file"); // e.g. /Users/yourname/project/sandbox.p12 | C:\Users\yourname\project\sandbox.p12
        ApiConfig.setAuthentication(new OAuthAuthentication(consumerKey, is, keyAlias, keyPassword));   // You only need to set this once
        ApiConfig.setDebug(true);   // Enable http wire logging
        // This is needed to change the environment to run the sample code. For production: use ApiConfig.setSandbox(false);
        ApiConfig.setEnvironment(Environment.parse("sandbox_static"));


        InputStream publicCertificate = RequestMap.class.getClassLoader().getResourceAsStream("Public Certificate.crt");
        InputStream privateKey = RequestMap.class.getClassLoader().getResourceAsStream("Private Certificate.key");
        //if you are given a publicKeyFingerprint
        ApiConfig.addCryptographyInterceptor(new MDESCryptography(publicCertificate, privateKey, "Insert PublicKeyFingerprint Here"));
        //otherwise
        ApiConfig.addCryptographyInterceptor(new MDESCryptography(publicCertificate, privateKey));

        try {
            RequestMap map = new RequestMap();
            map.set("requestId", "123456");
            map.set("paymentAppInstanceId", "123456789");
            map.set("tokenUniqueReference", "DWSPMC000000000132d72d4fcb2f4136a0532d3093ff1a45");
            map.set("includeTokenDetail", "true");
            // GetToken response = GetToken.create(map);
            GetToken g = new GetToken();

            // out(response, "responseId"); //-->123456
            // out(response, "token.tokenUniqueReference"); //-->DWSPMC000000000132d72d4fcb2f4136a0532d3093ff1a45
            // out(response, "token.status"); //-->ACTIVE
            // out(response, "token.productConfig.brandLogoAssetId"); //-->800200c9-629d-11e3-949a-0739d27e5a67
            // out(response, "token.productConfig.isCoBranded"); //-->true
            // out(response, "token.productConfig.coBrandName"); //-->Test CoBrand Name
            // out(response, "token.productConfig.coBrandLogoAssetId"); //-->Test coBrand Logo AssetId
            // out(response, "token.productConfig.cardBackgroundCombinedAssetId"); //-->739d27e5-629d-11e3-949a-0800200c9a66
            // out(response, "token.productConfig.foregroundColor"); //-->000000
            // out(response, "token.productConfig.issuerName"); //-->Issuing Bank
            // out(response, "token.productConfig.shortDescription"); //-->Bank Rewards MasterCard
            // out(response, "token.productConfig.longDescription"); //-->Bank Rewards MasterCard with the super duper rewards program
            // out(response, "token.productConfig.customerServiceUrl"); //-->https://bank.com/customerservice
            // out(response, "token.productConfig.termsAndConditionsUrl"); //-->https://bank.com/termsAndConditions
            // out(response, "token.productConfig.privacyPolicyUrl"); //-->https://bank.com/privacy
            // out(response, "token.productConfig.issuerProductConfigCode"); //-->123456
            // out(response, "token.tokenInfo.tokenPanSuffix"); //-->1234
            // out(response, "token.tokenInfo.accountPanSuffix"); //-->5675
            // out(response, "token.tokenInfo.tokenExpiry"); //-->0921
            // out(response, "token.tokenInfo.dsrpCapable"); //-->true
            // out(response, "token.tokenInfo.tokenAssuranceLevel"); //-->1
            // out(response, "tokenDetail.encryptedData.tokenNumber"); //-->5123456789012345
            // out(response, "tokenDetail.encryptedData.expiryMonth"); //-->09
            // out(response, "tokenDetail.encryptedData.expiryYear"); //-->21
            // out(response, "tokenDetail.tokenUniqueReference"); //-->DWSPMC000000000132d72d4fcb2f4136a0532d3093ff1a45

            // } catch (ApiException e) {
        } finally {
            ApiException e = new ApiException();
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