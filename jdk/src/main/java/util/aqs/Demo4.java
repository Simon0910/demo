package util.aqs;

import lombok.SneakyThrows;

public class Demo4 {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
//                    Thread.sleep(10);
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println(Thread.currentThread().getName() + " 依旧在运行");
                    } else {
                        System.out.println(Thread.currentThread().getName() + " 在运行");
                    }
                }
            }
        });

        thread.start();
        // 执行线程的interrupt()方法
        Thread.sleep(1000);
        thread.interrupt();

        System.out.println("main......");
    }

}
