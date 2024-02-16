package kz.baltabayev.gradingservice.service;

import kz.baltabayev.gradingservice.model.dto.GradeDto;
import kz.baltabayev.gradingservice.model.entity.Grade;

import java.util.List;
import java.util.Map;

public interface GradeService {
    Grade save(Grade grade);

    Grade getById(Long id);

    Map<Long, List<GradeDto>> getByStudentId(Long studentId);

    void deleteById(Long id);

    void deleteByStudentId(Long studentId);

    Grade update(Grade grade);

    Double getAverageScoreByStudentId(Long studentId);

    List<Grade> getByStudentIdAndSubjectId(Long studentId, Long subjectId);
}
