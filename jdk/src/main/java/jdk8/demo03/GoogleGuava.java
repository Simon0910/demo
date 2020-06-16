package jdk8.demo03;

import com.google.common.collect.Sets;
import org.apache.commons.collections.ListUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * GoogleGuava
 * 测试
 *
 * @author lzp
 * @version 1.0
 * @date 2020/6/9
 */
public class GoogleGuava {
    public static void main(String[] args) {
        test01();
    }

    private static void test01() {
        Set<Integer> set1 = Sets.newHashSet(1, 2, 3, 4, 5, 6);
        Set<Integer> set2 = Sets.newHashSet(1, 2, 3, 4);

        Sets.SetView<Integer> intersection = Sets.intersection(set1, set2);

        Sets.SetView<Integer> difference = Sets.difference(set1, set2);

        Sets.SetView<Integer> difference2 = Sets.difference(set2, set1);

        List<Student> students1 = Arrays.asList(
                new Student(1L, 21),
                new Student(2L, 22),
                new Student(3L, 23),
                new Student(4L, 24));
        List<Student> students2 = Arrays.asList(
                new Student(3L, 233),
                new Student(4L, 244),
                new Student(5L, 25),
                new Student(6L, 26));
        List<Student> intersection1 = ListUtils.intersection(students1, students2);

    }
}

class Student {
    Long id;
    Integer age;
    Boolean sex;
    Date birthday;
    String desc;

    public Student(Long id, Integer age) {
        this.id = id;
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}