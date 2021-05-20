package com.lookfor.yanaorental.models.auth;

public enum AuthProvider {
    GOOGLE,
    VK;

    public static AuthProvider get(String provider) {
        return valueOf(provider.toUpperCase());
    }
}
