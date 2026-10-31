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

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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

    public void delete(String pictureUrl) {
        // Only the file name is kept: the URL prefix may differ from the current publicUrl
        Path file = uploadDir.resolve(pictureUrl.substring(pictureUrl.lastIndexOf('/') + 1)).normalize();
        if (!file.getParent().equals(uploadDir)) {
            log.warn("Refusing to delete a file outside the upload directory: {}", pictureUrl);
            return;
        }
        try {
            Files.deleteIfExists(file);
        } catch (IOException e) {
            log.warn("Failed to delete image {}", file, e);
        }
    }

    private String extensionOf(MultipartFile image) {
        String contentType = image.getContentType();
        if (image.isEmpty() || contentType == null || !EXTENSIONS.containsKey(contentType)) {
            throw new InvalidImageException();
        }
        return EXTENSIONS.get(contentType);
    }
}
