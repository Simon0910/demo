package com.lzp.algorithm.sort;

/**
 * @author lzp on 2020/8/16.
 */
public class Sort02InsertionSort implements Sort {
    Sort02InsertionSort() {
    }

    @Override
    public <E extends Comparable> void sortArr(E[] arr) {
        sort(arr);
    }

    public static void main(String[] args) {
        int[] dataSize = {10001, 100000};
        dataSize = new int[]{10001, 10000000};
        for (int n : dataSize) {
            // Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
            Integer[] arr = ArrayGenerator.generateOrderedArray(n);
            SortingHelper.sortTest(new Sort02InsertionSort(), arr);
        }
    }

    public static <E extends Comparable> void sort(E[] arr) {
        for (int i = 1, len = arr.length; i < len; i++) {
            // 寻找元素arr[i]合适的插入位置

            // 写法1
            // for( int j = i ; j > 0 ; j -- ) {
            //     if( arr[j].compareTo( arr[j-1] ) < 0 ) {
            //         swap( arr, j , j-1 );
            //     } else {
            //         break;
            //     }
            // }

            // 写法2
            // for (int j = i; j > 0 && arr[j].compareTo(arr[j - 1]) < 0; j--) {
            //     swap(arr, j, j - 1);
            // }

            // 写法3
            E e = arr[i];
            int j = i;
            for (; j > 0 && arr[j - 1].compareTo(e) > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }

    /**
     * 对arr[l...r]的区间使用InsertionSort排序
     *
     * @param arr
     * @param l
     * @param r
     * @param <E>
     */
    public static <E extends Comparable> void sort3(E[] arr, int l, int r) {
        int len = r + 1;
        for (int i = l + 1; i < len; i++) {
            E e = arr[i];
            int j = i;
            for (; j > l && arr[j - 1].compareTo(e) > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }

    // private static <E> void swap(E[] arr, int i, int j) {
    //     E t = arr[i];
    //     arr[i] = arr[j];
    //     arr[j] = t;
    // }

}
