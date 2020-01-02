package jdk8.demo02;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author liuzhipeng
 * @description             内置函数式接口
 * @create 2019-12-23 16:37
 */
public class D06_Built_In_FunctionalInterfaces {
    public static void main(String[] args) {
        // Predicate 接口是只有一个参数的返回布尔类型值的 断言型 接口。
        // 该接口包含多种默认方法来将 Predicate 组合成其他复杂的逻辑（比如：与，或，非）：
        Predicate<String> predicate = (s) -> s.length() > 0;

        System.out.println(predicate.test("foo"));
        System.out.println(predicate.negate().test("foo"));

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

        // Function 接口接受一个参数并生成结果。默认方法可用于将多个函数链接在一起（compose, andThen）：
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        System.out.println(backToString.apply("123"));


        // Supplier 接口产生给定泛型类型的结果。 与 Function 接口不同，Supplier 接口不接受参数。
        Supplier<Person> personSupplier = Person::new;
        personSupplier.get();   // new Person


        // Consumer 接口表示要对单个输入参数执行的操作。
        Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
        greeter.accept(new Person("Luke", "Skywalker"));


        // Comparator 是老Java中的经典接口， Java 8在此之上添加了多种默认方法：
        Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);

        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Wonderland");

        comparator.compare(p1, p2);             // > 0
        comparator.reversed().compare(p1, p2);  // < 0


    }
}
