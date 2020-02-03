package com.bluec.vtpow.po;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Author: BlueCitizens
 * @Date: 2020/2/2 23:33
 */
public class Role implements GrantedAuthority {

    private Long id;
    private String name;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
