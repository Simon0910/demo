package com.lzp.demo.service.impl;

import com.lzp.demo.dao.AccuratePutMapper;
import com.lzp.demo.entity.AccuratePut;
import com.lzp.demo.service.AccuratePutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 精准投放表(AccuratePut)表服务实现类
 *
 * @author lzp
 * @date 2020-03-20 17:32.
 */
@Service("accuratePutService")
public class AccuratePutServiceImpl implements AccuratePutService {
    @Autowired
    private AccuratePutMapper accuratePutMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AccuratePut queryById(Long id) {
        return this.accuratePutMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<AccuratePut> queryAllByLimit(int offset, int limit) {
        return this.accuratePutMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param accuratePut 实例对象
     * @return 实例对象
     */
    @Override
    public AccuratePut insert(AccuratePut accuratePut) {
        this.accuratePutMapper.insert(accuratePut);
        return accuratePut;
    }

    /**
     * 修改数据
     *
     * @param accuratePut 实例对象
     * @return 实例对象
     */
    @Override
    public AccuratePut update(AccuratePut accuratePut) {
        this.accuratePutMapper.update(accuratePut);
        return this.queryById(accuratePut.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.accuratePutMapper.deleteById(id) > 0;
    }
}