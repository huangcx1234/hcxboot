package com.jiurong.hcxboot.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author hcx
 * @date 2018/5/16
 * explain:
 */
public class BaseServiceImpl<T> implements BaseService<T> {
    @Autowired
    private BaseMapper<T> baseMapper;

    @Override
    public int save(T t) {
        return baseMapper.save(t);
    }

    @Override
    public int deleteById(Integer id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public int deleteBySelective(T t) {
        return baseMapper.deleteBySelective(t);
    }

    @Override
    public int updateById(T t) {
        return baseMapper.updateById(t);
    }

    @Override
    public int updateByIdSelective(T t) {
        return baseMapper.updateByIdSelective(t);
    }

    @Override
    public T selectById(Integer id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<T> selectBySelective(Map params) {
        return baseMapper.selectBySelective(params);
    }

    @Override
    public int count(Map params) {
        return baseMapper.count(params);
    }

    @Override
    public PageInfo<T> pageInfo(int pageNum, int pageSize, Map params) {
        PageHelper.startPage(pageNum, pageSize);
        List<T> list = baseMapper.selectBySelective(params);
        return new PageInfo<>(list);
    }
}
