package jdk8.demo02;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-12-23 15:15
 */
public class D03_FunctionalInterfaces {
    public static void main(String[] args) {
        // TODO 将数字字符串转换为整数类型
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");
        System.out.println(converted.getClass()); //class java.lang.Integer
    }
}
