package com.lzp.demo.dao;

import com.lzp.demo.entity.AccuratePutPinPackage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 精准投放PIN包表(AccuratePutPinPackage)表数据库访问层
 *
 * @author lzp
 * @date 2020-03-20 17:31.
 */
public interface AccuratePutPinPackageMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AccuratePutPinPackage queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<AccuratePutPinPackage> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param accuratePutPinPackage 实例对象
     * @return 对象列表
     */
    List<AccuratePutPinPackage> queryAll(AccuratePutPinPackage accuratePutPinPackage);

    /**
     * 新增数据
     *
     * @param accuratePutPinPackage 实例对象
     * @return 影响行数
     */
    int insert(AccuratePutPinPackage accuratePutPinPackage);

    /**
     * 修改数据
     *
     * @param accuratePutPinPackage 实例对象
     * @return 影响行数
     */
    int update(AccuratePutPinPackage accuratePutPinPackage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<AccuratePutPinPackage> queryListByAccuratePutId(long accuratePutId);

    void insertListBatch(List<AccuratePutPinPackage> pinPackageList);

    Integer countByAccuratePutId(Long accuratePutId);
}