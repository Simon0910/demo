package util.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-09-12 17:58
 */
public class Demo {
    public static void main(String[] args) throws InterruptedException {
        final ReentrantLock lock = new ReentrantLock();
        Integer sleepTime = 10 * 1000;

        Thread thread5 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("thread5");
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "thread5");
        Thread thread6 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("thread6");
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "thread6");
        Thread thread7 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("thread7");
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "thread7");
        Thread thread8 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("thread8");
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "thread8");

        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread5.join();
        thread6.join();
        thread7.join();
        thread8.join();

    }

}
