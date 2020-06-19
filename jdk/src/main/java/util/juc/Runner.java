package util.juc;

/**
 * @author lzp on 2020/6/16.
 */
public class Runner implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            System.out.println(i);
        }
    }
}
