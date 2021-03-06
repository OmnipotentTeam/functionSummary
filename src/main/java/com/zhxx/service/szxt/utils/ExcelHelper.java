package com.zhxx.service.szxt.utils;


import com.alibaba.druid.util.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName: ExcelHelper
 * @Auther: 王新春
 * @Date: 2018/12/8 10:27
 * @Description: excel帮助类
 */
public class ExcelHelper {
    /**
     * 将excel解析为指定的对象集合 <br>
     * 例如： 要导入的excel格式为 <br>
     * 第1行：  | id   | username | password　|(与对象的字段对应)<br>
     * 第2行：  |1     | 小周　　 | 123456    |<br>
     * 第3行：  |2     | 老王　　 | 123456    |<br>
     * 调用： analysisExcel(file,User.class);
     *
     * @param file-----要解析的excel文件
     * @param c--------指定的对象类型
     * @throws IOException
     * @return---------对象集合
     */
    public static <T> List<T> analysisExcel(File file, Class<T> c) {
        List<T> list = new ArrayList<T>();
        InputStream inputStream = null;
        String fileName = null;
        Workbook wb = null;
        try {
            inputStream = new FileInputStream(file);
            fileName = file.getName();
            if (fileName.endsWith(".xls") || fileName.endsWith(".xlsx")) {
                //如果是2003版本
                if (fileName.endsWith(".xls")) {
                    //1.先解析文件
                    POIFSFileSystem fs = new POIFSFileSystem(inputStream);
                    wb = new HSSFWorkbook(fs);
                } else if (fileName.endsWith(".xlsx")) {//如果是2007以上版本
                    wb = new XSSFWorkbook(inputStream);
                } else {
                    return null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = null;
        Row row1 = null;
        try {
            sheet = wb.getSheetAt(0);
            if(sheet == null){
                return null;
            }
             row1 = sheet.getRow(0);
            if(row1 == null){
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        //获取第一行（标题行）

        //总列数
        int colNum = row1.getPhysicalNumberOfCells();
        //总行数
        int rowNum = sheet.getLastRowNum();
        //将标题行一一放入数组
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < colNum; i++) {
            if (StringUtils.isEmpty(row1.getCell(i).getStringCellValue()) || "null".equals(row1.getCell(i).getStringCellValue())) {
                continue;
            }
            titles.add(row1.getCell(i).getStringCellValue());
        }
        //获取指定对象所有的字段
        Field fields[] = c.getDeclaredFields();
        Map<String, Field> fieldMap = new HashMap<String, Field>();
        for (int i = 0; i < fields.length; i++) {
            fieldMap.put(fields[i].getName(), fields[i]);
        }
        //使用反射机制，将值存入对应对象中
        try {
            row:
            for (int i = 1; i < rowNum + 1; i++) {
                T t = c.newInstance();
                String title = null;
                item:
                for (int j = 0; j < titles.size(); j++) {
                    //当excel中有这个字段
                    if (fieldMap.containsKey(titles.get(j))) {
                        String fieldName = titles.get(j);
                        String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                        //调用该字段对应的set方法
                        Class cc = fieldMap.get(titles.get(j)).getType();
                        Method method = c.getMethod(methodName, cc);
                        String value = String.valueOf(sheet.getRow(i).getCell(j));
                        Object o = parseValue(value, cc);
                        if (o == null) {
                            break row;
                        }
                        title = o.toString();
                        method.invoke(t, o);
                    }
                }
                if (title != null) {
                    list.add(t);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    /**
     * 将字符串转化为指定类型的对象
     *
     * @param s----要转化的字符串
     * @param c----目标对象类型
     * @return
     */
    private static Object parseValue(String s, Class c) {
        Object obj = null;
        String className = c.getName();
        //excel中的数字解析之后可能末尾会有.0，需要去除
        if (s.endsWith(".0")) s = s.substring(0, s.length() - 2);
        if (className.equals("java.lang.Integer")) { //Integer
            if ("null".equals(s) && StringUtils.isEmpty(s)) {
                obj = null;
            } else {
                obj =Integer.valueOf(s);
            }
        } else if (className.equals("int")) { //int
            if ("null".equals(s) || StringUtils.isEmpty(s)) {
                obj = null;
            } else {
                obj = Integer.parseInt(s);
            }
        } else if (className.equals("java.lang.String")) { //String
            obj = s;
        } else if (className.equals("java.lang.Double")) { //Double
            if ("null".equals(s) || StringUtils.isEmpty(s)) {
                obj = null;
            } else {
                obj = new Double(s);
            }
        } else if (className.equals("double")) { //double
            if ("null".equals(s) || StringUtils.isEmpty(s)) {
                obj = null;
            } else {
                obj = new Double(s);
            }

        } else if (className.equals("java.lang.Float")) { //Float
            if ("null".equals(s) || StringUtils.isEmpty(s)) {
                obj = null;
            } else {
                obj = new Float(s);
            }

        } else if (className.equals("float")) { //float
            if ("null".equals(s) || StringUtils.isEmpty(s)) {
                obj = null;
            } else {
                obj = new Float(s);
            }
        } else if (className.equals("java.util.Date")) { //Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                obj = sdf.parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (className.equals("long")) { //long
            if ("null".equals(s) || StringUtils.isEmpty(s)) {
                obj = null;
            } else {
                obj = Long.parseLong(s);
            }

        } else if (className.equals("java.util.Long")) { //Long
            if ("null".equals(s) || StringUtils.isEmpty(s)) {
                obj = null;
            } else {
                obj = Long.valueOf(s);
            }

        }
        return obj;
    }

}
