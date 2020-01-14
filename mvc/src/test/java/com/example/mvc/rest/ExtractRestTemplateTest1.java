package com.example.mvc.rest;

import com.alibaba.fastjson.JSON;
import com.example.mvc.MvcApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liuzhipeng
 * @description
 * @create 2020-01-09 10:17
 */
@Slf4j
public class ExtractRestTemplateTest1 extends MvcApplicationTests {

    @Autowired
    ExtractRestTemplate extractRestTemplate;

    private static Integer sum = 1;
    private static Map<String, Object> map = new HashMap<>(sum);

    @Test
    public void requestTest() throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(sum);

        for (int i = 0; i < sum; i++) {
            Worker w = new Worker(latch, i + "");
            executor.submit(w);
        }

        latch.await();
        System.out.println(count);
        System.out.println(map.size());
    }


    class Worker implements Runnable {
        private CountDownLatch latch;
        private String name;

        public Worker(CountDownLatch latch, String name) {
            this.latch = latch;
            this.name = name;
        }

        @Override
        public void run() {
            this.doWork();
        }

        private void doWork() {
            ApiResponse<String> exchange = extractRestTemplate.exchange(
                    "https://www.baidu.com/",
                    null,
                    null,
                    HttpMethod.GET,
                    String.class);

            System.out.println(this.name + " ==> " + JSON.toJSONString(exchange.getData()));
            map.put(this.name, this.name);
            // add();
            latch.countDown();
        }
    }

    int count = 0;

    public void add() {
        count++;
    }

}
