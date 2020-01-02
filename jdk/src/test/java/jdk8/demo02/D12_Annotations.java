package jdk8.demo02;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-12-24 17:00
 */
public class D12_Annotations {
    public static void main(String[] args) {
        Hint hint = Persons.class.getAnnotation(Hint.class);
        System.out.println(hint);                   // null
        Hints hints1 = Persons.class.getAnnotation(Hints.class);
        System.out.println(hints1.value().length);  // 2

        Hint[] hints2 = Persons.class.getAnnotationsByType(Hint.class);
        System.out.println(hints2.length);          // 2
    }
}


// @Hints({@Hint("hint1"), @Hint("hint2")})
// class Persons {}

// @Hint("hint1")
@Hint("hint2")
class Persons {}