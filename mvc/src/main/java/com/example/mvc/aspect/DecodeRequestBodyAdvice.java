package com.example.mvc.aspect;

import com.example.mvc.config.MvcFieldLevelEncryptionConfig;
import com.example.mvc.config.MvcFieldLevelEncryptionParams;
import com.example.mvc.config.SecretConfiguration;
import com.example.mvc.json.JsonEngine;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.spi.json.JsonProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import static com.example.mvc.utils.EncodingUtils.decodeValue;

@Slf4j
@ControllerAdvice(basePackages = "com.example.mvc.controller")
@ConditionalOnProperty(prefix = "faster.secret", name = "enabled", havingValue = "true")
@EnableConfigurationProperties({SecretConfiguration.class})
@Order(1)
public class DecodeRequestBodyAdvice implements RequestBodyAdvice {
    private static final String SUN_JCE = "SunJCE";
    private static final String SYMMETRIC_CYPHER = "AES/CBC/PKCS5Padding";

    private static JsonEngine jsonEngine;
    private static Configuration jsonPathConfig = withJsonEngine(JsonEngine.getDefault());

    @Autowired
    private MvcFieldLevelEncryptionConfig config;

    @Autowired
    private SecretConfiguration secretConfiguration;

    /**
     * Specify the JSON engine to be used.
     *
     * @param jsonEngine A {@link JsonEngine} instance
     */
    public static synchronized Configuration withJsonEngine(JsonEngine jsonEngine) {
        DecodeRequestBodyAdvice.jsonEngine = jsonEngine;
        DecodeRequestBodyAdvice.jsonPathConfig = new Configuration.ConfigurationBuilder()
                .jsonProvider(jsonEngine.getJsonProvider())
                .options(Option.SUPPRESS_EXCEPTIONS)
                .build();
        return jsonPathConfig;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        if (!secretConfiguration.isEnabled()) {
            return false;
        }
        Class<? extends Annotation> annotationClass = secretConfiguration.getAnnotationClass();
        if (methodParameter.getClass().getAnnotationsByType(annotationClass) != null) {
            return true;
        }
        if (methodParameter.getMethodAnnotation(annotationClass) != null) {
            return true;
        }
        return methodParameter.getContainingClass().getAnnotation(annotationClass) != null;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage request, MethodParameter parameter, Type targetType,
                                           Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        InputStream encryptStream = request.getBody();
        String encryptBody = StreamUtils.copyToString(encryptStream, Charset.defaultCharset());
        if (!StringUtils.isEmpty(encryptBody)) {
            DocumentContext payloadContext = JsonPath.parse(encryptBody, jsonPathConfig);
            Map<String, String> decryptionPaths = config.decryptionPaths;
            if (!Objects.isNull(decryptionPaths)) {
                decryptionPaths.forEach((k, v) -> { decryptPayloadPath(payloadContext, k, v, null); });
                return new CustomHttpInputMessage(request.getHeaders(),
                        new ByteArrayInputStream(payloadContext.jsonString().getBytes("UTF-8")));
            }
        }
        return request;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
                                  Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    private void decryptPayloadPath(DocumentContext payloadContext, String jsonPathIn, String jsonPathOut,
                                    MvcFieldLevelEncryptionParams params) {
        try {
            Object inJsonObject = readJsonObject(payloadContext, jsonPathIn);
            if (inJsonObject == null) {
                // Nothing to decrypt
                return;
            }

            Object encryptedValueJsonElement = readAndDeleteJsonKey(payloadContext, jsonPathIn, inJsonObject,
                    config.encryptedValueFieldName);
            if (jsonEngine.isNullOrEmptyJson(encryptedValueJsonElement)) {
                // Nothing to decrypt
                return;
            }

            if (params == null) {
                // Read encryption params from the payload
                Object oaepDigestAlgorithmJsonElement = readAndDeleteJsonKey(payloadContext, jsonPathIn, inJsonObject
                        , config.oaepPaddingDigestAlgorithmFieldName);
                String oaepDigestAlgorithm = jsonEngine.isNullOrEmptyJson(oaepDigestAlgorithmJsonElement) ?
                        config.oaepPaddingDigestAlgorithm : jsonEngine.toJsonString(oaepDigestAlgorithmJsonElement);
                Object encryptedKeyJsonElement = readAndDeleteJsonKey(payloadContext, jsonPathIn, inJsonObject,
                        config.encryptedKeyFieldName);
                Object ivJsonElement = readAndDeleteJsonKey(payloadContext, jsonPathIn, inJsonObject,
                        config.ivFieldName);
                readAndDeleteJsonKey(payloadContext, jsonPathIn, inJsonObject,
                        config.encryptionCertificateFingerprintFieldName);
                readAndDeleteJsonKey(payloadContext, jsonPathIn, inJsonObject,
                        config.encryptionKeyFingerprintFieldName);
                params = new MvcFieldLevelEncryptionParams(jsonEngine.toJsonString(ivJsonElement),
                        jsonEngine.toJsonString(encryptedKeyJsonElement), oaepDigestAlgorithm, config);
            }

            // Decrypt data
            byte[] encryptedValueBytes = decodeValue(jsonEngine.toJsonString(encryptedValueJsonElement),
                    config.fieldValueEncoding);
            byte[] decryptedValueBytes = decryptBytes(params.getSecretKey(), params.getIvSpec(), encryptedValueBytes);

            // Add decrypted data at the given JSON path
            String decryptedValue = new String(decryptedValueBytes, StandardCharsets.UTF_8);
            decryptedValue = sanitizeJson(decryptedValue);
            checkOrCreateOutObject(payloadContext, jsonPathOut);
            addDecryptedDataToPayload(payloadContext, decryptedValue, jsonPathOut);

            // Remove the input if now empty
            Object inJsonElement = readJsonElement(payloadContext, jsonPathIn);
            if (0 == jsonEngine.getJsonProvider().length(inJsonElement) && !"$".equals(jsonPathIn)) {
                payloadContext.delete(jsonPathIn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Remove the input if now empty
        Object inJsonElement = readJsonElement(payloadContext, jsonPathIn);
        if (0 == jsonEngine.getJsonProvider().length(inJsonElement) && !"$".equals(jsonPathIn)) {
            payloadContext.delete(jsonPathIn);
        }
    }

    private static byte[] decryptBytes(Key key, AlgorithmParameterSpec iv, byte[] bytes) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(SYMMETRIC_CYPHER, SUN_JCE);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        return cipher.doFinal(bytes);
    }

    private static void addDecryptedDataToPayload(DocumentContext payloadContext, String decryptedValue,
                                                  String jsonPathOut) {
        JsonProvider jsonProvider = jsonEngine.getJsonProvider();
        Object decryptedValueJsonElement = jsonEngine.parse(decryptedValue);

        if (!jsonEngine.isJsonObject(decryptedValueJsonElement)) {
            // Array or primitive: overwrite
            payloadContext.set(jsonPathOut, decryptedValueJsonElement);
            return;
        }

        // Object: merge
        int length = jsonProvider.length(decryptedValueJsonElement);
        Collection<String> propertyKeys = (0 == length) ? Collections.<String>emptyList() :
                jsonProvider.getPropertyKeys(decryptedValueJsonElement);
        for (String key : propertyKeys) {
            payloadContext.delete(jsonPathOut + "." + key);
            payloadContext.put(jsonPathOut, key, jsonProvider.getMapValue(decryptedValueJsonElement, key));
        }
    }

    private static void checkOrCreateOutObject(DocumentContext context, String jsonPathOutString) {
        Object outJsonObject = readJsonObject(context, jsonPathOutString);
        if (null != outJsonObject) {
            // Object already exists
            return;
        }

        // Path does not exist: if parent exists then we create a new object under the parent
        String parentJsonPath = JsonEngine.getParentJsonPath(jsonPathOutString);
        Object parentJsonObject = readJsonObject(context, parentJsonPath);
        if (parentJsonObject == null) {
            throw new IllegalArgumentException(String.format("Parent path not found in payload: '%s'!",
                    parentJsonPath));
        }
        outJsonObject = jsonEngine.getJsonProvider().createMap();
        String elementKey = JsonEngine.getJsonElementKey(jsonPathOutString);
        context.put(parentJsonPath, elementKey, outJsonObject);
    }

    private static Object readAndDeleteJsonKey(DocumentContext context, String objectPath, Object object, String key) {
        if (null == key) {
            // Do nothing
            return null;
        }
        JsonProvider jsonProvider = jsonPathConfig.jsonProvider();
        Object value = jsonProvider.getMapValue(object, key);
        context.delete(objectPath + "." + key);
        return value;
    }

    private static String sanitizeJson(String json) {
        return json.replaceAll("\n", "")
                .replaceAll("\r", "")
                .replaceAll("\t", "");
    }

    private static Object readJsonElement(DocumentContext context, String jsonPathString) {
        Object payloadJsonObject = context.json();
        JsonPath jsonPath = JsonPath.compile(jsonPathString);
        return jsonPath.read(payloadJsonObject, jsonPathConfig);
    }

    private static Object readJsonObject(DocumentContext context, String jsonPathString) {
        Object jsonElement = readJsonElement(context, jsonPathString);
        if (jsonElement == null) {
            return null;
        }
        if (!jsonEngine.isJsonObject(jsonElement)) {
            throw new IllegalArgumentException(String.format("JSON object expected at path: '%s'!", jsonPathString));
        }
        return jsonElement;
    }

    static class CustomHttpInputMessage implements HttpInputMessage {
        HttpHeaders headers;
        InputStream body;

        public CustomHttpInputMessage(HttpHeaders headers, InputStream body) {
            this.headers = headers;
            this.body = body;
        }

        @Override
        public InputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }
    }
}