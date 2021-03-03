package com.lzp.demo;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class Guava {

    /**
     * list转换为字符串
     */
    @Test
    public void joinTest() {
        List<String> names = Lists.newArrayList("John", "Jane", "Adam", "Tom");
        String result = Joiner.on(",").join(names);

        assertEquals(result, "John,Jane,Adam,Tom");
    }


    /**
     * map转换为字符串
     */
    @Test
    public void whenConvertMapToString_thenConverted() {
        Map<String, Integer> salary = Maps.newHashMap();
        salary.put("John", 1000);
        salary.put("Jane", 1500);
        String result = Joiner.on(" , ").withKeyValueSeparator(" = ")
                .join(salary);
        System.out.println(result);
    }

    /**
     * list转String，跳过null
     */
    @Test
    public void whenConvertListToStringAndSkipNull_thenConverted() {
        List<String> names = Lists.newArrayList("John", null, "Jane", "Adam", "Tom");
        String result = Joiner.on(",").skipNulls().join(names);
        System.out.println(result);
        assertEquals(result, "John,Jane,Adam,Tom");
    }

    /**
     * list转String，将null变成其他值
     */
    @Test
    public void whenUseForNull_thenUsed() {
        List<String> names = Lists.newArrayList("John", null, "Jane", "Adam", "Tom");
        String result = Joiner.on(",").useForNull("nameless").join(names);
        System.out.println(result);
        assertEquals(result, "John,nameless,Jane,Adam,Tom");
    }

    /**
     * String to List
     */
    @Test
    public void whenCreateListFromString_thenCreated() {
        String input = "apple - banana - orange";
        List<String> result = Splitter.on("-").trimResults().splitToList(input);
        System.out.println(result);
        //assertThat(result, contains("apple", "banana", "orange"));
    }

    /**
     * String to Map
     */
    @Test
    public void whenCreateMapFromString_thenCreated() {
        String input = "John=first,Adam=second";
        Map<String, String> result = Splitter.on(",")
                .withKeyValueSeparator("=")
                .split(input);

        assertEquals("first", result.get("John"));
        assertEquals("second", result.get("Adam"));
    }

    /**
     * 多个字符进行分割
     */
    @Test
    public void whenSplitStringOnMultipleSeparator_thenSplit() {
        String input = "apple.banana,,orange,,.";
        List<String> result = Splitter.onPattern("[.|,]")
                .omitEmptyStrings()
                .splitToList(input);
        System.out.println(result);
    }

    /**
     * 每隔多少字符进行分割
     */
    @Test
    public void whenSplitStringOnSpecificLength_thenSplit() {
        String input = "Hello world";
        List<String> result = Splitter.fixedLength(3).splitToList(input);
        System.out.println(result);
    }

    /**
     * 限制分割多少字后停止
     */
    @Test
    public void whenLimitSplitting_thenLimited() {
        String input = "a,b,c,d,e";
        List<String> result = Splitter.on(",")
                .limit(4)
                .splitToList(input);

        assertEquals(4, result.size());
        System.out.println(result);
    }


    @Test
    public void testListMultimap() {
        ListMultimap<String, String> listMultimap = ArrayListMultimap.create();
        listMultimap.put("a", "100");
        listMultimap.put("a", "200");
        listMultimap.put("a", "300");
        listMultimap.put("a", "300");
        listMultimap.put("b", "1");
        listMultimap.put("b", "2");
        listMultimap.put("b", "3");

        listMultimap.remove("a", "300");

        List<String> aList = listMultimap.get("a");

        System.out.println(aList);
    }
}