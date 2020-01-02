package lang.thread;


import java.util.concurrent.CountDownLatch;

class Task extends Thread {
    private int i;
    private CountDownLatch latch;

    public Task(final CountDownLatch latch, int i) {
        this.latch = latch;
        this.i = i;
    }

    @Override
    public void run() {
        try {
            System.out.println("await===>" + i);
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // CustomHttpClientBuilder httpClientBuilder = ApiConfig.getHttpClientBuilder();
        // ApiConfig.getHttpClientBuilder().build();
        // System.out.println(i+"===>" + httpClientBuilder);
    }
}