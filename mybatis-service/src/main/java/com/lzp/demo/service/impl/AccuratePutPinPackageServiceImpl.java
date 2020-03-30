package com.lzp.demo.service.impl;

import com.lzp.demo.dao.AccuratePutPinPackageMapper;
import com.lzp.demo.entity.AccuratePutPinPackage;
import com.lzp.demo.service.AccuratePutPinPackageService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 精准投放PIN包表(AccuratePutPinPackage)表服务实现类
 *
 * @author lzp
 * @date 2020-03-20 17:31.
 */
@Service("accuratePutPinPackageService")
public class AccuratePutPinPackageServiceImpl implements AccuratePutPinPackageService {
    @Autowired
    private AccuratePutPinPackageMapper accuratePutPinPackageMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AccuratePutPinPackage queryById(Integer id) {
        return this.accuratePutPinPackageMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<AccuratePutPinPackage> queryAllByLimit(int offset, int limit) {
        return this.accuratePutPinPackageMapper.queryAllByLimit(offset, limit);
    }


    /**
     * 新增数据
     *
     * @param accuratePutPinPackage 实例对象
     * @return 实例对象
     */
    @Override
    public AccuratePutPinPackage insert(AccuratePutPinPackage accuratePutPinPackage) {
        this.accuratePutPinPackageMapper.insert(accuratePutPinPackage);
        return accuratePutPinPackage;
    }

    /**
     * 修改数据
     *
     * @param accuratePutPinPackage 实例对象
     * @return 实例对象
     */
    @Override
    public AccuratePutPinPackage update(AccuratePutPinPackage accuratePutPinPackage) {
        this.accuratePutPinPackageMapper.update(accuratePutPinPackage);
        return this.queryById(accuratePutPinPackage.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.accuratePutPinPackageMapper.deleteById(id) > 0;
    }

    @Override
    public void insertListBatch(List<AccuratePutPinPackage> pinPackageList) {
        this.accuratePutPinPackageMapper.insertListBatch(pinPackageList);
    }

    @Override
    public List<AccuratePutPinPackage> queryListByAccuratePutId(long accuratePutId) {
        return this.accuratePutPinPackageMapper.queryListByAccuratePutId(accuratePutId);
    }

    @Override
    public Integer countByAccuratePutId(Long accuratePutId) {
        return this.accuratePutPinPackageMapper.countByAccuratePutId(accuratePutId);
    }

    @Override
    public void initPinPackage(Set<AccuratePutPinPackage> pinPackages, Long accuratePutId) {
        if (CollectionUtils.isEmpty(pinPackages)) {
            return;
        }
        List<AccuratePutPinPackage> newAccuratePutPinPackageList = new ArrayList<>(pinPackages.size());
        Iterator<AccuratePutPinPackage> iterator = pinPackages.iterator();

        List<AccuratePutPinPackage> existAccuratePutPinPackageList = queryListByAccuratePutId(accuratePutId);
        if (CollectionUtils.isEmpty(existAccuratePutPinPackageList)) {
            while (iterator.hasNext()) {
                AccuratePutPinPackage accuratePutPinPackage = iterator.next();
                if (StringUtils.isEmpty(accuratePutPinPackage.getJdPin())) {
                    continue;
                }
                newAccuratePutPinPackageList.add(accuratePutPinPackage);
            }

        } else {
            Map<String, AccuratePutPinPackage> existAccuratePutPinPackageMap = existAccuratePutPinPackageList.stream().collect(Collectors.toMap(AccuratePutPinPackage::getJdPin, e -> e));
            while (iterator.hasNext()) {
                AccuratePutPinPackage accuratePutPinPackage = iterator.next();
                AccuratePutPinPackage existAccuratePutPinPackage = existAccuratePutPinPackageMap.get(accuratePutPinPackage.getJdPin());
                if (existAccuratePutPinPackage != null) {
                    continue;
                }

                if (!StringUtils.isEmpty(accuratePutPinPackage.getJdPin())) {
                    newAccuratePutPinPackageList.add(accuratePutPinPackage);
                }
            }
        }

        if (!CollectionUtils.isEmpty(newAccuratePutPinPackageList)) {
            insertListBatch(newAccuratePutPinPackageList);
        }
    }


}