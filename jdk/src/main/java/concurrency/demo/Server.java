package concurrency.demo;

/**
 * 服务端
 */
public class Server {
    public FutureData<String> getString() {
        final FutureData<String> data = new FutureData<>();
        // 创建工作线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 结果写回FutureData
                data.setData("world");
            }
        }).start();

        System.out.println("====");
        // 返回 FutureData
        return data;
    }
}