package com.bluec.assignment.service;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.InputStream;
import java.util.List;

/**
 * @Author: BlueCitizens
 * @Date: 2020/1/10 7:58
 */

public interface ExcelService {
    List getBankListByExcel(InputStream in, String fileName) throws Exception;

    Workbook getWorkbook(InputStream inStr, String fileName) throws Exception;
}
