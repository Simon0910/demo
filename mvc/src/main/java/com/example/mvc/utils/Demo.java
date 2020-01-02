package com.example.mvc.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-11-01 17:23
 */
public class Demo {
    public static void main(String[] args) throws IOException {
        InputStream resourceAsStream = Demo.class.getClassLoader().getResourceAsStream("1.txt");
        int read = resourceAsStream.read();
        System.out.println(read);
        InputStream dfssdf = Demo.class.getClassLoader().getResourceAsStream("");
        int read1 = dfssdf.read();
        System.out.println(read1);
    }
}
