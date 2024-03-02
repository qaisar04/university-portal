package kz.baltabayev.studentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "storage-service", path = "/api/v1/image")
public interface StorageServiceClient {

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<String> uploadImage(
            @RequestParam("source") String source,
            @RequestParam("target") Long target,
            @RequestPart MultipartFile file
    );

    @GetMapping("/{fileName}")
    ResponseEntity<byte[]> downloadImage(
            @PathVariable String fileName
    );
}
