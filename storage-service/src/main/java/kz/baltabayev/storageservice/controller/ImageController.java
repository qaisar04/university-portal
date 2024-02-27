package kz.baltabayev.storageservice.controller;

import kz.baltabayev.storageservice.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/image")
public class ImageController {

    private final StorageService storageService;

    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file) {
        String uploadedImage = storageService.uploadImage(file);
        return ResponseEntity.ok(uploadedImage);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) {
        byte[] downloadedImage = storageService.downloadImage(fileName);
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf("image/png"))
                .body(downloadedImage);
    }
}
