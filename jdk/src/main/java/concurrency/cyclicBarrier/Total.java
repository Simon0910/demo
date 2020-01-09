package concurrency.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Total {
    public static void main(String[] args) {
        TotalService totalService = new TotalServiceImpl();
        CyclicBarrier barrier = new CyclicBarrier(5, new TotalTask(totalService));
        // 实际系统是查出所有省编码code的列表，然后循环，每个code
        // 生成一个线程。
        new BillTask(new BillServiceImpl(), barrier, "北京").start();
        new BillTask(new BillServiceImpl(), barrier, "上海").start();
        new BillTask(new BillServiceImpl(), barrier, "广西").start();
        new BillTask(new BillServiceImpl(), barrier, "四川").start();
        new BillTask(new BillServiceImpl(), barrier, "黑龙江").start();

        System.out.println("main");
    }

}

/**
 * 主任务：汇总任务
 */
class TotalTask implements Runnable {
    private TotalService totalService;

    TotalTask(TotalService totalService) {
        this.totalService = totalService;
    }

    @Override
    public void run() {
        // 读取内存中各省的数据汇总，过程略。
        totalService.count();
        System.out.println("开始全国汇总");
    }

}

/**
 * 子任务：计费任务
 */
class BillTask extends Thread {
    private BillService billService;     // 计费服务
    private CyclicBarrier barrier;
    private String code;    // 代码，按省代码分类，各省数据库独立。

    BillTask(BillService billService, CyclicBarrier barrier, String code) {
        this.billService = billService;
        this.barrier = barrier;
        this.code = code;
    }

    @Override
    public void run() {
        System.out.println("开始计算--" + code + "省--数据！");
        billService.bill(code); // 把bill方法结果存入内存，如ConcurrentHashMap,vector等,代码略
        System.out.println(code + "省已经计算完成,并通知汇总Service！");
        try { // 通知barrier已经完成
            barrier.await();
            System.out.println(code+"继续开始。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

interface BillService {
    public void bill(String code);
}

interface TotalService {
    public void count();
}

class BillServiceImpl implements BillService {

    @Override
    public void bill(String code) {}
}

class TotalServiceImpl implements TotalService {

    @Override
    public void count() {}
}