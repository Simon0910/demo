package com.mtf;

import com.tr.core.ApiClient;
import com.tr.core.ApiConfig;
import com.tr.core.ApiException;
import com.tr.core.Environment;
import com.tr.core.api.DigitizeApi;
import com.tr.core.model.*;
import org.junit.Assert;

import java.util.Arrays;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-21 15:36
 */
public class DigitizeApiTest {
    public static void main(String[] args) throws ApiException {
        createDigitizeTest();
    }

    public static void createDigitizeTest() throws ApiException {
        new ApiConfig();

        ApiClient client = new ApiClient();
        client.setDebugging(true)
                .enableEncrypt()
                .setCurrentEnvironment(Environment.MTF);

        DigitizeApi api = new DigitizeApi(client);
        DigitizeRequestSchema digitizeRequestSchema = buildDigitizeRequestSchema();
        DigitizeResponseSchema response = api.createDigitize(digitizeRequestSchema);
        Assert.assertNotNull(response);

    }

    private static DigitizeRequestSchema buildDigitizeRequestSchema() {
        return new DigitizeRequestSchema()
                .requestId("70389416-4718-4fbb-803d-e240ae54f1ea")
                .responseHost("pre-tr.cjdfintech.com/master")
                .paymentAppInstanceId("jQCG9s7h3TWw9yHIOwUoSQ--DUPLICATE_REQUEST2")
                .eligibilityReceipt(buildEligibilityReceipt())
                .taskId("jQCG9s7h3TWw9yHIOwUoSQ--DUPLICATE_REQUEST2")
                .termsAndConditionsAssetId("8904093a-556d-4636-aa19-f298084193ed")
                .termsAndConditionsAcceptedTimestamp("2019-12-04T17:30:04.432-08:00")
                .fundingAccountInfo(buildFundingAccountInfo())
                // .tokenizationAuthenticationValue("RHVtbXkgYmFzZSA2NCBkYXRhIC0gdGhpcyBpcyBub3QgYSByZWFsIFRBViBleGFtcGxl")
                // .decisioningData(buildDecisioningData())
                .decisioningData(new DecisioningData())
                ;
    }

    private static DecisioningData buildDecisioningData() {
        return new DecisioningData()
                .recommendation("REQUIRE_ADDITIONAL_AUTHENTICATION")
                .recommendationAlgorithmVersion("01")
                .deviceScore("1")
                .accountScore("1")
                .recommendationReasons(Arrays.asList(
                        "ACCOUNT_TOO_NEW",
                        "TOO_MANY_RECENT_ATTEMPTS",
                        "OUTSIDE_HOME_TERRITORY"));
    }

    private static EligibilityReceipt buildEligibilityReceipt() {
        return new EligibilityReceipt()
                .value("397fd723-4673-41d0-87f6-7762e4ab79d3")
                // .validForMinutes("30")
                ;
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
                .accountNumber("5204890860010047")
                .securityCode("123")
                .expiryYear("22")
                .expiryMonth("10");
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
}
