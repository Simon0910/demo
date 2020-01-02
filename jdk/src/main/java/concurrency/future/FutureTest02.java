package concurrency.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FutureTest02 {

    // Future模式计算1到1亿的和
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<Integer>> futureList = new ArrayList<>();

        // 计算1000次1至1亿的和
        for (int i = 0; i < 1000; i++) {
            // 调度执行
            futureList.add(executorService.submit(new Calc()));
        }
        System.out.println("耗时: " + (System.currentTimeMillis() - start));

        for (int i = 0; i < 1000; i++) {
            try {
                Integer result = futureList.get(i).get();
                System.out.println("第" + i + "个结果: " + result);
            } catch (InterruptedException | ExecutionException e) {
            }
        }
        System.out.println("耗时: " + (System.currentTimeMillis() - start));
        executorService.shutdown();
    }

    public static class Calc implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            return cal(100000000);
        }

        public static int cal(int num) {
            int sum = 0;
            for (int i = 0; i < num; i++) {
                sum += i;
            }
            return sum;
        }
    }
}

// 执行结果：
// 耗时: 12058
// 第0个结果: 887459712
// 第1个结果: 887459712
// ...
// 第999个结果: 887459712
// 耗时: 12405

