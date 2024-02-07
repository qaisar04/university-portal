package kz.baltabayev.studentservice.controller;

import kz.baltabayev.studentservice.mapper.StudentMapper;
import kz.baltabayev.studentservice.model.dto.StudentRequest;
import kz.baltabayev.studentservice.model.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @RequestMapping("/status/check")
    public String status() {
        return "Working";
    }

    @PostMapping("/create")
    public ResponseEntity<?> createStudent(@RequestBody StudentRequest studentRequest) {
        return ResponseEntity.ok(studentService.save(studentMapper.toStudent(studentRequest)));
    }
}
