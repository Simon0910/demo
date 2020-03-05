package util.map;

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
        System.out.println(tableSizeFor(0));
        System.out.println(tableSizeFor(1));
        System.out.println(tableSizeFor(2));
        System.out.println(tableSizeFor(3));
        System.out.println(tableSizeFor(4));
        System.out.println(tableSizeFor(5));
        System.out.println(tableSizeFor(6));
        System.out.println(tableSizeFor(7));
        System.out.println(tableSizeFor(8));
        System.out.println(tableSizeFor(9));
        System.out.println(tableSizeFor(10));
        System.out.println(tableSizeFor(11));
        System.out.println(tableSizeFor(12));
        System.out.println(tableSizeFor(13));
        System.out.println(tableSizeFor(14));
        System.out.println(tableSizeFor(15));
        System.out.println(tableSizeFor(16));
        System.out.println(tableSizeFor(17));
        System.out.println(tableSizeFor(18));
        System.out.println(tableSizeFor(19));
        System.out.println(tableSizeFor(20));
        System.out.println(tableSizeFor(100));
        System.out.println(tableSizeFor(200));
    }
}
