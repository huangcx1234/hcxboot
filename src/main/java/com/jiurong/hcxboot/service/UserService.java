package com.jiurong.hcxboot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiurong.hcxboot.config.ClientException;
import com.jiurong.hcxboot.mapper.UserMapper;
import com.jiurong.hcxboot.model.User;
import com.jiurong.hcxboot.request.user.SaveUser;
import com.jiurong.hcxboot.request.user.SelectUser;
import com.jiurong.hcxboot.request.user.UpdateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author soyeajr
 * @date 2019-5-23
 * @Description 用户
 */
@Service(value = "userService")
public class UserService {

    @Value("${page.defaultPageSize}")
    private Integer defaultPageSize;

    @Autowired
    private UserMapper userMapper;

    public User save(SaveUser saveUser) {
        User user = new User();
        user.setUsername(saveUser.getUsername());
        user.setPassword(saveUser.getPassword());
        user.setPhone(saveUser.getPhone());
        userMapper.save(user);
        return user;
    }

    public void delete(String id) {
        if (userMapper.deleteById(id) == 0) {
            throw new ClientException("不存在");
        }
    }

    public User update(UpdateUser updateUser) {
        User user = userMapper.selectById(updateUser.getId());
        if (user == null) {
            throw new ClientException("不存在");
        }
        user.setUsername(updateUser.getUsername());
        user.setPassword(updateUser.getPassword());
        user.setPhone(updateUser.getPhone());
        userMapper.updateById(user);
        return user;
    }

    public User get(String id) {
        return userMapper.selectById(id);
    }

    public PageInfo<User> page(SelectUser selectUser) {
        PageHelper.startPage(selectUser.getPageNum() == null ? 1 : selectUser.getPageNum(), selectUser.getPageSize() == null ? defaultPageSize : selectUser.getPageSize());
        Map<String, Object> params = new HashMap<>();
        params.put("id", selectUser.getId());
        params.put("username", selectUser.getUsername());
        params.put("password", selectUser.getPassword());
        params.put("phone", selectUser.getPhone());
        List<User> list = userMapper.selectBySelective(params);
        return new PageInfo<>(list);
    }
}