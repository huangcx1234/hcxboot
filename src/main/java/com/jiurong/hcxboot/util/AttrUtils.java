package com.jiurong.hcxboot.util;

import java.text.DecimalFormat;

/**
 * @author hcx
 * @date 2019/5/17
 * explain:
 */
public class AttrUtils {
    /**
     * 数字类型属性解析
     *
     * @param value    协议值
     * @param accuracy 小数位数
     * @return 实际值
     */
    public static String intValueParse(String value, Integer accuracy) {
        if(EmptyUtils.isEmpty(value)){
            return "0";
        }
        if(EmptyUtils.isEmpty(accuracy)){
            accuracy = 0;
        }
        StringBuilder pattern = new StringBuilder("#0");
        if (accuracy > 0) {
            pattern.append(".");
        }
        for (int i = 0; i < accuracy; i++) {
            pattern.append("0");
        }
        DecimalFormat decimalFormat = new DecimalFormat(pattern.toString());
        return decimalFormat.format(Double.parseDouble(value) / Math.pow(10, accuracy));
    }

    /**
     * 数字类型属性格式化
     *
     * @param value    实际值
     * @param accuracy 小数位数
     * @return 协议值
     */
    public static String intValueFormat(String value, Integer accuracy) {
        if(EmptyUtils.isEmpty(value)){
            return "0";
        }
        if(EmptyUtils.isEmpty(accuracy)){
            accuracy = 0;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#0");
        return decimalFormat.format(Double.parseDouble(value) * Math.pow(10, accuracy));
    }

    public static void main(String... args) {
        String value = "-00598";
        Integer accuracy = 0;
        String parseValue = AttrUtils.intValueParse(value, accuracy);
        String formatValue = AttrUtils.intValueFormat(parseValue, accuracy);
        System.out.println(parseValue);
        System.out.println(formatValue);
    }
}
