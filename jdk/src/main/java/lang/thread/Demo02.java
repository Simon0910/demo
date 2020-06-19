package lang.thread;

/**
 * 6种状态
 *
 * @author lzp on 2020/6/18.
 */
public class Demo02 implements Runnable {
    private static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        Demo02 demo02 = new Demo02();
        Thread thread1 = new Thread(new Demo02());
        Thread thread2 = new Thread(new Demo02());
        thread1.start();
        thread2.start();
        Thread.sleep(500);

        System.out.println(thread1.getState());
        System.out.println(thread2.getState());

        Thread.sleep(2500);
        System.out.println(thread1.getState());

        thread1.join();
        thread2.join();
    }

    private void syn() throws InterruptedException {
        synchronized (object) {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(2000);
            // this.wait(2000); // Exception in thread "Thread-0" java.lang.IllegalMonitorStateException
            // object.wait(2000);
        }
    }

    @Override
    public void run() {
        try {
            // object.notify();  // Exception in thread "Thread-0" Exception in thread "Thread-1" java.lang.IllegalMonitorStateException
            syn();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
