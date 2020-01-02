package jdk8.demo01;

import domains.Person;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import utils.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;


/**
 * https://blog.csdn.net/lu930124/article/details/77595585/
 *
 * @author liuzhipeng
 * @description
 * @create 2019-12-19 10:52
 */
public class MainTest {
    static List<Apple> appleList = new ArrayList<>();//存放apple对象集合


    @Before
    public void init() {
        Apple apple1 = new Apple(null, "苹果1", new BigDecimal("3.25"), 10);
        Apple apple12 = new Apple(1, "苹果2", new BigDecimal("1.35"), 20);
        Apple apple2 = new Apple(2, "香蕉", new BigDecimal("2.89"), 30);
        Apple apple3 = new Apple(3, "荔枝", new BigDecimal("9.99"), 40);

        appleList.add(apple1);
        appleList.add(apple12);
        appleList.add(apple2);
        appleList.add(apple3);
    }


    // 分组
    public void test01() {
        //List 以ID分组 Map<Integer,List<Apple>>
        Map<Integer, List<Apple>> groupBy = appleList.stream().collect(Collectors.groupingBy(Apple::getId));
        System.err.println("groupBy:" + groupBy);
    }

    // 2、List转Map
    // id为key，apple对象为value，可以这么做：
    @Test
    public void test02() {
        /* *
         * List -> Map
         * 需要注意的是：
         * toMap 如果集合对象有重复的key，会报错Duplicate key .... apple1,apple12的id都为1。
         *  可以用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
         */
        Map<Integer, Apple> appleMap = appleList.stream()
                // .filter(a -> a.getId() != null)
                .collect(Collectors.toMap(Apple::getId, a -> a, (k1, k2) -> k1));
        System.out.println(appleMap);
    }


    //3、过滤Filter
    // 从集合中过滤出来符合条件的元素：
    public void test03() {
        //过滤出符合条件的数据
        List<Apple> filterList = appleList.stream().filter(a -> a.getName().equals("香蕉")).collect(Collectors.toList());
        System.err.println("filterList:" + filterList);
    }


    // 4.求和
    // 将集合中的数据按照某个属性求和:
    public void test04() {
        BigDecimal totalMoney = appleList.stream().map(Apple::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.err.println("totalMoney:" + totalMoney);  //totalMoney:17.48
    }


    // 5.查找流中最大 最小值
    // Collectors.maxBy 和 Collectors.minBy 来计算流中的最大或最小值。
    public void test05() {
        // Optional<Dish> maxDish = Dish.menu.stream().
        //         collect(Collectors.maxBy(Comparator.comparing(Dish::getCalories)));
        // maxDish.ifPresent(System.out::println);
        //
        // Optional<Dish> minDish = Dish.menu.stream().
        //         collect(Collectors.minBy(Comparator.comparing(Dish::getCalories)));
        // minDish.ifPresent(System.out::println);
    }


    // 6.去重
    public void test06() {
        // 根据id去重
        List<Apple> unique = appleList.stream().collect(
                        collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(Apple::getId))), ArrayList::new));
        System.out.println(unique);
    }
}
