package com.lzp.demo.controller;

import com.lzp.demo.entity.AccuratePut;
import com.lzp.demo.service.AccuratePutService;
import com.lzp.demo.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * 精准投放表(AccuratePut)表控制层
 *
 * @author lzp
 * @date 2020-03-20 17:32.
 */
@RestController
@RequestMapping("accuratePut")
public class AccuratePutController1 {
    /**
     * 服务对象
     */
    @Autowired
    private AccuratePutService accuratePutService;

    @Autowired
    private ImportService importService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public AccuratePut selectOne(Long id) {
        return this.accuratePutService.queryById(id);
    }

    @PostMapping("insertAccuratePut")
    public String insertAccuratePut(HttpServletRequest request, AccuratePut accuratePut) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("filename");
        if (file.isEmpty()) {
            return "文件不能为空";
        }

        List<List<Object>> list = null;
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            list = importService.getBankListByExcel(inputStream, file.getOriginalFilename());
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    inputStream = null;
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            List<Object> lo = list.get(i);
            //TODO 随意发挥
            System.out.println(lo);

        }
        return "上传成功";
    }


    @PostMapping("uploadAccuratePut")
    public String uploadAccuratePut(HttpServletRequest request, AccuratePut accuratePut) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("filename");

        return "success";
    }

}