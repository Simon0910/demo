package domains;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.List;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-12-12 16:09
 */
public class Person {
    private String nickName;
    private Integer age;
    private Date brithday;

    private List<Book> books;

    /**
     * @return nickName
     */
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return brithday
     */
    public Date getBrithday() {
        return brithday;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    /**
     * @return books
     */
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
