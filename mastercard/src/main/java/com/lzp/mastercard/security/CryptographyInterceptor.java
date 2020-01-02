package com.lzp.mastercard.security;


import com.lzp.mastercard.exception.SdkException;

import java.util.List;
import java.util.Map;

public interface CryptographyInterceptor {
    List<String> getTriggeringEndPath();

    Map<String, Object> encrypt(Map<String, Object> var1) throws SdkException;

    Map<String, Object> decrypt(Map<String, Object> var1) throws SdkException;
}