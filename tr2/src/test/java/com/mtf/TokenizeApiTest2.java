package com.mtf;

import com.tr.core.ApiClient;
import com.tr.core.ApiConfig;
import com.tr.core.Configuration;
import com.tr.core.Environment;
import com.tr.core.api.TokenizeApi;
import com.tr.core.model.*;
import org.junit.Assert;


/**
 * API tests for TokenizeApi
 */
public class TokenizeApiTest2 {

    public static void createTokenizeTest() throws Exception {
        new ApiConfig();

        ApiClient client = new ApiClient();
        client.setDebugging(true)
                .enableEncrypt()
                .enableSgin()
                .setCurrentEnvironment(Environment.MTF);

        TokenizeApi api = new TokenizeApi(client);
        TokenizeRequestSchema tokenizeRequestSchema = buildTokenizeRequestSchema();
        TokenizeResponseSchema response = api.createTokenize(tokenizeRequestSchema);
        Assert.assertNotNull(response);
        Assert.assertEquals("APPROVED", response.getDecision());
        Assert.assertEquals("500181d9f8e0629211e3949a08002", response.getTokenDetail().getEncryptedData().getPaymentAccountReference());
    }


    public static TokenizeRequestSchema buildTokenizeRequestSchema() {
        return new TokenizeRequestSchema()
                .requestId("123456")
                .responseHost("site1.your-server.com")
                .taskId("123456")
                .tokenType("CLOUD")
                .fundingAccountInfo(buildFundingAccountInfo())
                .consumerLanguage("en")
                .tokenizationAuthenticationValue("RHVtbXkgYmFzZSA2NCBkYXRhIC0gdGhpcyBpcyBub3QgYSByZWFsIFRBViBleGFtcGxl")
                .tokenRequestorId("98765432101");
    }

    private static FundingAccountInfo buildFundingAccountInfo() {
        return new FundingAccountInfo()
                .encryptedPayload(buildFundingAccountInfoEncryptedPayload());
    }

    private static FundingAccountInfoEncryptedPayload buildFundingAccountInfoEncryptedPayload() {
        return new FundingAccountInfoEncryptedPayload()
                .encryptedData(buildFundingAccountData());
    }

    private static FundingAccountData buildFundingAccountData() {
        return new FundingAccountData()
                .accountHolderData(buildAccountHolderData())
                .source("ACCOUNT_ON_FILE")
                .cardAccountData(buildCardAccountDataInbound());
    }

    private static CardAccountDataInbound buildCardAccountDataInbound() {
        return new CardAccountDataInbound()
                .accountNumber("5123456789012345")
                .securityCode("123")
                .expiryYear("21")
                .expiryMonth("09");
    }

    private static AccountHolderData buildAccountHolderData() {
        return new AccountHolderData()
                .accountHolderAddress(buildBillingAddress())
                .accountHolderName("John Doe");
    }

    private static BillingAddress buildBillingAddress() {
        return new BillingAddress()
                .line1("100 1st Street")
                .line2("Apt. 4B")
                .city("St. Louis")
                .countrySubdivision("MO")
                .postalCode("61000")
                .country("USA");
    }

    public static void main(String[] args) throws Exception {
        /**
         * log:
         *
         * Content-Type: application/json; charset=utf-8
         * Content-Length: 666
         * Accept: application/json
         * User-Agent: OpenAPI-Generator/1.0.0/java
         *
         * {"responseHost":"site1.your-server.com","requestId":"123456","tokenType":"CLOUD","tokenRequestorId":"98765432101","taskId":"123456","fundingAccountInfo":{"encryptedPayload":{"encryptedData":{"cardAccountData":{"accountNumber":"5123456789012345","expiryMonth":"09","expiryYear":"21","securityCode":"123"},"accountHolderData":{"accountHolderName":"John Doe","accountHolderAddress":{"line1":"100 1st Street","line2":"Apt. 4B","city":"St. Louis","countrySubdivision":"MO","postalCode":"61000","country":"USA"}},"source":"ACCOUNT_ON_FILE"}}},"consumerLanguage":"en","tokenizationAuthenticationValue":"RHVtbXkgYmFzZSA2NCBkYXRhIC0gdGhpcyBpcyBub3QgYSByZWFsIFRBViBleGFtcGxl"}
         *
         * Server: Apache-Coyote/1.1
         * correlation-id: 0000016c1aebee6a-1c0e3df
         * Content-Type: application/json
         * Date: Wed, 16 Oct 2019 07:49:41 GMT
         * Connection: keep-alive
         * Vary: Accept-Encoding
         * Set-Cookie: WalletAPI_JSESSIONID=CQ5tLbA987ijVK0JcbnsWXQS; Path=/mdes/digitization/static/1/0; Secure; Domain=sandbox.api.mastercard.com
         * {"decision":"APPROVED","authenticationMethods":[{"id":12344,"type":"TEXT_TO_CARDHOLDER_NUMBER","value":"12X-XXX-XX32"},{"id":12345,"type":"CARDHOLDER_TO_CALL_AUTOMATED_NUMBER","value":"1-800-BANK-NUMBER"}],"panUniqueReference":"FWSPMC000000000159f71f703d2141efaf04dd26803f922b","responseHost":"site.1.sample.service.mastercard.com","productConfig":{"longDescription":"Bank Rewards MasterCard with the super duper rewards program","issuerProductConfigCode":"123456","termsAndConditionsUrl":"https://bank.com/termsAndConditions","issuerMobileApp":{},"issuerName":"Issuing Bank","cardBackgroundCombinedAssetId":"739d27e5-629d-11e3-949a-0800200c9a66","foregroundColor":"000000","shortDescription":"Bank Rewards MasterCard","brandLogoAssetId":"800200c9-629d-11e3-949a-0739d27e5a66","customerServiceUrl":"https://bank.com/customerservice","privacyPolicyUrl":"https://bank.com/privacy"},"tokenDetail":{"encryptedData":{"paymentAccountReference":"500181d9f8e0629211e3949a08002"},"tokenUniqueReference":"4c4ead5927f0df8117f178eea9308daa58e27abc"},"tokenUniqueReference":"DWSPMC000000000132d72d4fcb2f4136a0532d3093ff1a45","tokenInfo":{"accountPanExpiry":"0921","accountPanSuffix":"2345","tokenPanSuffix":"1234","tokenExpiry":"1022","tokenAssuranceLevel":1,"productCategory":"CREDIT","dsrpCapable":false},"responseId":"123456"}
         *
         */
        createTokenizeTest();
    }

}
