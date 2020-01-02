package javase;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-09-19 15:59
 */
public class Demo2 {
    public static void main(String[] args) {
        System.out.println(test01());
        // test02();
    }

    private static void test02() {
        int x = 1, y = 2, z = 3;

        x = y = z;

        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
    }

    private static String test01() {
        String str = "1";
        try {
            str = "2";
            // int i = 5/0;
            throw new RuntimeException("ex");
            // str = "3";
            // return str;
        } finally {
            str = "4";
            return str;
        }
        // return str;
    }

}
