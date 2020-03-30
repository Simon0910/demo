package com.lzp.demo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 精准投放PIN包表(AccuratePutPinPackage)实体类
 *
 * @author lzp
 * @date 2020-03-20 17:31.
 */
public class AccuratePutPinPackage implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 202541890532552560L;
    /**
     * 自增主键
     */
    private Integer id;
    /**
     * 精准投放id
     */
    private Long accuratePutId;
    /**
     * 京东PIN
     */
    private String jdPin;
    /**
     * 投放时间
     */
    private Date putTime;
    /**
     * 投放状态:0未投放,1已投放,2投放成功,3投放失败， 默认0
     */
    private Integer status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getAccuratePutId() {
        return accuratePutId;
    }

    public void setAccuratePutId(Long accuratePutId) {
        this.accuratePutId = accuratePutId;
    }

    public String getJdPin() {
        return jdPin;
    }

    public void setJdPin(String jdPin) {
        this.jdPin = jdPin;
    }

    public Date getPutTime() {
        return putTime;
    }

    public void setPutTime(Date putTime) {
        this.putTime = putTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccuratePutPinPackage that = (AccuratePutPinPackage) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(accuratePutId, that.accuratePutId) &&
                Objects.equals(jdPin, that.jdPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accuratePutId, jdPin);
    }
}