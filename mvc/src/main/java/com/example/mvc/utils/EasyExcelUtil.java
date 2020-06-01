package com.example.mvc.utils;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * EasyExcelUtil
 * excel工具
 *
 * @author lzp
 * @version 1.0
 * @date 2020/4/12
 */
public class EasyExcelUtil {

    /**
     * 下载reponse设置
     *
     * @param response
     * @param fileName
     * @throws Exception
     */
    public static void downloadResponseSet(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Expose-Headers", "filename");
        response.addHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), StandardCharsets.ISO_8859_1) + ExcelTypeEnum.XLSX.getValue()
                + "; filename*=utf-8''" + new String(fileName.getBytes("gb2312"), StandardCharsets.ISO_8859_1) + ExcelTypeEnum.XLSX.getValue());
        response.addHeader("Pragma", "No-cache");
        response.addHeader("filename", JdStringUtils.urlEncode(fileName + ExcelTypeEnum.XLSX.getValue()));
    }

    /**
     * 下载reponse设置
     *
     * @param response response
     * @param fileName fileName
     * @param ext      ext
     */
    public static void downloadResponseSet(HttpServletResponse response, String fileName, ExcelTypeEnum ext) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Expose-Headers", "filename");
        response.addHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), StandardCharsets.ISO_8859_1) + ext.getValue()
                + "; filename*=utf-8''" + new String(fileName.getBytes("gb2312"), StandardCharsets.ISO_8859_1) + ext.getValue());
        response.addHeader("Pragma", "No-cache");
        response.addHeader("filename", JdStringUtils.urlEncode(fileName + ext.getValue()));
    }


    /**
     * 导出样式设置
     *
     * @return
     */
    public static HorizontalCellStyleStrategy getHorizontalCellStyleStrategy() {
        // 头的策略
        WriteCellStyle headWriteCellStyle = buildHeadCellStyle();
        // 内容的策略
        WriteCellStyle contentWriteCellStyle = buildContentCellStyle();
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
    }


    /**
     * buildHeadCellStyle
     *
     * @param
     * @return
     */
    private static WriteCellStyle buildHeadCellStyle() {
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 12);
        headWriteFont.setFontName("微软雅黑");
        headWriteFont.setBold(true);
        headWriteCellStyle.setWriteFont(headWriteFont);
        return headWriteCellStyle;
    }


    /**
     * buildContentCellStyle
     *
     * @return
     */
    private static WriteCellStyle buildContentCellStyle() {
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontName("宋体");
        contentWriteFont.setFontHeightInPoints((short) 12);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        // 下边框
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
        // 左边框
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        // 上边框
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        // 右边框
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        // 水平对齐方式
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 垂直对齐方式
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return contentWriteCellStyle;
    }


}
