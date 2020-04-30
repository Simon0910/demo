package com.lzp.demo.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * (AccuratePutPinSuport)实体类
 *
 * @author lzp
 * @date 2020-03-20 17:32.
 */
public class AccuratePutPinSuport implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 599298904485027486L;
    /**
     * id
     */
    private Long id;
    /**
     * 京东PIN
     */
    private String jdPin;
    /**
     * 精准投放id集合，以逗号分隔，无投放为0
     */
    private String accuratePutIds;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJdPin() {
        return jdPin;
    }

    public void setJdPin(String jdPin) {
        this.jdPin = jdPin;
    }

    public String getAccuratePutIds() {
        return accuratePutIds;
    }

    public void setAccuratePutIds(String accuratePutIds) {
        this.accuratePutIds = accuratePutIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccuratePutPinSuport that = (AccuratePutPinSuport) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(jdPin, that.jdPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jdPin);
    }
}