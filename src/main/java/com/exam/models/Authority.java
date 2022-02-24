package com.exam.models;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {
    private String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    //this is used to return the authority like NORMAL and ADMIN
    //whenever spring security will ASK for authority this method will be called and we are returning authority here
    @Override
    public String getAuthority() {
        return this.authority;
    }
}
