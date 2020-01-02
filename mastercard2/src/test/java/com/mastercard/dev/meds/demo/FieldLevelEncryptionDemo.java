package com.mastercard.dev.meds.demo;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.lzp.mastercard.mdes.client.ProgressRequestBody;
import com.lzp.mastercard.mdes.client.api.TokenizeApi;
import com.lzp.mastercard.mdes.client.model.TokenizeRequestSchema;
import com.mastercard.dev.meds.mdec.TokenizeApiTest;
import com.mastercard.developer.encryption.FieldLevelEncryption;
import com.mastercard.developer.encryption.FieldLevelEncryptionConfig;
import com.mastercard.developer.encryption.FieldLevelEncryptionConfigBuilder;
import com.mastercard.developer.interceptors.OkHttpFieldLevelEncryptionInterceptor;
import com.mastercard.developer.utils.EncryptionUtils;
import okhttp3.*;
import okio.BufferedSink;
import org.json.JSONObject;

import javax.annotation.Nullable;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.cert.Certificate;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-09-29 17:11
 */
public class FieldLevelEncryptionDemo {

    private final static String encryptionCertificateFilePath = "E:\\IdeaProjects\\demo\\mastercard2\\src\\test\\resources\\demo\\publickey.cer";

    // private final static String decryptionKeyFilePath = "E:\\IdeaProjects\\demo\\mastercard2\\src\\test\\resources\\demo\\mykeystore.p12";
    // static String privateKeyAlias = "mykey";
    // static String privateKeyPassword = "password";

    private final static String decryptionKeyFilePath = "E:\\IdeaProjects\\demo\\mastercard2\\src\\test\\resources\\demo\\myPrivateKey.key";

    private static FieldLevelEncryptionConfig config;

    public static void before() throws Exception {
        Certificate encryptionCertificate = EncryptionUtils.loadEncryptionCertificate(encryptionCertificateFilePath);
        // PrivateKey decryptionKey = EncryptionUtils.loadDecryptionKey(decryptionKeyFilePath, privateKeyAlias, privateKeyPassword);
        PrivateKey decryptionKey = EncryptionUtils.loadDecryptionKey(decryptionKeyFilePath);
        config = FieldLevelEncryptionConfigBuilder.aFieldLevelEncryptionConfig()
                .withEncryptionPath("$.fundingAccountInfo.encryptedPayload.encryptedData", "$.fundingAccountInfo.encryptedPayload")
                .withEncryptionPath("$.encryptedPayload.encryptedData", "$.encryptedPayload")
                .withDecryptionPath("$.tokenDetail", "$.tokenDetail.encryptedData")
                .withDecryptionPath("$.encryptedPayload", "$.encryptedPayload.encryptedData")
                .withEncryptionCertificate(encryptionCertificate)
                .withDecryptionKey(decryptionKey)
                .withOaepPaddingDigestAlgorithm("SHA-512")
                .withEncryptedValueFieldName("encryptedData")
                .withEncryptedKeyFieldName("encryptedKey")
                .withIvFieldName("iv")
                .withOaepPaddingDigestAlgorithmFieldName("oaepHashingAlgorithm")
                .withEncryptionCertificateFingerprintFieldName("publicKeyFingerprint")
                .withFieldValueEncoding(FieldLevelEncryptionConfig.FieldValueEncoding.HEX)
                .build();
    }

    public static void main(String[] args) throws Exception {
        before();

        // 1 源
        String payload = "{" +
                "    \"fundingAccountInfo\": {" +
                "        \"encryptedPayload\": {" +
                "            \"encryptedData\": {" +
                "                \"cardAccountData\": {" +
                "                    \"accountNumber\": \"5123456789012345\"," +
                "                    \"expiryMonth\": \"09\"," +
                "                    \"expiryYear\": \"21\"," +
                "                    \"securityCode\": \"123\"" +
                "                }," +
                "                \"accountHolderData\": {" +
                "                    \"accountHolderName\": \"John Doe\"," +
                "                    \"accountHolderAddress\": {" +
                "                        \"line1\": \"100 1st Street\"," +
                "                        \"line2\": \"Apt. 4B\"," +
                "                        \"city\": \"St. Louis\"," +
                "                        \"countrySubdivision\": \"MO\"," +
                "                        \"postalCode\": \"61000\"," +
                "                        \"country\": \"USA\"" +
                "                    }" +
                "                }," +
                "                \"source\": \"ACCOUNT_ON_FILE\"" +
                "            }" +
                "        }" +
                "    }" +
                "}";
        // 1.1
        // JSONObject jsonObject = new JSONObject(payload);
        // String requestPayload = jsonObject.toString();

        // 1.1
        // DocumentContext payloadContext = JsonPath.parse(payload, jsonPathConfig);
        // DocumentContext payloadContext = JsonPath.parse(payload);
        // String requestPayload2 = payloadContext.jsonString();

        // 1 源
        // TokenizeRequestSchema schema = TokenizeApiTest.buildTokenizeRequestSchema();
        // DocumentContext parse = JsonPath.parse(schema);
        // String requestPayload = parse.jsonString();

        // 2 加密
        String encryptPayload = FieldLevelEncryption.encryptPayload(payload, config);
        System.out.println("encryptPayload==>" + encryptPayload);

        // 3.1
        encryptPayload = "{" +
                "    \"encryptedPayload\": {" +
                "        \"encryptedData\": \"2df3ea3309e12bb1279368ab7a6f005502a267169c015c855ca76ee6559980b0b50b6e190118cb667d3a187df6334b9a0783ed83c5d4b6b8b631c12d8887a1f188d0dacbf4dc1188540c152b6f60f443f14908c85e006e0569ef5b2f9064dfc5b80d32c39aaece58cb7345405b5635dcf4db28ad4ea56d056ba33cf7818e9511c1d281e70737f67b35c072ab2d179957780b934dddd662a7e2b7bffb2dae8aba472e49e9f31b313530876394726780bf1b80ab0e9b85e8212ffe156fbbf4d49dca4cf4e83359aaa18210718ab4f05a049a3bf3fcc1cf1387aed02d9dec0f674ed101d7cb242a854d932892f924832bd3d36edf4a64741cc5a4a5e5e9eb986c41935442a4938c12fa9c57e0964aaf91b0df5c362c21fa825487a7f66fd857d066cd6d28e208956c509232cad01373a5d1bf936a9575510cba9884498233b081a59133c30a7e2edf456d2ae23198456cda14c36694fd7f7e5fef8cf8da16d81249\"," +
                "        \"encryptedKey\": \"24d3b9066f8cbf5dee79800e89fc6adf561ffc4bfa522e697325d03abef24e25fc333479575b5bf2258f34522918c4cc52417a1f45b5fecf4f98f4e1ae72bd8eb93667a6706f1aac11974af0a25ccffda556cebbc81f601b4aa1134f1e3c37f56313c398b3a2bdd9f98f23cc30e93fedfcc27e1a61c24a230a556b2101c0f23be0f77cfd67429cfd358d4dec2d0102a849eddb37ac8fad3d8ce3f546aebd9d48cc75d53e36c66881dbbfb1d24180713263bc505f711f413253474175b2123da967f56ade350e832a50cb211b2c836d7cfc02051b7b0bc0a0f080bc4c32fb6fdc8c6a6e7fd4a0508a193ac1fc6b2512b8abb5052e3499e6bbbf3b94f54cffa773\"," +
                "        \"publicKeyFingerprint\": \"4911531d9f06121a36ee0af1b1b5bb11fa1b42eb86841ebe136d9561ed15fd22\"," +
                "        \"oaepHashingAlgorithm\": \"SHA512\"," +
                "        \"iv\": \"c255f11de5062127229d02f18352c5fa\"" +
                "    }" +
                "}";

        // 3.1
        encryptPayload = "{" +
                "        \"encryptedPayload\": {" +
                "            \"encryptedData\": \"960309d6893e0c64f08124d19b4856029e95e7ed362d68e11753d24d7a7d2f8b6496dca6414a3148d0ef92ff5bbeb0318838f6a172e32db53a04c5758d8309f785b36f833e72a0a50f063bfad2536da216fbcca80841fb0e36a2e3b77451037f1e063ad735c1bfed1cc06a6c4420b61553af4a239d5f56e2c91a69d584f6b6d5b9c35fa040ce5a8c8360a57f294e00bc39cd848deb06d497c492222d7e1da25654e7fa940ec3a06ccf660a95bd9eb1fcc56c4eb2e8259b867837a1a47943cd7d1a51f129a20658b87223b071193bd9343cd75c03092d1db80722d7c84667e92fa1d33f3937886cf6e0cd3f4057c41245d01a3244e5b8cb964918068f9b19959f06cb0a8ad5c66b3ce25c608cba8b7bbae8ece118fc7bca54d0df48eaa84346b68584948280ed0f46f66b516f80a64035ddb67dd4bf67d8c604b34e29b55139a7c46fb1e6c0dc9c031fdf69b07cd0fe8a78194d6a8c191abedbf2d39a815ade6c\"," +
                "            \"encryptedKey\": \"89c359154c75cc5128e96b89b2a8f3407d99b4a6d5c559cedb6257274cd13972c73526406f016c8b5cb13581723f57cf6d2bed2a37dc80bfc130fedca8fbe34341d84341c76c94fe5351e2e14178e5ec8c5f089db0e458ca44cd7960be82627900fd4378e96bd4ff5fa3337887ded802454d08ab470ea3556cc79a661b4d32bc7aaa562aca813c207bf71ec6efcdf3719990f4cdc9219932cbdf404441b4d06368039e1279d928bfefc2929183f311d263daf0d05c03f7cc1a944c4b64a83ca503551c9f3d26ba8e4abbfc361a2d8117a416302a0f119d0f7999cf89d46ca76809a8aa2d5c328a4492db7a9ea10829ee5c9842a27bc1392a3526b5f1b39a173e\"," +
                "            \"publicKeyFingerprint\": \"4911531d9f06121a36ee0af1b1b5bb11fa1b42eb86841ebe136d9561ed15fd22\"," +
                "            \"oaepHashingAlgorithm\": \"SHA512\"," +
                "            \"iv\": \"0c056572ad1a32a0b0b205113eb69d10\"" +
                "        }" +
                "}";

        // 4 解密
        String decryptPayload = FieldLevelEncryption.decryptPayload(encryptPayload, config);
        System.out.println("decryptPayload==>" + decryptPayload);
    }
}
