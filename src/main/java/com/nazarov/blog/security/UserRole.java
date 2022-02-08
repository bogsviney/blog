package com.nazarov.blog.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.nazarov.blog.security.UserPermission.*;

public enum UserRole {

    USER_USER(Sets.newHashSet()),
    USER_MODERATOR(Sets.newHashSet(POST_WRITE, POST_READ)),
    USER_ADMIN(Sets.newHashSet(POST_WRITE, POST_READ, POST_DELETE));


    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("USER" + this.name()));
        return permissions;
    }
}
