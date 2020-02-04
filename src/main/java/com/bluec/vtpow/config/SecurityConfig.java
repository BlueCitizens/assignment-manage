package com.bluec.vtpow.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @Author: BlueCitizens
 * @Date: 2020/1/31 16:15
 */


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationProvider provider;
    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailHander;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO Auto-generated method stub

        auth
                .authenticationProvider(provider);
    }
    @Override
    public void configure(WebSecurity web) {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/assets/**","/static/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().loginPage("/login")
                .loginProcessingUrl("/form")
                //.defaultSuccessUrl("/whoim") //成功登陆后跳转页面
                //.failureUrl("/404")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailHander)
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().access("@rbacService.hasPermission(request,authentication)")
                /*.antMatchers("/bus/**", "/whoim").permitAll()
                .antMatchers("/conf", "/index", "/bus/**").hasRole("USER")
                .antMatchers("/sys/**").hasRole("ADMIN")
                .and().exceptionHandling().accessDeniedPage("/404")*/
                .and()
                .csrf().disable(); //关闭CSRF
    }

}
