package com.bluec.vtpow.mapper;

import com.bluec.vtpow.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
	User getpwdbyname(String stu_id);
}
