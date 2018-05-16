package com.jiurong.hcxboot.service.impl;

import com.jiurong.hcxboot.base.BaseServiceImpl;
import com.jiurong.hcxboot.model.User;
import com.jiurong.hcxboot.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author hcx
 * @date 2018-5-16
 * @Description
 */
@Service(value = "userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
}