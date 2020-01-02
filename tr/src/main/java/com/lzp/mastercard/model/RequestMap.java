//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lzp.mastercard.model;

import com.lzp.mastercard.model.map.SmartMap;

import java.util.Map;

public class RequestMap extends SmartMap {
    public RequestMap() {
    }

    public RequestMap(Map<String, Object> map) {
        super(map);
    }

    public RequestMap(String jsonMapString) {
        super(jsonMapString);
    }

    public RequestMap(String keyPath, Object value) {
        super(keyPath, value);
    }

    @Override
    public RequestMap set(String keyPath, Object value) {
        super.set(keyPath, value);
        return this;
    }
}
