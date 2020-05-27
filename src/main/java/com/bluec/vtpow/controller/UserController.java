package com.bluec.vtpow.controller;

import com.alibaba.fastjson.JSONObject;
import com.bluec.vtpow.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Author: BlueCitizens
 * @Date: 2020/1/31 16:03
 * @Note: 测试security配置
 */
@RestController
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/whoim")
    public Object whoIm() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    @RequestMapping(value = "/bus/pwd_conf", method = RequestMethod.POST)
    public String pwdConf(@RequestBody JSONObject data, HttpSession httpSession) {
        String user_id = (String)httpSession.getAttribute("id");
        String status = (String)httpSession.getAttribute("status");
        JSONObject jsonObject = new JSONObject(data);
        if(status.equals("yes_pwd")){
            String old_pwd = (String)jsonObject.get("old_pwd");
            System.out.println(old_pwd);
        }
        String new_pwd = (String)jsonObject.get("new_pwd");
        userService.changePassword(user_id,new_pwd);
        return "ok";
    }
}







