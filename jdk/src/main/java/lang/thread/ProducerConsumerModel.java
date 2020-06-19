package lang.thread;

import java.util.Date;
import java.util.LinkedList;

/**
 * @author lzp on 2020/6/19.
 */
public class ProducerConsumerModel {
    public static void main(String[] args) throws InterruptedException {
        Storage storage = new Storage();
        Thread thread1 = new Thread(new Producer(storage));
        Thread thread2 = new Thread(new Consumer(storage));
        thread1.start();
        // thread2.start();

        System.out.println("thread1::" + thread1.getState());
        System.out.println("thread2::" + thread2.getState());
        Thread.sleep(2000);
        System.out.println("thread1::" + thread1.getState());
        System.out.println("thread2::" + thread2.getState());
        thread2.start();
        Thread.sleep(2000);
        System.out.println("thread1::" + thread1.getState());
        System.out.println("thread2::" + thread2.getState());
        Thread.sleep(2000);
        System.out.println("thread1::" + thread1.getState());
        System.out.println("thread2::" + thread2.getState());
    }
}

class Producer implements Runnable {
    private Storage storage;

    public Producer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        while (true) {
            storage.put();
        }
    }
}

class Consumer implements Runnable {
    private Storage storage;

    public Consumer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        while (true) {
            storage.take();
        }
    }
}

class Storage {
    private int maxSize;
    private LinkedList<Date> storage;

    public Storage() {
        maxSize = 10;
        storage = new LinkedList<>();
    }

    public synchronized void put() { //BLOCKED
        while (storage.size() > maxSize) {
            try {
                System.out.println("生产者被阻塞=====.");
                wait(); // WAITING释放(synchronized):  notify()后 ==> RUNABLE(ready?running?) ==> BLOCKED(synchronized)
                System.out.println("生产者被唤醒......");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            // Thread.sleep(5000); // TIMED_WAITING
            Thread.sleep(1);
            // wait(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Date date = new Date();
        storage.add(date);
        System.out.println("生产了" + date + ", 总共: " + storage.size());
        notify();
    }

    public synchronized Date take() {//BLOCKED
        while (storage.size() < 1) {
            try {
                System.out.println("消费者被阻塞=====.");
                wait();// WAITING
                System.out.println("消费者被唤醒......");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(500);// TIMED_WAITING
            // wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Date date = storage.poll();
        System.out.println("消费了" + date + "还剩: " + storage.size());
        notify();
        return date;
    }
}