package kz.baltabayev.studentservice.client;

import kz.baltabayev.studentservice.model.payload.FileUploadResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "storage-service", path = "/api/v1/storage")
public interface StorageServiceClient {

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<FileUploadResponse[]> upload(
            @RequestParam("source") String source,
            @RequestParam("target") Long target,
            @RequestPart("file") MultipartFile file
    );

    @DeleteMapping("/{source}/{fileName}")
    ResponseEntity<?> delete(
            @PathVariable String source,
            @PathVariable String fileName
    );

    @GetMapping("/info")
    ResponseEntity<String[]> info(
            @RequestParam String url
    );
}
