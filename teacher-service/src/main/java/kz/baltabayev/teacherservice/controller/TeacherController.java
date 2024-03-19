package kz.baltabayev.teacherservice.controller;

import kz.baltabayev.teacherservice.model.dto.TeacherRequest;
import kz.baltabayev.teacherservice.model.entity.Teacher;
import kz.baltabayev.teacherservice.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<Teacher>> getAll() {
        return ResponseEntity.ok(teacherService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> get(@PathVariable String id) {
        return ResponseEntity.ok(teacherService.get(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Teacher> createTeacher(@RequestBody TeacherRequest teacherRequest) {
        return ResponseEntity.ok(teacherService.save(teacherRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Teacher> updateTeacher(@RequestBody TeacherRequest teacherRequest, @PathVariable String id) {
        return ResponseEntity.ok(teacherService.update(teacherRequest, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable String id) {
        teacherService.delete(id);
        return ResponseEntity.ok("Teacher with id: " + id + " was deleted");
    }
}
