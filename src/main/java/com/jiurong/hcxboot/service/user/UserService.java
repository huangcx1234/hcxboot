package com.jiurong.hcxboot.service.user;


import com.github.pagehelper.PageInfo;
import com.jiurong.hcxboot.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/19.
 */
public interface UserService {
    int save(User user);

    int deleteById(Integer id);

    int deleteBySelective(User user);

    int updateById(User user);

    int updateByIdSelective(User user);

    User selectById(Integer id);

    List<User> selectBySelective(Map params);

    int count(Map params);

    PageInfo<User> selectPageInfo(int pageNum, int pageSize, Map params);
}