package com.lzp.algorithm.sort;

/**
 * @author lzp
 */
public class SortingHelper {

    private SortingHelper() {
    }

    /**
     * 判断arr数组是否有序
     *
     * @param arr
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> boolean isSorted(E[] arr) {

        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1].compareTo(arr[i]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 测试
     *
     * @param sort
     * @param arr
     * @param <E>
     */
    public static <E extends Comparable<E>> void sortTest(Sort sort, E[] arr) {
        sortTest("-", sort, arr);
    }

    /**
     * 测试
     *
     * @param name
     * @param sort
     * @param arr
     * @param <E>
     */
    public static <E extends Comparable<E>> void sortTest(String name, Sort sort, E[] arr) {

        long startTime = System.nanoTime();
        sort.sortArr(arr);
        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000000.0;

        if (!SortingHelper.isSorted(arr)) {
            throw new RuntimeException(name + " ^failed^");
        }
        System.out.println(String.format("%s , n = %d : %f s", name, arr.length, time));
    }
}
