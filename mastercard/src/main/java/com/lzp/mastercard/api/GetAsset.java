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

public class GetAsset extends BaseObject {
    private static Map<String, OperationConfig> operationConfigs = new HashMap();

    public GetAsset() {
    }

    public GetAsset(BaseObject o) {
        this.putAll(o);
    }

    public GetAsset(RequestMap requestMap) {
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

    public static GetAsset read(String id) throws ApiException {
        return read((Authentication) null, id, (RequestMap) null);
    }

    public static GetAsset read(String id, RequestMap map) throws ApiException {
        return read((Authentication) null, id, map);
    }

    public static GetAsset read(Authentication auth, String id) throws ApiException {
        return read(auth, id, (RequestMap) null);
    }

    public static GetAsset read(Authentication auth, String id, RequestMap map) throws ApiException {
        GetAsset val = new GetAsset();
        if (id != null) {
            val.put("id", id);
        }

        if (map != null) {
            val.putAll(map);
        }

        return new GetAsset(BaseObject.executeOperation(auth, "0f729f12-735b-416b-8a1f-9dcb3ec64e5f", val));
    }

    static {
        operationConfigs.put("0f729f12-735b-416b-8a1f-9dcb3ec64e5f", new OperationConfig("/mdes/assets/#env/1/0/asset/{AssetId}", Action.read, Arrays.asList(""), Arrays.asList("")));
    }
}
