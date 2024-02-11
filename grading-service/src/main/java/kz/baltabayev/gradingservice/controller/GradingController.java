package kz.baltabayev.gradingservice.controller;

import kz.baltabayev.gradingservice.mapper.GradeMapper;
import kz.baltabayev.gradingservice.model.dto.GradeDto;
import kz.baltabayev.gradingservice.model.entity.Grade;
import kz.baltabayev.gradingservice.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/grade")
@RequiredArgsConstructor
public class GradingController {

    private final GradeService gradeService;
    private final GradeMapper gradeMapper;

    @PostMapping("/add")
    public ResponseEntity<GradeDto> addGrade(@RequestBody GradeDto gradeDto) {
        gradeService.save(gradeMapper.toEntity(gradeDto));
        return ResponseEntity.ok(gradeDto);
    }

    @GetMapping("/average/{studentId}")
    public ResponseEntity<Double> getAverageScore(@PathVariable Long studentId) {
        Double avgScore = gradeService.getAverageScoreByStudentId(studentId);
        return ResponseEntity.ok(avgScore);
    }









}
