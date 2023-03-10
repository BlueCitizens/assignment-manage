package com.bluec.assignment.po;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * @Author: BlueCitizens
 * @Date: 2020/2/2 23:33
 */
public class Role implements GrantedAuthority {

    private int id;
    private String name;
    private List<Permission> permissions;

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }

}
