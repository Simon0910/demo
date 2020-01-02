package javase;

import java.util.Arrays;
import java.util.List;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-12-03 16:46
 */
public class Demo06 {
    public static void main(String[] args) {

        List<String> strings = Arrays.asList("user", "risk", "app", "");

        for (int i = 0; i < strings.size(); i++) {
            Boolean success = Boolean.FALSE;
            String msgType = strings.get(i);
            switch (msgType) {
                case "user":
                    System.out.println("user task start.......................");
                    success = Boolean.TRUE;
                    break;
                case "risk":
                    // try {
                    //     System.out.println("risk task start..........................");
                    //     int num = 1/0;
                    //     success = Boolean.TRUE;
                    // }catch (Exception e) {
                    //     System.out.println("=====>" + e.getMessage());
                    // }
                    success = task();
                    break;
                case "app":
                    System.out.println("app task start..........................");
                    success = Boolean.TRUE;
                    break;
                default:
                    System.out.println("default");
                    break;
            }
            if (success) {
                System.out.println(msgType + ": is success");
            }
            System.out.println(msgType + " ++ " + success);
        }

    }


    private static boolean task() {
        boolean isOk = false;
        try {
            System.out.println("risk task start..........................");
            int num = 1/0;
            isOk = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isOk;
    }
}
