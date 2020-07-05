package util.juc02.collections.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述：     组合操作并不保证线程安全
 */
public class OptionsNotSafe implements Runnable {

    private static ConcurrentHashMap<String, Integer> scores = new ConcurrentHashMap<String, Integer>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        scores.put("小明", 0);
        Thread t1 = new Thread(new OptionsNotSafe());
        Thread t2 = new Thread(new OptionsNotSafe());
        Thread t3 = new Thread(new OptionsNotSafe());
        Thread t4 = new Thread(new OptionsNotSafe());
        Thread t5 = new Thread(new OptionsNotSafe());
        Thread t6 = new Thread(new OptionsNotSafe());

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();

        // executorService.execute(t1);
        // executorService.execute(t2);
        // executorService.execute(t3);
        // executorService.execute(t4);
        // executorService.execute(t5);
        // executorService.execute(t6);
        // executorService.shutdown();
        // while (!executorService.isTerminated()){}

        System.out.println(scores);
    }

    // @Override
    // public void run() {
    //     long start = System.currentTimeMillis();
    //     for (int i = 0; i < 1000000; i++) {
    //         Integer score = scores.get("小明");
    //         Integer newScore = score + 1;
    //         scores.put("小明", newScore);
    //     }
    //     System.out.println(System.currentTimeMillis() - start);
    // }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        synchronized (OptionsNotSafe.class) {
            for (int i = 0; i < 1000000; i++) {
                Integer score = scores.get("小明");
                Integer newScore = score + 1;
                scores.put("小明", newScore);
            }
        }
        System.out.println(System.currentTimeMillis() - start); // 52
    }

    // @Override
    // public void run() {
    //     long start = System.currentTimeMillis();
    //     for (int i = 0; i < 1000000; i++) {
    //         boolean replaced;
    //         do {
    //             Integer score = scores.get("小明");
    //             Integer newScore = score + 1;
    //             replaced = scores.replace("小明", score, newScore);
    //         } while (!replaced);
    //     }
    //     System.out.println(System.currentTimeMillis() - start); // 150
    // }
}
