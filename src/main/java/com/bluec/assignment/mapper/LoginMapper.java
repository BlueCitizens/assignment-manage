package com.bluec.assignment.mapper;

import com.bluec.assignment.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
	User getpwdbyname(String stu_id);
}
