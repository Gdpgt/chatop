package com.chatop.backend.service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chatop.backend.domain.exception.InvalidImageException;

@Service
public class ImageStorageService {

    // Extension derived from the content type, never from the client file name
    private static final Map<String, String> EXTENSIONS = Map.of(
            "image/jpeg", ".jpg",
            "image/png", ".png",
            "image/gif", ".gif",
            "image/webp", ".webp");

    private final Path uploadDir;
    private final String publicUrl;

    public ImageStorageService(@Value("${storage.upload-dir}") String uploadDir,
                               @Value("${storage.public-url}") String publicUrl) throws IOException {
        this.uploadDir = Files.createDirectories(Path.of(uploadDir).toAbsolutePath().normalize());
        this.publicUrl = publicUrl;
    }

    public String store(MultipartFile image) {
        String fileName = UUID.randomUUID() + extensionOf(image);
        try {
            image.transferTo(uploadDir.resolve(fileName));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to store image " + fileName, e);
        }
        return publicUrl + "/" + fileName;
    }

    private String extensionOf(MultipartFile image) {
        String contentType = image.getContentType();
        if (image.isEmpty() || contentType == null || !EXTENSIONS.containsKey(contentType)) {
            throw new InvalidImageException();
        }
        return EXTENSIONS.get(contentType);
    }
}
