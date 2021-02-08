package proxy.jdk;

import java.lang.reflect.Proxy;

public class Demo {
    public static void main(String[] args) {
        ProxyClass proxyClass = new ProxyClass(new Person());
        Eat o = (Eat) Proxy.newProxyInstance(Demo.class.getClassLoader(), Person.class.getInterfaces(), proxyClass);
        o.eay("apple");

    }
}
