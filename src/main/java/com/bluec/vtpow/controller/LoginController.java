package com.bluec.vtpow.controller;

import com.bluec.vtpow.po.User;
import com.bluec.vtpow.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Author: BlueCitizens
 * @Date: 2020/1/16 22:02
 */

@Controller
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @RequestMapping(value = "/loginvalidate", method = RequestMethod.POST)
    public String loginvalidate(@RequestParam("username") String user_id, @RequestParam("password") String pwd, HttpSession httpSession) {
        System.out.println(user_id + pwd);
        System.out.println("name="+user_id);
        User user = loginService.getpwdbyname(user_id);
        if (user == null){
            System.out.println("用户不存在");
            return "404";
        }
        System.out.println(user.toString());
        String username = user.getUsername();
        String realpwd = user.getPassword();
        System.out.println(realpwd);
        if (!username.equals("") && realpwd == null && pwd.equals("pwd_undefined")) {
            httpSession.setAttribute("username", username);
            httpSession.setAttribute("id", user_id);
            httpSession.setAttribute("status", "no_pwd");
            return "index";
        } else if (!username.equals("") && pwd.equals(realpwd)) {
            httpSession.setAttribute("username", username);
            httpSession.setAttribute("status", "yes_pwd");
            return "index";
        } else
            return "404";
    }
}
