package com.yxlg.base.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by tom on 2017/2/17.
 */
public class PoiUtils {

    // excel 文件后缀名
    private static final String[] EXCEL_EXT = {"xlsx", "xls"};

    /**
     * 默认读取Excel数据
     * @param stream
     * @param fileExt
     * @param withHead 是否保存title数据
     * @return
     * @throws IOException
     */
    public static Map<Integer, List<Object>> readeExcel(InputStream stream, String fileExt, boolean withHead) throws IOException {
        if (!isSuportFileExt(fileExt, "excel")) {
            throw new IOException("文件格式错误");
        }
        Sheet sheet = workbook(stream, fileExt).getSheetAt(0);
        Row first = sheet.getRow(0);
        if(withHead){
            return readExcelSheetData(sheet, 0, sheet.getFirstRowNum(), sheet.getLastRowNum(), 0, first.getLastCellNum());
        }else {
            return readExcelSheetData(sheet, 0, 1, sheet.getLastRowNum(), 0, first.getLastCellNum());
        }
    }

    /**
     * 判定是否支持的文件格式
     *
     * @param fileExt 文件后缀名
     * @param type    文件类型：excel,word
     * @return
     */
    private static boolean isSuportFileExt(String fileExt, String type) {
        List<String> fileExts = new LinkedList<>();
        if ("excel".equals(type)) {
            fileExts = Arrays.asList(EXCEL_EXT);
        }
        return fileExts.contains(fileExt);
    }

    /**
     * 判断从文件中解析出来数据的格式
     *N
     * @param cell
     * @return
     */
    private static String cellValue(Cell cell) {
        String value = "";
        if (cell != null) {
            // 简单的查检列类型
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:     // 字符串
                    value = cell.getRichStringCellValue().getString();
                    break;
                case Cell.CELL_TYPE_NUMERIC:    // 数字
                    DecimalFormat df = new DecimalFormat("#.00");
                    value = df.format(cell.getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA:    //公式
                    value = String.valueOf(cell.getCellFormula());
                    break;
                case Cell.CELL_TYPE_BOOLEAN:    // boolean型值
                    value = String.valueOf(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_ERROR:
                    value = String.valueOf(cell.getErrorCellValue());
                    break;
                case Cell.CELL_TYPE_BLANK:
                    value = "";
                    break;
                default:
                    value = "";
                    break;
            }
        }
        return value;
    }

    private static Workbook workbook(InputStream stream, String fileExt) throws IOException {
        Workbook wb = null;
        if (fileExt.equals("xls")) {
            wb = new HSSFWorkbook(stream);
        } else if (fileExt.equals("xlsx")) {
            wb = new XSSFWorkbook(stream);
        }
        return wb;
    }

    /**
     * 读取excel表数据
     * @param sheet
     * @param sheetIndex
     * @param startRow
     * @param endRow
     * @param startCol
     * @param endCol
     * @return
     * @throws IOException
     */
    private static Map<Integer, List<Object>> readExcelSheetData(Sheet sheet, int sheetIndex, int startRow, int endRow, int startCol, int endCol) throws IOException {
        Map<Integer, List<Object>> datas = new HashMap<>();
        if (sheet != null) {
            for (int r = startRow; r <= endRow; r++) {
                Row row = sheet.getRow(r);
                List<Object> cellDats = new LinkedList<>();
                for (int cn = startCol; cn < endCol; cn++) {
                    Cell cell = row.getCell(cn, Row.CREATE_NULL_AS_BLANK);
                    cellDats.add(cellValue(cell));
                }
                datas.put(row.getRowNum(), cellDats);
            }
        }
        return datas;
    }
}
