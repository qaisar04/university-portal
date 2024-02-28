package kz.baltabayev.storageservice.controller;

import kz.baltabayev.storageservice.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/image")
public class StorageController {

    private final StorageService storageService;

    @PostMapping
    public ResponseEntity<String> uploadImage(
            @RequestParam("bucketName") String bucketName,
            @RequestParam("fileName") String fileName,
            @RequestParam("filePath") String filePath) {
        storageService.uploadFile(bucketName, fileName, filePath);
        return ResponseEntity.ok("SUCCESS");
    }

    //todo: add endpoint
}
