package com.bluec.assignment.service.impl;


import com.bluec.assignment.mapper.LoginMapper;
import com.bluec.assignment.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED,isolation= Isolation.DEFAULT,timeout=5)
@Service
public class LoginServiceImpl{
	@Autowired
	LoginMapper loginmapper;
	public User getpwdbyname(String stu_id) {
		User s=loginmapper.getpwdbyname(stu_id);
		if(s!=null)
		return s;
		else
		return null;
	}

}
