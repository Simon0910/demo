package proxy.jdk;

public class Person implements Eat {
    @Override
    public String eay(String food) {
        System.out.println("person eat " + food);
        return null;
    }
}
