package com.lzp.demo.service.impl;

import com.lzp.demo.dao.AccuratePutPinSuportMapper;
import com.lzp.demo.entity.AccuratePutPinSuport;
import com.lzp.demo.service.AccuratePutPinSuportService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * (AccuratePutPinSuport)表服务实现类
 *
 * @author lzp
 * @date 2020-03-20 17:32.
 */
@Service("accuratePutPinSuportService")
public class AccuratePutPinSuportServiceImpl implements AccuratePutPinSuportService {
    @Autowired
    private AccuratePutPinSuportMapper accuratePutPinSuportMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AccuratePutPinSuport queryById(Long id) {
        return this.accuratePutPinSuportMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<AccuratePutPinSuport> queryAllByLimit(int offset, int limit) {
        return this.accuratePutPinSuportMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param accuratePutPinSuport 实例对象
     * @return 实例对象
     */
    @Override
    public AccuratePutPinSuport insert(AccuratePutPinSuport accuratePutPinSuport) {
        this.accuratePutPinSuportMapper.insert(accuratePutPinSuport);
        return accuratePutPinSuport;
    }

    /**
     * 修改数据
     *
     * @param accuratePutPinSuport 实例对象
     * @return 实例对象
     */
    @Override
    public AccuratePutPinSuport update(AccuratePutPinSuport accuratePutPinSuport) {
        this.accuratePutPinSuportMapper.update(accuratePutPinSuport);
        return this.queryById(accuratePutPinSuport.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.accuratePutPinSuportMapper.deleteById(id) > 0;
    }

    @Override
    public List<AccuratePutPinSuport> queryListByJdPinList(List<String> jdPinList) {
        return this.accuratePutPinSuportMapper.queryListByJdPinList(jdPinList);
    }

    @Override
    public void insertListBatch(List<AccuratePutPinSuport> pinSupportList) {
        this.accuratePutPinSuportMapper.insertListBatch(pinSupportList);
    }

    @Override
    public void initAccuratePutPinSuport(Set<AccuratePutPinSuport> pinSupports) {
        if (CollectionUtils.isEmpty(pinSupports)) {
            return;
        }
        List<AccuratePutPinSuport> newAccuratePutPinSupportList = new ArrayList<>(pinSupports.size());
        Iterator<AccuratePutPinSuport> iterator = pinSupports.iterator();

        List<String> jdPinList = pinSupports.stream().map(e -> {
            return e.getJdPin();
        }).filter(s -> !StringUtils.isEmpty(s)).collect(Collectors.toList());
        List<AccuratePutPinSuport> existAccuratePutPinSupportList = queryListByJdPinList(jdPinList);
        if (CollectionUtils.isEmpty(existAccuratePutPinSupportList)) {
            while (iterator.hasNext()) {
                AccuratePutPinSuport accuratePutPinSuport = iterator.next();
                String accuratePutIds = accuratePutPinSuport.getAccuratePutIds();
                if (StringUtils.isEmpty(accuratePutIds)) {
                    continue;
                }
                newAccuratePutPinSupportList.add(accuratePutPinSuport);
            }

        } else {
            Map<String, AccuratePutPinSuport> existAccuratePutPinSupportMap = existAccuratePutPinSupportList.stream()
                    .collect(Collectors.toMap(AccuratePutPinSuport::getJdPin, e -> e));
            while (iterator.hasNext()) {
                AccuratePutPinSuport accuratePutPinSuport = iterator.next();
                String accuratePutIds = accuratePutPinSuport.getAccuratePutIds();
                if (StringUtils.isEmpty(accuratePutIds)) {
                    continue;
                }

                AccuratePutPinSuport existAccuratePutPinSupport = existAccuratePutPinSupportMap.get(accuratePutPinSuport.getJdPin());
                if (existAccuratePutPinSupport != null) {
                    String existAccuratePutIds = existAccuratePutPinSupport.getAccuratePutIds();
                    if (existAccuratePutIds.indexOf(accuratePutIds) < 0) {
                        String updatePutIds = existAccuratePutIds + "," + accuratePutIds;
                        AccuratePutPinSuport updateAccuratePutPinSupport = new AccuratePutPinSuport();
                        updateAccuratePutPinSupport.setId(existAccuratePutPinSupport.getId());
                        updateAccuratePutPinSupport.setAccuratePutIds(updatePutIds);
                        accuratePutPinSuportMapper.update(updateAccuratePutPinSupport);
                    }
                } else {
                    newAccuratePutPinSupportList.add(accuratePutPinSuport);
                }
            }
        }

        if (CollectionUtils.isNotEmpty(newAccuratePutPinSupportList)) {
            insertListBatch(newAccuratePutPinSupportList);
        }
    }


}