package com.lookfor.yanaorental.utils;

import org.springframework.util.SerializationUtils;

import javax.servlet.http.Cookie;
import java.util.Base64;

public class Base64Utils {
    public static byte[] decode(String source) {
        return Base64
                .getDecoder()
                .decode(source);
    }

    public static String serialize(Object object) {
        return Base64
                .getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(object));
    }

    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        return cls.cast(
                SerializationUtils
                        .deserialize(Base64.getUrlDecoder().decode(cookie.getValue()))
        );
    }
}
