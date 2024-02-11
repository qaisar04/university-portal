package kz.baltabayev.gradingservice.service;

import kz.baltabayev.gradingservice.model.entity.Grade;

import java.util.List;

public interface GradeService {
    Grade save(Grade grade);

    List<Grade> getAll();

    Grade getById(Long id);

    List<Grade> getByStudentId(Long studentId);

    void deleteById(Long id);

    void deleteByStudentId(Long studentId);

    Grade update(Grade grade);

    Double getAverageScoreByStudentId(Long studentId);

    List<Grade> getByStudentIdAndSubjectId(Long studentId, Long subjectId);
}
