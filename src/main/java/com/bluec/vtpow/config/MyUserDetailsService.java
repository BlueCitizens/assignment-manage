package com.bluec.vtpow.config;

import com.bluec.vtpow.po.Role;
import com.bluec.vtpow.po.User;
import com.bluec.vtpow.po.UserInfo;
import com.bluec.vtpow.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: BlueCitizens
 * @Date: 2020/2/2 23:41
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
@Autowired
    LoginServiceImpl loginService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //这里可以可以通过username（登录时输入的用户名）然后到数据库中找到对应的用户信息，并构建成我们自己的UserInfo来返回。
        if(!username.equals("admin")){
            System.out.println("not admin");
            User user = loginService.getpwdbyname(username);
            if(user != null){
                UserInfo userInfo = new UserInfo();
                userInfo.setUsername(user.getUsername());
                userInfo.setId(user.getStu_id());
                if(user.getPassword() == null){
                    userInfo.setPassword("");
                }else{
                    userInfo.setPassword(user.getPassword());
                }
                Role role = new Role(1L,"USER");
                List<Role> list = new ArrayList();
                list.add(role);
                userInfo.setRoles(list);
                return userInfo;
            }
        }else

        //这里可以通过数据库来查找到实际的用户信息，这里我们先模拟下,后续我们用数据库来实现
        {
            //假设返回的用户信息如下;
            UserInfo userInfo=new UserInfo();
            userInfo.setUsername("admin");
            userInfo.setPassword("123456");
            Role role = new Role(1L,"ADMIN");
            List<Role> list = new ArrayList();
            list.add(role);
            userInfo.setRoles(list);
            return userInfo;
        }
        return null;
    }
}

