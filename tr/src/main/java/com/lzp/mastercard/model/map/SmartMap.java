//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lzp.mastercard.model.map;

import org.json.simple.JSONValue;

import java.util.LinkedHashMap;
import java.util.Map;

public class SmartMap extends AbstractSmartMap {
    public SmartMap() {
        this.store = this.createMap();
    }

    public SmartMap(Map<String, Object> map) {
        this.store = this.createMap(map);
    }

    public SmartMap(String jsonMapString) {
        this.store = this.createMap((Map) JSONValue.parse(jsonMapString));
    }

    public SmartMap(String keyPath, Object value) {
        this.store = this.createMap();
        this.put(keyPath, value);
    }

    public SmartMap set(String keyPath, Object value) {
        this.put(keyPath, value);
        return this;
    }

    @Override
    protected Map<String, Object> createMap() {
        return new LinkedHashMap();
    }

    @Override
    protected Map<String, Object> createMap(Map<String, Object> map) {
        return new LinkedHashMap(map);
    }
}
