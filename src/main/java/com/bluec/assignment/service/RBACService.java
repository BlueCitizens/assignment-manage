package com.bluec.assignment.service;

/**
 * @Author: BlueCitizens
 * @Date: 2020/2/3 20:19
 */

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * 返回权限验证的接口
 */
public interface RBACService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
