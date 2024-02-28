package kz.baltabayev.studentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "storage-service", path = "/api/v1/image")
public interface StorageServiceClient {

    @PostMapping
    ResponseEntity<String> uploadImage(@RequestParam("image")MultipartFile file);

    @GetMapping("/{fileName}")
    ResponseEntity<byte[]> downloadImage(@PathVariable String fileName);
}
