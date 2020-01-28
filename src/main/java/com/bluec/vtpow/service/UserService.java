package com.bluec.vtpow.service;

import com.bluec.vtpow.po.User;

import java.util.List;

/**
 * @Author: BlueCitizens
 * @Date: 2020/1/13 1:54
 */
public interface UserService {
    boolean saveAllUser(List<User> users);

    User findByName(String name, String password);
}
