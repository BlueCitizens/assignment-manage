package com.bluec.assignment.service.impl;

import com.bluec.assignment.mapper.UserMapper;
import com.bluec.assignment.mapper.WorkMapper;
import com.bluec.assignment.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: BlueCitizens
 * @Date: 2020/1/13 0:42
 */
@Service
public class UserServiceImpl implements com.bluec.assignment.service.UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    WorkMapper workMapper;

    @Override
    public boolean saveAllUser(List<User> users) {
        userMapper.addBatchUser(users);
        return true;
    }

    @Override
    public User findByName(String name, String password) {
        return userMapper.findByName(name, password);
    }

    public List<User> getAllUser() {
        return userMapper.findAll();
    }

    public int changePassword(String uid, String new_pwd) {
        return userMapper.confPassword(uid, new_pwd);
    }
}
