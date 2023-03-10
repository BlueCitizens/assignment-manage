package com.bluec.assignment.config;

import com.bluec.assignment.po.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author: BlueCitizens
 * @Date: 2020/2/3 12:06
 */
@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        //什么都不做的话，那就直接调用父类的方法
        //super.onAuthenticationSuccess(request, response, authentication);

        //这里可以根据实际情况，来确定是跳转到页面或者json格式。
        //如果是返回json格式，那么我们这么写

        /*Map<String,String> map=new HashMap<>();
        map.put("code", "200");
        map.put("msg", "登录成功");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(map));*/
        UserInfo userDetails = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUsername());
        String username = userDetails.getUsername();
        String id = userDetails.getId();
        String pwd = userDetails.getPassword();
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("username", username);
        httpSession.setAttribute("id", id);
        httpSession.setAttribute("status", pwd);

        //如果是要跳转到某个页面的，比如我们的那个whoim的则
        new DefaultRedirectStrategy().sendRedirect(request, response, "/index");

    }
}