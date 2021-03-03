package util.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
//        test01();
        // test04();
        // test06();
        // test07();
        test08();
    }

    /**
     * treeifyBin
     */
    private static void test08() {
        HashMap<User, User> map = new HashMap<User, User>(12);
        for (int i = 1; i <= 12; i++) {
            User user = new User();
            map.put(user, user);
            System.out.println("i =" + i);
        }
    }

    private static void test07() {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 13; j++) {
                System.out.println(map.put("a" + j, "aa"));
            }
        }
    }

    /**
     * resize()
     */
    private static void test06() {
        int size = 100;

        int oldCap = 16;
        List[] oldTab = new ArrayList[oldCap];

        // % 取模 2的n次幂-1
        for (int hash = 0; hash < size; hash++) {
            List oldList = oldTab[hash & oldCap - 1];
            if (oldList != null) {
                oldList.add(hash);
            } else {
                List list = new ArrayList();
                list.add(hash);
                oldTab[hash & oldCap - 1] = list;
            }
        }

        // 扩容
        System.out.println(">>>>>>>>>>>>>>>>>>>>");
        List[] newTab = new ArrayList[oldCap * 2];

        for (int i = 0; i < oldCap; i++) {
            List<Integer> oldHead = oldTab[i];
            int len = oldHead.size();
            int j = 0;

            List loHead = new ArrayList();
            List hiHead = new ArrayList();
            do {
                Integer hash = oldHead.get(j);
                if ((hash & oldCap) == 0) {
                    loHead.add(hash);
                } else {
                    hiHead.add(hash);
                }
                j++;
            } while (j < len);
            newTab[i] = loHead;
            newTab[i + oldCap] = hiHead;
        }

    }

    private static void test05() {
        System.out.println(tableSizeFor(5000));
        System.out.println(8192 * 0.75);
    }

    private static void test04() {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < 13; i++) {
            System.out.println(map.put("a" + i, "aa"));
        }
    }

    private static void test03() {
        Integer[] arr = new Integer[16];
        System.out.println(arr.length);

        Integer[] arr2 = new Integer[]{1, 2, 3};
        System.out.println(arr2.length);
    }

    private static void test02() {
        HashMap<String, String> map = new HashMap<>();
        System.out.println(map.put("a", "aa"));
        System.out.println(map.put("a", "AA"));
        System.out.println(map.put("a", "AAaa"));

    }

    private static void test01() {
        System.out.println("0)) ==> " + tableSizeFor(0) + " - " + tableSizeFor(0) * 0.75);
        System.out.println("1)) ==> " + tableSizeFor(1) + " - " + tableSizeFor(1) * 0.75);
        System.out.println("2)) ==> " + tableSizeFor(2) + " - " + tableSizeFor(2) * 0.75);
        System.out.println("3)) ==> " + tableSizeFor(3) + " - " + tableSizeFor(3) * 0.75);
        System.out.println("4)) ==> " + tableSizeFor(4) + " - " + tableSizeFor(4) * 0.75);
        System.out.println("5)) ==> " + tableSizeFor(5) + " - " + tableSizeFor(5) * 0.75);
        System.out.println("6)) ==> " + tableSizeFor(6) + " - " + tableSizeFor(6) * 0.75);
        System.out.println("7)) ==> " + tableSizeFor(7) + " - " + tableSizeFor(7) * 0.75);
        System.out.println("8)) ==> " + tableSizeFor(8) + " - " + tableSizeFor(8) * 0.75);
        System.out.println("9)) ==> " + tableSizeFor(9) + " - " + tableSizeFor(9) * 0.75);
        System.out.println("10) ==> " + tableSizeFor(10) + " - " + tableSizeFor(10) * 0.75);
        System.out.println("11) ==> " + tableSizeFor(11) + " - " + tableSizeFor(11) * 0.75);
        System.out.println("12) ==> " + tableSizeFor(12) + " - " + tableSizeFor(12) * 0.75);
        System.out.println("13) ==> " + tableSizeFor(13) + " - " + tableSizeFor(13) * 0.75);
        System.out.println("14) ==> " + tableSizeFor(14) + " - " + tableSizeFor(14) * 0.75);
        System.out.println("15) ==> " + tableSizeFor(15) + " - " + tableSizeFor(15) * 0.75);
        System.out.println("16) ==> " + tableSizeFor(16) + " - " + tableSizeFor(16) * 0.75);
        System.out.println("17) ==> " + tableSizeFor(17) + " - " + tableSizeFor(17) * 0.75);
        System.out.println("18) ==> " + tableSizeFor(18) + " - " + tableSizeFor(18) * 0.75);
        System.out.println("19) ==> " + tableSizeFor(19) + " - " + tableSizeFor(19) * 0.75);
        System.out.println("20) ==> " + tableSizeFor(20) + " - " + tableSizeFor(20) * 0.75);
        System.out.println("24) ==> " + tableSizeFor(24) + " - " + tableSizeFor(24) * 0.75);
        System.out.println("48) ==> " + tableSizeFor(48) + " - " + tableSizeFor(48) * 0.75);
        System.out.println("96) ==> " + tableSizeFor(96) + " - " + tableSizeFor(96) * 0.75);
        System.out.println("100 ==> " + tableSizeFor(100) + " - " + tableSizeFor(100) * 0.75);
        System.out.println("200 ==> " + tableSizeFor(200) + " - " + tableSizeFor(200) * 0.75);
        System.out.println("256 ==> " + tableSizeFor(256) + " - " + tableSizeFor(256) * 0.75);
    }
}


class User {

    //保证每个元素放入同一个索引,将hashcode设置为1
    @Override
    public int hashCode() {
        return 1;
    }

    //保证在同一个索引中,每个元素不会因为相同被覆盖,将equles设置为false
    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
