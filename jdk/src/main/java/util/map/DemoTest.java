package util.map;

import java.util.HashMap;

public class DemoTest {
    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void main(String[] args) {
        test02();
    }

    private static void test02() {
        HashMap<String, String> map = new HashMap<>();
        System.out.println(map.put("a", "aa"));
        System.out.println(map.put("a", "AA"));
        System.out.println(map.put("a", "AAaa"));

    }

    private static void test01() {
        System.out.println("0)) ==> " + tableSizeFor(0));
        System.out.println("1)) ==> " + tableSizeFor(1));
        System.out.println("2)) ==> " + tableSizeFor(2));
        System.out.println("3)) ==> " + tableSizeFor(3));
        System.out.println("4)) ==> " + tableSizeFor(4));
        System.out.println("5)) ==> " + tableSizeFor(5));
        System.out.println("6)) ==> " + tableSizeFor(6));
        System.out.println("7)) ==> " + tableSizeFor(7));
        System.out.println("8)) ==> " + tableSizeFor(8));
        System.out.println("9)) ==> " + tableSizeFor(9));
        System.out.println("10) ==> " + tableSizeFor(10));
        System.out.println("11) ==> " + tableSizeFor(11));
        System.out.println("12) ==> " + tableSizeFor(12));
        System.out.println("13) ==> " + tableSizeFor(13));
        System.out.println("14) ==> " + tableSizeFor(14));
        System.out.println("15) ==> " + tableSizeFor(15));
        System.out.println("16) ==> " + tableSizeFor(16));
        System.out.println("17) ==> " + tableSizeFor(17));
        System.out.println("18) ==> " + tableSizeFor(18));
        System.out.println("19) ==> " + tableSizeFor(19));
        System.out.println("20) ==> " + tableSizeFor(20));
        System.out.println("100 ==> " + tableSizeFor(100));
        System.out.println("200 ==> " + tableSizeFor(200));
    }
}
