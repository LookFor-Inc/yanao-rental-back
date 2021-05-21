package com.lookfor.yanaorental.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

public interface ImageService {
    URL save(MultipartFile file);

    Resource load(String filename);
}
