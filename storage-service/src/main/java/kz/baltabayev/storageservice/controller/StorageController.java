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
public class StorageController {

    private final StorageService storageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(
            @RequestParam("source") String source,
            @RequestParam("target") Long target,
            @RequestPart("file") MultipartFile file
    ) {
        String fileName = storageService.uploadFile(source, target, file);

        return ResponseEntity.ok(fileName);
    }

    //todo: all endpoint
}
