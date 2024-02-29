package kz.baltabayev.storageservice.controller;

import kz.baltabayev.storageservice.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/image")
public class StorageController {

    private final StorageService storageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(
            @RequestParam("source") String source,
            @RequestParam("target") Long target,
            @RequestPart("file") MultipartFile file
    ) {
        String fileName = storageService.uploadFile(source, target, file);
        return ResponseEntity.ok(fileName);
    }

    @GetMapping("/{bucketName}/{fileName}")
    public ResponseEntity<?> download(
            @PathVariable String bucketName,
            @PathVariable String fileName
    ) {
        var content = storageService.downloadFile(bucketName, fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(new ByteArrayResource(content));
    }

    @DeleteMapping("/{bucketName}/{fileName}")
    public ResponseEntity<?> delete(
            @PathVariable String bucketName,
            @PathVariable String fileName
    ) {
        storageService.deleteFile(bucketName, fileName);
        return ResponseEntity.ok("%s removed successfully".formatted(fileName));
    }
}
