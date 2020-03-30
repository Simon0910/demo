package com.lzp.demo.dao;

import com.lzp.demo.entity.AccuratePutPinSuport;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (AccuratePutPinSuport)表数据库访问层
 *
 * @author lzp
 * @date 2020-03-20 17:32.
 */
public interface AccuratePutPinSuportMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AccuratePutPinSuport queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<AccuratePutPinSuport> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param accuratePutPinSuport 实例对象
     * @return 对象列表
     */
    List<AccuratePutPinSuport> queryAll(AccuratePutPinSuport accuratePutPinSuport);

    /**
     * 新增数据
     *
     * @param accuratePutPinSuport 实例对象
     * @return 影响行数
     */
    int insert(AccuratePutPinSuport accuratePutPinSuport);

    /**
     * 修改数据
     *
     * @param accuratePutPinSuport 实例对象
     * @return 影响行数
     */
    int update(AccuratePutPinSuport accuratePutPinSuport);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<AccuratePutPinSuport> queryListByJdPinList(List<String> jdPinList);

    void insertListBatch(List<AccuratePutPinSuport> pinSupportList);
}