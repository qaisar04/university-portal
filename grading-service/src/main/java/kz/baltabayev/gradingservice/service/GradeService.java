package kz.baltabayev.gradingservice.service;

import kz.baltabayev.gradingservice.model.entity.Grade;

import java.util.List;
import java.util.Optional;

public interface GradeService {
    Grade save(Grade grade);

    List<Grade> getAll();

    Grade getById(Long id);

    List<Grade> getByStudentId(Long studentId);

    void deleteById(Long id);

    Grade update(Grade grade);

    Double getAverageScoreByStudentId(Long studentId);

    List<Grade> getByStudentIdAndSubjectId(Long studentId, Long subjectId);
}
