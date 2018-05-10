package com.jiurong.hcxboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping(value = "/world", method = RequestMethod.GET)
    @ResponseBody
    String world() {
        return "Hello World!";
    }
}
