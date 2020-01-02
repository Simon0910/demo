package com.example.mvc.controller;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-12-05 15:10
 */
public class EnvironmentAdapter {
    public static String getEnv(String env) {
        switch (env) {
            case "mtf":
                return "MTF";
            default:
                return "prod";
        }
    }
}
