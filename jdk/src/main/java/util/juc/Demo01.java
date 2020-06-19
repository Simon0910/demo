package util.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 测试
 *
 * @author lzp on 2020/6/16.
 */
public class Demo01 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // ThreadPoolExecutor.submit
        Future<Runner> submit = executorService.submit(Runner::new);
    }
}
