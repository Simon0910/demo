//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lzp.mastercard.crypto.util;

import java.io.ByteArrayOutputStream;

public enum DataEncoding {
    BASE64,
    HEX;

    private static final char[] b64chars = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static final int[] b64ints = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

    DataEncoding() {
    }

    public static String base64Encode(byte[] bytes) {
        if (null == bytes) {
            throw new IllegalArgumentException("Can't base64 encode a null value!");
        } else {
            StringBuilder buffer = new StringBuilder();
            int pad = 0;

            int i;
            for (i = 0; i < bytes.length; i += 3) {
                int b = (bytes[i] & 255) << 16 & 16777215;
                if (i + 1 < bytes.length) {
                    b |= (bytes[i + 1] & 255) << 8;
                } else {
                    ++pad;
                }

                if (i + 2 < bytes.length) {
                    b |= bytes[i + 2] & 255;
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

    protected static byte[] base64Decode(String value) {
        if (null == value) {
            throw new IllegalArgumentException("Can't base64 decode a null value!");
        } else {
            byte[] valueBytes = value.getBytes();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int i = 0;

            while (true) {
                while (i < valueBytes.length) {
                    if (b64ints[valueBytes[i]] != -1) {
                        int b = (b64ints[valueBytes[i]] & 255) << 18;
                        int num = 0;
                        if (i + 1 < valueBytes.length && b64ints[valueBytes[i + 1]] != -1) {
                            b |= (b64ints[valueBytes[i + 1]] & 255) << 12;
                            ++num;
                        }

                        if (i + 2 < valueBytes.length && b64ints[valueBytes[i + 2]] != -1) {
                            b |= (b64ints[valueBytes[i + 2]] & 255) << 6;
                            ++num;
                        }

                        if (i + 3 < valueBytes.length && b64ints[valueBytes[i + 3]] != -1) {
                            b |= b64ints[valueBytes[i + 3]] & 255;
                            ++num;
                        }

                        while (num > 0) {
                            int c = (b & 16711680) >> 16;
                            outputStream.write((char) c);
                            b <<= 8;
                            --num;
                        }

                        i += 4;
                    } else {
                        ++i;
                    }
                }

                return outputStream.toByteArray();
            }
        }
    }
}
