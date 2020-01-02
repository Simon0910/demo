package concurrency.demo;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-11-08 14:06
 */
public class Client {
    public static void main(String[] args) {
        // 创建服务端
        Server server = new Server();
        // 请求服务端
        FutureData<String> futureData = server.getString();

        //先执行其他任务
        String hello = "hello";
        System.out.println(hello);
        // 获取服务端结果
        System.out.print(hello + " " + futureData.getData());
    }
}
