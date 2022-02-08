package com.nazarov.blog.security;

public enum UserPermission {
    POST_WRITE("post write"),
    POST_DELETE("post delete"),
    POST_READ("post read");


    private final String permission;


    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
