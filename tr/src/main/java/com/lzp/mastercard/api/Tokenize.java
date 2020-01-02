//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lzp.mastercard.api;

import com.lzp.mastercard.exception.ApiException;
import com.lzp.mastercard.model.*;
import com.lzp.mastercard.security.Authentication;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Tokenize extends BaseObject {
    private static Map<String, OperationConfig> operationConfigs = new HashMap();

    public Tokenize() {
    }

    public Tokenize(BaseObject o) {
        this.putAll(o);
    }

    public Tokenize(RequestMap requestMap) {
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

    public static Tokenize create(RequestMap map) throws ApiException {
        return create((Authentication) null, map);
    }

    public static Tokenize create(Authentication auth, RequestMap map) throws ApiException {
        return new Tokenize(BaseObject.executeOperation(auth, "7f62746f-d15d-4817-8f75-a18b09c64801", new Tokenize(map)));
    }

    static {
        operationConfigs.put("7f62746f-d15d-4817-8f75-a18b09c64801", new OperationConfig("/mdes/digitization/#env/1/0/tokenize", Action.create, Arrays.asList(""), Arrays.asList("")));
    }
}
