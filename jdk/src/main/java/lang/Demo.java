package lang;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-12-11 14:32
 */
public class Demo {
    public static void main(String[] args) {
        System.out.printf("Start TransmittableThreadLocal[%s] Dump...\n", "title1");
        System.out.format("Start TransmittableThreadLocal[%s] Dump...\n", "title2");
        Map<String, Object> map = new HashMap<>(2000);
        for (int i = 0; i < 1000; i++) {
            map.put("collectUniqueNo", "value1234567");
        }
        System.out.println("ok...");

    }
}
