package kz.baltabayev.studentservice.controller;

import kz.baltabayev.studentservice.mapper.StudentMapper;
import kz.baltabayev.studentservice.model.dto.StudentRequest;
import kz.baltabayev.studentservice.model.entity.Student;
import kz.baltabayev.studentservice.model.service.impl.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentServiceImpl studentService;
    private final StudentMapper studentMapper;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody StudentRequest studentRequest) {
        return ResponseEntity.ok(studentService.save(studentMapper.toStudent(studentRequest)));
    }

    @PostMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestBody StudentRequest studentRequest) {
        return ResponseEntity.ok(studentService.update(studentMapper.toStudent(studentRequest)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.ok("Student with id: " + id + " was deleted");
    }
}
