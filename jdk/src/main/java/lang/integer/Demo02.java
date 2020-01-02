package lang.integer;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-11-07 17:55
 */
public class Demo02 {
    public static void main(String[] args) {
        // int i1 = Integer.parseInt(null);
        int i2 = Integer.parseInt("0");
        int i3 = Integer.parseInt("1");

        int i4 = Double.valueOf("1.0").intValue();
        int i5 = Double.valueOf("1.1").intValue();

        // int i6 = Integer.parseInt("null");
        // int i7 = Integer.parseInt(" ");
        int i7 = Integer.parseInt(" 34fd");
        System.out.println();
    }
}
