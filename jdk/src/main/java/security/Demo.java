package security;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-09-29 11:10
 */
public class Demo {
    public static void main(String[] args) {
        System.out.println(generateNonce());
    }

    final private static SecureRandom random = new SecureRandom();

    protected static String generateNonce() {
        return new BigInteger(130, random).toString(32);
    }
}
