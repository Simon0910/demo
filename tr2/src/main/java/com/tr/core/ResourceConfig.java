//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.tr.core;


public class ResourceConfig implements ResourceConfigInterface {
    private String override = null;
    private String host = null;
    private String context = null;
    private ResourceConfig instance = null;

    private ResourceConfig() {
    }

    public static ResourceConfig getInstance(ApiClient apiClient) {
        ResourceConfig instance = new ResourceConfig();
        Environment currentEnvironment = apiClient.getCurrentEnvironment();
        if (currentEnvironment == null) {
            currentEnvironment = Configuration.getDefaultEnvironment();
        }
        instance.setEnvironment(currentEnvironment);
        // Configuration.registerResourceConfig(instance);
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
