// package com.lzp.mastercard.merchants; /**
//  *
//  * Script-Name: example_transact_ucaf
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
// import java.util.Map;
//
// public class example_transact_ucaf {
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
//       map.set("requestId", "111111");
//       map.set("tokenUniqueReference", "DWSPMC000000000132d72d4fcb2f4136a0532d3093ff1a45");
//       map.set("dsrpType", "UCAF");
//       map.set("unpredictableNumber", "A1B2C3D4");
//       Transact response = Transact.create(map);
//
//       out(response, "responseId"); //-->123456
//       out(response, "encryptedPayload.encryptedData.accountNumber"); //-->5480981500100002
//       out(response, "encryptedPayload.encryptedData.applicationExpiryDate"); //-->210930
//       out(response, "encryptedPayload.encryptedData.panSequenceNumber"); //-->01
//       out(response, "encryptedPayload.encryptedData.track2Equivalent"); //-->5480981500100002D18112011000000000000F
//       out(response, "encryptedPayload.encryptedData.de48se43Data"); //-->11223344556677889900112233445566778899
//       out(response, "encryptedPayload.publicKeyFingerprint"); //-->4c4ead5927f0df8117f178eea9308daa58e27c2b
//       out(response, "encryptedPayload.encryptedKey"); //-->A1B2C3D4E5F6112233445566
//       out(response, "encryptedPayload.oaepHashingAlgorithm"); //-->SHA512
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