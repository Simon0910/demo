package jdk8;

/**
 * FunctionalInterface
 * 接口有且仅有一个抽象方法
 * 允许定义静态方法
 * 允许定义默认方法
 * 允许java.lang.Object中的public方法
 * 该注解不是必须的，如果一个接口符合"函数式接口"定义，那么加不加该注解都没有影响。加上该注解能够更好地让编译器进行检查。如果编写的不是函数式接口，但是加上了@FunctionInterface，那么编译器会报错
 */
@FunctionalInterface
interface MathOperation {
    int operation(int a, int b);

    @Override
    boolean equals(Object obj);

    default int haha(int a, int b) {
        return a;
    }
}