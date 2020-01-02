package jdk8.demo02;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-12-23 14:49
 */
public class D02_LambdaExpressions {
    public static void main(String[] args) {
        // 首先看看在老版本的Java中是如何排列字符串的：
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                // return b.compareTo(a);
                int i = a.compareTo(b);
                return i;
            }
        });

        Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });

        Collections.sort(names, (String a, String b) -> b.compareTo(a));

        names.sort((a, b) -> b.compareTo(a));
    }
}
