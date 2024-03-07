package kz.baltabayev.studentservice.client;

import kz.baltabayev.studentservice.model.payload.Department;
import kz.baltabayev.studentservice.model.payload.Faculty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "faculty-service", path = "api/v1")
public interface FacultyServiceClient {

    @GetMapping("/faculty/{id}")
    Faculty getFaculty(@PathVariable("id") Long id);

    @GetMapping("/department/{id}")
    Department getDepartment(@PathVariable("id") Long id);

    @GetMapping("/department/{facultyId}/{departmentId}")
    Boolean isExist(@PathVariable Long facultyId, @PathVariable Long departmentId);
}