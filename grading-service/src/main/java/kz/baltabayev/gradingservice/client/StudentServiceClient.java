package kz.baltabayev.gradingservice.client;

import kz.baltabayev.gradingservice.model.entity.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "student-service", path = "/students")
public interface StudentServiceClient {

    @GetMapping
    ResponseEntity<List<Student>> getAllStudents();
}
