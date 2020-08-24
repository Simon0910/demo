package com.lzp.algorithm.sort;

/**
 * O(n^2)
 *
 * @author lzp
 */
public class Sort01SelectionSort implements Sort {

    private Sort01SelectionSort() {
    }

    public static void main(String[] args) {
        int[] dataSize = {10001, 50000};
        for (int n : dataSize) {
            Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
            SortingHelper.sortTest("SelectionSort", new Sort01SelectionSort(), arr);
        }
    }

    @Override
    public <E extends Comparable> void sortArr(E[] arr) {
        // sort(arr);
        // sort2(arr);
        sort3(arr);
    }

    public static void sort3(Comparable[] arr) {
        // 感谢github @zhengquan45 提出, 可以对选择排序进行优化
        // 在每一轮中, 可以同时找到当前未处理元素的最大值和最小值
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int minIndex = left;
            int maxIndex = right;

            // 在每一轮查找时, 要保证arr[minIndex] <= arr[maxIndex]
            if (arr[minIndex].compareTo(arr[maxIndex]) > 0) {
                swap(arr, minIndex, maxIndex);
            }

            for (int i = left + 1; i < right; i++) {
                Comparable currentItem = arr[i];
                if (currentItem.compareTo(arr[minIndex]) < 0) {
                    minIndex = i;
                } else if (currentItem.compareTo(arr[maxIndex]) > 0) {
                    maxIndex = i;
                }
            }

            swap(arr, left, minIndex);
            swap(arr, right, maxIndex);

            left++;
            right--;
        }
    }

    public static <E extends Comparable> void sort2(E[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {

            // 寻找 [0...i] 区间里的最大值
            int maxIndex = i;
            for (int j = i - 1; j > -1; j--) {
                if (arr[j].compareTo(arr[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }

            swap(arr, i, maxIndex);
        }
    }

    public static <E extends Comparable> void sort(E[] arr) {
        int len = arr.length;
        for (int i = 0, lastIndex = len - 1; i < lastIndex; i++) {

            // 寻找 [i...lastIndex] 区间里的最小值
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            swap(arr, i, minIndex);
        }
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }


}
