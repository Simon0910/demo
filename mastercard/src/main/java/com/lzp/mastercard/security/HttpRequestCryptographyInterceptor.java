//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lzp.mastercard.security;

import org.apache.http.Header;

import java.util.Map;

public interface HttpRequestCryptographyInterceptor {
    Map<String, Object> addCustomHeaders(Map<String, Object> var1, Map<String, Object> var2);

    Map<String, Object> removeCustomHeaders(Header[] var1, Map<String, Object> var2);
}
