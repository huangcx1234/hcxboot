package com.jiurong.hcxboot.controller;

import com.jiurong.hcxboot.model.User;
import com.jiurong.hcxboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hcx
 * @date 2018-5-16
 * @Description
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/save")
    public Object save(User user) {
        int result = userService.save(user);
        if (result > 0) {
            logger.info("成功");
            return "成功";
        } else {
            logger.info("失败");
            return "失败";
        }
    }

    @ResponseBody
    @PostMapping("/deleteById")
    public Object deleteById(User user) {
        int result = userService.deleteById(user.getId());
        if (result > 0) {
            logger.info("成功");
            return "成功";
        } else {
            logger.info("失败");
            return "失败";
        }
    }

    @ResponseBody
    @PostMapping("/deleteBySelective")
    public Object deleteBySelective(User user) {
        int result = userService.deleteBySelective(user);
        if (result > 0) {
            logger.info("成功");
            return "成功";
        } else {
            logger.info("失败");
            return "失败";
        }
    }

    @ResponseBody
    @PostMapping("/updateById")
    public Object updateById(User user) {
        int result = userService.updateById(user);
        if (result > 0) {
            logger.info("成功");
            return "成功";
        } else {
            logger.info("失败");
            return "失败";
        }
    }

    @ResponseBody
    @PostMapping("/updateByIdSelective")
    public Object updateByIdSelective(User user) {
        int result = userService.updateByIdSelective(user);
        if (result > 0) {
            logger.info("成功");
            return "成功";
        } else {
            logger.info("失败");
            return "失败";
        }
    }

    @ResponseBody
    @PostMapping("/selectById")
    public Object selectById(User user) {
        return userService.selectById(user.getId());
    }

    @ResponseBody
    @PostMapping("/selectBySelective")
    public Object selectBySelective(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", user.getId());
        params.put("userName", user.getUserName());
        params.put("password", user.getPassword());
        params.put("phone", user.getPhone());
        return userService.selectBySelective(params);
    }

    @ResponseBody
    @PostMapping("/count")
    public int count(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", user.getId());
        params.put("userName", user.getUserName());
        params.put("password", user.getPassword());
        params.put("phone", user.getPhone());
        return userService.count(params);
    }

    @ResponseBody
    @PostMapping("/pageInfo")
    public Object pageInfo(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
            User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", user.getId());
        params.put("userName", user.getUserName());
        params.put("password", user.getPassword());
        params.put("phone", user.getPhone());
        return userService.pageInfo(pageNum, pageSize, params);
    }
}