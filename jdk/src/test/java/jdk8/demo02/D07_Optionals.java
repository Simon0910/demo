package jdk8.demo02;

import java.util.Optional;

/**
 * https://blog.kaaass.net/archives/764
 *
 * @author liuzhipeng
 * @description
 * @create 2019-12-23 18:09
 */
public class D07_Optionals {

    public static void main(String[] args) {
        //of（）：为非null的值创建一个Optional
        Optional<String> optional = Optional.of("bam");
        // isPresent（）： 如果值存在返回true，否则返回false
        optional.isPresent();           // true
        //get()：如果Optional有值则将其返回，否则抛出NoSuchElementException
        optional.get();                 // "bam"
        //orElse（）：如果有值则将其返回，否则返回指定的其它值
        optional.orElse("fallback");    // "bam"
        //ifPresent（）：如果Optional实例有值则为其调用consumer，否则不做处理
        optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"

        System.out.println(getName(null));
        System.out.println(getName(new Person("FName", "LName")));

        String ss = null;
        Optional<String> string = Optional.ofNullable(ss);
        string.ifPresent(System.out::println);

        setName("dd99");
    }


    public static String getName(Person p) {
        // if (p == null || p.firstName == null)
        //     return "Unknown";
        // return p.firstName;

        // return Optional.ofNullable(p)
        //         .map(u->u.firstName)
        //         .orElse("Unknown");

        Optional<Person> p1 = Optional.ofNullable(p);
        Optional<String> s = p1.map(u -> u.firstName);
        String unknown = s.orElse("Unknown");
        return unknown;
    }


    public static String getChampionName( ) throws IllegalArgumentException {
        // if (comp != null) {
        //     CompResult result = comp.getResult();
        //     if (result != null) {
        //         User champion = result.getChampion();
        //         if (champion != null) {
        //             return champion.getName();
        //         }
        //     }
        // }
        // throw new IllegalArgumentException("The value of param comp isn't available.");

        // return Optional.ofNullable(comp)
        //         .map(c->c.getResult())
        //         .map(r->r.getChampion())
        //         .map(u->u.getName())
        //         .orElseThrow(()->new IllegalArgumentException("The value of param comp isn't available."));
        return null;
    }

    private static String name;
    public static void setName(String name) throws IllegalArgumentException {
        name = Optional.ofNullable(name).filter(D07_Optionals::isNameValid)
                .orElseThrow(()->new IllegalArgumentException("Invalid username."));
    }

    private static boolean isNameValid(String s) {
        return s.length() > 3;
    }

}
