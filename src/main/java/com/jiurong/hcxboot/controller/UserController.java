package com.jiurong.hcxboot.controller;

import com.jiurong.hcxboot.model.User;
import com.jiurong.hcxboot.service.user.UserService;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/16.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/save")
    public int addUser(User user) {
        return userService.save(user);
    }

    @ResponseBody
    @GetMapping("/deleteById")
    public int deleteById(User user) {
        return userService.deleteById(user.getId());
    }

    @ResponseBody
    @GetMapping("/deleteBySelective")
    public int deleteBySelective(User user) {
        return userService.deleteBySelective(user);
    }

    @ResponseBody
    @GetMapping("/updateById")
    public int updateById(User user) {
        return userService.updateById(user);
    }

    @ResponseBody
    @GetMapping("/updateByIdSelective")
    public int updateByIdSelective(User user) {
        return userService.updateByIdSelective(user);
    }

    @ResponseBody
    @GetMapping("/selectById")
    public Object selectById(User user) {
        return userService.selectById(user.getId());
    }

    @ResponseBody
    @GetMapping("/selectBySelective")
    public Object selectBySelective(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", user.getId());
        params.put("userName", user.getUserName());
        params.put("password", user.getPassword());
        params.put("phone", user.getPhone());
        return userService.selectBySelective(params);
    }

    @ResponseBody
    @GetMapping("/count")
    public int count(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", user.getId());
        params.put("userName", user.getUserName());
        params.put("password", user.getPassword());
        params.put("phone", user.getPhone());
        return userService.count(params);
    }

    @ResponseBody
    @GetMapping("/selectPageInfo")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize, User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", user.getId());
        params.put("userName", user.getUserName());
        params.put("password", user.getPassword());
        params.put("phone", user.getPhone());
        return userService.selectPageInfo(pageNum, pageSize, params);
    }
}