package com.springapp.mvc.user.model;

import org.springframework.security.core.GrantedAuthority;

public final class Role implements GrantedAuthority {
    public static final Role ROLE_USER = new Role(0, "ROLE_USER");
    public static final Role ROLE_ADMIN = new Role(1, "ROLE_ADMIN");

    private int id;
    private String roleName;

    private Role(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }

    public int getId() {
        return id;
    }

    public static Role getRoleById(int id) {
        return id == 0? ROLE_USER: id == 1? ROLE_ADMIN: null;
    }
}
