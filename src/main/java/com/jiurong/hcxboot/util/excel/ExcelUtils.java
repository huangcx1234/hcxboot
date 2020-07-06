package com.jiurong.hcxboot.util.excel;

import com.jiurong.hcxboot.exception.ClientException;
import com.jiurong.hcxboot.util.EmptyUtils;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.Color;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hcx
 * @date 2019/5/8
 * explain: EXCEL工具
 */
public class ExcelUtils {

    @SneakyThrows
    public static void exportExcel(ExcelData excelData, HttpServletResponse response) {
        response.setHeader("Content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(excelData.getName() + ".xlsx", "utf-8"));
        writeExcel(excelData, response.getOutputStream());
    }

    @SneakyThrows
    public static ExcelData getExcelData(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (EmptyUtils.isEmpty(fileName) || !fileName.endsWith(".xlsx")) {
            throw new ClientException("导入失败，文件格式错误，必须为xlsx格式！");
        }
        XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
        //解析SheetData
        Sheet sheet = wb.getSheetAt(0);
        SheetData sheetData = new SheetData();
        sheetData.setName(sheet.getSheetName());
        //解析表头
        List<String> titles = new ArrayList<>();
        Row head = sheet.getRow(0);
        int lastCellNum = head.getLastCellNum();
        //如果最后一个单元格为空，则往前推一格
        for (int i = 0; i <= lastCellNum; i++) {
            Cell cell = head.getCell(i);
            if (cell == null) {
                titles.add(null);
            } else {
                cell.setCellType(CellType.STRING);
                titles.add(cell.getStringCellValue());
            }
        }
        sheetData.setTitles(titles);
        //解析表内容
        List<List<String>> rows = new ArrayList<>();
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            List<String> row = new ArrayList<>();
            Row body = sheet.getRow(i);
            for (int j = 0; j < lastCellNum; j++) {
                Cell cell = body.getCell(j);
                if (cell == null) {
                    row.add(null);
                } else {
                    cell.setCellType(CellType.STRING);
                    row.add(cell.getStringCellValue());
                }
            }
            rows.add(row);
        }
        sheetData.setRows(rows);
        //解析ExcelData
        ExcelData excelData = new ExcelData();
        excelData.setName(fileName);
        List<SheetData> sheetDataList = new ArrayList<>();
        sheetDataList.add(sheetData);
        excelData.setSheetDataList(sheetDataList);
        return excelData;
    }

    @SneakyThrows
    private static void writeExcel(ExcelData excelData, OutputStream out) throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook();
        try {
            excelData.getSheetDataList().forEach(sheetData -> {
                XSSFSheet sheet = wb.createSheet(sheetData.getName());
                writeSheet(wb, sheet, sheetData);
            });
            wb.write(out);
        } finally {
            out.close();
        }
    }

    /**
     * 表显示字段
     *
     * @param wb
     * @param sheet
     * @param sheetData
     * @return
     */
    private static void writeSheet(XSSFWorkbook wb, Sheet sheet, SheetData sheetData) {
        writeTitlesToSheet(wb, sheet, sheetData.getTitles());
        writeRowsToSheet(wb, sheet, sheetData.getRows(), 1);
        autoSizeColumns(sheet, sheetData.getTitles().size() + 1);
    }

    /**
     * 设置表头
     *
     * @param wb
     * @param sheet
     * @param titles
     * @return
     */
    private static void writeTitlesToSheet(XSSFWorkbook wb, Sheet sheet, List<String> titles) {
        int colIndex = 0;

        Font titleFont = wb.createFont();
        titleFont.setFontName("宋体");
        titleFont.setBoldweight(Short.MAX_VALUE);
        titleFont.setFontHeightInPoints((short) 12);
        titleFont.setColor(IndexedColors.BLACK.index);

        XSSFCellStyle titleStyle = wb.createCellStyle();
        titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        titleStyle.setFillForegroundColor(new XSSFColor(new Color(182, 184, 192)));
        titleStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        titleStyle.setFont(titleFont);
        setBorder(titleStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));

        Row titleRow = sheet.createRow(0);
        titleRow.setHeightInPoints(15);
        for (String title : titles) {
            Cell cell = titleRow.createCell(colIndex);
            cell.setCellValue(title);
            cell.setCellStyle(titleStyle);
            cell.setCellType(CellType.STRING);
            colIndex++;
        }
    }

    /**
     * 设置内容
     *
     * @param wb
     * @param sheet
     * @param rows
     * @param rowIndex
     * @return
     */
    private static void writeRowsToSheet(XSSFWorkbook wb, Sheet sheet, List<List<String>> rows, int rowIndex) {
        int colIndex;
        Font dataFont = wb.createFont();
        dataFont.setFontName("宋体");
        dataFont.setFontHeightInPoints((short) 10);
        dataFont.setColor(IndexedColors.BLACK.index);

        XSSFCellStyle dataStyle = wb.createCellStyle();
        dataStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        dataStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        dataStyle.setFont(dataFont);
        setBorder(dataStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));

        for (List<String> rowData : rows) {
            Row dataRow = sheet.createRow(rowIndex);
            dataRow.setHeightInPoints(15);
            colIndex = 0;
            for (Object cellData : rowData) {
                Cell cell = dataRow.createCell(colIndex);
                if (cellData != null) {
                    cell.setCellValue(cellData.toString());
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(dataStyle);
                cell.setCellType(CellType.STRING);
                colIndex++;
            }
            rowIndex++;
        }
    }

    /**
     * 自动调整列宽
     *
     * @param sheet
     * @param columnNumber
     */
    private static void autoSizeColumns(Sheet sheet, int columnNumber) {
        for (int i = 0; i < columnNumber; i++) {
            int orgWidth = sheet.getColumnWidth(i);
            sheet.autoSizeColumn(i, true);
            int newWidth = sheet.getColumnWidth(i) + 100;
            if (newWidth > orgWidth) {
                sheet.setColumnWidth(i, newWidth);
            } else {
                sheet.setColumnWidth(i, orgWidth);
            }
        }
    }

    /**
     * 设置边框
     *
     * @param style
     * @param border
     * @param color
     */
    private static void setBorder(XSSFCellStyle style, BorderStyle border, XSSFColor color) {
        style.setBorderTop(border);
        style.setBorderLeft(border);
        style.setBorderRight(border);
        style.setBorderBottom(border);
        style.setBorderColor(BorderSide.TOP, color);
        style.setBorderColor(BorderSide.LEFT, color);
        style.setBorderColor(BorderSide.RIGHT, color);
        style.setBorderColor(BorderSide.BOTTOM, color);
    }
}
