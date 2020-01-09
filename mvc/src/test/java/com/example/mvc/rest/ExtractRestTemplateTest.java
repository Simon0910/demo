package com.example.mvc.rest;

import com.example.mvc.MvcApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liuzhipeng
 * @description
 * @create 2020-01-09 10:17
 */
@Slf4j
public class ExtractRestTemplateTest extends MvcApplicationTests {

    @Autowired
    ExtractRestTemplate extractRestTemplate;

    private static Integer sum = 5000;

    @Test
    public void baiduTest() throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(sum);
        CyclicBarrier barrier = new CyclicBarrier(sum);

        for (int i = 0; i < sum; i++) {
            Worker w = new Worker(barrier, latch, i + "");
            executor.submit(w);
        }

        latch.await();
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
            // RestResponseDTO<String> exchange = extractRestTemplate.exchange(
            //         "https://www.baidu.com/",
            //         null,
            //         String.class);
            // System.out.println(this.name + " ==> " + exchange.getData());
            // try {
            //     this.barrier.await();
            // } catch (InterruptedException | BrokenBarrierException e) {
            //     e.printStackTrace();
            // }
            add();
            latch.countDown();
        }
    }

    int count = 0;

    public void add() {
        count++;
    }

}
