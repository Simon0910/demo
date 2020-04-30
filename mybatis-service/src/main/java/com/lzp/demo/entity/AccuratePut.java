package com.lzp.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 精准投放表(AccuratePut)实体类
 *
 * @author lzp
 * @date 2020-03-20 17:32.
 */
public class AccuratePut implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -51674851240813140L;
    /**
     * 自增主键
     */
    private Long id;
    /**
     * 投放说明
     */
    private String putExplain;
    /**
     * 预览地址
     */
    private String putPreviewUrl;
    /**
     * 包的pin数量，大于0
     */
    private Integer pinSumOfPackage;
    /**
     * 已经投放数量, 默认0
     */
    private Integer alreadyPutPinSum;
    /**
     * 完成情况:0未开始,1进行中,2已完成,3已结束
     */
    private Integer status;
    /**
     * 投放开始时间
     */
    private Date startTime;
    /**
     * 投放结束时间
     */
    private Date endTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 修改人
     */
    private String updateUser;
    /**
     * 逻辑删除 0:删除 1:正常， 默认1
     */
    private Integer yn;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPutExplain() {
        return putExplain;
    }

    public void setPutExplain(String putExplain) {
        this.putExplain = putExplain;
    }

    public String getPutPreviewUrl() {
        return putPreviewUrl;
    }

    public void setPutPreviewUrl(String putPreviewUrl) {
        this.putPreviewUrl = putPreviewUrl;
    }

    public Integer getPinSumOfPackage() {
        return pinSumOfPackage;
    }

    public void setPinSumOfPackage(Integer pinSumOfPackage) {
        this.pinSumOfPackage = pinSumOfPackage;
    }

    public Integer getAlreadyPutPinSum() {
        return alreadyPutPinSum;
    }

    public void setAlreadyPutPinSum(Integer alreadyPutPinSum) {
        this.alreadyPutPinSum = alreadyPutPinSum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }

}