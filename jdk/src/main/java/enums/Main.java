package enums;

/**
 * @author liuzhipeng
 * @description
 * @create 2020-01-10 17:59
 */
public class Main {
    public static void main(String[] args) {
        BookTypeEnum bookTypeEnum = BookType2Enum.cc.getBookTypeEnum();

        System.out.println(bookTypeEnum == BookTypeEnum.AA_AA);
    }
}
