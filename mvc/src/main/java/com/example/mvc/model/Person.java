package com.example.mvc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.aspectj.lang.annotation.DeclareAnnotation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-28 15:41
 */
public class Person {

    @NotNull(message = "name不能为空！")
    @Min(value = 3, message = "name必须大于3个字符！")
    private String name;

    @NotNull(message = "年龄不能为空！")
    @Min(value = 1, message = "年龄必须大于18岁！")
    // @Column(length=50)
    private Integer age;

    @JsonFormat(pattern="yyyy/MM/dd HH:mm")
    private Date brithday;

    @NumberFormat(pattern="#,###")
    private Integer salary;

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
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getBrithday() {
        return brithday;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    /**
     * @return salary
     */
    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Person {");
        sb.append("    name :").append(toIndentedString(name)).append("");
        sb.append("    age :").append(toIndentedString(age)).append("");
        sb.append("    brithday :").append(toIndentedString(brithday)).append("");
        sb.append("    salary :").append(toIndentedString(salary)).append("");
        sb.append('}');
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("", "");
    }
}
