package com.mtf;

import com.alibaba.fastjson.JSON;
import com.tr.core.ApiClient;
import com.tr.core.ApiConfig;
import com.tr.core.ApiException;
import com.tr.core.Environment;
import com.tr.core.api.RegisterApi;
import com.tr.core.encrypt.encryption.EncryptionException;
import com.tr.core.model.RegisterRequestSchema;
import com.tr.core.model.RegisterResponseSchema;
import com.tr.core.model.RnsInfo;
import com.tr.service.EncryptService;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.UUID;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-22 09:36
 */
public class RegisterApiTest {
    public static void main(String[] args) throws ApiException, EncryptionException, FileNotFoundException,
            GeneralSecurityException, UnsupportedEncodingException {
        registerTest();
    }

    public static void registerTest() throws ApiException, EncryptionException, UnsupportedEncodingException,
            GeneralSecurityException, FileNotFoundException {
        new ApiConfig();

        ApiClient client = new ApiClient();
        client.setDebugging(true)
                .setCurrentEnvironment(Environment.MTF);

        RegisterApi api = new RegisterApi(client);
        // RegisterRequestSchema registerRequestSchema = buildRegisterRequestSchema();
        RegisterRequestSchema registerRequestSchema = buildRegisterRequestSchema();
        RegisterResponseSchema response = api.register(registerRequestSchema);
        // Assert.assertNotNull(response);
        System.out.println(JSON.toJSONString(response));
    }

    private static final String generateRequestId() {
        return UUID.randomUUID().toString();
    }

    private static RegisterRequestSchema buildRequest() throws EncryptionException, FileNotFoundException, GeneralSecurityException, UnsupportedEncodingException {
        RegisterRequestSchema request = new RegisterRequestSchema();
        request.paymentAppId("DOLFINWALLET01")
                .paymentAppInstanceId("iYBVQzoZIuwVARezwT3spA--")
                .deviceFingerprint("C45BFDE08BA9685308AAD9D548E0442FA03078FAA7E9152122D57C47A19A25E0")
                .requestId(generateRequestId())
                .responseHost("pre-tr.cjdfintech.com/master")
                .publicKeyFingerprint("e47d4a6402593e9b9198a2224e188d9e873418c920984e3efe2846dd886a6290")
                .rgk("a41fe2c8cb6d58ada39edc06cfa8c0b429d9d22a871e817bb3c358e649543a97218687970b2988fd3aafedfc7936130b66980b52566db1db32e55b4a22841ec5a64601b8a2b58144e1e9497f6dcb376cf816f04e13e7c055102da9d8878c506b5f8a887376ea57668f8214eeb90be116389b6bb409853aea5d2d0d727ff11096e04f7d56edb409e0f02c0600857e0da661cf3d523e9e51d07c70828fb14e61d8d7b5f0dcda64be154cdd0c2df8c17397b1cce75a9fc8e1eac0de211b8e4c36c51cd199354b1765ce2a41a889aeebded8bd82793c66966c585ca5503c042b883f53153d79b818094c69fd490370ee96eb827217be1c1d01bce54a2a48008e6ceb")
                // .newMobilePin("2468")
        ;

        // EncryptService encryptService = new EncryptService();
        // try {
        //     encryptService.encryptPIN(request);
        // } catch (Exception e) {
        //     // log.error()
        //     throw e;
        // }
        return request;
    }


    private static RegisterRequestSchema buildRegisterRequestSchema() {
        return new RegisterRequestSchema()
                // .requestId("bbbb90e8-4b27-48a8-a033-379495088b50")
                .requestId("iOS15767213977310431")
                .responseHost("pre-tr.cjdfintech.com")
                .paymentAppId("DOLFINWALLET01")
                .paymentAppInstanceId("pwa88hte6vDw-uRMdl1_Fw--")
                // .rnsInfo(buildRnsInfo())
                // .publicKeyFingerprint("e47d4a6402593e9b9198a2224e188d9e873418c920984e3efe2846dd886a6290")
                // .publicKeyFingerprint("d2d9fa7869cef60eb09fa13922f469fe455f431b")
                .publicKeyFingerprint("e47d4a6402593e9b9198a2224e188d9e873418c920984e3efe2846dd886a6290")
                // .rgk("a41fe2c8cb6d58ada39edc06cfa8c0b429d9d22a871e817bb3c358e649543a97218687970b2988fd3aafedfc7936130b66980b52566db1db32e55b4a22841ec5a64601b8a2b58144e1e9497f6dcb376cf816f04e13e7c055102da9d8878c506b5f8a887376ea57668f8214eeb90be116389b6bb409853aea5d2d0d727ff11096e04f7d56edb409e0f02c0600857e0da661cf3d523e9e51d07c70828fb14e61d8d7b5f0dcda64be154cdd0c2df8c17397b1cce75a9fc8e1eac0de211b8e4c36c51cd199354b1765ce2a41a889aeebded8bd82793c66966c585ca5503c042b883f53153d79b818094c69fd490370ee96eb827217be1c1d01bce54a2a48008e6ceb")
                .rgk("361944b6af2d64c1083d36cb860e0c310655781b570e6da978f9545c11d8f09024f503476ce089b9fab27d6cc8cec909696c03c53f2b66621db44cc75a29cfd01eb93d4922da5c08c08a87af666287620dfd14444b215d7668be0d4c2f1a163bd4504cede06ad05e4bec4970d6359c86417620edcc1478ce0be8f214333b688c7106f61f4186612ae62f7005e371b7e6f0c157454351a079c259b7553537183bffb6d13c5dfd9eadb4c7f56bc26be621b41996f3f50139137f5e662c1ec094a22a31892d10713a21afa161a13bf6b22aa6ad228d73e90482f69cafa3a36eef01536cc1e81d939217fdc3ca028677f60ea67e41db1860c7634dcf596b3ae74dbe")
                // .deviceFingerprint("C45BFDE08BA9685308AAD9D548E0442FA03078FAA7E9152122D57C47A19A25E0")
                .deviceFingerprint("39A748DF8439CBE2441D53E822A2FC5EBA0AF1DE5C48EE9C7E8EA7F040585025")
                // .newMobilePin("4E4F54205245414C2050494E20424C4F434B")
                ;
    }

    private static RnsInfo buildRnsInfo() {
        return new RnsInfo().gcmRegistrationId("APA91bHPRgkF3JUikC4ENAHEeMrd41Zxv3hVZjC9KtT8OvPVGJ-" +
                "hQMRKRrZuJAEcl7B338qju59zJMjw2DELjzEvxwYv7hH5Ynpc1ODQ0aT4U4OFEeco8ohsN5PjL1iC2dNtk2BA" +
                "okeMCg2ZXKqpc8FXKmhX94kIxQ");
    }

}
