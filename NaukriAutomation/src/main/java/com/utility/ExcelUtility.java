package com.utility;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;

public class ExcelUtility {
    // Basic method to get cell value (extend as needed)
    public static String readCell(String filePath, String sheetName, int row, int col) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook wb = new XSSFWorkbook(fis)) {
            Sheet sheet = wb.getSheet(sheetName);
            if (sheet == null) return null;
            Row r = sheet.getRow(row);
            if (r == null) return null;
            Cell c = r.getCell(col);
            if (c == null) return null;
            return c.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read excel", e);
        }
    }
}