package com.bluec.vtpow.service.impl;

import com.bluec.vtpow.mapper.PermissionMapper;
import com.bluec.vtpow.po.Permission;
import com.bluec.vtpow.po.Role;
import com.bluec.vtpow.po.UserInfo;
import com.bluec.vtpow.service.RBACService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: BlueCitizens
 * @Date: 2020/2/3 20:20
 */
@Component("rbacService")
public class RBACServiceImpl implements RBACService {
    @Autowired
    PermissionMapper permissionMapper;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    //对所有用户开放的url写死在了这里，但我觉得最好数据库新建一个通用role-user来解决，硬编码永远不是最优解
    //这里方便调试就先这样写了
    private final static Set<String> general_urls = new HashSet<String>() {
        {
            add("/index");
            add("/get_session");
            add("/bus/work_list");
            add("/sys/**");
            add("/get_own_work");
            add("/get_all_work");
        }
    };

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof UserInfo) { //首先判断先当前用户是否是我们UserDetails对象。
            UserInfo userInfo = (UserInfo) principal;
            List<Role> roleList = ((UserInfo) principal).getRoles();
            Set<String> urls = new HashSet<>(); // 数据库读取 //读取用户所拥有权限的所有URL
            for (Role role : roleList) {
                List<Permission> permissionList = permissionMapper.getPermsByRole(role.getId());
                for (Permission permission : permissionList) {
                    urls.add(permission.getUrl());
                }
            }
            //urls.add("/index");

            urls.addAll(general_urls);
            // 注意这里不能用equal来判断，因为有些URL是有参数的，所以要用AntPathMatcher来比较
            for (String url : urls) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }
}
