package com.jiurong.hcxboot.util;

import com.github.pagehelper.PageInfo;

/**
 * @author hcx
 * @date 2019/3/28
 * explain: 分析信息拷贝工具类
 */
public class PageInfoUtil {
    public static void copy(PageInfo source, PageInfo target) {
        target.setPageNum(source.getPageNum());
        target.setPageSize(source.getPageSize());
        target.setSize(source.getSize());
        target.setStartRow(source.getStartRow());
        target.setEndRow(source.getEndRow());
        target.setPages(source.getPages());
        target.setPrePage(source.getPrePage());
        target.setNextPage(source.getNextPage());
        target.setIsFirstPage(source.isIsFirstPage());
        target.setIsLastPage(source.isIsFirstPage());
        target.setHasPreviousPage(source.isHasPreviousPage());
        target.setHasNextPage(source.isHasNextPage());
        target.setNavigatePages(source.getNavigatePages());
        target.setNavigatepageNums(source.getNavigatepageNums());
        target.setNavigateFirstPage(source.getNavigateFirstPage());
        target.setNavigateLastPage(source.getNavigateLastPage());
        target.setTotal(source.getTotal());
    }

    /**
     * 获取页数
     *
     * @param pageSize 每页的数量
     * @param total    总记录数
     * @return
     */
    public static int getPages(int pageSize, int total) {
        if (pageSize > 0) {
            return (total / pageSize + (total % pageSize == 0 ? 0 : 1));
        } else {
            return 0;
        }
    }
}
