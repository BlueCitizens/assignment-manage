package com.bluec.assignment.service;

import com.bluec.assignment.po.User;

import java.util.List;

/**
 * @Author: BlueCitizens
 * @Date: 2020/1/13 1:54
 */
public interface UserService {
    boolean saveAllUser(List<User> users);

    User findByName(String name, String password);
}
