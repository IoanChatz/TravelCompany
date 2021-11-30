package com.travelcompany.eshop.util;

import com.travelcompany.eshop.model.Directory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class ExcelWriter {

    public void writeCustomersToFile(List<String> dataList, String filename) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(Directory.REPORTS_DIRECTORY + filename);
             XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Reports");
            int rowIndex = 0;
            for (String data : dataList) {
                XSSFRow row = sheet.createRow(rowIndex);
                XSSFCell cell0 = row.createCell(0);
                cell0.setCellValue(data);
                rowIndex++;
            }
            workbook.write(fileOutputStream);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}


