package com.lzp.demo.service;

import com.lzp.demo.entity.AccuratePutPinPackage;
import com.lzp.demo.entity.AccuratePutPinSuport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @author lzp on 2020/3/20.
 */
@Service
public class ImportPinPackageService {
    @Autowired
    private AccuratePutPinPackageService accuratePutPinPackageService;
    @Autowired
    private AccuratePutPinSuportService accuratePutPinSuportService;


    @Transactional
    public void importPinPackage(Set<AccuratePutPinPackage> pinPackages, Long accuratePutId, Set<AccuratePutPinSuport> pinSupports) {
        accuratePutPinPackageService.initPinPackage(pinPackages, accuratePutId);
        accuratePutPinSuportService.initAccuratePutPinSuport(pinSupports);
    }
}
