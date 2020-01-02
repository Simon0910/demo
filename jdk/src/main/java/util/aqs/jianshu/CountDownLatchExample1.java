package util.aqs.jianshu;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample1 {

    private final static int threadCount = 200;

    public static void main(String[] args) throws Exception {

        ExecutorService exec = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    test(threadNum, countDownLatch);
                } catch (Exception e) {
                    // log.error("exception", e);
                    System.out.println(e);
                }
            });
        }

        Thread.sleep(3000);

        for (int i = 0; i < threadCount; i++) {
            countDownLatch.countDown();
        }

        System.out.println("finish");
        // exec.shutdown();
    }

    private static void test(int threadNum, CountDownLatch countDownLatch) throws Exception {
        Thread.sleep(100);
        countDownLatch.await();
        System.out.println("threadNum=" + threadNum);
        Thread.sleep(100);
    }
}