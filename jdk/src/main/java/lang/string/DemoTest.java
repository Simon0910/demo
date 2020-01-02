package lang.string;

/**
 * @description:
 * @author: lzp
 * @date: 2020/1/2
 */

public class DemoTest {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
         sb.append(",sd");
        StringBuilder stringBuilder = sb.deleteCharAt(sb.length() - 1);
        String s = stringBuilder.toString();
        System.out.println(s);
    }
}
