package com.example.springbootpro.utils;

import com.example.springbootpro.myinterface.ExcelColumn;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 解析excel 上传数据
 *
 * @author Administrator
 */
@Component
public class ExcelUtils {

    private static final Logger log = LoggerFactory.getLogger(ExcelUtils.class);

    /**
     * 将传入的数据导出excel表并下载
     * @param response 返回的HttpServletResponse
     * @param importlist 要导出的对象的集合
     * @param attributeNames 含有每个对象属性在excel表中对应的标题字符串的数组（请按对象中属性排序调整字符串在数组中的位置）
     */
    public  void export(HttpServletResponse response, List<?> importlist, String[] attributeNames,String fileName) throws UnsupportedEncodingException {
        //获取数据集
        List<?> datalist = importlist;

        //声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生成一个表格
        HSSFSheet sheet = workbook.createSheet();
        //设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 18);

        //获取字段名数组
        String[] tableAttributeName = attributeNames;
        //获取对象属性
        Field[] fields = ClassUtils.getClassAttribute(importlist.get(0));
        //获取对象get方法
        List<Method> methodList = ClassUtils.getMethodGet(importlist.get(0));

        //循环字段名数组，创建标题行
        Row row = sheet.createRow(0);
        for (int j = 0; j< tableAttributeName.length; j++){
            //创建列
            Cell cell = row.createCell(j);
            //设置单元类型为String
            cell.setCellType(CellType.STRING);
            cell.setCellValue(tableAttributeName[j].toString());
        }
        //创建普通行
        for (int i = 0;i<datalist.size();i++){
            //因为第一行已经用于创建标题行，故从第二行开始创建
            row = sheet.createRow(i+1);
            //如果是第一行就让其为标题行
            Object targetObj = datalist.get(i);
            for (int j = 0;j<fields.length;j++){
                //创建列
                Cell cell = row.createCell(j);
                cell.setCellType(CellType.STRING);
                //
                try {
                    Object value = methodList.get(j).invoke(targetObj, new Object[]{});
                    cell.setCellValue(value.toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        response.reset();
        // 如果文件名是英文名不需要加编码格式，如果是中文名需要添加"iso-8859-1"防止乱码
        // 设置响应头信息，响应信息以附件形式展示，附件的图标是Excel
        response.setHeader("Content-disposition",
                "attachment;filename=" + new String(fileName.getBytes("utf-8"), "iso-8859-1") + ".xls");// 设置响应表头，文件名
        response.setContentType("application/vnd.ms-excel");// 改成输出Excel文件



        try {
            response.flushBuffer();
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //第二种excel导出，不写头部的导出（直接通过注解获取）
    public  <T> void writeExcel(HttpServletResponse response, List<T> dataList, Class<T> cls,String fileName){
        Field[] fields = cls.getDeclaredFields();
        List<Field> fieldList = Arrays.stream(fields)
                .filter(field -> {
                    ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
                    if (annotation != null && annotation.col() > 0) {
                        field.setAccessible(true);
                        return true;
                    }
                    return false;
                }).sorted(Comparator.comparing(field -> {
                    int col = 0;
                    ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
                    if (annotation != null) {
                        col = annotation.col();
                    }
                    return col;
                })).collect(Collectors.toList());

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Sheet1");
        AtomicInteger ai = new AtomicInteger();
        {
            Row row = sheet.createRow(ai.getAndIncrement());
            AtomicInteger aj = new AtomicInteger();
            //写入头部
            fieldList.forEach(field -> {
                ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
                String columnName = "";
                if (annotation != null) {
                    columnName = annotation.value();
                }
                Cell cell = row.createCell(aj.getAndIncrement());

                CellStyle cellStyle = wb.createCellStyle();
                cellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                cellStyle.setAlignment(HorizontalAlignment.CENTER);

                Font font = wb.createFont();
                font.setBold(true);
                cellStyle.setFont(font);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(columnName);
            });
        }
        if (CollectionUtils.isNotEmpty(dataList)) {
            dataList.forEach(t -> {
                Row row1 = sheet.createRow(ai.getAndIncrement());
                AtomicInteger aj = new AtomicInteger();
                fieldList.forEach(field -> {
                    Class<?> type = field.getType();
                    Object value = "";
                    try {
                        value = field.get(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Cell cell = row1.createCell(aj.getAndIncrement());
                    if (value != null) {
                        if (type == Date.class) {
                            cell.setCellValue(value.toString());
                        } else {
                            cell.setCellValue(value.toString());
                        }
                        cell.setCellValue(value.toString());
                    }
                });
            });
        }
        //冻结窗格
        wb.getSheet("Sheet1").createFreezePane(0, 1, 0, 1);
        //浏览器下载excel
        buildExcelDocument(fileName+".xls",wb,response);
        //生成excel文件
//        buildExcelFile(".\\default.xlsx",wb);
    }

    /**
     * 浏览器下载excel
     * @param fileName
     * @param wb
     * @param response
     */

    private static  void  buildExcelDocument(String fileName, Workbook wb, HttpServletResponse response){
        try {
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
            response.flushBuffer();
            wb.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}