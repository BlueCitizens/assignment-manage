package com.bluec.vtpow.mapper;

import com.bluec.vtpow.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAll();
    int addBatchUser(List<User> users);
    User findByName(String name, String password);
}