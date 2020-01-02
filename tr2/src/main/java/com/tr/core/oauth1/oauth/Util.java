//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.tr.core.oauth1.oauth;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public final class Util {
    private static final char[] b64chars = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    private Util() {
    }

    public static String percentEncode(String str, Charset charset) {
        if (str != null && !str.isEmpty()) {
            try {
                return URLEncoder.encode(str, charset.name()).replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
            } catch (UnsupportedEncodingException var3) {
                throw new IllegalArgumentException("Unable to decode URL using " + charset.displayName() + " encoding", var3);
            }
        } else {
            return "";
        }
    }

    public static String b64Encode(byte[] data) {
        StringBuilder buffer = new StringBuilder();
        int pad = 0;

        int i;
        for (i = 0; i < data.length; i += 3) {
            int b = (data[i] & 255) << 16 & 16777215;
            if (i + 1 < data.length) {
                b |= (data[i + 1] & 255) << 8;
            } else {
                ++pad;
            }

            if (i + 2 < data.length) {
                b |= data[i + 2] & 255;
            } else {
                ++pad;
            }

            for (int j = 0; j < 4 - pad; ++j) {
                int c = (b & 16515072) >> 18;
                buffer.append(b64chars[c]);
                b <<= 6;
            }
        }

        for (i = 0; i < pad; ++i) {
            buffer.append("=");
        }

        return buffer.toString();
    }
}
