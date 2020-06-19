package lang.thread;

/**
 * 6种状态
 *
 * @author lzp on 2020/6/18.
 */
public class Demo03 implements Runnable {
    private static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        Demo03 demo02 = new Demo03();
        Thread thread1 = new Thread(new Demo03());
        Thread thread2 = new Thread(new Demo03());
        thread1.start();
        thread2.start();
        Thread.sleep(500);

        System.out.println(thread1.getState());
        System.out.println(thread2.getState());

        Thread.sleep(2500);
        System.out.println(thread1.getState());
        System.out.println(thread2.getState());

        thread1.join();
        thread2.join();
    }

    private void syn() throws InterruptedException {
        synchronized (object) {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10000);
        }
    }

    @Override
    public void run() {
        try {
            syn();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
