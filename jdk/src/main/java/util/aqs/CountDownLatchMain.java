package util.aqs;

import java.util.concurrent.CountDownLatch;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-09-23 17:00
 */
public class CountDownLatchMain {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        StringBuffer sb = new StringBuffer(255);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sb.append("waitThread1 等待任务就绪;\n");
                    latch.await();
                    sb.append("waitThread1 等待结束\n");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "one");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sb.append("waitThread2 等待任务就绪;\n");
                    latch.await();
                    sb.append("waitThread2 等待结束\n");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "two");
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sb.append("waitThread3 等待任务就绪;\n");
                    latch.await();
                    sb.append("waitThread3 等待结束\n");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "three");
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sb.append("waitThread4 等待任务就绪;\n");
                    latch.await();
                    sb.append("waitThread4 等待结束\n");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "four");
        Thread thread5 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sb.append("waitThread5 等待任务就绪;\n");
                    latch.await();
                    sb.append("waitThread5 等待结束\n");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "five");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();


        Thread.sleep(3000);

        for (int i = 0; i < 5; i++) {
            final int tmp = i;
            Thread task = new Thread(new Runnable() {
                @Override
                public void run() {
                    sb.append(String.format("task[%s] 准备就绪;\n", tmp));
                    latch.countDown();
                    sb.append(String.format("task[%s] 结束\n", tmp));
                }
            }, "down" + i);
            task.start();
        }
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();
        sb.append("test 结束;");
        System.out.println(sb.toString());
    }
}
