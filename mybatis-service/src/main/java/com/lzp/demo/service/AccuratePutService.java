package com.lzp.demo.service;

import com.lzp.demo.entity.AccuratePut;

import java.util.List;

/**
 * 精准投放表(AccuratePut)表服务接口
 *
 * @author lzp
 * @date 2020-03-20 17:32.
 */
public interface AccuratePutService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AccuratePut queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<AccuratePut> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param accuratePut 实例对象
     * @return 实例对象
     */
    AccuratePut insert(AccuratePut accuratePut);

    /**
     * 修改数据
     *
     * @param accuratePut 实例对象
     * @return 实例对象
     */
    AccuratePut update(AccuratePut accuratePut);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}