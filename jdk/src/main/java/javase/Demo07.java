package javase;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-18 10:30
 */
public class Demo07 {
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

    public static void error() {
        try {
            System.out.println("one");
            int i = 1/0;
            System.out.println("two");
        } catch (Exception e) {
            e.printStackTrace();
            // throw new RuntimeException("error");
        }

        System.out.println("three");
    }
}
