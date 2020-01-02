package com.lzp.mastercard.api;

import com.lzp.mastercard.exception.ApiException;
import com.lzp.mastercard.model.*;
import com.lzp.mastercard.security.Authentication;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-09-06 11:05
 */
public class Digitize extends BaseObject {
    private static Map<String, OperationConfig> operationConfigs = new HashMap();
    // TODO: 2019/9/29
    private static String resourcePath = "/mdes/digitization/#env/1/0/digitize";
    private static String operationUUID = "23420e42-1c58-408f-8233-bea878a7e65b";

    static {
        operationConfigs.put(operationUUID, new OperationConfig(resourcePath,
                Action.create,
                Arrays.asList(""),
                Arrays.asList("")
        ));
    }

    public Digitize() {
    }

    public Digitize(BaseObject o) {
        this.putAll(o);
    }

    public Digitize(RequestMap requestMap) {
        this.putAll(requestMap);
    }

    @Override
    protected final OperationConfig getOperationConfig(String operationUUID) throws IllegalArgumentException {
        OperationConfig operationConfig = (OperationConfig) operationConfigs.get(operationUUID);
        if (operationConfig == null) {
            throw new IllegalArgumentException("Invalid operationUUID supplied: " + operationUUID);
        } else {
            return operationConfig;
        }
    }

    @Override
    protected OperationMetadata getOperationMetadata() throws IllegalArgumentException {
        return new OperationMetadata(ResourceConfig.getInstance().getVersion(), ResourceConfig.getInstance().getHost(), ResourceConfig.getInstance().getContext(), ResourceConfig.getInstance().getJsonNative());
    }

    public static Digitize create(RequestMap map) throws ApiException {
        return create((Authentication) null, map);
    }

    public static Digitize create(Authentication auth, RequestMap map) throws ApiException {
        return new Digitize(BaseObject.executeOperation(auth, operationUUID, new Digitize(map)));
    }

}
