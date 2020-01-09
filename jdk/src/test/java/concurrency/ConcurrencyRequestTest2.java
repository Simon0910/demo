package concurrency;


import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author liuzhipeng
 * @description
 * @create 2020-01-09 10:17
 */
public class ConcurrencyRequestTest2 {


    private static Integer sum = 5000;

    @Test
    public void baiduTest() throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(1);
        CyclicBarrier barrier = new CyclicBarrier(sum, new Boss());
        // CyclicBarrier barrier = new CyclicBarrier(sum);

        for (int i = 0; i < sum; i++) {
            Worker w = new Worker(barrier, latch, i + "");
            executor.submit(w);
        }

        // 1. 无错误数据的情况
        // Thread.sleep(2000);
        // latch.countDown();

        System.out.println(count);
        Thread.sleep(10000);
        System.out.println(count);
    }


    class Worker implements Runnable {
        private CyclicBarrier barrier;
        private CountDownLatch latch;
        private String name;

        public Worker(CyclicBarrier barrier, CountDownLatch latch, String name) {
            this.barrier = barrier;
            this.latch = latch;
            this.name = name;
        }

        @Override
        public void run() {
            this.doWork();
        }

        private void doWork() {
            // 1. 无错误数据的情况
            // try {
            //     latch.await();
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }
            // add();

            // 2. 无错误数据的情况
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            add();
        }
    }

    /**
     * 主任务：汇总任务
     */
    class Boss implements Runnable {

        Boss() {

        }

        @Override
        public void run() {
            // 读取内存中各省的数据汇总，过程略。
            System.out.println("开始全国汇总");
            System.out.println(count);
        }

    }

    int count = 0;

    public void add() {
        count++;
    }

}
