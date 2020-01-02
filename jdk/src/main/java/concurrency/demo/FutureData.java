package concurrency.demo;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Future 包装类
 */
public class FutureData<T> {
    private boolean mIsReady = false;
    private T mData;
    private volatile static Lock lock = new ReentrantLock();
    private volatile static Condition condition = lock.newCondition();

    public synchronized void setData(T data) {
        mIsReady = true;
        mData = data;

        // 数据准备好，退出等待 
        // TODO: 2019/11/8 使用指定唤醒 conditionLock
        //  condition.signal();
        notifyAll();
    }

    public synchronized T getData() {
        while (!mIsReady) {
            // 数据未准备，好等待
            System.out.println("ready...");
            try {
                // condition.await();
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ready finish");

        }
        return mData;
    }
}

