package com.lzp.algorithm.sort;

import java.util.Random;

/**
 * @author lzp
 */
public class ArrayGenerator {

    private ArrayGenerator() {
    }

    /**
     * 生成一个长度为 n 的有序数组 范围是 [0, n)
     *
     * @param n
     * @return
     */
    public static Integer[] generateOrderedArray(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }

    /**
     * 生成一个长度为 n 的随机数组，每个数字的范围是 [0, bound)
     *
     * @param n
     * @param bound
     * @return
     */
    public static Integer[] generateRandomArray(int n, int bound) {
        Integer[] arr = new Integer[n];
        Random rnd = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = rnd.nextInt(bound);
        }
        return arr;
    }


    /**
     * 生成有n个元素的随机数组,每个元素的随机范围为[rangeL, rangeR]
     *
     * @param n
     * @param rangeL
     * @param rangeR
     * @return
     */
    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {
        assert rangeL <= rangeR;
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Integer((int) (Math.random() * (rangeR - rangeL + 1) + rangeL));
        }
        return arr;
    }


    /**
     * 打印arr数组的所有内容
     *
     * @param arr
     */
    public static void printArray(Object[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(' ');
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int l = -1;
        int r = -1;
        Random rnd = new Random();
        for (; ; ) {
            int random = rnd.nextInt(10);
            if (random == 0) {
                l = random;
                System.out.println(random);
            }
            if (random == 9) {
                r = random;
                System.out.println(random);
            }
            if (l == 0 && r == 9) {
                System.out.println("L ==" + l);
                System.out.println("R ==" + r);
                break;
            }
        }

        for (int i = 0; i < 10; i++) {
            double random = Math.random();
            System.out.println(random);
        }

        for (int i = 0; i < 10; i++) {
            double random = Math.random();
            System.out.println((int) (random * (10 - 5 + 1) + 5));
        }
    }
}
