package com.bluec.assignment.service.impl;

import com.bluec.assignment.po.User;
import com.bluec.assignment.service.ExcelService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {
    /**
     * 处理上传的文件
     *
     * @param in
     * @param fileName
     * @return
     * @throws Exception
     */
    @Override
    public List<User> getBankListByExcel(InputStream in, String fileName) throws Exception {
        List<User> list = new ArrayList<User>();
        //创建Excel工作薄
        Workbook work = this.getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        //注意：比实际行数小1
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            for (int j = 0; j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }

                DataFormatter dataFormatter = new DataFormatter();
                User user = new User();
                int y = row.getFirstCellNum();
                String stu_id = dataFormatter.formatCellValue(row.getCell(y));
                String name = dataFormatter.formatCellValue(row.getCell(y + 1));
                if (row.getCell(y + 2) != null) {
                    String password = dataFormatter.formatCellValue(row.getCell(y + 2));
                    user.setPassword(String.valueOf(password));
                }
                System.out.println(stu_id);
                System.out.println(name);
                user.setStu_id(String.valueOf(stu_id));
                user.setUsername(String.valueOf(name));
                list.add(user);
            }
        }
        work.close();
        System.out.println(list);
        /*for (User user : list
        ) {
            System.out.println(user.toString());
        }*/
        return list;
    }

    /**
     * 判断文件格式
     *
     * @param inStr
     * @param fileName
     * @return
     * @throws Exception
     */
    @Override
    public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook workbook = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (".xls".equals(fileType)) {
            workbook = new HSSFWorkbook(inStr);
        } else if (".xlsx".equals(fileType)) {
            workbook = new XSSFWorkbook(inStr);
        } else {
            throw new Exception("请上传excel文件！");
        }
        return workbook;
    }
}
