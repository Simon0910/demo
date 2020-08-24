package com.lzp.algorithm.sort;

/**
 * @author lzp on 2020/8/22.
 */
public class Demo {
    public static void main(String[] args) {
        int dataSize = 9700000;
        dataSize = 9000000;
        dataSize = 6000000;
        dataSize = 5000000;
        long startTime = System.nanoTime();
        Integer[] arr = ArrayGenerator.generateRandomArray(dataSize, dataSize);
        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println(time);

        startTime = System.nanoTime();
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i - 1];
            if (i < dataSize) {
                arr[i] = temp;
            }
        }
        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000000.0;
        System.out.println(time);

    }
}
