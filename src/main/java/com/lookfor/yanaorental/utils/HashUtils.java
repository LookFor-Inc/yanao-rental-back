package com.lookfor.yanaorental.utils;

import lombok.SneakyThrows;

import java.security.MessageDigest;

public class HashUtils {
    @SneakyThrows
    public static String md5(String source) {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(source.getBytes());

        byte[] digest = messageDigest.digest();
        StringBuilder sb = new StringBuilder();

        for (byte b : digest) {
            String hex = Integer.toHexString(b & 0xff);

            if (hex.length() == 1) {
                sb.append('0');
            }

            sb.append(hex);
        }

        return sb.toString();
    }
}
