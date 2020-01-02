package javase;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-15 18:48
 */
public class Demo04 {
    private static Ext ext = new Ext();

    private static Ext get() {
        return ext;
    }

    public static void main(String[] args) throws InterruptedException {
        Demo04.get();
        Demo04.get();
        Demo04.get();

        Thread.sleep(5000);

        Demo04.get();
        Demo04.get();
        Demo04.get();
    }
}
