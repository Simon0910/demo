package jdk8.demo02;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-12-23 16:05
 */
public class D05_LambdaScopes {
    public static void main(String[] args) {
        //
        final int num = 1;
        Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);

        String convert = stringConverter.convert(2);// 3
        System.out.println(convert);


        int num2 = 1;
        Converter<Integer, String> stringConverter2 =  (from) -> String.valueOf(from + num2);

        System.out.println(stringConverter2.convert(2)); // 3


        int num3 = 1;
        Converter<Integer, String> stringConverter3 = (from) -> String.valueOf(from + num3);
        // num3 = 3;//在lambda表达式中试图修改num同样是不允许的。

        // 无法从 lambda 表达式中访问默认方法,故以下代码无法编译：
        // Formula formula = (a) -> sqrt(a * 100);
    }


    static class Lambda4 {
        static int outerStaticNum;
        int outerNum;

        void testScopes() {
            Converter<Integer, String> stringConverter1 = (from) -> {
                outerNum = 23;
                return String.valueOf(from);
            };

            Converter<Integer, String> stringConverter2 = (from) -> {
                outerStaticNum = 72;
                return String.valueOf(from);
            };
        }
    }
}
