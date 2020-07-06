package com.jiurong.hcxboot.util.excel;

import lombok.Data;

import java.util.List;

/**
 * @author soyeajr
 * @date 2019-2-26
 * @Description Excel实体类
 */
@Data
public class ExcelData {
    private String name;
    private List<SheetData> sheetDataList;
}