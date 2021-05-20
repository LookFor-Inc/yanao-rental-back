package com.lookfor.yanaorental.utils;

import com.lookfor.yanaorental.config.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class CookieHelper {
    private final AppProperties appProperties;

    public void addCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds) {
        CookieUtils.addCookie(
                response,
                name,
                value,
                appProperties.getAuth().getCookie().isSecure(),
                maxAgeInSeconds
        );
    }

    public void deleteCookie(HttpServletResponse response, String name) {
        CookieUtils.deleteCookie(
                response,
                name,
                appProperties.getAuth().getCookie().isSecure()
        );
    }

    public void addAuthCookie(HttpServletResponse response, String token) {
        addCookie(
                response,
                appProperties.getAuth().getCookie().getName(),
                token,
                appProperties.getAuth().getJwtTokenExpiration() / 1000
        );
    }

    public void deleteAuthCookie(HttpServletResponse response) {
        deleteCookie(
                response,
                appProperties.getAuth().getCookie().getName()
        );
    }
}
