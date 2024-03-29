//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class StringUtils {
    public static final String EMPTY = "";

    public StringUtils() {
    }

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    public static String trimToNull(String str) {
        String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }

    public static String trimToEmpty(String str) {
        return str == null ? "" : str.trim();
    }

    public static String toString(byte[] bytes, String charsetName) throws UnsupportedEncodingException {
        return charsetName == null ? new String(bytes) : new String(bytes, charsetName);
    }

    public static String defaultString(String str) {
        return str == null ? "" : str;
    }

    public static String[] split(String src, String separator) {
        if (isEmpty(separator)) {
            return new String[]{src};
        } else {
            return isEmpty(src) ? new String[0] : src.split(separator, -1);
        }
    }

    public static String[] splitWithCommaOrSemicolon(String src) {
        if (isEmpty(src)) {
            return new String[0];
        } else {
            String[] ss = split(src.replace(',', ';'), ";");
            List<String> list = new ArrayList();
            String[] arr$ = ss;
            int len$ = ss.length;

            for (int i$ = 0; i$ < len$; ++i$) {
                String s = arr$[i$];
                if (!isBlank(s)) {
                    list.add(s.trim());
                }
            }

            return (String[]) list.toArray(new String[list.size()]);
        }
    }

    public static String join(String[] strings, String separator) {
        if (strings != null && strings.length != 0) {
            StringBuilder sb = new StringBuilder();
            String[] arr$ = strings;
            int len$ = strings.length;

            for (int i$ = 0; i$ < len$; ++i$) {
                String string = arr$[i$];
                if (isNotBlank(string)) {
                    sb.append(string).append(separator);
                }
            }

            return sb.length() > 0 ? sb.substring(0, sb.length() - separator.length()) : "";
        } else {
            return "";
        }
    }

    public static String joinWithComma(String... strings) {
        return join(strings, ",");
    }

    public static boolean isValidParamKey(String paramkey) {
        char c = paramkey.charAt(0);
        return c != '.' && c != '_';
    }

    public static boolean isInternalParamKey(String paramkey) {
        char c = paramkey.charAt(0);
        return c == '_';
    }

    public static String toString(Object o) {
        return o != null ? o.toString() : null;
    }
}
