package kz.baltabayev.studentservice.client;

import kz.baltabayev.studentservice.model.dto.GradeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;


@FeignClient(name = "grading-service", path = "/api/v1/grade")
public interface GradingServiceClient {

    @GetMapping("/student/{studentId}")
    ResponseEntity<Map<Long, List<GradeResponse>>> getByStudentId(@PathVariable Long studentId);

    @DeleteMapping("/student/{studentId}")
    ResponseEntity<String> deleteByStudentId(@PathVariable Long studentId);

    @GetMapping("/average/{studentId}")
    ResponseEntity<Double> getAverageScore(@PathVariable Long studentId);
}
