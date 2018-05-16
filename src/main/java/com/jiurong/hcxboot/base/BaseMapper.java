package com.jiurong.hcxboot.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BaseMapper<T> {
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
    int deleteById(@Param("id") Integer id);

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
    T selectById(@Param("id") Integer id);

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
}
