package kz.baltabayev.authservice.client;

import kz.baltabayev.authservice.model.dto.StudentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "student-service", path = "/api/v1/students")
public interface StudentServiceClient {

    @PostMapping("/create")
    ResponseEntity<?> create(StudentRequest studentRequest);
}