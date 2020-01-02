package lang.thread;


import java.util.concurrent.CountDownLatch;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-09-06 17:06
 */
public class Demo {
    private static CountDownLatch countDownLatch = new CountDownLatch(10);
    private static CountDownLatch countDownLatch2 = new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Task(countDownLatch, i).start();
            countDownLatch.countDown();
        }

        Thread.sleep(5000);

        for (int i = 0; i < 10; i++) {
            new Task(countDownLatch2, i).start();
            countDownLatch2.countDown();
        }

    }
}
