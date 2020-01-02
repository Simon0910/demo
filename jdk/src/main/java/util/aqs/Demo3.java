package util.aqs;

import java.util.concurrent.locks.LockSupport;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-09-23 15:08
 */
public class Demo3 {
    public static void main(String[] args) {
        MyAQS myAQS = new MyAQS();

        myAQS.ApplyPermission();
        myAQS.ApplyPermission();
        myAQS.ApplyPermission();

        boolean b = myAQS.parkAndCheckInterrupt();
        System.out.println(b);
        boolean b2 = myAQS.parkAndCheckInterrupt();
        System.out.println(b);
        boolean b3 = myAQS.parkAndCheckInterrupt();
        System.out.println(b);
        boolean b4 = myAQS.parkAndCheckInterrupt();
        System.out.println(b);

    }

    static class MyAQS {
        public final boolean parkAndCheckInterrupt() {
            LockSupport.park(this);
            return Thread.interrupted();
        }

        public void ApplyPermission() {
            LockSupport.unpark(Thread.currentThread());
        }
    }
}
