package com.lookfor.yanaorental.utils;

import lombok.SneakyThrows;

import java.net.URL;

public class UrlUtils {
    public static String getLastPart(URL url) {
        return url != null ? getLastPart(url.getPath()) : null;
    }

    public static String getLastPart(String url) {
        String lastPart = null;

        if (url != null) {
            String[] spl = url.split("/");
            lastPart = spl[spl.length - 1];
        }

        return lastPart;
    }

    @SneakyThrows
    public static URL stringToUrl(String url) {
        return new URL(url);
    }
}
