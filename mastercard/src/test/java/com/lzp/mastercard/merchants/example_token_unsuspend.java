// package com.lzp.mastercard.merchants; /**
//  *
//  * Script-Name: example_token_unsuspend
//  */
//
// import com.mastercard.api.core.ApiConfig;
// import com.mastercard.api.core.exception.*;
// import com.mastercard.api.core.model.*;
// import com.mastercard.api.core.model.map.*;
// import com.mastercard.api.core.security.oauth.OAuthAuthentication;
// import com.mastercard.api.mdesmerchants.*;
// import com.mastercard.api.core.security.mdes.*;
//
// import java.io.*;
// import java.util.List;
// import java.util.Map;
//
// public class example_token_unsuspend {
//
//   public static void main(String[] args) throws Exception {
//
//     String consumerKey = "your consumer key";   // You should copy this from "My Keys" on your project page e.g. UTfbhDCSeNYvJpLL5l028sWL9it739PYh6LU5lZja15xcRpY!fd209e6c579dc9d7be52da93d35ae6b6c167c174690b72fa
//     String keyAlias = "keyalias";   // For production: change this to the key alias you chose when you created your production key
//     String keyPassword = "keystorepassword";   // For production: change this to the key alias you chose when you created your production key
//     InputStream is = new FileInputStream("path to your .p12 private key file"); // e.g. /Users/yourname/project/sandbox.p12 | C:\Users\yourname\project\sandbox.p12
//     ApiConfig.setAuthentication(new OAuthAuthentication(consumerKey, is, keyAlias, keyPassword));   // You only need to set this once
//     ApiConfig.setDebug(true);   // Enable http wire logging
//     // This is needed to change the environment to run the sample code. For production: use ApiConfig.setSandbox(false);
//     ApiConfig.setEnvironment(Environment.parse("sandbox_static"));
//
//
//     InputStream publicCertificate = RequestMap.class.getClassLoader().getResourceAsStream("Public Certificate.crt");
//     InputStream privateKey = RequestMap.class.getClassLoader().getResourceAsStream("Private Certificate.key");
//     //if you are given a publicKeyFingerprint
//     ApiConfig.addCryptographyInterceptor(new MDESCryptography(publicCertificate, privateKey, "Insert PublicKeyFingerprint Here"));
//     //otherwise
//     ApiConfig.addCryptographyInterceptor(new MDESCryptography(publicCertificate, privateKey));
//
//     try {
//       RequestMap map = new RequestMap();
//       map.set("responseHost", "site2.your-server.com");
//       map.set("requestId", "123456");
//       map.set("tokenUniqueReferences[0]", "DWSPMC000000000132d72d4fcb2f4136a0532d3093ff1a45");
//       map.set("tokenUniqueReferences[1]", "DWSPMC00000000032d72d4ffcb2f4136a0532d32d72d4fcb");
//       map.set("tokenUniqueReferences[2]", "DWSPMC000000000fcb2f4136b2f4136a0532d2f4136a0532");
//       map.set("reason", " LOST_STOLEN_DEVICE_FOUND ");
//       map.set("reasonCode", "DEVICE_FOUND");
//       map.set("causedBy", "CARDHOLDER");
//       Unsuspend response = Unsuspend.create(map);
//
//       out(response, "responseId"); //-->123456
//       out(response, "responseHost"); //-->site.1.sample.service.mastercard.com
//       out(response, "tokens[0].tokenUniqueReference"); //-->DWSPMC000000000132d72d4fcb2f4136a0532d3093ff1a45
//       out(response, "tokens[0].status"); //-->ACTIVE
//       out(response, "tokens[1].tokenUniqueReference"); //-->DWSPMC00000000032d72d4ffcb2f4136a0532d32d72d4fcb
//       out(response, "tokens[1].status"); //-->ACTIVE
//       out(response, "tokens[2].tokenUniqueReference"); //-->DWSPMC000000000fcb2f4136b2f4136a0532d2f4136a0532
//       out(response, "tokens[2].status"); //-->ACTIVE
//       // This sample shows looping through tokens
//       System.out.println("This sample shows looping through tokens");
//       for(Map<String,Object> item : (List<Map<String, Object>>) response.get("tokens")) {
//           out(item, "tokenUniqueReference");
//           out(item, "status");
//       }
//
//     } catch (ApiException e) {
//       err("HttpStatus: "+e.getHttpStatus());
//       err("Message: "+e.getMessage());
//       err("ReasonCode: "+e.getReasonCode());
//       err("Source: "+e.getSource());
//     }
//   }
//
//   public static void out(SmartMap response, String key) {
//     System.out.println(key+"-->"+response.get(key));
//   }
//
//   public static void out(Map<String,Object> map, String key) {
//     System.out.println(key+"--->"+map.get(key));
//   }
//
//   public static void err(String message) {
//     System.err.println(message);
//   }
// }