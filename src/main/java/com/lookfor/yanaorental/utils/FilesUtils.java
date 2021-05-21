package com.lookfor.yanaorental.utils;

import java.util.Optional;

public class FilesUtils {
    public static Optional<String> getExtensionByString(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }
}
