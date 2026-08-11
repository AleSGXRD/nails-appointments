package com.xrd.nails_appointment.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
public class ImageStorageService {
    private final Path uploadPath = Paths.get("uploads/appointments");

    public ImageStorageService() throws IOException{
        Files.createDirectories(uploadPath);
    }

    public String save(MultipartFile file) throws IOException {
        String extension = getExtension(file.getOriginalFilename());

        String fileName =
                UUID.randomUUID() + extension;

        Path destination =
                uploadPath.resolve(fileName);

        Files.copy(
                file.getInputStream(),
                destination,
                StandardCopyOption.REPLACE_EXISTING
        );

        return "/uploads/appointments/" + fileName;
    }

    public boolean delete(String imageUrl){
        try {
            if (imageUrl == null || imageUrl.isBlank()) {
                System.out.println("image url not valid: " + imageUrl);
                return false;
            }

            String fileName = Paths.get(imageUrl).getFileName().toString();

            Path file = uploadPath.resolve(fileName);
            Files.deleteIfExists(file);

            return true;
        }
        catch (IOException ioEx){
            System.out.println("image url not found in path: " + imageUrl);
            return false;
        }
    }

    private String getExtension(String filename) {

        if (filename == null || !filename.contains(".")) {
            return "";
        }

        return filename.substring(
                filename.lastIndexOf(".")
        );
    }
}
