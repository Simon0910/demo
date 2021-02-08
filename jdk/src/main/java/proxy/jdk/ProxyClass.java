package proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ProxyClass implements InvocationHandler {

    private Object obj;

    ProxyClass(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ((!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers())) && !method.isAccessible()) {
            method.setAccessible(true);
        }

        System.out.println("log start ...");

        Object invoke = method.invoke(obj, args);

        System.out.println("log end ...");
        return invoke;
    }
}
