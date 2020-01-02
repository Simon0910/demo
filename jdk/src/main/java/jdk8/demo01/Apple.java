package jdk8.demo01;

import javax.annotation.Nullable;
import java.math.BigDecimal;

public class Apple {
    private Integer id;
    private String name;
    private BigDecimal money;
    private Integer num;

    public Apple(Integer id, String name, BigDecimal money, Integer num) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.num = num;
    }

    /**
     * public Apple id(java.lang.Integer id) {this.id = id;return this;}
     *
     * @return id
     */
    @Nullable
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * public Apple name(java.lang.String name) {this.name = name;return this;}
     *
     * @return name
     */
    @Nullable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * public Apple money(java.math.BigDecimal money) {this.money = money;return this;}
     *
     * @return money
     */
    @Nullable
    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * public Apple num(java.lang.Integer num) {this.num = num;return this;}
     *
     * @return num
     */
    @Nullable
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}