package util.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-12-19 15:19
 */
public class ListDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("1");
        list.add("2");
        list.add(null);

        System.out.println(list);
        System.out.println(list.contains(null));
        System.out.println(list.contains(""));
        System.out.println(list.contains("2"));
    }
}
