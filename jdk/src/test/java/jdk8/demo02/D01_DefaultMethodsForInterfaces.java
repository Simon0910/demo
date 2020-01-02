package jdk8.demo02;

public class D01_DefaultMethodsForInterfaces {

  public static void main(String[] args) {
    // TODO 通过匿名内部类方式访问接口
    Formula formula = new Formula() {
        @Override
        public double calculate(int a) {
            return sqrt(a * 100);
        }
    };

    System.out.println(formula.calculate(100));     // 100.0
    System.out.println(formula.sqrt(16));           // 4.0

  }

}