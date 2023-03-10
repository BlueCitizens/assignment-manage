package com.bluec.assignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.MultipartConfig;

@MultipartConfig
@Controller
public class PageController {


    @GetMapping("index")
    public String index() {
        return "index";
    }

    @GetMapping("/sys/stu_list")
    public String getStuList() {
        return "system/stulist";
    }

    @GetMapping("/sys/work_list")
    public String getWorkList() {
        return "system/worklist";
    }

    @GetMapping("/bus/work_list")
    public String getWork() {
        return "business/worklist";
    }

    @GetMapping({"login","/"})
    public String getLogin() {
        return "login";
    }

    @GetMapping("404")
    public String get404() {
        return "404";
    }

    @GetMapping("/bus/upload_history")
    public String getHistory() {
        return "business/history";
    }

    @GetMapping("profile")
    public String getProfile() {
        return "business/profile";
    }

    @GetMapping("conf")
    public String getConf() {
        return "business/conf";
    }

    @GetMapping("sys_conf")
    public String getSysConf() {
        return "system/sysconf";
    }


}
