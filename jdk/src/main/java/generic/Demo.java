package generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuzhipeng
 * @description
 * @create 2020-01-10 10:04
 */
public class Demo {

    public static <T extends Operate> int getResult(int numberA, int numberB, Class<T> clz) {
        int result = 0;
        try {
            return clz.newInstance().compute(numberA, numberB);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return result;
        }
    }

    public static void main(String[] args) {
        // 调用的时候直接传递 class 即可
        System.out.println(getResult(1, 2, SumOpearte.class));
    }

    public void inspect(List<Object> list) {
        for (Object obj : list) {
            System.out.println(obj);
        }
        //这个操作在当前方法的上下文是合法的。
        list.add("1");
    }

    public void test() {
        List<String> strs = new ArrayList<>();
        //编译错误
        // inspect(strs);

        String.valueOf(23);
        String.valueOf("23");
    }

}
