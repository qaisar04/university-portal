package kz.baltabayev.storageservice.controller;

import kz.baltabayev.storageservice.model.dto.FileUploadResponse;
import kz.baltabayev.storageservice.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/storage")
public class StorageController {

    private final StorageService storageService;

    // source - bucket | target - id | file
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileUploadResponse[]> upload(
            @RequestParam("source") String source,
            @RequestParam("target") String target,
            @RequestPart("file") List<MultipartFile> file
    ) {
        return ResponseEntity.ok(
                storageService.uploadFile(source, target, file)
        );
    }

    @GetMapping("/{source}/{fileName}")
    public ResponseEntity<?> download(
            @PathVariable String source,
            @PathVariable String fileName
    ) {
        var content = storageService.downloadFile(source, fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(new ByteArrayResource(content));
    }

    @DeleteMapping("/{source}/{fileName}")
    public ResponseEntity<String> delete(
            @PathVariable String source,
            @PathVariable String fileName
    ) {
        storageService.deleteFile(source, fileName);
        return ResponseEntity.ok("%s removed successfully".formatted(fileName));
    }

    @GetMapping("/info")
    public ResponseEntity<String[]> info(@RequestParam String url) {
        return ResponseEntity.ok(storageService.extractSourceAndFileName(url));
    }
}
