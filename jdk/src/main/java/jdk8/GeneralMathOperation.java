package jdk8;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-08-21 16:28
 */
public class GeneralMathOperation {

    private GeneralMathOperation() {
    }

    private static final class Singleton {
        private static GeneralMathOperation instance;

        static {
            instance = new GeneralMathOperation();
        }

        public static GeneralMathOperation getInstance() {
            return instance;
        }
    }

    public static GeneralMathOperation getInstance() {
        return Singleton.getInstance();
    }

    /**
     * 类型声明
     * 相当于方法实现
     * 万一接口有新增方法会报错！！！！
     */
    static MathOperation addition = (int a, int b) -> a + b;

    /**
     * 不用类型声明
     */
    static MathOperation subtraction = (a, b) -> a - b;

    /**
     * 大括号中的返回语句
     */
    static MathOperation multiplication = (int a, int b) -> {
        return a * b;
    };

    /**
     * 没有大括号及返回语句
     */
    static MathOperation division = (int a, int b) -> a / b;

    /**
     * 不用括号
     */
    static GreetingService greetService1 = message -> System.out.println("Hello " + message);

    /**
     * 用括号
     */
    static GreetingService greetService2 = (message) -> System.out.println("Hello " + message);

    public static int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }
}
