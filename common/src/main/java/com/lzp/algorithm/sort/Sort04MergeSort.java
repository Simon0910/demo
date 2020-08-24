package com.lzp.algorithm.sort;


import java.util.Arrays;

/**
 * @author lzp on 2020/8/22.
 */
public class Sort04MergeSort implements Sort {

    @Override
    public <E extends Comparable> void sortArr(E[] arr) {
        // sort(arr);
        sort2(arr); // 优先
    }


    public static void main(String[] args) {
        int[] dataSize = {10001, 1000000};
        for (int n : dataSize) {
            // Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
            Integer[] arr = ArrayGenerator.generateOrderedArray(n);
            Integer[] arr2 = Arrays.copyOfRange(arr, 0, n);
            SortingHelper.sortTest(new Sort02InsertionSort(), arr);
            SortingHelper.sortTest(new Sort04MergeSort(), arr2);
        }
    }


    public static <E extends Comparable> void sort2_2(E[] arr) {
        int n = arr.length;
        // Merge Sort Bottom Up 优化
        // 对于小数组, 使用插入排序优化
        for (int i = 0; i < n; i += 16) {
            Sort02InsertionSort.sort3(arr, i, Math.min(i + 15, n - 1));
        }

        for (int sz = 16; sz < n; sz += sz) {
            for (int i = 0; i < n - sz; i += sz + sz)
            // 对于arr[mid] <= arr[mid+1]的情况,不进行merge
            {
                if (arr[i + sz - 1].compareTo(arr[i + sz]) > 0) {
                    merge(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1));
                }
            }
        }
    }

    public static <E extends Comparable> void sort2_1(E[] arr) {
        int n = arr.length;
        // Merge Sort Bottom Up 无优化版本
        for (int sz = 1; sz < n; sz *= 2) {
            for (int i = 0; i < n - sz; i += sz + sz)
            // 对 arr[i...i+sz-1] 和 arr[i+sz...i+2*sz-1] 进行归并
            {
                merge(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1));
            }
        }
    }


    public static <E extends Comparable> void sort2(E[] arr) {
        int n = arr.length;
        recursionSort2(arr, 0, n - 1);
    }

    // 递归使用归并排序,对arr[l...r]的范围进行排序
    public static <E extends Comparable> void recursionSort2(E[] arr, int l, int r) {
        // 优化2: 对于小规模数组, 使用插入排序
        if (r - l <= 15) {
            Sort02InsertionSort.sort3(arr, l, r);
            return;
        }

        int mid = (l + r) / 2;
        recursionSort2(arr, l, mid);
        recursionSort2(arr, mid + 1, r);
        // 优化1: 对于arr[mid] <= arr[mid+1]的情况,不进行merge
        // 对于近乎有序的数组非常有效,但是对于一般情况,有一定的性能损失
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge(arr, l, mid, r);
        }
    }

    public static <E extends Comparable> void sort(E[] arr) {
        int n = arr.length;
        recursionSort(arr, 0, n - 1);
    }

    public static <E extends Comparable> void recursionSort(E[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        recursionSort(arr, l, mid);
        recursionSort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    private static <E extends Comparable> void merge(E[] arr, int l, int mid, int r) {
        int len = r + 1;
        E[] tempArr = Arrays.copyOfRange(arr, l, len);

        // 初始化，i指向左半部分的起始索引位置l；j指向右半部分起始索引位置mid+1
        int i = l, j = mid + 1;
        for (int k = l; k < len; k++) {

            if (i > mid) {  // 如果左半部分元素已经全部处理完毕
                arr[k] = tempArr[j - l];
                j++;
            } else if (j > r) {   // 如果右半部分元素已经全部处理完毕
                arr[k] = tempArr[i - l];
                i++;
            } else if (tempArr[i - l].compareTo(tempArr[j - l]) < 0) {  // 左半部分所指元素 < 右半部分所指元素
                arr[k] = tempArr[i - l];
                i++;
            } else {  // 左半部分所指元素 >= 右半部分所指元素
                arr[k] = tempArr[j - l];
                j++;
            }
        }
    }

}
