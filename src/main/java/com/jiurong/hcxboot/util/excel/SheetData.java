package com.jiurong.hcxboot.util.excel;

import lombok.Data;

import java.util.List;

/**
 * @author soyeajr
 * @date 2019-2-26
 * @Description ExcelSheet实体类
 */
@Data
public class SheetData {
    private String name;
    private List<String> titles;
    private List<List<String>> rows;
}