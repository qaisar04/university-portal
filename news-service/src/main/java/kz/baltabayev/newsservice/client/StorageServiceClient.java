package kz.baltabayev.newsservice.client;

import kz.baltabayev.newsservice.model.payload.FileUploadResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "storage-service", path = "/api/v1/storage")
public interface StorageServiceClient {

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<FileUploadResponse[]> upload(
            @RequestParam("source") String source,
            @RequestParam("target") Long target,
            @RequestPart("file") List<MultipartFile> file
    );

    @DeleteMapping("/{source}/{fileName}")
    ResponseEntity<String> delete(
            @PathVariable String source,
            @PathVariable String fileName
    );
}
