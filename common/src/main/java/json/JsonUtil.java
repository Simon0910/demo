package json;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import json.engine.JsonEngine;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-11-12 15:56
 */
public final class JsonUtil {
    private static JsonEngine jsonEngine;
    private static Configuration jsonPathConfig = withJsonEngine(JsonEngine.getDefault());

    /**
     * Specify the JSON engine to be used.
     *
     * @param jsonEngine A {@link JsonEngine} instance
     */
    public static synchronized Configuration withJsonEngine(JsonEngine jsonEngine) {
        JsonUtil.jsonEngine = jsonEngine;
        JsonUtil.jsonPathConfig = new Configuration.ConfigurationBuilder()
                .jsonProvider(jsonEngine.getJsonProvider())
                .options(Option.SUPPRESS_EXCEPTIONS)
                .build();
        return jsonPathConfig;
    }

    private JsonUtil(){}

    private static Object readJsonElement(DocumentContext context, String jsonPathString) {
        Object payloadJsonObject = context.json();
        JsonPath jsonPath = JsonPath.compile(jsonPathString);
        return jsonPath.read(payloadJsonObject, jsonPathConfig);
    }

    private static String sanitizeJson(String json) {
        return json.replaceAll("\n", "")
                .replaceAll("\r", "")
                .replaceAll("\t", "");
    }

    public static void main(String[] args) {
        String jsonPathIn = "$.fundingAccountInfo.encryptedPayload";
        String jsonPathOut = "";
        DocumentContext payloadContext = JsonPath.parse(payload, jsonPathConfig);
        String s = payloadContext.jsonString();
        System.out.println(s);

        payloadContext.delete(jsonPathIn);
        System.out.println(payloadContext);
        payloadContext.put(jsonPathOut, "name1", "value1");
        System.out.println(payloadContext);

        Object inJsonElement = readJsonElement(payloadContext, jsonPathIn);
        if (inJsonElement == null) {
            // Nothing to encrypt
            return;
        }

        String s1 = jsonEngine.toJsonString(inJsonElement);
        System.out.println(s1);
        String s2 = sanitizeJson(s1);
        System.out.println(s2);
    }



    static String payload = "{" +
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
}
