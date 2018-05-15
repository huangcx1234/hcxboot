package com.jiurong.hcxboot.base;

import java.util.List;

public interface BaseMapper<T> {
    int save(T t);
    int deleteById(Integer id);
    void updateByIdSelective(T t);
    void updateById(T t);
    T selectById(Integer id);
    List<T> selectAll(T t);
}
