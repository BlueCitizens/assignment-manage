package com.bluec.vtpow.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: BlueCitizens
 * @Date: 2020/1/31 16:03
 * @Note: 测试security配置
 */
@RestController
public class UserController {
    @RequestMapping("/whoim")
    public Object whoIm() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}







