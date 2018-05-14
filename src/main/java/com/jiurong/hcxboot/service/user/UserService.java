package com.jiurong.hcxboot.service.user;


import com.jiurong.hcxboot.model.UserDomain;
import org.apache.catalina.User;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19.
 */
public interface UserService {

    int addUser(User user);

    List<UserDomain> findAllUser(int pageNum, int pageSize);
}