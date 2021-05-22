package com.lookfor.yanaorental.controllers;

import com.lookfor.yanaorental.services.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;

@RestController
@RequestMapping("/images/uploads")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @SneakyThrows
    @GetMapping(value = "/{filename:.+}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] getFile(@PathVariable String filename) {
        Resource file = imageService.load(filename);
        return Files.readAllBytes(file.getFile().toPath());
    }
}
