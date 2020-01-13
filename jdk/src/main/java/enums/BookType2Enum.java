package enums;

/**
 * @author liuzhipeng
 * @description
 * @create 2020-01-10 17:56
 */
public enum BookType2Enum {
    cc("cc", BookTypeEnum.AA_AA),
    dd("dd", BookTypeEnum.BB),
    ;

    private String desc;
    private BookTypeEnum bookTypeEnum;

    BookType2Enum(String desc, BookTypeEnum bookTypeEnum) {
        this.desc = desc;
        this.bookTypeEnum = bookTypeEnum;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BookTypeEnum getBookTypeEnum() {
        return bookTypeEnum;
    }

    public void setBookTypeEnum(BookTypeEnum bookTypeEnum) {
        this.bookTypeEnum = bookTypeEnum;
    }
}
