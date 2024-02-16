package kz.baltabayev.gradingservice.controller;

import kz.baltabayev.gradingservice.mapper.GradeMapper;
import kz.baltabayev.gradingservice.model.dto.GradeDto;
import kz.baltabayev.gradingservice.model.dto.GradeResponse;
import kz.baltabayev.gradingservice.model.entity.Grade;
import kz.baltabayev.gradingservice.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller for the Grade entity.
 * This controller provides endpoints for performing CRUD operations on grades.
 */
@Controller
@RestController
@RequestMapping("/api/v1/grade")
@RequiredArgsConstructor
public class GradingController {

    private final GradeService gradeService;
    private final GradeMapper gradeMapper;

    /**
     * Controller for the Grade entity.
     * This controller provides endpoints for performing CRUD operations on grades.
     */
    @PostMapping("/add")
    public ResponseEntity<GradeDto> addGrade(@RequestBody GradeDto request) {
        GradeDto saved = gradeService.save(gradeMapper.toEntity(request));
        return ResponseEntity.ok(saved);
    }

    /**
     * Retrieves the average score for a specific student.
     *
     * @param studentId the ID of the student
     * @return the average score
     */
    @GetMapping("/average/{studentId}")
    public ResponseEntity<Double> getAverageScore(@PathVariable Long studentId) {
        Double avgScore = gradeService.getAverageScoreByStudentId(studentId);
        return ResponseEntity.ok(avgScore);
    }

    /**
     * Retrieves all grades for a specific student, grouped by subject.
     *
     * @param studentId the ID of the student
     * @return a map where the key is the subject ID and the value is a list of grades for that subject
     */
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Map<Long, List<GradeResponse>>> getByStudentId(@PathVariable Long studentId) {
        Map<Long, List<GradeResponse>> gradingList = gradeService.getByStudentId(studentId);
        return ResponseEntity.ok(gradingList);
    }

    /**
     * Updates a grade.
     *
     * @param request the grade to update
     * @return the updated grade
     */
    @PutMapping("/update")
    public ResponseEntity<?> updateGrade(@RequestBody GradeDto request) {
        Grade updatedDrade = gradeService.update(gradeMapper.toEntity(request));
        return ResponseEntity.ok(updatedDrade);
    }

    /**
     * Deletes a grade by its ID.
     *
     * @param id the ID of the grade
     * @return a message indicating that the grade was successfully deleted
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGrade(@PathVariable Long id) {
        gradeService.deleteById(id);
        return ResponseEntity.ok("Grade with id %s was successfully deleted".formatted(id));
    }

    /**
     * Deletes all grades for a specific student.
     *
     * @param studentId the ID of the student
     * @return a message indicating that the grades were successfully deleted
     */
    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<String> deleteGradeByStudentId(@PathVariable Long studentId) {
        gradeService.deleteByStudentId(studentId);
        return ResponseEntity.ok("Grade with studentId %s was successfully deleted".formatted(studentId));
    }
}
