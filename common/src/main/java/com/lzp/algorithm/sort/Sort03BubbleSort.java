package com.lzp.algorithm.sort;

/**
 * @author lzp on 2020/8/22.
 */
public class Sort03BubbleSort implements Sort {

    @Override
    public <E extends Comparable> void sortArr(E[] arr) {
        mySort(arr);
        // sort(arr);
        // sort2(arr);
    }

    public static void main(String[] args) {
        int[] dataSize = {10001, 100000};
        // dataSize = new int[]{10001, 10000000};
        for (int n : dataSize) {
            Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
            // Integer[] arr = ArrayGenerator.generateOrderedArray(n);
            SortingHelper.sortTest(new Sort03BubbleSort(), arr);
        }
    }

    public static <E extends Comparable> void mySort(E[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, i - 1, i);
                }
            }
        }
    }

    public static <E extends Comparable> void sort2(E[] arr) {
        int n = arr.length;
        int newLastIndex;
        do {
            newLastIndex = 0;
            for (int i = 1; i < n; i++) {
                if (arr[i - 1].compareTo(arr[i]) > 0) {
                    swap(arr, i - 1, i);

                    // 记录最后一次的交换位置,在此之后的元素在下一轮扫描中均不考虑
                    newLastIndex = i;
                }
            }
            n = newLastIndex;
        } while (newLastIndex > 0);
    }


    public static <E extends Comparable> void sort(E[] arr) {
        int n = arr.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (arr[i - 1].compareTo(arr[i]) > 0) {
                    swap(arr, i - 1, i);
                    swapped = true;
                }
            }
            n--;
        } while (swapped);
    }


    private static <E> void swap(E[] arr, int i, int j) {
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

}
