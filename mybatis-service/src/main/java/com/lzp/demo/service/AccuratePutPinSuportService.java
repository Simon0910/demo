package com.lzp.demo.service;

import com.lzp.demo.entity.AccuratePutPinSuport;

import java.util.List;
import java.util.Set;

/**
 * (AccuratePutPinSuport)表服务接口
 *
 * @author lzp
 * @date 2020-03-20 17:32.
 */
public interface AccuratePutPinSuportService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AccuratePutPinSuport queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<AccuratePutPinSuport> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param accuratePutPinSuport 实例对象
     * @return 实例对象
     */
    AccuratePutPinSuport insert(AccuratePutPinSuport accuratePutPinSuport);

    /**
     * 修改数据
     *
     * @param accuratePutPinSuport 实例对象
     * @return 实例对象
     */
    AccuratePutPinSuport update(AccuratePutPinSuport accuratePutPinSuport);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    List<AccuratePutPinSuport> queryListByJdPinList(List<String> jdPinList);

    void insertListBatch(List<AccuratePutPinSuport> pinSupportList);

    void initAccuratePutPinSuport(Set<AccuratePutPinSuport> pinSupportList);
}