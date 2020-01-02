package com.lzp.json;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.json.JettisonProvider;

/**
 * http://www.ibloger.net/article/2329.html
 * http://jsonpath.herokuapp.com
 * https://www.jianshu.com/p/658bd8926a96
 *
 * @author liuzhipeng
 * @description
 * @create 2019-09-30 17:01
 */
public class Demo {

    static GsonJsonProvider jsonProvider = getJsonProvider();

    static Configuration jsonPathConfig = new Configuration.ConfigurationBuilder()
            .jsonProvider(jsonProvider)
            .options(Option.SUPPRESS_EXCEPTIONS)
            .build();

    // 1 源
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

    // static String jsonPathString = "$.encryptedPayload.encryptedData";
    static String jsonPathString = "$.fundingAccountInfo.encryptedPayload.encryptedData";

    public static void main(String[] args) {

        DocumentContext payloadContext = JsonPath.parse(payload, jsonPathConfig);
        String s = payloadContext.jsonString();
        System.out.println(s);
        // http://www.ibloger.net/article/2329.html
        // payloadContext.read()

        Object jsonElement = readJsonElement(payloadContext, jsonPathString);
        boolean map = jsonProvider.isMap(jsonElement);
        System.out.println(map);
        if(!map) {
            System.out.println(String.format("JSON object expected at path: '%s'!", jsonPathString));
        }

    }


    private static Object readJsonElement(DocumentContext context, String jsonPathString) {
        Object payloadJsonObject = context.json();
        JsonPath jsonPath = JsonPath.compile(jsonPathString);
        return jsonPath.read(payloadJsonObject, jsonPathConfig);
    }


    private static GsonJsonProvider getJsonProvider() {
        if (isClassFound("com.fasterxml.jackson.databind.ObjectMapper")) {
            // return new JacksonJsonProvider();
        }

        if (isClassFound("org.codehaus.jettison.json.JSONObject")) {
            // return new JettisonProvider();
        }

        if (isClassFound("org.json.JSONObject")) {
            // return new JsonOrgJsonProvider();
        }

        if (isClassFound("net.minidev.json.parser.JSONParser")) {
            // return new JsonSmartJsonProvider();
        }

        if (isClassFound("com.google.gson.Gson")) {
            return new GsonJsonProvider();
        }

        String message = "At least one of the following JSON library must be added to your classpath:\n" +
                "* com.fasterxml.jackson.core:jackson-databind\n" +
                "* net.minidev:json-smart\n" +
                "* org.codehaus.jettison:jettison\n" +
                "* org.json:json\n" +
                "* com.google.code.gson:gson";
        throw new IllegalStateException(message);
    }

    private static boolean isClassFound(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

}
