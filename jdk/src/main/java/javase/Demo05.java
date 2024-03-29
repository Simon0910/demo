package javase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-18 10:30
 */
public class Demo05 {
    private static final Logger logger = LoggerFactory.getLogger(Demo05.class);
    public static void main(String[] args) {
        try {
            error();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sucess();
    }

    public static void sucess() {
        System.out.println("sucess");
    }

    public static String error() {
        String str = null;
        try {
            System.out.println("one");

            int i = 1/0;
            System.out.println("two");
            if (true) {
                str = "wot";
                return str;
            }
        } catch (Exception e) {
            e.printStackTrace();
            str = "error";
            return str;
            // throw new RuntimeException("error");
        } finally {
            System.out.println("str = " + str);
        }

        System.out.println("three");
        return str;
    }
}
