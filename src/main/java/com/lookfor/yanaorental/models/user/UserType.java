package com.lookfor.yanaorental.models.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum UserType {
    RENTER,
    LANDLORD,
    ADMIN;

    public GrantedAuthority getGrantedAuthority() {
        return new SimpleGrantedAuthority("ROLE_" + name());
    }
}
