package jdk8.topn;

import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Map<String, Integer> mapRepeat = new HashMap<>();
        mapRepeat.put("aa", 1);
        mapRepeat.put("bb", 45);
        mapRepeat.put("cc", 32);
        mapRepeat.put("dd", 226);
        mapRepeat.put("ee", 16);
        mapRepeat.put("ff", 320);
        mapRepeat.put("gg", 99);

        Map<String, Integer> collect = mapRepeat.entrySet().stream()
                .sorted(Comparator.comparing(e -> e.getValue(), Comparator.naturalOrder()))
                .limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(collect);

        Map<String, Integer> collect3 = mapRepeat.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(collect3);


        // 1.8以后 使用lambda表达式和Stream处理
        // 1.对Map的value进行降序排序，并取前5个key
        List<String> mobileList = mapRepeat.entrySet().stream()
                .sorted((Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) -> e2.getValue() - e1.getValue())
                .map(entry -> entry.getKey())
                .collect(Collectors.toList())
                .subList(0, 3);
        System.out.println(JSON.toJSONString(mobileList));


        // 2、正向对Map的value排序并将结果输出到LinkedHashMap
        final Map<String, Integer> sortedByCount1 = mapRepeat.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println(JSON.toJSONString(sortedByCount1));

        // 3、反向 reversed对Map的value排序并将结果输出到LinkedHashMap
        final Map<String, Integer> sortedByCount2 = mapRepeat.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println(JSON.toJSONString(sortedByCount2));


        // 4.正向 Comparator 对Map的value排序并将结果输出到LinkedHashMap
        final Map<String, Integer> sortedByCount3 = mapRepeat.entrySet()
                .stream()
                .sorted((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println(JSON.toJSONString(sortedByCount3));


        //反向 Comparator 对Map的value排序并将结果输出到LinkedHashMap
        final Map<String, Integer> sortedByCount4 = mapRepeat.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println(JSON.toJSONString(sortedByCount4));

        // 1.8以前
        List<Map.Entry<String, Integer>> list1 = new ArrayList<>();
        list1.addAll(mapRepeat.entrySet());
        Collections.sort(list1, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        for (Map.Entry<String, Integer> entry : list1) {
            System.out.println("key:" + entry.getKey() + ",value:" + entry.getValue());
        }


        // most popular sports

        Sport football = new Sport("Football");
        Sport tennis = new Sport("Tennis");
        Sport basketBall = new Sport("BasketBall");
        Sport handball = new Sport("Handball");
        Sport swimming = new Sport("Swimming");
        Sport running = new Sport("Running");
        Sport climbing = new Sport("Climbing");

        List<Person> people = new ArrayList<>();
        people.add(new Person(UUID.randomUUID(), "Bob", Arrays.asList(football, handball)));
        people.add(new Person(UUID.randomUUID(), "Tom", Arrays.asList(football, basketBall, tennis)));
        people.add(new Person(UUID.randomUUID(), "Tim", Arrays.asList(climbing, handball, football)));
        people.add(new Person(UUID.randomUUID(), "Marc", Arrays.asList(football, basketBall)));
        people.add(new Person(UUID.randomUUID(), "Gerard", Arrays.asList(tennis, handball)));
        people.add(new Person(UUID.randomUUID(), "Claudia", Arrays.asList(running, handball)));
        people.add(new Person(UUID.randomUUID(), "Sara", Arrays.asList(football, climbing)));
        people.add(new Person(UUID.randomUUID(), "Laura", Arrays.asList(football)));
        people.add(new Person(UUID.randomUUID(), "Mo", Arrays.asList(football, tennis)));

        //Step 1 - Merge all the sports lists of all students
        List<Sport> allSports = new ArrayList<>();
        for (Person person : people) {
            allSports.addAll(person.getSports());
        }

        // Step 2 - Transfor into a Map with groupBy and count
        Map<Sport, Long> collect2 = allSports.stream().collect(groupingBy(Function.identity(), counting()));

        // Return top 3 most popular sports
        collect2.entrySet().stream()
                .sorted(Map.Entry.<Sport, Long>comparingByValue().reversed())
                .limit(3)
                .forEach(s -> System.out.println(s.getKey().getName()));

        System.out.println();
    }



}


class Person {
    private UUID id;
    private String name;
    private List<Sport> sports = new ArrayList<>();
    //getter and setters + constructor

    public Person(UUID id, String name, List<Sport> sports) {
        this.id = id;
        this.name = name;
        this.sports = sports;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Sport> getSports() {
        return sports;
    }

    public void setSports(List<Sport> sports) {
        this.sports = sports;
    }
}

class Sport {
    private String name;
    public Sport(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}