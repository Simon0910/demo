package utils;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-11-12 15:49
 */
public final class StringUtil {
    private StringUtil() {}

    public static boolean isNullOrEmpty(String str) {
        return null == str || str.length() == 0;
    }

    /**
     * @描述 TODO : 将指定的字符串进行倒转
     * @参数 [s]  要倒转的字符串
     * @返回值 java.lang.String 倒转后的字符串
     * @创建人 韩林
     * @创建时间 2019-06-14 17:11
     * @修改人和其它信息
     */
    public static String spiltRtoL(String s) {

        StringBuffer sb = new StringBuffer();
        int length = s.length();
        char[] c = new char[length];
        for (int i = 0; i < length; i++) {
            c[i] = s.charAt(i);
        }
        for (int i = length - 1; i >= 0; i--) {
            sb.append(c[i]);
        }

        return sb.toString();
    }
}
