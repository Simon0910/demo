package com.lzp.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.lzp.demo.entity.AccuratePut;
import com.lzp.demo.entity.AccuratePutPinPackage;
import com.lzp.demo.entity.AccuratePutPinSuport;
import com.lzp.demo.service.AccuratePutPinPackageService;
import com.lzp.demo.service.AccuratePutService;
import com.lzp.demo.service.ImportPinPackageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 模板的读取类
 *
 * @author Jiaju Zhuang
 * @date 2020-03-20 18:57
 */
// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
public class UploadDataListener extends AnalysisEventListener<PinPackageExcel> {
    private static final Logger LOGGER = LoggerFactory.getLogger(com.lzp.demo.excel.UploadDataListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 3;
    /**
     * 保存pin包
     */
    private List<AccuratePutPinPackage> pinPackages = new ArrayList<>(BATCH_COUNT);
    /**
     *
     */
    private List<AccuratePutPinSuport> pinSupports = new ArrayList<>(BATCH_COUNT);
    /**
     *
     */
    private Long accuratePutId ;
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private AccuratePutService accuratePutService;
    private AccuratePutPinPackageService accuratePutPinPackageService;
    private ImportPinPackageService importPinPackageService;

    /**
     * PIN包服务
     *
     * @param accuratePutService
     */
    public UploadDataListener(AccuratePutService accuratePutService,
                              AccuratePutPinPackageService accuratePutPinPackageService,
                              ImportPinPackageService importPinPackageService) {
        AccuratePut accuratePut = new AccuratePut();
        accuratePut.setPinSumOfPackage(0);
        accuratePut.setAlreadyPutPinSum(0);
        accuratePut.setPutExplain("test");
        accuratePut.setPutPreviewUrl("test");
        accuratePut.setStartTime(new Date());
        accuratePut.setEndTime(new Date());
        accuratePut.setStatus(0);
        accuratePut.setCreateTime(new Date());
        accuratePut.setUpdateTime(new Date());
        accuratePut.setCreateUser("test");
        accuratePut.setUpdateUser("test");
        accuratePut.setYn(1);
        accuratePutService.insert(accuratePut);
        accuratePutId = accuratePut.getId();
        this.accuratePutService = accuratePutService;
        this.accuratePutPinPackageService = accuratePutPinPackageService;
        this.importPinPackageService = importPinPackageService;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(PinPackageExcel data, AnalysisContext context) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        String jdPin = data.getJdPin();
        if (StringUtils.isEmpty(jdPin)) {
            return;
        }
        AccuratePutPinPackage accuratePutPinPackage = new AccuratePutPinPackage();
        accuratePutPinPackage.setAccuratePutId(accuratePutId);
        accuratePutPinPackage.setJdPin(jdPin);
        accuratePutPinPackage.setStatus(0);
        pinPackages.add(accuratePutPinPackage);

        AccuratePutPinSuport accuratePutPinSuport = new AccuratePutPinSuport();
        accuratePutPinSuport.setJdPin(jdPin);
        accuratePutPinSuport.setAccuratePutIds(String.valueOf(accuratePutId));
        pinSupports.add(accuratePutPinSuport);

        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (pinPackages.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            pinPackages.clear();
            pinSupports.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        Integer count = accuratePutPinPackageService.countByAccuratePutId(accuratePutId);
        AccuratePut updateAccuratePut = new AccuratePut();
        updateAccuratePut.setId(accuratePutId);
        updateAccuratePut.setPinSumOfPackage(count);
        updateAccuratePut.setStatus(1);
        accuratePutService.update(updateAccuratePut);
        LOGGER.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        LOGGER.info("{}条数据，开始存储数据库！", pinPackages.size());
        Set<AccuratePutPinPackage> accuratePutPinPackages = new HashSet<>(pinPackages);
        Set<AccuratePutPinSuport> accuratePutPinSuports = new HashSet<>(pinSupports);
        importPinPackageService.importPinPackage(accuratePutPinPackages, accuratePutId, accuratePutPinSuports);
        LOGGER.info("存储数据库成功！");
    }
}
