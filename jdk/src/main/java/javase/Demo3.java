package javase;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-09-23 11:03
 */
public class Demo3 {
    public static void main(String[] args) {
        Person p = new Person();
        Person s = new Person();
        System.out.println((p == s));

        Person ss = p;
        System.out.println((p == ss));
    }

    static class Person {
    }

}
