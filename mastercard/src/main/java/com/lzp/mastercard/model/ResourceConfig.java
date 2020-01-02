//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lzp.mastercard.model;

import com.lzp.mastercard.ApiConfig;

public class ResourceConfig implements ResourceConfigInterface {
    private String override = null;
    private String host = null;
    private String context = null;
    private static ResourceConfig instance = null;

    private ResourceConfig() {
    }

    public static ResourceConfig getInstance() {
        if (instance == null) {
            instance = new ResourceConfig();
            instance.setEnvironment(ApiConfig.getEnvironment());
            ApiConfig.registerResourceConfig(instance);
        }

        return instance;
    }

    public String getContext() {
        return this.context;
    }

    public String getHost() {
        return this.override != null ? this.override : this.host;
    }

    public String getVersion() {
        return "mdes-merchants:1.0.1";
    }

    public boolean getJsonNative() {
        return true;
    }

    @Override
    public void setEnvironment(Environment environment) {
        if (Environment.MAPPINGS.containsKey(environment)) {
            String[] config = (String[]) Environment.MAPPINGS.get(environment);
            this.host = config[0];
            this.context = config[1];
        } else {
            throw new RuntimeException("Environment: " + environment.name() + " not found for sdk:" + this.getClass().getName());
        }
    }

    @Override
    public void setEnvironment(String host, String context) {
        this.context = context;
        this.host = host;
    }
}
