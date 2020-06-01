package com.lzp.easyexcel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Font;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.metadata.TableStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * WriteExcelDemo
 *
 * @author lzp
 * @version 1.0
 * @date 2020/5/29
 */
public class WriteExcelDemo {

    public static void main(String[] args) throws Exception {
        writeExcel2();
    }

    public static void writeExcel2() throws Exception {
        // 文件输出位置
        OutputStream out = new FileOutputStream("D:\\test.xlsx");

        ExcelWriter writer = EasyExcelFactory.getWriter(out);

        // 动态添加表头，适用一些表头动态变化的场景
        Sheet sheet1 = new Sheet(1, 0);

        sheet1.setSheetName("第一个sheet");
        sheet1.setAutoWidth(true);

        // 创建一个表格，用于 Sheet 中使用
        Table table1 = new Table(1);
        // 样式
        table1.setTableStyle(createTableStyle());
        // 无注解的模式，动态添加表头
        table1.setHead(createTestListStringHead());
        // 写数据
        writer.write1(createDynamicModelList(), sheet1, table1);

        // 将上下文中的最终 outputStream 写入到指定文件中
        writer.finish();

        // 关闭流
        out.close();
    }

    private static TableStyle createTableStyle() {
        TableStyle tableStyle = new TableStyle();
        // 设置表头样式
        Font headFont = new Font();
        // 字体是否加粗
        headFont.setBold(true);
        // 字体大小
        headFont.setFontHeightInPoints((short) 12);
        // 字体
        headFont.setFontName("楷体");
        tableStyle.setTableHeadFont(headFont);
        // 背景色
        tableStyle.setTableHeadBackGroundColor(IndexedColors.BLUE);


        // 设置表格主体样式
        Font contentFont = new Font();
        contentFont.setBold(true);
        contentFont.setFontHeightInPoints((short) 12);
        contentFont.setFontName("黑体");
        tableStyle.setTableContentFont(contentFont);
        tableStyle.setTableContentBackGroundColor(IndexedColors.GREEN);
        return tableStyle;
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

    public static List<List<String>> createTestListStringHead() {
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
