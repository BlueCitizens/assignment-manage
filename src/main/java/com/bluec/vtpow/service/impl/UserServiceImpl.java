package com.bluec.vtpow.service.impl;

import com.bluec.vtpow.mapper.UserMapper;
import com.bluec.vtpow.mapper.WorkMapper;
import com.bluec.vtpow.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: BlueCitizens
 * @Date: 2020/1/13 0:42
 */
@Service
public class UserServiceImpl implements com.bluec.vtpow.service.UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    WorkMapper workMapper;

    @Override
    public boolean saveAllUser(List<User> users){
        userMapper.addBatchUser(users);
        return true;
    }

    @Override
    public User findByName(String name,String password){
        return userMapper.findByName(name,password);
    }

    public List<User> getAllUser(){
        return userMapper.findAll();
    }
}
