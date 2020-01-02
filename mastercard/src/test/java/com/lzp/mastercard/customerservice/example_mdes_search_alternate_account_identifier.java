package com.lzp.mastercard.customerservice; /**
 * Script-Name: example_mdes_search_alternate_account_identifier
 */

import com.lzp.mastercard.ApiConfig;
import com.lzp.mastercard.exception.ApiException;
import com.lzp.mastercard.model.RequestMap;
import com.lzp.mastercard.model.map.SmartMap;
import com.lzp.mastercard.security.oauth.OAuthAuthentication;
import com.mastercard.api.mdescustomerservice.Search;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

public class example_mdes_search_alternate_account_identifier {

    public static void main(String[] args) throws Exception {

        String consumerKey = "your consumer key";   // You should copy this from "My Keys" on your project page e.g. UTfbhDCSeNYvJpLL5l028sWL9it739PYh6LU5lZja15xcRpY!fd209e6c579dc9d7be52da93d35ae6b6c167c174690b72fa
        String keyAlias = "keyalias";   // For production: change this to the key alias you chose when you created your production key
        String keyPassword = "keystorepassword";   // For production: change this to the key alias you chose when you created your production key
        InputStream is = new FileInputStream("path to your .p12 private key file"); // e.g. /Users/yourname/project/sandbox.p12 | C:\Users\yourname\project\sandbox.p12
        ApiConfig.setAuthentication(new OAuthAuthentication(consumerKey, is, keyAlias, keyPassword));   // You only need to set this once
        ApiConfig.setDebug(true);   // Enable http wire logging
        ApiConfig.setSandbox(true); // For production: use ApiConfig.setSandbox(false);

        try {
            RequestMap map = new RequestMap();
            map.set("SearchRequest.AlternateAccountIdentifier", "NL91ABNA0417164300");
            map.set("SearchRequest.AuditInfo.UserId", "A1435477");
            map.set("SearchRequest.AuditInfo.UserName", "John Smith");
            map.set("SearchRequest.AuditInfo.Organization", "Any Bank");
            map.set("SearchRequest.AuditInfo.Phone", "5555551234");
            // Search response = Search.create(map);
            Search s = new Search();

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