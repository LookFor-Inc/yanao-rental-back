package com.lookfor.yanaorental.services.impl;

import com.lookfor.yanaorental.config.AppProperties;
import com.lookfor.yanaorental.exceptions.rest.BadRequestException;
import com.lookfor.yanaorental.exceptions.rest.NotFoundException;
import com.lookfor.yanaorental.services.ImageService;
import com.lookfor.yanaorental.utils.FilesUtils;
import com.lookfor.yanaorental.utils.TokenUtils;
import com.lookfor.yanaorental.utils.UrlUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocalImageService implements ImageService {
    private final Path root = Paths.get("src/main/resources/static/images/uploads");
    private final AppProperties appProperties;

    @Override
    public URL save(MultipartFile file) {
        String hash = TokenUtils.generateUUID();
        String fileFormat = FilesUtils.getExtensionByString(file.getOriginalFilename())
                .orElseThrow(() -> new BadRequestException("Invalid file"));
        String newFileName = String.format("%s.%s", hash, fileFormat);

        try {
            Files.copy(file.getInputStream(), this.root.resolve(newFileName));
            file.getContentType();
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }

        return UrlUtils.stringToUrl(
                String.format("%s/images/uploads/%s", appProperties.getHost(), newFileName)
        );
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new NotFoundException(String.format("File %s not found", filename));
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
