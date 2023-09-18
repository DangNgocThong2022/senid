package main.java.common.helpers;

import java.awt.Color;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;


public class ExcelHelper {
    private static FileInputStream fis;
    private static FileOutputStream fileOut;
    private static Workbook wb;
    private static Sheet sh;
    private static Cell cell;
    private static Row row;
    private static CellStyle cellstyle;
    private static Color mycolor;
    private static String excelFilePath;
    private static Map<String, Integer> columns = new HashMap<>();

    public static void setExcelFile(String ExcelPath, String SheetName) throws Exception {
        try {
            File f = new File(ExcelPath);

            if (!f.exists()) {
                f.createNewFile();
                System.out.println("File doesn't exist, so created!");
            }

            fis = new FileInputStream(ExcelPath);
            wb = WorkbookFactory.create(fis);
            sh = wb.getSheet(SheetName);
            //sh = wb.getSheetAt(0); //0 - index of 1st sheet
            if (sh == null) {
                sh = wb.createSheet(SheetName);
            }

            excelFilePath = ExcelPath;

            //adding all the column header names to the map 'columns'
            sh.getRow(0).forEach(cell -> {
                columns.put(cell.getStringCellValue(), cell.getColumnIndex());
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getCellData(int rownum, int colnum) throws Exception {
        try {
            cell = sh.getRow(rownum).getCell(colnum);
            String CellData = null;
            switch (cell.getCellType()) {
                case STRING:
                    CellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        CellData = String.valueOf(cell.getDateCellValue());
                    } else {
                        CellData = String.valueOf((long) cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    CellData = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    CellData = "";
                    break;
            }
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }

    //Gọi ra hàm này nè
    public static String getCellData(String columnName, int rownum) throws Exception {
        return getCellData(rownum, columns.get(columnName));
    }

    public static void setCellData(String text, int rownum, int colnum) throws Exception {
        try {
            row = sh.getRow(rownum);
            if (row == null) {
                row = sh.createRow(rownum);
            }
            cell = row.getCell(colnum);

            if (cell == null) {
                cell = row.createCell(colnum);
            }
            cell.setCellValue(text);

            fileOut = new FileOutputStream(excelFilePath);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            throw (e);
        }
    }

    public static int findRow(String filePath, String sheetName, String cellContent) throws IOException {

        fis = new FileInputStream(filePath);
        wb = WorkbookFactory.create(fis);
        sh = wb.getSheet(sheetName);
        for (Row row : sh) {
            for (Cell cell : row) {
                if (cell.getCellType() == CellType.STRING) {
                    if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
                        return row.getRowNum();
                    }
                }
            }
        }
        return 0;
    }

    public static String getLocatorValueFromExcel(String filePath, String sheetName, String objectName) throws Exception {
        String locatorValue = getCellData(findRow(filePath, sheetName, objectName), 2);
        return locatorValue;

    }

    public static String getLocatorTypeFromExcel(String filePath, String sheetName, String objectName) throws Exception {
        String locatorType = getCellData(findRow(filePath, sheetName, objectName), 1);
        return locatorType;

    }
}
