//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lzp.mastercard.security;

import com.lzp.mastercard.exception.SdkException;
import com.lzp.mastercard.model.HttpMethod;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;

import java.net.URI;

public interface Authentication {
    HttpRequestBase sign(URI var1, HttpMethod var2, ContentType var3, Object var4, HttpRequestBase var5) throws SdkException;
}
