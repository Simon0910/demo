package jdk8.demo02;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-12-24 11:11
 */
public class D10_Maps {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        map.forEach((id, val) -> System.out.println(val));//val0 val1 val2 val3 val4 val5 val6 val7 val8 val9


        // 如何使用函数在 map 上计算代码
        map.computeIfPresent(3, (num, val) -> val + num);
        map.get(3);             // val33

        map.computeIfPresent(9, (num, val) -> null);
        map.containsKey(9);     // false

        map.computeIfAbsent(23, num -> "val" + num);
        map.containsKey(23);    // true

        map.computeIfAbsent(3, num -> "bam");
        map.get(3);             // val33


        map.remove(3, "val3");
        map.get(3);             // val33
        map.remove(3, "val33");
        map.get(3);             // null


        map.getOrDefault(42, "not found");  // not found


        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        map.get(9);             // val9
        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        map.get(9);             // val9concat
    }
}
