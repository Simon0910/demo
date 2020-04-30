package com.lzp.demo.service;

import com.lzp.demo.entity.AccuratePutPinPackage;

import java.util.List;
import java.util.Set;

/**
 * 精准投放PIN包表(AccuratePutPinPackage)表服务接口
 *
 * @author lzp
 * @date 2020-03-20 17:31.
 */
public interface AccuratePutPinPackageService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AccuratePutPinPackage queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<AccuratePutPinPackage> queryAllByLimit(int offset, int limit);

    List<AccuratePutPinPackage> queryListByAccuratePutId(long accuratePutId);

    /**
     * 新增数据
     *
     * @param accuratePutPinPackage 实例对象
     * @return 实例对象
     */
    AccuratePutPinPackage insert(AccuratePutPinPackage accuratePutPinPackage);

    /**
     * 修改数据
     *
     * @param accuratePutPinPackage 实例对象
     * @return 实例对象
     */
    AccuratePutPinPackage update(AccuratePutPinPackage accuratePutPinPackage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);


    void insertListBatch(List<AccuratePutPinPackage> pinPackageList);

    void initPinPackage(Set<AccuratePutPinPackage> pinPackageList, Long accuratePutId);

    Integer countByAccuratePutId(Long accuratePutId);
}