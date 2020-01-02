package com.example.mvc.common;

import java.util.List;

/**
 * @description:
 * @author: lzp
 * @date: 2020/1/2
 */

public class PageInfo<T> {

    private List<T> list;

    public static int getTotalPage(int totalCount, int pageSize) {
        if (totalCount % pageSize == 0) {
            // 刚好整除
            return totalCount / pageSize;
        } else {
            // 不能整除则总页数为：商 + 1
            return totalCount / pageSize + 1;
        }
    }
}
