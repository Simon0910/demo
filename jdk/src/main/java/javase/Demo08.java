package javase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuzhipeng
 * @description
 * @create 2020-01-17 09:52
 */
public class Demo08 {
    private static List<Observer> observers = new ArrayList<>(4);
    static {
        observers.add(new Demo08().new OneObserver());
        observers.add(new Demo08().new TwoObserver());
    }


    public static void main(String[] args) {
        notifyObserver();
        System.out.println("main");
    }

    public static void notifyObserver() {
        observers.forEach(o -> {
            o.handler();
        });
    }

    interface Observer {
        void handler();
    }

    class OneObserver implements Observer {
        @Override
        public void handler() {
            System.out.println("one 。。。");
            sendMq();
            System.out.println("one");
        }
    }

    class TwoObserver implements Observer {
        @Override
        public void handler() {
            System.out.println("two 。。。");
            sendMq();
            System.out.println("two");
        }
    }

    private void sendMq() {
        try {
            int i = 1/0;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
