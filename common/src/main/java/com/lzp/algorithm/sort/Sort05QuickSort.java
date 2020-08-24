package com.lzp.algorithm.sort;

import java.util.Arrays;

/**
 * @author lzp on 2020/8/23.
 */
public class Sort05QuickSort implements Sort {

    @Override
    public <E extends Comparable> void sortArr(E[] arr) {
        // sort(arr);
        // sort2(arr);
        // sort3(arr);
        sort4(arr);
    }

    // 测试 QuickSort
    public static void main(String[] args) {
        // Quick Sort也是一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
        int n = 1000000;
        // Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        // Integer[] arr = ArrayGenerator.generateOrderedArray(n); // sort2
        // Integer[] arr = ArrayGenerator.generateRandomArray(n, 1000); //sort2
        Integer[] arr = ArrayGenerator.generateRandomArray(n, 10); //sort3

        Integer[] arr2 = Arrays.copyOfRange(arr, 0, n);
        SortingHelper.sortTest(new Sort04MergeSort(), arr);
        SortingHelper.sortTest(new Sort05QuickSort(), arr2);
        return;
    }

    public static void sort4(Comparable[] arr) {
        int n = arr.length;
        recursionSort4(arr, 0, n - 1);
    }

    // 递归使用快速排序,对arr[l...r]的范围进行排序
    private static void recursionSort4(Comparable[] arr, int l, int r) {

        // 对于小规模数组, 使用插入排序
        if (r - l <= 15) {
            Sort02InsertionSort.sort3(arr, l, r);
            return;
        }

        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);

        Comparable v = arr[l];

        int lt = l;     // arr[l+1...lt] < v
        int gt = r + 1; // arr[gt...r] > v
        int i = l + 1;    // arr[lt+1...i) == v
        while (i < gt) {
            if (arr[i].compareTo(v) < 0) {
                swap(arr, i, lt + 1);
                i++;
                lt++;
            } else if (arr[i].compareTo(v) > 0) {
                swap(arr, i, gt - 1);
                gt--;
            } else { // arr[i] == v
                i++;
            }
        }

        swap(arr, l, lt);

        recursionSort4(arr, l, lt - 1);
        recursionSort4(arr, gt, r);
    }

    public static void sort3(Comparable[] arr) {
        int n = arr.length;
        recursionSort3(arr, 0, n - 1);
    }

    // 递归使用快速排序,对arr[l...r]的范围进行排序
    private static void recursionSort3(Comparable[] arr, int l, int r) {
        if (r - l <= 15) {
            Sort02InsertionSort.sort3(arr, l, r);
            return;
        }
        int p = partition3(arr, l, r);
        recursionSort3(arr, l, p - 1);
        recursionSort3(arr, p + 1, r);
    }

    // 双路快速排序的partition
    // 返回p, 使得arr[l...p-1] <= arr[p] ; arr[p+1...r] >= arr[p]
    // 双路快排处理的元素正好等于arr[p]的时候要注意，详见下面的注释：）
    private static int partition3(Comparable[] arr, int l, int r) {

        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);

        Comparable v = arr[l];

        // arr[l+1...i) <= v; arr(j...r] >= v
        int i = l + 1, j = r;
        while (true) {
            // 注意这里的边界, arr[i].compareTo(v) < 0, 不能是arr[i].compareTo(v) <= 0
            // 思考一下为什么?
            while (i <= j && arr[i].compareTo(v) < 0) {
                i++;
            }

            // 注意这里的边界, arr[j].compareTo(v) > 0, 不能是arr[j].compareTo(v) >= 0
            // 思考一下为什么?
            while (j > i && arr[j].compareTo(v) > 0) {
                j--;
            }

            // 对于上面的两个边界的设定, 有的同学在课程的问答区有很好的回答:)
            // 大家可以参考: http://coding.imooc.com/learn/questiondetail/4920.html

            if (i > j) {
                break;
            }

            swap(arr, i, j);
            i++;
            j--;
        }

        swap(arr, l, j);

        return j;
    }

    public static void sort2(Comparable[] arr) {
        int n = arr.length;
        recursionSort2(arr, 0, n - 1);
    }

    // 递归使用快速排序,对arr[l...r]的范围进行排序
    private static void recursionSort2(Comparable[] arr, int l, int r) {
        if (r - l <= 15) {
            Sort02InsertionSort.sort3(arr, l, r);
            return;
        }
        int p = partition2(arr, l, r);
        recursionSort2(arr, l, p - 1);
        recursionSort2(arr, p + 1, r);
    }

    // 对arr[l...r]部分进行partition操作
    // 返回p, 使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p]
    private static int partition2(Comparable[] arr, int l, int r) {

        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);

        Comparable v = arr[l];

        int j = l; // arr[l+1...j] < v ; arr[j+1...i) > v
        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(v) < 0) {
                j++;
                swap(arr, j, i);
            }
        }

        swap(arr, l, j);

        return j;
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        recursionSort(arr, 0, n - 1);
    }

    // 递归使用快速排序,对arr[l...r]的范围进行排序
    private static void recursionSort(Comparable[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = partition(arr, l, r);
        recursionSort(arr, l, p - 1);
        recursionSort(arr, p + 1, r);
    }

    // 对arr[l...r]部分进行partition操作
    // 返回p, 使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p]
    private static int partition(Comparable[] arr, int l, int r) {

        Comparable v = arr[l];

        int j = l; // arr[l+1...j] < v ; arr[j+1...i) > v
        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(v) < 0) {
                j++;
                swap(arr, j, i);
            }
        }

        swap(arr, l, j);

        return j;
    }

    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
