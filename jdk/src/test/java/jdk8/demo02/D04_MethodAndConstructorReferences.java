package jdk8.demo02;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-12-23 15:45
 */
public class D04_MethodAndConstructorReferences {
    public static void main(String[] args) {
        // 静态方法引用来表示
        Converter<String, Integer> converter = Integer::valueOf;
        Integer converted = converter.convert("123");
        System.out.println(converted.getClass());   //class java.lang.Integer


        Something something = new Something();
        Converter<String, String> converter2 = something::startsWith;
        String converted2 = converter2.convert("Java");
        System.out.println(converted2);    // "J"

        // 构造函数
        PersonFactory<Person> personFactory  = Person::new;
        Person person = personFactory.create("Peter", "Parker");



    }



}
