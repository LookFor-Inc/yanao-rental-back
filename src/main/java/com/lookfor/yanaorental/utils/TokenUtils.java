package com.lookfor.yanaorental.utils;

import java.util.UUID;

public class TokenUtils {
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
