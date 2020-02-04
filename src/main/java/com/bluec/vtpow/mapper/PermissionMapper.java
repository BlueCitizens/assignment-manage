package com.bluec.vtpow.mapper;

import com.bluec.vtpow.po.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: BlueCitizens
 * @Date: 2020/2/4 10:10
 */
@Mapper
public interface PermissionMapper {
    List<Permission> getPermsByRole(int role_id);
}
