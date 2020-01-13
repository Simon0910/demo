package enums;

/**
 * @author liuzhipeng
 * @description
 * @create 2020-01-10 17:54
 */
public enum BookTypeEnum {
    AA_AA("aa"),
    BB("bb");

    private String desc;

    BookTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
