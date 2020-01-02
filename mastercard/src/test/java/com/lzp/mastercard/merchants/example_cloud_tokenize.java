package com.lzp.mastercard.merchants; /**
 * Script-Name: example_cloud_tokenize
 */

import com.lzp.mastercard.ApiConfig;
import com.lzp.mastercard.api.Tokenize;
import com.lzp.mastercard.exception.ApiException;
import com.lzp.mastercard.model.Environment;
import com.lzp.mastercard.model.RequestMap;
import com.lzp.mastercard.model.map.SmartMap;
import com.lzp.mastercard.security.mdes.MDESCryptography;
import com.lzp.mastercard.security.oauth.OAuthAuthentication;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class example_cloud_tokenize {

    public static void main(String[] args) throws Exception {

        String consumerKey = "SYxB5pWNgbA7znnsjqrW3j3cPQKswcoSVxJcVhHJcaf24271!1b1ee9c2908d41979333f37d5370c9b30000000000000000";
        String keyAlias = "keyalias";
        String keyPassword = "keystorepassword";
        InputStream is = example_asset_request.class.getClassLoader().getResourceAsStream("cert/token-reuqest-sandbox.p12");
        ApiConfig.setAuthentication(new OAuthAuthentication(consumerKey, is, keyAlias, keyPassword));

        ApiConfig.setDebug(true);
        ApiConfig.setEnvironment(Environment.parse("sandbox_static"));

        // SANDBOX
        InputStream publicCertificate = RequestMap.class.getClassLoader().getResourceAsStream("cert/Public Key-Encrypt.crt");
        InputStream privateKey = RequestMap.class.getClassLoader().getResourceAsStream("cert/Private Key-Decrypt.key");
        String publicKeyFingerprint = "8FC11150A7508F14BACA07285703392A399CC57C";
        ApiConfig.addCryptographyInterceptor(new MDESCryptography(publicCertificate, privateKey));

        try {
            RequestMap map = new RequestMap();
            map.set("taskId", "123456");
            map.set("requestId", "123456");
            map.set("tokenType", "CLOUD");
            map.set("tokenRequestorId", "98765432101");
            map.set("cardInfo.encryptedData.accountNumber", "5123456789012345");
            map.set("cardInfo.encryptedData.expiryMonth", "09");
            map.set("cardInfo.encryptedData.expiryYear", "21");
            map.set("cardInfo.encryptedData.source", "CARD_ON_FILE");
            map.set("cardInfo.encryptedData.cardholderName", "John Doe");
            map.set("cardInfo.encryptedData.securityCode", "123");
            map.set("cardInfo.encryptedData.billingAddress.line1", "100 1st Street");
            map.set("cardInfo.encryptedData.billingAddress.line2", "Apt. 4B");
            map.set("cardInfo.encryptedData.billingAddress.city", "St. Louis");
            map.set("cardInfo.encryptedData.billingAddress.countrySubdivision", "MO");
            map.set("cardInfo.encryptedData.billingAddress.postalCode", "61000");
            map.set("cardInfo.encryptedData.billingAddress.country", "USA");
            Tokenize response = Tokenize.create(map);

            out(response, "responseId"); //-->123456
            out(response, "responseHost"); //-->site.1.sample.service.mastercard.com
            out(response, "decision"); //-->APPROVED
            out(response, "authenticationMethods[0].id"); //-->12344
            out(response, "authenticationMethods[0].type"); //-->TEXT_TO_CARDHOLDER_NUMBER
            out(response, "authenticationMethods[0].value"); //-->12X-XXX-XX32
            out(response, "authenticationMethods[1].id"); //-->12345
            out(response, "authenticationMethods[1].type"); //-->CARDHOLDER_TO_CALL_AUTOMATED_NUMBER
            out(response, "authenticationMethods[1].value"); //-->1-800-BANK-NUMBER
            out(response, "tokenUniqueReference"); //-->DWSPMC000000000132d72d4fcb2f4136a0532d3093ff1a45
            out(response, "panUniqueReference"); //-->FWSPMC000000000159f71f703d2141efaf04dd26803f922b
            out(response, "productConfig.longDescription"); //-->Bank Rewards MasterCard with rewards program
            out(response, "productConfig.issuerProductConfigCode"); //-->123456
            out(response, "productConfig.termsAndConditionsUrl"); //-->https://bank.com/termsAndConditions
            out(response, "productConfig.issuerName"); //-->Issuing Bank
            out(response, "productConfig.cardBackgroundCombinedAssetId"); //-->739d27e5-629d-11e3-949a-0800200c9a66
            out(response, "productConfig.foregroundColor"); //-->000000
            out(response, "productConfig.shortDescription"); //-->Bank Rewards MasterCard
            out(response, "productConfig.brandLogoAssetId"); //-->800200c9-629d-11e3-949a-0739d27e5a66
            out(response, "productConfig.customerServiceUrl"); //-->https://bank.com/customerservice
            out(response, "productConfig.privacyPolicyUrl"); //-->https://bank.com/privacy
            out(response, "tokenInfo.tokenPanSuffix"); //-->1234
            out(response, "tokenInfo.accountPanSuffix"); //-->2345
            out(response, "tokenInfo.tokenExpiry"); //-->0921
            out(response, "tokenInfo.accountPanExpiry"); //-->0921
            out(response, "tokenInfo.dsrpCapable"); //-->false
            out(response, "tokenInfo.tokenAssuranceLevel"); //-->1
            out(response, "tokenInfo.productCategory"); //-->CREDIT
            out(response, "tokenDetail.tokenUniqueReference"); //-->4c4ead5927f0df8117f178eea9308daa58e27abc
            out(response, "tokenDetail.publicKeyFingerprint"); //-->3e3ff1c50fd4046b9a80c39d3d077f7313b92ea01462744bfe50b62769dbef68
            out(response, "tokenDetail.encryptedKey"); //-->
            out(response, "tokenDetail.oaepHashingAlgorithm"); //-->SHA256
            out(response, "tokenDetail.iv"); //-->398376e672c3e71690f80c421a3650b2
            out(response, "tokenDetail.encryptedData.paymentAccountReference"); //-->5001a9f027e5629d11e3949a0800a
            // This sample shows looping through authenticationMethods
            System.out.println("This sample shows looping through authenticationMethods");
            for (Map<String, Object> item : (List<Map<String, Object>>) response.get("authenticationMethods")) {
                out(item, "id");
                out(item, "type");
                out(item, "value");
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