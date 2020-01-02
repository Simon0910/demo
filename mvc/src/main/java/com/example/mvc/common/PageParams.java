package com.example.mvc.common;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *
 */
public class PageParams<T> implements Serializable {

    private static final long serialVersionUID = 6297178964005032338L;

    /**
     * 当前页数
     */
    private int pageNum = 1;

    /**
     * 每页记录数
     */
    private int pageSize = 100;

    /**
     * 数据库对象
     */
    private T entity;

    /**
     * 自定义条件
     */
    private Map<String, Object> sqlWhere;

    /**
     * 自定义排序sql字段
     */
    private String orderBy;

    /**
     * 默认构造函数
     */
    public PageParams(){}

    /**
     * 带参数的构造函数
     * @param pageNum
     * @param pageSize
     */
    public PageParams(int pageNum , int pageSize){
        if (pageNum < 0) {
            throw new IllegalArgumentException("Page index must not be less than zero!");
        }

        if (pageSize < 1) {
            throw new IllegalArgumentException("Page size must not be less than one!");
        }

    	this.pageNum = pageNum;
    	this.pageSize = pageSize;
    }

    public int getStartIndex() {
        return (pageNum - 1) * pageSize;
    }

    /** 当前页数 */
    public int getPageNum() {
        return pageNum;
    }

    /** 当前页数 */
    public void setPageNum(int pageNum) {
        if (pageNum < 0) {
            throw new IllegalArgumentException("Page index must not be less than zero!");
        }
        this.pageNum = pageNum;
    }

    /** 每页记录数 */
    public int getPageSize() {
        return pageSize;
    }

    /** 每页记录数 */
    public void setPageSize(int pageSize) {
        if (pageSize < 1) {
            throw new IllegalArgumentException("Page size must not be less than one!");
        }
        this.pageSize = pageSize;
    }


    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public Map<String, Object> getSqlWhere() {
        return sqlWhere;
    }

    public void setSqlWhere(Map<String, Object> sqlWhere) {
        this.sqlWhere = sqlWhere;
    }

    public String getOrderBy() {
        // SQL过滤，防止注入
        String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
                + "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
        Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        if (sqlPattern.matcher(orderBy).find()) {
            return "";
        }
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        if (StringUtils.isBlank(orderBy)) { return; }
        StringBuilder sb = new StringBuilder();
        String[] split = orderBy.split(",");
        int length = split.length;
        for (int i = 0; i < length; i++) {
            String orders = split[i];
            String[] order = orders.split("\\$");
            if (order.length > 1) {
                sb.append(order[0]).append(" ").append(order[1]).append(",");
            } else {
                sb.append(order[0]).append(" ").append(",");
            }
        }
        this.orderBy = sb.deleteCharAt(sb.length() - 1).toString();
    }
}
