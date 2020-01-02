// package com.lzp.mastercard.customerservice; /**
//  *
//  * Script-Name: example_mdes_search_token_unique_ref
//  */
//
// import com.mastercard.api.core.ApiConfig;
// import com.mastercard.api.core.exception.*;
// import com.mastercard.api.core.model.*;
// import com.mastercard.api.core.model.map.*;
// import com.mastercard.api.core.security.oauth.OAuthAuthentication;
// import com.mastercard.api.mdescustomerservice.*;
//
// import java.io.*;
// import java.util.List;
// import java.util.Map;
//
// public class example_mdes_search_token_unique_ref {
//
//   public static void main(String[] args) throws Exception {
//
//     String consumerKey = "your consumer key";   // You should copy this from "My Keys" on your project page e.g. UTfbhDCSeNYvJpLL5l028sWL9it739PYh6LU5lZja15xcRpY!fd209e6c579dc9d7be52da93d35ae6b6c167c174690b72fa
//     String keyAlias = "keyalias";   // For production: change this to the key alias you chose when you created your production key
//     String keyPassword = "keystorepassword";   // For production: change this to the key alias you chose when you created your production key
//     InputStream is = new FileInputStream("path to your .p12 private key file"); // e.g. /Users/yourname/project/sandbox.p12 | C:\Users\yourname\project\sandbox.p12
//     ApiConfig.setAuthentication(new OAuthAuthentication(consumerKey, is, keyAlias, keyPassword));   // You only need to set this once
//     ApiConfig.setDebug(true);   // Enable http wire logging
//     ApiConfig.setSandbox(true); // For production: use ApiConfig.setSandbox(false);
//
//     try {
//       RequestMap map = new RequestMap();
//       map.set("SearchRequest.TokenUniqueReference", "DWSPMC00000000010906a349d9ca4eb1a4d53e3c90a11d9c");
//       map.set("SearchRequest.AuditInfo.UserId", "A1435477");
//       map.set("SearchRequest.AuditInfo.UserName", "John Smith");
//       map.set("SearchRequest.AuditInfo.Organization", "Any Bank");
//       map.set("SearchRequest.AuditInfo.Phone", "5555551234");
//       Search response = Search.create(map);
//
//       out(response, "SearchResponse.Accounts.Account[0].AccountPanSuffix"); //-->1234
//       out(response, "SearchResponse.Accounts.Account[0].ExpirationDate"); //-->1215
//       out(response, "SearchResponse.Accounts.Account[0].AlternateAccountIdentifierSuffix"); //-->4300
//       out(response, "SearchResponse.Accounts.Account[0].Tokens.Token[0].TokenUniqueReference"); //-->DWSPMC00000000010906a349d9ca4eb1a4d53e3c90a11d9c
//       out(response, "SearchResponse.Accounts.Account[0].Tokens.Token[0].PrimaryAccountNumberUniqueReference"); //-->FWSPMC0000000004793dac803f190a4dca4bad33c90a11d3
//       out(response, "SearchResponse.Accounts.Account[0].Tokens.Token[0].TokenSuffix"); //-->7639
//       out(response, "SearchResponse.Accounts.Account[0].Tokens.Token[0].ExpirationDate"); //-->0216
//       out(response, "SearchResponse.Accounts.Account[0].Tokens.Token[0].AccountPanSequenceNumber"); //-->002
//       out(response, "SearchResponse.Accounts.Account[0].Tokens.Token[0].DigitizationRequestDateTime"); //-->2015-01-20T18:04:35-06:00
//       out(response, "SearchResponse.Accounts.Account[0].Tokens.Token[0].TokenActivatedDateTime"); //-->2015-01-20T18:04:35-06:00
//       out(response, "SearchResponse.Accounts.Account[0].Tokens.Token[0].FinalTokenizationDecision"); //-->A
//       out(response, "SearchResponse.Accounts.Account[0].Tokens.Token[0].CorrelationId"); //-->101234
//       out(response, "SearchResponse.Accounts.Account[0].Tokens.Token[0].CurrentStatusCode"); //-->A
//       out(response, "SearchResponse.Accounts.Account[0].Tokens.Token[0].CurrentStatusDescription"); //-->Active
//       out(response, "SearchResponse.Accounts.Account[0].Tokens.Token[0].ProvisioningStatusCode"); //-->S
//       out(response, "SearchResponse.Accounts.Account[0].Tokens.Token[0].ProvisioningStatusDescription"); //-->Provisioning successful
//       out(response, "SearchResponse.Accounts.Account[0].Tokens.Token[0].TokenRequestorId"); //-->00212345678
//       out(response, "SearchResponse.Accounts.Account[0].Tokens.Token[0].WalletId"); //-->103
//       out(response, "SearchResponse.Accounts.Account[0].Tokens.Token[0].PaymentAppInstanceId"); //-->92de9357a535b2c21a3566e446f43c532a46b54c46
//       out(response, "SearchResponse.Accounts.Account[0].Tokens.Token[0].TokenType"); //-->S
//       out(response, "SearchResponse.Accounts.Account[0].Tokens.Token[0].LastCommentId"); //-->2376
//       out(response, "SearchResponse.Accounts.Account[0].Tokens.Token[0].Device.DeviceId"); //-->3e5edf24a24ba98e27d43e345b532a245e4723d7a9c4f624e93452c92de9357a535b2c21a3566e446f43c532d34s6
//       out(response, "SearchResponse.Accounts.Account[0].Tokens.Token[0].Device.DeviceName"); //-->John Phone
//       out(response, "SearchResponse.Accounts.Account[0].Tokens.Token[0].Device.DeviceType"); //-->09
//       out(response, "SearchResponse.Accounts.Account[0].Tokens.Token[0].Device.SecureElementId"); //-->92de9357a535b2c21a3566e446f43c532a46b54c46
//       out(response, "SearchResponse.Accounts.Account[0].Tokens.Token[0].TokenDeletedFromConsumerApp"); //-->false
//       // This sample shows looping through SearchResponse.Accounts.Account
//       System.out.println("This sample shows looping through SearchResponse.Accounts.Account");
//       for(Map<String,Object> item : (List<Map<String, Object>>) response.get("SearchResponse.Accounts.Account")) {
//           out(item, "AccountPanSuffix");
//           out(item, "ExpirationDate");
//           out(item, "AlternateAccountIdentifierSuffix");
//           out(item, "Tokens");
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