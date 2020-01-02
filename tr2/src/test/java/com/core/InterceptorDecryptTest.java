package com.core;

import com.tr.core.encrypt.config.Configuration;
import com.tr.core.encrypt.encryption.EncryptionException;
import com.tr.core.encrypt.encryption.FieldLevelEncryption;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-11-26 09:59
 */
public class InterceptorDecryptTest {

    private static String requestPayload = "{\n" +
            "  \"responseId\": \"123456\",\n" +
            "  \"responseHost\": \"stl.services.mastercard.com/mtf/mdes\",\n" +
            "  \"decision\": \"APPROVED\",\n" +
            "  \"tokenUniqueReference\": \"DCJDMC00001441364f3c9db87c6742c5af5754a73e07ed8f\",\n" +
            "  \"panUniqueReference\": \"FCJDMC0000144136930d0239fe334ea9a9e16f0d7796489b\",\n" +
            "  \"productConfig\": {\n" +
            "    \"longDescription\": \"Test Bank for MasterCard MTF\",\n" +
            "    \"issuerProductConfigCode\": \"DEFAULTCFA\",\n" +
            "    \"issuerName\": \"Test Issuer®\",\n" +
            "    \"cardBackgroundCombinedAssetId\": \"7ad6ff2a-3898-4bfe-b944-3f94431347f6\",\n" +
            "    \"iconAssetId\": \"6247b36c-329f-46e7-8215-c4450836a676\",\n" +
            "    \"foregroundColor\": \"0f0f0f\",\n" +
            "    \"issuerLogoAssetId\": \"7e350230-6291-11e3-949a-0800200c9a66\",\n" +
            "    \"shortDescription\": \"MasterCard Test Bank\",\n" +
            "    \"privacyPolicyUrl\": \"https://www.mastercard.us/en-us/about-mastercard/what-we-do/privacy.html\"," +
            "\n" +
            "    \"customerServiceEmail\": \"bank@testbanknme.com\",\n" +
            "    \"customerServicePhoneNumber\": \"123-456-7890\",\n" +
            "    \"isCoBranded\": \"false\",\n" +
            "    \"brandLogoAssetId\": \"f9f027e3-629d-11e3-949a-0800200c9a66\",\n" +
            "    \"customerServiceUrl\": \"https://www.testbankname.com\",\n" +
            "    \"coBrandLogoAssetId\": \"edc99958-07b8-4847-8ea7-0609243bf158\",\n" +
            "    \"cardBackgroundAssetId\": \"20e6a2e0-6292-11e3-949a-0800200c9a66\"\n" +
            "  },\n" +
            "  \"tokenInfo\": {\n" +
            "    \"tokenPanSuffix\": \"0121\",\n" +
            "    \"accountPanSuffix\": \"0013\",\n" +
            "    \"tokenExpiry\": \"1222\",\n" +
            "    \"dsrpCapable\": true,\n" +
            "    \"accountPanExpiry\": \"1022\",\n" +
            "    \"productCategory\": \"CREDIT\"\n" +
            "  },\n" +
            "  \"tdsRegistrationUrl\": \"services.mastercard.com/mtf/mdes\",\n" +
            "  \"encryptedPayload\": {\n" +
            "    \"publicKeyFingerprint\": \"ccc5f29ed75bd44bb4a8727aad85d95801dc54c8\",\n" +
            "    \"encryptedKey\": " +
            "\"761bca388512bd9d6da6a520e2a8d91aab91af9de660511a5c35f848918d675d5f586db683960a81e3e61f3daba187af168e12af48ce3e3e249c833dd2ba932d353c99cdfdd5c95403e8061b36fd17e7a35962f6c736032ae4b48b4172d7721388b0dc19b3a3ada3c4186744adcef0d76238121486551068184d1ba7261f27adf6ea95726e3b972aa767b58ae0f83c6fb9e1eb25efcc40731aed490644137d216fc9fce99eaf82b4db09bb018aa40438216deb48f989c10ea5a42e597b9c763d48ce3b77bde99ed3b10d821a0e6a1f035cb4cda4289702d0a5e86ab22403363a41f2ac153a9688fbd13d7ecbb29cf4a4565a76b6fb38f1e3b2c1dcb4c865a298\",\n" +
            "    \"iv\": \"8e51ad8ee1f17a1cd41e69add13e8a76\",\n" +
            "    \"oaepHashingAlgorithm\": \"SHA512\",\n" +
            "    \"encryptedData\": " +
            "\"6ea2d8ce9238dffdf009311eea6673dfcd6a7a9fe67a1e14054ddd459ba7bea4fa59fd73ad56788388ea084eed4937c483de99affd9c0f6878a63810c354a9e6\"\n" +
            "  }\n" +
            "}";


    private static final String sss = "{\n" +
            "  \"responseId\": \"iOS15761455469373768\",\n" +
            "  \"responseHost\": \"stl.services.mastercard.com/mtf/mdes\",\n" +
            "  \"decision\": \"APPROVED\",\n" +
            "  \"tokenUniqueReference\": \"DCJDMC000014413664c29e0a438848a99a3336e506dad8c1\",\n" +
            "  \"panUniqueReference\": \"FCJDMC00001441365e7601134b2b42c291b006dbe858dd0e\",\n" +
            "  \"productConfig\": {\n" +
            "    \"longDescription\": \"Test Bank for MasterCard MTF\",\n" +
            "    \"issuerProductConfigCode\": \"DEFAULTCFA\",\n" +
            "    \"issuerName\": \"Test Issuer®\",\n" +
            "    \"cardBackgroundCombinedAssetId\": \"7ad6ff2a-3898-4bfe-b944-3f94431347f6\",\n" +
            "    \"iconAssetId\": \"6247b36c-329f-46e7-8215-c4450836a676\",\n" +
            "    \"foregroundColor\": \"0f0f0f\",\n" +
            "    \"issuerLogoAssetId\": \"7e350230-6291-11e3-949a-0800200c9a66\",\n" +
            "    \"shortDescription\": \"MasterCard Test Bank\",\n" +
            "    \"privacyPolicyUrl\": \"https://www.mastercard.us/en-us/about-mastercard/what-we-do/privacy.html\"," +
            "\n" +
            "    \"customerServiceEmail\": \"bank@testbanknme.com\",\n" +
            "    \"customerServicePhoneNumber\": \"123-456-7890\",\n" +
            "    \"isCoBranded\": \"false\",\n" +
            "    \"brandLogoAssetId\": \"f9f027e3-629d-11e3-949a-0800200c9a66\",\n" +
            "    \"customerServiceUrl\": \"https://www.testbankname.com\",\n" +
            "    \"coBrandLogoAssetId\": \"edc99958-07b8-4847-8ea7-0609243bf158\",\n" +
            "    \"cardBackgroundAssetId\": \"20e6a2e0-6292-11e3-949a-0800200c9a66\"\n" +
            "  },\n" +
            "  \"tokenInfo\": {\n" +
            "    \"tokenPanSuffix\": \"1251\",\n" +
            "    \"accountPanSuffix\": \"0021\",\n" +
            "    \"tokenExpiry\": \"0123\",\n" +
            "    \"dsrpCapable\": true,\n" +
            "    \"accountPanExpiry\": \"1022\",\n" +
            "    \"productCategory\": \"CREDIT\"\n" +
            "  },\n" +
            "  \"tdsRegistrationUrl\": \"services.mastercard.com/mtf/mdes\",\n" +
            "  \"encryptedPayload\": {\n" +
            "    \"publicKeyFingerprint\": \"ccc5f29ed75bd44bb4a8727aad85d95801dc54c8\",\n" +
            "    \"encryptedKey\": " +
            "\"4af12a0b5c7c3b5cff5808f8f4ce097fec3264fa6f8932d451f317bc1ea8a19582303677fafa50d1668d71e7a8e11ed3c19394feede3fa2f37260dff9c0df1ca7fb0fd7cd8d4b76309e4e7e4a424ea4697a5b72b2c5fb3eb486f76c6405906116a40f0d16fb0e0e69d581539cc71ede58674d0f3de33e1f1bcffa1ee09f820f910569813d8d1009ad46bfc0a50d5b0d118707f9467f73dfbef65b21505c05f23b8caa9b58d5912533c0a007ee43524ee3bdf82d6a38878eebae4b5a27b0eb74237a4f3669b4321aecabe1c0afe09f8e7127e4d111904780e27662f0e7740ddc3084a39d2b473876fed00630f5222617cc5de849b92ca0dcf2191f04157728c24\",\n" +
            "    \"iv\": \"73166b1da7615d399df9b3f886f119a8\",\n" +
            "    \"oaepHashingAlgorithm\": \"SHA512\",\n" +
            "    \"encryptedData\": " +
            "\"bda3945e88ddd397db347ee4385c8ee53a075266a1fc71dd8fce69022813192fb6726b08c9bcc6a22f0661ed36b82a7076f1d67193ba5e76e0204ee3fb97fc18\"\n" +
            "  }\n" +
            "}";

    private static String notify = "{\n" +
            "  \"encryptedPayload\": {\n" +
            "    \"encryptedData\": " +
            "\"fa0155b3f63681fcd136603fa58de2a73033f767c1fab147f77c83f04740668068a777adb769c7dbcf539cf7c315777fed07724af22b5d0036153de092ac39d077f059fa7f2de91899a65d84e939ad3761195a543f2dd84afee729b60698a57b68b1704bf731b7889976512e5181edc6dbb393b553b7a656de8fd853c0190a41a8d35f71d8f557a246e9c27eaad95a0dd47ce4b528c6505f83575bd8615d6191e627fcc058a70a033d64f9e73ba396d3eb2ca45209572fac75517c21ea4738a6a3da5738d21568ca601bd3880e50b9b36ab7b7dc5fdc0208b791c9a70ed3698712070fa1ad469cd538a38ac54d6c1201\",\n" +
            "    \"encryptedKey\": " +
            "\"1e9f58f595a37a1c8a40a8fde49481836c773d8e48774eb1d63bebfd212b79195e959027be44f890bdc4154497c4cb6699e4460fba76240a8578a8bc2fc1e008a798fba0a244f9ee25fd4dd1cfda5363a3e0630139e5cdee286d4dc019d787960803db311ff9a3b9d494bfe3d4f42fcddbd94b04e081fbbd98a953a3f706079e9da40dc2ca636681505bf60d2bd18e7678bd8a569f392e15751eb4e03d0ce645ccb4a3e47cfe97753b0abf8b5c35fb95062fce48a8fca162904c1bc82946c990837a2e0e024875f0f4e950433c8e33105295396727c25217e6ad686c40fd517e4ab710e22f308ea5da9a57ba3f4ec0e319de7a1387a38f3772bbdfb487e8b7c5\",\n" +
            "    \"iv\": \"67d756781a3a5d0e7b94979474924d0f\",\n" +
            "    \"oaepHashingAlgorithm\": \"SHA512\",\n" +
            "    \"publicKeyFingerprint\": \"ccc5f29ed75bd44bb4a8727aad85d95801dc54c8\"\n" +
            "  },\n" +
            "  \"requestId\": \"cc4151ea-301d-4542-aba9-b01c40bf3525\",\n" +
            "  \"responseHost\": \"stl.services.mastercard.com/mtf/mdes\"\n" +
            "}";

    public static void main(String[] args) throws EncryptionException {
        String decryptPayload = FieldLevelEncryption.decryptPayload(notify, Configuration.config);
        System.out.println(decryptPayload);
    }
}
