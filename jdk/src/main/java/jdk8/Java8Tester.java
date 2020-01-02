package jdk8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Java8Tester {


    public static void main(String args[]) {

        /*System.out.println("10 + 5 = " + GeneralMathOperation.operate(10, 5, GeneralMathOperation.addition));
        System.out.println("10 - 5 = " + GeneralMathOperation.operate(10, 5, GeneralMathOperation.subtraction));
        System.out.println("10 x 5 = " + GeneralMathOperation.operate(10, 5, GeneralMathOperation.multiplication));
        System.out.println("10 / 5 = " + GeneralMathOperation.operate(10, 5, GeneralMathOperation.division));
*/
        /*int operate = GeneralMathOperation.operate(2, 64, new MathOperation() {
            @Override
            public int operation(int a, int b) {
                int A = 1;
                long B = 1;
                int len = b + 1;
                for (int i = 1; i < len; i++) {
                    A = A * a;
                    B = B * a;
                    if (A != B) {
                        System.out.println("==>"+i);
                    }
                }
                System.out.println(""+A);
                System.out.println(""+B);
                return A;
            }
        });

        GeneralMathOperation.operate(1, 2, (int a, int b) -> a / b);
*/

        /*GeneralMathOperation.greetService1.sayMessage("Runoob");
        GeneralMathOperation.greetService2.sayMessage("Google");
*/
        /*final int num = 1;
        Converter<Integer, String> s = (param) -> System.out.println(String.valueOf(param + num));
        // 输出结果为 3
        s.convert(2);


        // 在 Lambda 表达式当中不允许声明一个与局部变量同名的参数或者局部变量。
        String first11 = "";
        Comparator<String> comparator = (first, second) -> Integer.compare(first.length(), second.length());
*/

        /*int n = 1;
        long r = 1;
        for (int i = 0,j=1; i < j; i++,j++) {
            r++;
            n++;
            if(r != n) {
                System.out.println(r);
                System.out.println(n);
                return;
            }
        }*/

        List names = new ArrayList();

        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");

        names.forEach(System.out::println);

    }

    // 使用 java 7 排序
    private void sortUsingJava7(List<String> names) {
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });
    }

    // 使用 java 8 排序
    private void sortUsingJava8(List<String> names) {
        Collections.sort(names, (s1, s2) -> s1.compareTo(s2));
    }

}