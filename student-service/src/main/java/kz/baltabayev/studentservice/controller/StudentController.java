package kz.baltabayev.studentservice.controller;

import jakarta.validation.Valid;
import kz.baltabayev.studentservice.mapper.StudentMapper;
import kz.baltabayev.studentservice.model.dto.StudentRequest;
import kz.baltabayev.studentservice.model.dto.StudentResponse;
import kz.baltabayev.studentservice.model.entity.Student;
import kz.baltabayev.studentservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<StudentResponse> getInfoStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getInfo(id));
    }

    @PatchMapping("{id}/avatar")
    public ResponseEntity<String> uploadAvatar(
            @RequestParam("file") MultipartFile file,
            @PathVariable Long id) {
        studentService.uploadAvatar(id, file);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody @Valid StudentRequest studentRequest) {
        return ResponseEntity.ok(studentService.save(studentRequest));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody StudentRequest studentRequest, @PathVariable Long id) {
        return ResponseEntity.ok(studentService.update(studentRequest, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.ok("Student with id: " + id + " was deleted");
    }
}
