package proxy.cglib;

import proxy.jdk.Person;

public class Demo {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        Person proxy = (Person) proxyFactory.createProxy(new Person());
        proxy.eay("Dd");
    }
}
