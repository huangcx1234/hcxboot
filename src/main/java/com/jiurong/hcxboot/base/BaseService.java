package com.jiurong.hcxboot.base;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author hcx
 * @date 2018/5/16
 * explain:
 */
public interface BaseService<T> {
    /**
     * 保存一个实体
     *
     * @param t 实体
     * @return 保存的行数
     */
    int save(T t);

    /**
     * 删除一个实体
     *
     * @param id 实体id
     * @return 删除的行数
     */
    int deleteById(Integer id);

    /**
     * 删除一个实体
     *
     * @param t 实体
     * @return 删除的行数
     */
    int deleteBySelective(T t);

    /**
     * 更新一个实体
     *
     * @param t 实体
     * @return 更新的行数
     */
    int updateById(T t);

    /**
     * 更新一个实体可选字段
     *
     * @param t 实体
     * @return 更新的行数
     */
    int updateByIdSelective(T t);

    /**
     * 查询一个实体
     *
     * @param id id
     * @return 实体
     */
    T selectById(Integer id);

    /**
     * 查询实体列表
     *
     * @param params 参数Map
     * @return 实体列表
     */
    List<T> selectBySelective(Map params);

    /**
     * 查询数量
     *
     * @param params 参数Map
     * @return 数量
     */
    int count(Map params);

    /**
     * 查询分页数据
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @param params   参数
     * @return 分页数据
     */
    PageInfo<T> pageInfo(int pageNum, int pageSize, Map params);
}
