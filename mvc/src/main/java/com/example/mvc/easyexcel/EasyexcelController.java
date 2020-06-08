package com.example.mvc.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.mvc.utils.EasyExcelUtil.getHorizontalCellStyleStrategy;

/**
 * EasyexcelController
 * easyexcel 测试
 *
 * @author lzp
 * @version 1.0
 * @date 2020/6/1
 */
@RestController
@RequestMapping("/easyexcel")
public class EasyexcelController {

    @GetMapping("/downloadExcel")
    public Object downloadExcel(HttpServletResponse response) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        EasyExcel.write(outputStream)
                .head(createDynamicHeadList())
                .autoCloseStream(Boolean.FALSE)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .registerWriteHandler(getHorizontalCellStyleStrategy())
                .sheet("sheetname-test")
                .doWrite(createDynamicModelList());

        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        return "ss";
    }


    private static List<List<Object>> createDynamicModelList() {
        // 所有行
        List<List<Object>> rows = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            // 一行数据
            List<Object> row = new ArrayList<>();
            row.add("字符串" + i);
            row.add(Long.valueOf(1856464L + i));
            row.add(Integer.valueOf(2233 + i));
            row.add("犬小哈");
            row.add("微信公众号: 小哈学Java");
            rows.add(row);
        }
        return rows;
    }

    public static List<List<String>> createDynamicHeadList() {
        // 模型上没有注解，表头数据动态传入
        List<List<String>> head = new ArrayList<List<String>>();
        List<String> headCoulumn1 = new ArrayList<String>();
        List<String> headCoulumn2 = new ArrayList<String>();
        List<String> headCoulumn3 = new ArrayList<String>();
        List<String> headCoulumn4 = new ArrayList<String>();
        List<String> headCoulumn5 = new ArrayList<String>();

        headCoulumn1.add("目的省份");
        headCoulumn1.add("目的省份");

        headCoulumn2.add("目的城市");
        headCoulumn2.add("目的城市");

        headCoulumn3.add("发货仓优先级");
        headCoulumn3.add("发货仓1");

        headCoulumn4.add("发货仓优先级");
        headCoulumn4.add("发货仓2");

        headCoulumn5.add("发货仓优先级");
        headCoulumn5.add("发货仓3");

        head.add(headCoulumn1);
        head.add(headCoulumn2);
        head.add(headCoulumn3);
        head.add(headCoulumn4);
        head.add(headCoulumn5);
        return head;
    }
}
