package domains;

import java.util.Date;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-12-12 16:11
 */
public class Book {
    private String name;
    private String author;
    private Date publication;

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return publication
     */
    public Date getPublication() {
        return publication;
    }

    public void setPublication(Date publication) {
        this.publication = publication;
    }
}
