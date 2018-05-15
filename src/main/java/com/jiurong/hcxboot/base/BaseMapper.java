package com.jiurong.hcxboot.base;

import java.util.List;
import java.util.Map;

public interface BaseMapper<T> {
    int save(T t);

    int deleteById(Integer id);

    int deleteBySelective(T t);

    int updateById(T t);

    int updateByIdSelective(T t);

    T selectById(Integer id);

    List<T> selectBySelective(Map params);

    int count(Map params);
}
