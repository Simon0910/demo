package com.example.mvc.rest;

import com.alibaba.fastjson.JSON;
import com.example.mvc.MvcApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * @author liuzhipeng
 * @description
 * @create 2020-01-09 10:17
 */
@Slf4j
public class ExtractRestTemplateTest1 extends MvcApplicationTests {

    @Autowired
    ExpandRestTemplate expandRestTemplate;

    private static Integer sum = 1;
    private static Map<String, Object> map = new HashMap<>(sum);

    @Test
    public void requestTest() throws InterruptedException, ApiException {
        // ExecutorService executor = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(sum);
        //
        // for (int i = 0; i < sum; i++) {
        //     Worker w = new Worker(latch, i + "");
        //     executor.submit(w);
        // }
        //
        // latch.await();
        // System.out.println(count);
        // System.out.println(map.size());

        Worker worker = new Worker(latch, "test");
        worker.doWork();

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
            try {
                this.doWork();
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }

        private void doWork() throws ApiException {
            List<Pair> queryParams = new ArrayList<>();
            queryParams.add(new Pair("time", new Date().toString()));
            Map<String, String> uriVariables = new HashMap();
            uriVariables.put("fooValue", "2");
            String url = "https://www.baidu.com?name={fooValue}";

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.TEXT_HTML);
            ApiResponse<String> exchange = expandRestTemplate.exchange(
                    url,
                    queryParams,
                    null,
                    null,
                    HttpMethod.GET,
                    String.class,
                    uriVariables);

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
