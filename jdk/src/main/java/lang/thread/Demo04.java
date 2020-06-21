package lang.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 侦测到那个线程, 第几次出错的
 *
 * @author lzp on 2020/6/20.
 */
public class Demo04 implements Runnable {
    int index = 0;
    static Demo04 demo04 = new Demo04();
    final boolean[] marked = new boolean[1000000];
    static AtomicInteger realIndex = new AtomicInteger();
    static AtomicInteger wrongCont = new AtomicInteger();
    static CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    static CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(demo04);
        Thread thread2 = new Thread(demo04);
        thread.start();
        thread2.start();
        thread.join();
        thread2.join();
        System.out.println("表面运行次数: " + demo04.index);
        System.out.println("真正运行次数: " + realIndex.get());
        System.out.println("错误次数: " + wrongCont.get());
        // 表面运行次数: 19929
        // 真正运行次数: 20000
        // 错误次数: 30
        // 表面运行次数: 19987
        // 真正运行次数: 20000
        // 错误次数: 14
    }

    @Override
    public void run() {
        // while (isRun() && index < 10000) {
        //     index++;
        // }
        marked[0] = true;
        for (int i = 0; i < 10000; i++) {
            try {
                cyclicBarrier2.reset();
                cyclicBarrier1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            index++;
            try {
                cyclicBarrier1.reset();
                cyclicBarrier2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            realIndex.incrementAndGet();
            synchronized (demo04) {
                if (marked[index] && marked[index - 1]) {
                    String name = Thread.currentThread().getName();
                    System.out.println("errro::" + name + index);
                    wrongCont.incrementAndGet();
                }
                marked[index] = true; // 当还没赋值成功, 错误的次数没有统计到,就发生对不上账啦
            }
        }
    }

    private boolean isRun() {
        System.out.println("aa");
        return true;
    }
}
