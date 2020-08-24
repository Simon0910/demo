package com.lzp.algorithm.sort;

/**
 * @author lzp on 2020/8/20.
 */
public interface Sort {
    /**
     * sort
     *
     * @param arr
     * @param <E>
     * @return
     */
    <E extends Comparable> void sortArr(E[] arr);
}
