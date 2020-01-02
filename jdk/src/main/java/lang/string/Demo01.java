package lang.string;

import org.apache.commons.lang3.StringUtils;
import utils.StringUtil;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-11-18 13:55
 */
public class Demo01 {

    public static void main(String[] args) {
        String oPCName = "模拟驱动.Channel_1.Device_1.Tag_2";

        //第一种
        String code = StringUtils.substringBeforeLast(oPCName, ".");
        System.out.println("第一种:" + code);
        //第一种:模拟驱动.Channel_1.Device_1

        //第二种
        String[] split = oPCName.split("\\.");
        for (int i = 0, len = split.length; i < len; i++) {
            System.out.println("第二种:" + split[i]);
        }
        //第二种:模拟驱动
        //第二种:Channel_1
        //第二种:Device_1
        //第二种:Tag_2

        //第三种
        String dpName = oPCName.substring(oPCName.lastIndexOf(".") + 1);
        System.out.println("第三种:" + dpName);
        //第三种:Tag_2


        String s = StringUtils.substringAfter(oPCName, ".");
        System.out.println(s);

        String year = "2089";
        // String substring = year.substring(-2);
        // System.out.println(substring);

        String substring1 = year.substring(2, 4);
        System.out.println(substring1);

        String s1 = StringUtil.spiltRtoL(year);
        String substring = s1.substring(0, 2);
        System.out.println(substring);

        year = null;
        String substring2 = StringUtils.substring(year, -2);
        System.out.println(substring2);
    }
}
