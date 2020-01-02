package com.mtf;

import com.tr.core.ApiClient;
import com.tr.core.ApiConfig;
import com.tr.core.ApiException;
import com.tr.core.Environment;
import com.tr.core.api.CheckEligibilityApi;
import com.tr.core.enums.FormFactorEnum;
import com.tr.core.enums.OsNameEnum;
import com.tr.core.enums.StorageTechnologyEnum;
import com.tr.core.model.*;
import org.junit.Assert;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-22 16:11
 */
public class CheckEligibilityApiTest {

    public static void main(String[] args) throws ApiException {
        checkEligibilityTest();
    }

    public static void checkEligibilityTest() throws ApiException {
        new ApiConfig();

        ApiClient client = new ApiClient();
        client.setDebugging(true)
                .enableEncrypt()
                .setCurrentEnvironment(Environment.MTF)
        // .setConnectTimeout(20000)
        // .setReadTimeout(100)
        ;

        CheckEligibilityApi api = new CheckEligibilityApi(client);
        CheckEligibilityRequestSchema checkEligibilityRequestSchema = buildCheckEligibilityRequestSchema();
        CheckEligibilityResponseSchema checkEligibilityResponseSchema = api.checkEligibility(checkEligibilityRequestSchema);
        Assert.assertNotNull(checkEligibilityResponseSchema);
    }

    private static CheckEligibilityRequestSchema buildCheckEligibilityRequestSchema() {
        return new CheckEligibilityRequestSchema()
                .requestId("38609416-4718-4fbb-803d-e240ae54f1ea")
                .responseHost("pre-tr.cjdfintech.com/master")
                .paymentAppId("DOLFINWALLET01")
                .paymentAppInstanceId("jQCG9s7h3TWw9yHIOwUoSQ--DUPLICATE_REQUEST2")
                .tokenType("CLOUD")
                .deviceInfo(buildDeviceInfo())
                .fundingAccountInfo(buildFundingAccountInfo())
                .cardletId("MCBP_599")
                .consumerLanguage("en")
                ;
    }

    private static DeviceInfo buildDeviceInfo() {
        return new DeviceInfo()
                .deviceName("My Phone")
                .serialNumber("2IUGnZ14NCnTnGAgrAppnw--")
                .formFactor(FormFactorEnum.PHONE.name())
                .storageTechnology(StorageTechnologyEnum.DEVICE_MEMORY.name())
                .osName(OsNameEnum.ANDROID.name())
                .osVersion("12.2")
                .imei("866146034648729")
                .msisdn("true")
                .nfcCapable(false)
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
                .accountNumber("5204890860010021")
                .securityCode("123")
                .expiryYear("22")
                .expiryMonth("10");
    }

    private static AccountHolderData buildAccountHolderData() {
        return new AccountHolderData()
                // .accountHolderAddress(buildBillingAddress())
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
