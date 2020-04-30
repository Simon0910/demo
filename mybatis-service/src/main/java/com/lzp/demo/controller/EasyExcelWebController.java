package com.lzp.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.lzp.demo.excel.PinPackageExcel;
import com.lzp.demo.excel.UploadDataListener;
import com.lzp.demo.service.AccuratePutPinPackageService;
import com.lzp.demo.service.AccuratePutService;
import com.lzp.demo.service.ImportPinPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * esay excle test
 *
 * @author lzp on 2020/3/20.
 */
@RestController
@RequestMapping("easy-excel")
public class EasyExcelWebController {

    /**
     * 服务对象
     */
    @Autowired
    private AccuratePutService accuratePutService;
    @Autowired
    private AccuratePutPinPackageService accuratePutPinPackageService;
    @Autowired
    private ImportPinPackageService importPinPackageService;

    /**
     * 文件上传
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link AccuratePutPinPackageService}
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link UploadDataListener}
     * <p>
     * 3. 直接读即可
     */
    @PostMapping("upload")
    @ResponseBody
    public String upload(HttpServletRequest request) throws IOException {

        // 已初始化 禁止上传.

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("filename");
        EasyExcel.read(file.getInputStream(),
                PinPackageExcel.class,
                new UploadDataListener(accuratePutService, accuratePutPinPackageService, importPinPackageService))
                .sheet().doRead();
        return "success";
    }

}
