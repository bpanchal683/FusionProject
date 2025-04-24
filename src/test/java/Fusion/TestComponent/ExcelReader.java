package Fusion.TestComponent;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {

    public static List<Map<String, String>> getTestData(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        List<Map<String, String>> dataList = new ArrayList<>();
        Row headerRow = sheet.getRow(0);
        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = headerRow.getLastCellNum();

        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
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
                                : String.valueOf(cell.getNumericCellValue());
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
}
