package Fusion.TestComponent;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {

    //Code for Single set of data
    public static List<Map<String, String>> getTestData(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        List<Map<String, String>> dataList = new ArrayList<>();
        Row headerRow = sheet.getRow(0);
        int colCount = headerRow.getLastCellNum();

        // Only read the first data row (row index 1)
        Row row = sheet.getRow(1);
        if (row != null) {
            Map<String, String> dataMap = new HashMap<>();
            for (int j = 0; j < colCount; j++) {
                String key = headerRow.getCell(j).getStringCellValue();
                String value = "";
                Cell cell = row.getCell(j);
                if (cell != null) {
                    value = switch (cell.getCellType()) {
                        case STRING -> cell.getStringCellValue();
                        case NUMERIC -> DateUtil.isCellDateFormatted(cell)
                                ? cell.getDateCellValue().toString()
                                : BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString();
                        case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
                        default -> "";
                    };
                }
                dataMap.put(key, value);
            }
            dataList.add(dataMap);
        }

        workbook.close();
        fis.close();
        return dataList;
    }


    //code for multiple set of data
    public static List<Map<String, String>> getMultiTestData(String filePath, String sheetName) throws IOException {
        List<Map<String, String>> dataList = new ArrayList<>();

        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        Row headerRow = sheet.getRow(0);
        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = headerRow.getLastCellNum();

        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Map<String, String> dataMap = new HashMap<>();
            for (int j = 0; j < colCount; j++) {
                String key = headerRow.getCell(j).getStringCellValue().trim();
                String value = "";

                Cell cell = row.getCell(j);
                if (cell != null) {
                    switch (cell.getCellType()) {
                        case STRING -> value = cell.getStringCellValue().trim();
                        case NUMERIC -> value = String.valueOf(cell.getNumericCellValue());
                        case BOOLEAN -> value = String.valueOf(cell.getBooleanCellValue());
                        default -> value = cell.toString();
                    }
                }

                dataMap.put(key, value);
            }

            dataMap.put("rowIndex", String.valueOf(i));
            dataList.add(dataMap);
        }

        workbook.close();
        fis.close();
        return dataList;
    }
}
